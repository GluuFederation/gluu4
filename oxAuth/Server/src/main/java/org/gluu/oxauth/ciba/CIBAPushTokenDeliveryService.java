/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.client.ciba.push.PushTokenDeliveryClient;
import org.gluu.oxauth.client.ciba.push.PushTokenDeliveryRequest;
import org.gluu.oxauth.client.ciba.push.PushTokenDeliveryResponse;
import org.gluu.oxauth.model.common.TokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Javier Rojas Blum
 * @version September 4, 2019
 */
@ApplicationScoped
public class CIBAPushTokenDeliveryService {

    private final static Logger log = LoggerFactory.getLogger(CIBAPushTokenDeliveryService.class);

    public void pushTokenDelivery(String authReqId, String clientNotificationEndpoint, String clientNotificationToken,
                                  String accessToken, String refreshToken, String idToken, Integer expiresIn) {
        PushTokenDeliveryRequest pushTokenDeliveryRequest = new PushTokenDeliveryRequest();

        pushTokenDeliveryRequest.setClientNotificationToken(clientNotificationToken);
        pushTokenDeliveryRequest.setAuthReqId(authReqId);
        pushTokenDeliveryRequest.setAccessToken(accessToken);
        pushTokenDeliveryRequest.setTokenType(TokenType.BEARER);
        pushTokenDeliveryRequest.setRefreshToken(refreshToken);
        pushTokenDeliveryRequest.setExpiresIn(expiresIn);
        pushTokenDeliveryRequest.setIdToken(idToken);

        PushTokenDeliveryClient pushTokenDeliveryClient = new PushTokenDeliveryClient(clientNotificationEndpoint);
        pushTokenDeliveryClient.setRequest(pushTokenDeliveryRequest);
        PushTokenDeliveryResponse pushTokenDeliveryResponse = pushTokenDeliveryClient.exec();

        log.debug("CIBA: push token delivery result status " + pushTokenDeliveryResponse.getStatus());
    }
}
