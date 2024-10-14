package org.gluu.oxauth.ws.rs;

import static org.gluu.oxauth.client.Asserter.assertOk;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.gluu.oxauth.BaseTest;
import org.gluu.oxauth.client.AuthorizationRequest;
import org.gluu.oxauth.client.AuthorizationResponse;
import org.gluu.oxauth.client.RegisterClient;
import org.gluu.oxauth.client.RegisterRequest;
import org.gluu.oxauth.client.RegisterResponse;
import org.gluu.oxauth.client.RevokeSessionClient;
import org.gluu.oxauth.client.RevokeSessionRequest;
import org.gluu.oxauth.client.RevokeSessionResponse;
import org.gluu.oxauth.model.common.AuthenticationMethod;
import org.gluu.oxauth.model.common.ResponseType;
import org.gluu.oxauth.model.register.ApplicationType;
import org.gluu.oxauth.model.util.StringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author Yuriy Zabrovarnyy
 */
public class RevokeSessionHttpTest extends BaseTest {

    @Parameters({"redirectUris", "userId", "userSecret", "redirectUri", "sectorIdentifierUri", "umaPatClientId", "umaPatClientSecret"})
    @Test
    public void revokeSession(
            final String redirectUris, final String userId, final String userSecret, final String redirectUri,
            final String sectorIdentifierUri, String umaPatClientId, String umaPatClientSecret) throws Exception {
        showTitle("revokeSession");

        final AuthenticationMethod authnMethod = AuthenticationMethod.CLIENT_SECRET_BASIC;

        // 1. Register client
        List<ResponseType> responseTypes = Arrays.asList(ResponseType.CODE, ResponseType.ID_TOKEN);

        RegisterRequest registerRequest = new RegisterRequest(ApplicationType.WEB, "oxAuth test app",
                StringUtils.spaceSeparatedToList(redirectUris));

        registerRequest.setTokenEndpointAuthMethod(authnMethod);
        registerRequest.setSectorIdentifierUri(sectorIdentifierUri);
        registerRequest.setResponseTypes(responseTypes);

        RegisterClient registerClient = newRegisterClient(registerRequest);
        RegisterResponse registerResponse = registerClient.exec();

        showClient(registerClient);
        assertOk(registerResponse);
        assertNotNull(registerResponse.getRegistrationAccessToken());

        // 3. Request authorization
        List<String> scopes = Arrays.asList(
                "openid",
                "profile",
                "address",
                "email");
        String state = UUID.randomUUID().toString();
        String nonce = UUID.randomUUID().toString();

        AuthorizationRequest authorizationRequest = new AuthorizationRequest(responseTypes, registerResponse.getClientId(), scopes, redirectUri, nonce);
        authorizationRequest.setState(state);

        AuthorizationResponse authorizationResponse = authenticateResourceOwnerAndGrantAccess(
                authorizationEndpoint, authorizationRequest, userId, userSecret);

        assertNotNull(authorizationResponse.getLocation(), "The location is null");
        assertNotNull(authorizationResponse.getCode(), "The authorization code is null");
        assertNotNull(authorizationResponse.getIdToken(), "The ID Token is null");
        assertNotNull(authorizationResponse.getState(), "The state is null");
        assertNotNull(authorizationResponse.getScope(), "The scope is null");

        RevokeSessionRequest revokeSessionRequest = new RevokeSessionRequest("uid", "test");
        revokeSessionRequest.setAuthenticationMethod(authnMethod);
        revokeSessionRequest.setAuthUsername(umaPatClientId); // it must be client with revoke_session scope
        revokeSessionRequest.setAuthPassword(umaPatClientSecret);

        RevokeSessionClient revokeSessionClient = newRevokeSessionClient(revokeSessionRequest);
        final RevokeSessionResponse revokeSessionResponse = revokeSessionClient.exec();

        showClient(revokeSessionClient);

        assertEquals(revokeSessionResponse.getStatus(), 200);
    }
}
