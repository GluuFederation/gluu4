/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.client.ciba.ping.PingCallbackClient;
import org.gluu.oxauth.client.ciba.ping.PingCallbackRequest;
import org.gluu.oxauth.client.ciba.ping.PingCallbackResponse;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

/**
 * @author Javier Rojas Blum
 * @version December 21, 2019
 */
@ApplicationScoped
public class CIBAPingCallbackService {

    private final static Logger log = LoggerFactory.getLogger(CIBAPingCallbackService.class);

    @Inject
    private AppConfiguration appConfiguration;

    public void pingCallback(String authReqId, String clientNotificationEndpoint, String clientNotificationToken) {
        PingCallbackRequest pingCallbackRequest = new PingCallbackRequest();

        pingCallbackRequest.setClientNotificationToken(clientNotificationToken);
        pingCallbackRequest.setAuthReqId(authReqId);

        PingCallbackClient pingCallbackClient = new PingCallbackClient(clientNotificationEndpoint, appConfiguration.getFapiCompatibility());
        pingCallbackClient.setRequest(pingCallbackRequest);
        PingCallbackResponse pingCallbackResponse = pingCallbackClient.exec();

        log.debug("CIBA: ping callback result status " + pingCallbackResponse.getStatus());
    }
}
