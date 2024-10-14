/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.uma.service;

import org.gluu.oxauth.model.common.ExecutionContext;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.error.ErrorResponseFactory;
import org.gluu.oxauth.model.jwt.Jwt;
import org.gluu.oxauth.model.registration.Client;
import org.gluu.oxauth.model.uma.UmaErrorResponseType;
import org.gluu.oxauth.model.uma.UmaTokenResponse;
import org.gluu.oxauth.model.uma.persistence.UmaPermission;
import org.gluu.oxauth.security.Identity;
import org.gluu.oxauth.uma.authorization.*;
import org.gluu.oxauth.util.ServerUtil;
import org.oxauth.persistence.model.Scope;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * UMA Token Service
 */
@ApplicationScoped
public class UmaTokenService {

    @Inject
    private Logger log;
    @Inject
    private Identity identity;
    @Inject
    private ErrorResponseFactory errorResponseFactory;
    @Inject
    private UmaRptService rptService;
    @Inject
    private UmaPctService pctService;
    @Inject
    private UmaPermissionService permissionService;
    @Inject
    private UmaValidationService umaValidationService;
    @Inject
    private AppConfiguration appConfiguration;
    @Inject
    private UmaNeedsInfoService umaNeedsInfoService;
    @Inject
    private UmaExpressionService expressionService;

    public Response requestRpt(
            String grantType,
            String ticket,
            String claimToken,
            String claimTokenFormat,
            String pctCode,
            String rptCode,
            String scope,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse) {
        try {
            log.trace("requestRpt grant_type: {}, ticket: {}, claim_token: {}, claim_token_format: {}, pct: {}, rpt: {}, scope: {}"
                    , grantType, ticket, claimToken, claimTokenFormat, pctCode, rptCode, scope);

            umaValidationService.validateGrantType(grantType);
            List<UmaPermission> permissions = umaValidationService.validateTicket(ticket);
            Jwt idToken = umaValidationService.validateClaimToken(claimToken, claimTokenFormat);
            UmaPCT pct = umaValidationService.validatePct(pctCode);
            UmaRPT rpt = umaValidationService.validateRPT(rptCode);
            Client client = umaValidationService.validate(identity.getSessionClient().getClient());
            Map<Scope, Boolean> scopes = umaValidationService.validateScopes(scope, permissions, client);
            pct = pctService.updateClaims(pct, idToken, client.getClientId(), permissions); // creates new pct if pct is null in request
            Claims claims = new Claims(idToken, pct, claimToken);

            Map<UmaScriptByScope, UmaAuthorizationContext> scriptMap = umaNeedsInfoService.checkNeedsInfo(claims, scopes, permissions, pct, httpRequest, client);

            if (!scriptMap.isEmpty()) {
                expressionService.evaluate(scriptMap, permissions);
            } else {
                log.warn("There are no any policies that protects scopes. Scopes: " + UmaScopeService.asString(scopes.keySet()) + ". Configuration property umaGrantAccessIfNoPolicies: " + appConfiguration.getUmaGrantAccessIfNoPolicies());

                if (appConfiguration.getUmaGrantAccessIfNoPolicies() != null && appConfiguration.getUmaGrantAccessIfNoPolicies()) {
                    log.warn("Access granted because there are no any protection. Make sure it is intentional behavior.");
                } else {
                    log.warn("Access denied because there are no any protection. Make sure it is intentional behavior.");
                    throw errorResponseFactory.createWebApplicationException(Response.Status.FORBIDDEN, UmaErrorResponseType.FORBIDDEN_BY_POLICY, "Access denied because there are no any protection. Make sure it is intentional behavior.");
                }
            }

            log.trace("Access granted.");

            updatePermissionsWithClientRequestedScope(permissions, scopes);
            addPctToPermissions(permissions, pct);

            boolean upgraded = false;
            if (rpt == null) {
                ExecutionContext executionContext = new ExecutionContext(httpRequest, httpResponse);
                executionContext.setClient(client);
                rpt = rptService.createRPTAndPersist(executionContext, permissions);
                rptCode = rpt.getNotHashedCode();
            } else if (rptService.addPermissionToRPT(rpt, permissions)) {
                upgraded = true;
            }

            UmaTokenResponse response = new UmaTokenResponse();
            response.setAccessToken(rptCode);
            response.setUpgraded(upgraded);
            response.setTokenType("Bearer");
            response.setPct(pct.getCode());

            return Response.ok(ServerUtil.asJson(response)).build();
        } catch (Exception ex) {
            log.error("Exception happened", ex);
            if (ex instanceof WebApplicationException) {
                throw (WebApplicationException) ex;
            }
        }

        log.error("Failed to handle request to UMA Token Endpoint.");
        throw errorResponseFactory.createWebApplicationException(Response.Status.INTERNAL_SERVER_ERROR, UmaErrorResponseType.SERVER_ERROR, "Failed to handle request to UMA Token Endpoint.");
    }

    private void addPctToPermissions(List<UmaPermission> permissions, UmaPCT pct) {
        for (UmaPermission p : permissions) {
            p.getAttributes().put(UmaPermission.PCT, pct.getCode());
            permissionService.mergeSilently(p);
        }
    }

    private void updatePermissionsWithClientRequestedScope(List<UmaPermission> permissions, Map<Scope, Boolean> scopes) {
        log.trace("Updating permissions with requested scopes ...");
        for (UmaPermission permission : permissions) {
            Set<String> scopeDns = new HashSet<>(permission.getScopeDns());

            for (Map.Entry<Scope, Boolean> entry : scopes.entrySet()) {
                log.trace("Updating permissions with scope: " + entry.getKey().getId() + ", isRequestedScope: " + entry.getValue() + ", permisson: " + permission.getDn());
                scopeDns.add(entry.getKey().getDn());
            }

            permission.setScopeDns(new ArrayList<>(scopeDns));
        }
    }
}
