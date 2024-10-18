package org.gluu.oxauth.authorize.ws.rs;

import com.google.common.collect.Sets;
import org.gluu.oxauth.model.common.GrantType;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.error.ErrorResponseFactory;
import org.gluu.oxauth.model.registration.Client;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.slf4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static org.mockito.Mockito.when;

/**
 * @author Yuriy Z
 */

@Listeners(MockitoTestNGListener.class)
public class AuthorizationChallengeValidatorTest {

    @InjectMocks
    private AuthorizationChallengeValidator authorizationChallengeValidator;
    @Mock
    private Logger log;
    @Mock
    private AppConfiguration appConfiguration;
    @Mock
    private ErrorResponseFactory errorResponseFactory;

    @Test(expectedExceptions = WebApplicationException.class)
    public void validateGrantType_whenClientIsNull_shouldThrowError() {
        when(errorResponseFactory.newErrorResponse(Response.Status.BAD_REQUEST)).thenCallRealMethod();
        authorizationChallengeValidator.validateGrantType(null, null);
    }

    @Test(expectedExceptions = WebApplicationException.class)
    public void validateGrantType_whenClientGrantTypesAreNull_shouldThrowError() {
        when(errorResponseFactory.newErrorResponse(Response.Status.BAD_REQUEST)).thenCallRealMethod();
        authorizationChallengeValidator.validateGrantType(new Client(), null);
    }

    @Test(expectedExceptions = WebApplicationException.class)
    public void validateGrantType_whenClientGrantTypesDoesNotHaveAuthorizationCode_shouldThrowError() {
        when(errorResponseFactory.newErrorResponse(Response.Status.BAD_REQUEST)).thenCallRealMethod();
        final Client client = new Client();
        client.setGrantTypes(new GrantType[]{GrantType.CLIENT_CREDENTIALS});
        authorizationChallengeValidator.validateGrantType(client, null);
    }

    @Test(expectedExceptions = WebApplicationException.class)
    public void validateGrantType_whenGrantTypeIsNotAllowedByConfig_shouldThrowError() {
        when(errorResponseFactory.newErrorResponse(Response.Status.BAD_REQUEST)).thenCallRealMethod();
        when(appConfiguration.getGrantTypesSupported()).thenReturn(Sets.newHashSet(GrantType.IMPLICIT));
        final Client client = new Client();
        client.setGrantTypes(new GrantType[]{GrantType.AUTHORIZATION_CODE});
        authorizationChallengeValidator.validateGrantType(client, null);
    }

    @Test
    public void validateGrantType_whenGrantTypeIsAllowedByConfigAndClient_shouldPassSuccessfully() {
        when(appConfiguration.getGrantTypesSupported()).thenReturn(Sets.newHashSet(GrantType.IMPLICIT, GrantType.AUTHORIZATION_CODE));
        final Client client = new Client();
        client.setGrantTypes(new GrantType[]{GrantType.AUTHORIZATION_CODE});
        authorizationChallengeValidator.validateGrantType(client, "state");
    }
}
