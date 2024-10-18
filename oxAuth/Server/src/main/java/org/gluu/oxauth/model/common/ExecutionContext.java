package org.gluu.oxauth.model.common;

import org.gluu.model.custom.script.conf.CustomScriptConfiguration;
import org.gluu.oxauth.authorize.ws.rs.AuthzRequest;
import org.gluu.oxauth.model.authzdetails.AuthzDetail;
import org.gluu.oxauth.model.authzdetails.AuthzDetails;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.ldap.TokenLdap;
import org.gluu.oxauth.model.registration.Client;
import org.gluu.oxauth.model.session.SessionId;
import org.gluu.oxauth.service.AttributeService;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Yuriy Zabrovarnyy
 */
public class ExecutionContext {

    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;

    private Client client;
    private AuthorizationGrant grant;
    private User user;
    private CustomScriptConfiguration script;

    private TokenLdap idTokenEntity;
    private TokenLdap accessTokenEntity;
    private TokenLdap refreshTokenEntity;

    private AppConfiguration appConfiguration;
    private AttributeService attributeService;

    private int refreshTokenLifetimeFromScript;

    private AuthzRequest authzRequest;
    private AuthzDetails authzDetails;
    private AuthzDetail authzDetail;

    private SessionId sessionId;
    private List<SessionId> currentSessions;
    private SessionId authorizationChallengeSessionId;

    public ExecutionContext() {
    }

    public ExecutionContext(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public static ExecutionContext of(AuthzRequest authzRequest) {
        ExecutionContext executionContext = new ExecutionContext();
        if (authzRequest == null) {
            return executionContext;
        }

        executionContext.setHttpRequest(authzRequest.getHttpRequest());
        executionContext.setHttpResponse(authzRequest.getHttpResponse());
        executionContext.setClient(authzRequest.getClient());
        executionContext.setAuthzRequest(authzRequest);
        executionContext.setAuthzDetails(authzRequest.getAuthzDetails());
        return executionContext;
    }

    public static ExecutionContext of(ExternalContext externalContext) {
        ExecutionContext executionContext = new ExecutionContext();
        if (externalContext != null) {
            if (externalContext.getRequest() instanceof HttpServletRequest) {
                executionContext.setHttpRequest((HttpServletRequest) externalContext.getRequest());
            }
            if (externalContext.getResponse() instanceof HttpServletResponse) {
                executionContext.setHttpResponse((HttpServletResponse) externalContext.getResponse());
            }
        }
        return executionContext;
    }

    public List<SessionId> getCurrentSessions() {
        return currentSessions;
    }

    public ExecutionContext setCurrentSessions(List<SessionId> currentSessions) {
        this.currentSessions = currentSessions;
        return this;
    }

    public SessionId getAuthorizationChallengeSessionId() {
        return authorizationChallengeSessionId;
    }

    public ExecutionContext setAuthorizationChallengeSessionId(SessionId authorizationChallengeSessionId) {
        this.authorizationChallengeSessionId = authorizationChallengeSessionId;
        return this;
    }

    public CustomScriptConfiguration getScript() {
        return script;
    }

    public ExecutionContext setScript(CustomScriptConfiguration script) {
        this.script = script;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ExecutionContext setUser(User user) {
        this.user = user;
        return this;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public ExecutionContext setSessionId(SessionId sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public AuthzRequest getAuthzRequest() {
        return authzRequest;
    }

    public ExecutionContext setAuthzRequest(AuthzRequest authzRequest) {
        this.authzRequest = authzRequest;
        return this;
    }

    public AuthzDetails getAuthzDetails() {
        return authzDetails;
    }

    public ExecutionContext setAuthzDetails(AuthzDetails authzDetails) {
        this.authzDetails = authzDetails;
        return this;
    }

    public AuthzDetail getAuthzDetail() {
        return authzDetail;
    }

    public ExecutionContext setAuthzDetail(AuthzDetail authzDetail) {
        this.authzDetail = authzDetail;
        return this;
    }

    public ExecutionContext setHttpRequest(HttpServletRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }

    public ExecutionContext setHttpResponse(HttpServletResponse httpResponse) {
        this.httpResponse = httpResponse;
        return this;
    }

    public int getRefreshTokenLifetimeFromScript() {
        return refreshTokenLifetimeFromScript;
    }

    public void setRefreshTokenLifetimeFromScript(int refreshTokenLifetimeFromScript) {
        this.refreshTokenLifetimeFromScript = refreshTokenLifetimeFromScript;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }

    public HttpServletResponse getHttpResponse() {
        return httpResponse;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public AuthorizationGrant getGrant() {
        return grant;
    }

    public void setGrant(AuthorizationGrant grant) {
        this.grant = grant;
    }

    public TokenLdap getIdTokenEntity() {
        return idTokenEntity;
    }

    public void setIdTokenEntity(TokenLdap idTokenEntity) {
        this.idTokenEntity = idTokenEntity;
    }

    public TokenLdap getAccessTokenEntity() {
        return accessTokenEntity;
    }

    public void setAccessTokenEntity(TokenLdap accessTokenEntity) {
        this.accessTokenEntity = accessTokenEntity;
    }

    public TokenLdap getRefreshTokenEntity() {
        return refreshTokenEntity;
    }

    public void setRefreshTokenEntity(TokenLdap refreshTokenEntity) {
        this.refreshTokenEntity = refreshTokenEntity;
    }

    public AppConfiguration getAppConfiguration() {
        return appConfiguration;
    }

    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public AttributeService getAttributeService() {
        return attributeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
}
