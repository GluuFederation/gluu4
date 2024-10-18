package org.gluu.oxauth.authorize.ws.rs;

import org.gluu.oxauth.service.RequestParameterService;
import org.gluu.oxauth.util.QueryStringDecoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The authorization challenge endpoint is a new endpoint defined by "OAuth 2.0 for First-Party Native Applications"
 * specification which the native application uses to obtain an authorization code.
 * The endpoint accepts the authorization request parameters defined in [RFC6749] for the authorization endpoint
 * as well as all applicable extensions defined for the authorization endpoint. Some examples of such extensions
 * include Proof Key for Code Exchange (PKCE) [RFC7636], Resource Indicators [RFC8707], and OpenID Connect [OpenID].
 * It is important to note that some extension parameters have meaning in a web context but don't have meaning in
 * a native mechanism (e.g. response_mode=query).
 *
 * @author Yuriy Z
 */
@Path("/authorize-challenge")
public class AuthorizationChallengeEndpoint {

    @Inject
    private RequestParameterService requestParameterService;

    @Inject
    private AuthorizationChallengeService authorizationChallengeService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response requestAuthorizationPost(
            @FormParam("client_id") String clientId,
            @FormParam("scope") String scope,
            @FormParam("acr_values") String acrValues,
            @FormParam("auth_session") String authorizationChallengeSession,
            @FormParam("use_auth_session") String useAuthorizationChallengeSession,
            @FormParam("prompt") String prompt,
            @FormParam("state") String state,
            @FormParam("nonce") String nonce,
            @FormParam("code_challenge") String codeChallenge,
            @FormParam("code_challenge_method") String codeChallengeMethod,
            @FormParam("authorization_details") String authorizationDetails,
            @Context HttpServletRequest httpRequest,
            @Context HttpServletResponse httpResponse) {

        AuthzRequest authzRequest = new AuthzRequest();
        authzRequest.setHttpMethod(HttpMethod.POST);
        authzRequest.setClientId(clientId);
        authzRequest.setScope(scope);
        authzRequest.setAcrValues(acrValues);
        authzRequest.setAuthorizationChallengeSession(authorizationChallengeSession);
        authzRequest.setUseAuthorizationChallengeSession(Boolean.parseBoolean(useAuthorizationChallengeSession));
        authzRequest.setState(state);
        authzRequest.setNonce(nonce);
        authzRequest.setPrompt(prompt);
        authzRequest.setCustomParameters(requestParameterService.getCustomParameters(QueryStringDecoder.decode(httpRequest.getQueryString())));
        authzRequest.setHttpRequest(httpRequest);
        authzRequest.setHttpResponse(httpResponse);
        authzRequest.setCodeChallenge(codeChallenge);
        authzRequest.setCodeChallengeMethod(codeChallengeMethod);
        authzRequest.setAuthzDetailsString(authorizationDetails);

        return authorizationChallengeService.requestAuthorization(authzRequest);
    }
}
