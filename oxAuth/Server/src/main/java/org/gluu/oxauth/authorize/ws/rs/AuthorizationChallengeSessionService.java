package org.gluu.oxauth.authorize.ws.rs;

import org.apache.commons.lang.StringUtils;
import org.gluu.oxauth.model.config.StaticConfiguration;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.session.AuthorizationChallengeSession;
import org.gluu.persist.PersistenceEntryManager;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * @author Yuriy Z
 */
@Named
@ApplicationScoped
public class AuthorizationChallengeSessionService {

    @Inject
    private Logger log;

    @Inject
    private AppConfiguration appConfiguration;

    @Inject
    private PersistenceEntryManager persistenceEntryManager;

    @Inject
    private StaticConfiguration staticConfiguration;

    public AuthorizationChallengeSession newAuthorizationChallengeSession() {
        final String id = UUID.randomUUID().toString();
        return newAuthorizationChallengeSession(id);
    }

    public AuthorizationChallengeSession newAuthorizationChallengeSession(String id) {
        int lifetimeInSeconds = appConfiguration.getAuthorizationChallengeSessionLifetimeInSeconds();

        final Calendar calendar = new GregorianCalendar();
        final Date creationDate = calendar.getTime();
        calendar.add(Calendar.SECOND, lifetimeInSeconds);
        final Date expirationDate = calendar.getTime();

        AuthorizationChallengeSession AuthorizationChallengeSession = new AuthorizationChallengeSession();
        AuthorizationChallengeSession.setId(id);
        AuthorizationChallengeSession.setDn(buildDn(id));
        AuthorizationChallengeSession.setDeletable(true);
        AuthorizationChallengeSession.setTtl(lifetimeInSeconds);
        AuthorizationChallengeSession.setCreationDate(creationDate);
        AuthorizationChallengeSession.setExpirationDate(expirationDate);
        return AuthorizationChallengeSession;
    }

    public String buildDn(String id) {
        return String.format("oxId=%s,%s", id, staticConfiguration.getBaseDn().getSessions());
    }

    public AuthorizationChallengeSession getAuthorizationChallengeSessionByDn(String dn) {
        try {
            return persistenceEntryManager.find(AuthorizationChallengeSession.class, dn);
        } catch (Exception e) {
            log.trace(e.getMessage(), e);
            return null;
        }
    }

    public AuthorizationChallengeSession getAuthorizationChallengeSession(String id) {
        if (StringUtils.isNotBlank(id)) {
            AuthorizationChallengeSession result = getAuthorizationChallengeSessionByDn(buildDn(id));
            log.debug("Found {} entries for AuthorizationChallengeSession id = {}", result != null ? 1 : 0, id);

            return result;
        }
        return null;
    }

    public void persist(AuthorizationChallengeSession entity) {
        persistenceEntryManager.persist(entity);
    }

    public void merge(AuthorizationChallengeSession entity) {
        persistenceEntryManager.merge(entity);
    }
}
