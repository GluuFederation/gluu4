/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.client.ciba.fcm.FirebaseCloudMessagingClient;
import org.gluu.oxauth.client.ciba.fcm.FirebaseCloudMessagingRequest;
import org.gluu.oxauth.client.ciba.fcm.FirebaseCloudMessagingResponse;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.service.ciba.CibaEncryptionService;
import org.gluu.oxauth.service.external.ExternalCibaEndUserNotificationService;
import org.gluu.oxauth.service.external.context.ExternalCibaEndUserNotificationContext;
import org.gluu.oxauth.util.RedirectUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import java.util.UUID;

import static org.gluu.oxauth.model.authorize.AuthorizeRequestParam.*;

/**
 * @author Javier Rojas Blum
 * @version October 7, 2019
 */
@ApplicationScoped
public class CIBAEndUserNotificationService {

    private final static Logger log = LoggerFactory.getLogger(CIBAEndUserNotificationService.class);

    @Inject
    private AppConfiguration appConfiguration;

    @Inject
    private CibaEncryptionService cibaEncryptionService;

    @Inject
    private ExternalCibaEndUserNotificationService externalCibaEndUserNotificationService;

    public void notifyEndUser(String scope, String acrValues, String authReqId, String deviceRegistrationToken) {
        try {
            if (externalCibaEndUserNotificationService.isEnabled()) {
                log.debug("CIBA: Authorization request sending to the end user with custom interception scripts");
                ExternalCibaEndUserNotificationContext context = new ExternalCibaEndUserNotificationContext(scope,
                        acrValues, authReqId, deviceRegistrationToken, appConfiguration, cibaEncryptionService);
                log.info("CIBA: Notification sent to the end user, result {}",
                        externalCibaEndUserNotificationService.executeExternalNotifyEndUser(context));
            } else {
                this.notifyEndUserUsingFCM(scope, acrValues, authReqId, deviceRegistrationToken);
            }
        } catch (Exception e) {
            log.info("Error when it was sending the notification to the end user to validate the Ciba authorization", e);
        }
    }

    /**
     * Method responsible to send notifications to the end user using Firebase Cloud Messaging.
     * @param deviceRegistrationToken Device already registered.
     * @param scope Scope of the authorization request
     * @param acrValues Acr values used to the authorzation request
     * @param authReqId Authentication request id.
     */
    private void notifyEndUserUsingFCM(String scope, String acrValues, String authReqId, String deviceRegistrationToken) {
        String clientId = appConfiguration.getBackchannelClientId();
        String redirectUri = appConfiguration.getBackchannelRedirectUri();
        String url = appConfiguration.getCibaEndUserNotificationConfig().getNotificationUrl();
        String key = cibaEncryptionService.decrypt(appConfiguration.getCibaEndUserNotificationConfig()
                .getNotificationKey(), true);
        String to = deviceRegistrationToken;
        String title = "oxAuth Authentication Request";
        String body = "Client Initiated Backchannel Authentication (CIBA)";

        RedirectUri authorizationRequestUri = new RedirectUri(appConfiguration.getAuthorizationEndpoint());
        authorizationRequestUri.addResponseParameter(CLIENT_ID, clientId);
        authorizationRequestUri.addResponseParameter(RESPONSE_TYPE, "id_token");
        authorizationRequestUri.addResponseParameter(SCOPE, scope);
        authorizationRequestUri.addResponseParameter(ACR_VALUES, acrValues);
        authorizationRequestUri.addResponseParameter(REDIRECT_URI, redirectUri);
        authorizationRequestUri.addResponseParameter(STATE, UUID.randomUUID().toString());
        authorizationRequestUri.addResponseParameter(NONCE, UUID.randomUUID().toString());
        authorizationRequestUri.addResponseParameter(PROMPT, "consent");
        authorizationRequestUri.addResponseParameter(AUTH_REQ_ID, authReqId);

        String clickAction = authorizationRequestUri.toString();

        FirebaseCloudMessagingRequest firebaseCloudMessagingRequest = new FirebaseCloudMessagingRequest(key, to, title, body, clickAction);
        FirebaseCloudMessagingClient firebaseCloudMessagingClient = new FirebaseCloudMessagingClient(url);
        firebaseCloudMessagingClient.setRequest(firebaseCloudMessagingRequest);
        FirebaseCloudMessagingResponse firebaseCloudMessagingResponse = firebaseCloudMessagingClient.exec();

        log.debug("CIBA: firebase cloud messaging result status " + firebaseCloudMessagingResponse.getStatus());
    }

}