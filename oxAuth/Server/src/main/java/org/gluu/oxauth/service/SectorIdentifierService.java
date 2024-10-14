package org.gluu.oxauth.service;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.gluu.oxauth.model.common.*;
import org.gluu.oxauth.model.config.StaticConfiguration;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.registration.Client;
import org.gluu.persist.PersistenceEntryManager;
import org.gluu.util.StringHelper;
import org.oxauth.persistence.model.PairwiseIdentifier;
import org.oxauth.persistence.model.SectorIdentifier;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.net.URI;
import java.util.UUID;

/**
 * @author Javier Rojas Blum
 * @version April 10, 2020
 */
@ApplicationScoped
public class SectorIdentifierService {

    @Inject
    private Logger log;

    @Inject
    private PersistenceEntryManager ldapEntryManager;

    @Inject
    private StaticConfiguration staticConfiguration;

    @Inject
    private PairwiseIdentifierService pairwiseIdentifierService;

    @Inject
    protected AppConfiguration appConfiguration;

    /**
     * Get sector identifier by oxId
     *
     * @param oxId Sector identifier oxId
     * @return Sector identifier
     */
    public SectorIdentifier getSectorIdentifierById(String oxId) {
        SectorIdentifier result = null;
        try {
            result = ldapEntryManager.find(SectorIdentifier.class, getDnForSectorIdentifier(oxId));
        } catch (Exception e) {
            log.error("Failed to find sector identifier by oxId " + oxId, e);
        }
        return result;
    }

    /**
     * Build DN string for sector identifier
     *
     * @param oxId Sector Identifier oxId
     * @return DN string for specified sector identifier or DN for sector identifiers branch if oxId is null
     * @throws Exception
     */
    public String getDnForSectorIdentifier(String oxId) {
        String sectorIdentifierDn = staticConfiguration.getBaseDn().getSectorIdentifiers();
        if (StringHelper.isEmpty(oxId)) {
            return sectorIdentifierDn;
        }

        return String.format("oxId=%s,%s", oxId, sectorIdentifierDn);
    }

    public String getSub(IAuthorizationGrant grant) {
        Client client = grant.getClient();
        User user = grant.getUser();

        if (user == null) {
            log.trace("User is null, return blank sub");
            return "";
        }
        if (client == null) {
            log.trace("Client is null, return blank sub.");
            return "";
        }

        return getSub(client, user, grant instanceof CIBAGrant);
    }

    public String getSub(Client client, User user, boolean isCibaGrant) {
        if (user == null) {
            log.trace("User is null, return blank sub");
            return "";
        }
        if (client == null) {
            log.trace("Client is null, return blank sub.");
            return "";
        }

        final boolean isClientPairwise = SubjectType.PAIRWISE.equals(SubjectType.fromString(client.getSubjectType()));
        if (isClientPairwise) {
            final String sectorIdentifierUri;

            if (StringUtils.isNotBlank(client.getSectorIdentifierUri())) {
                sectorIdentifierUri = client.getSectorIdentifierUri();
            } else {
                if (!isCibaGrant) {
                    sectorIdentifierUri = !ArrayUtils.isEmpty(client.getRedirectUris()) ? client.getRedirectUris()[0] : null;
                } else {
                    if (client.getBackchannelTokenDeliveryMode() == BackchannelTokenDeliveryMode.PUSH) {
                        sectorIdentifierUri = client.getBackchannelClientNotificationEndpoint();
                    } else {
                        sectorIdentifierUri = client.getJwksUri();
                    }
                }
            }

            String userInum = user.getAttribute("inum");

            try {
                if (StringUtils.isNotBlank(sectorIdentifierUri)) {
                    String sectorIdentifier = URI.create(sectorIdentifierUri).getHost();
                    if (appConfiguration.getSubjectIdentifierBasedOnWholeUriBackwardCompatibility()) // todo remove in 5.0
                        sectorIdentifier = sectorIdentifierUri;

                    PairwiseIdentifier pairwiseIdentifier = pairwiseIdentifierService.findPairWiseIdentifier(userInum,
                            sectorIdentifier, client.getClientId());
                    if (pairwiseIdentifier == null) {
                        pairwiseIdentifier = new PairwiseIdentifier(sectorIdentifier, client.getClientId(), userInum);
                        pairwiseIdentifier.setId(UUID.randomUUID().toString());
                        pairwiseIdentifier.setDn(pairwiseIdentifierService.getDnForPairwiseIdentifier(pairwiseIdentifier.getId(), userInum));
                        pairwiseIdentifierService.addPairwiseIdentifier(userInum, pairwiseIdentifier);
                    }
                    return pairwiseIdentifier.getId();
                } else {
                    log.trace("Sector identifier uri is blank for client: " + client.getClientId());
                }
            } catch (Exception e) {
                log.error("Failed to get sub claim. PairwiseIdentifierService failed to find pair wise identifier.", e);
                return "";
            }
        }

        String openidSubAttribute = appConfiguration.getOpenidSubAttribute();
        if (StringHelper.equalsIgnoreCase(openidSubAttribute, "uid")) {
            return user.getUserId();
        }
        return user.getAttribute(openidSubAttribute);
    }
}
