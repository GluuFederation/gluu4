/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.client.ciba.push.PushErrorClient;
import org.gluu.oxauth.client.ciba.push.PushErrorRequest;
import org.gluu.oxauth.client.ciba.push.PushErrorResponse;
import org.gluu.oxauth.model.ciba.PushErrorResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Javier Rojas Blum
 * @version May 9, 2020
 */
@ApplicationScoped
public class CIBAPushErrorService {

    private final static Logger log = LoggerFactory.getLogger(CIBAPushErrorService.class);

    public void pushError(String authReqId, String clientNotificationEndpoint, String clientNotificationToken,
                          PushErrorResponseType error, String errorDescription) {
        PushErrorRequest pushErrorRequest = new PushErrorRequest();

        pushErrorRequest.setClientNotificationToken(clientNotificationToken);
        pushErrorRequest.setAuthReqId(authReqId);
        pushErrorRequest.setErrorType(error);
        pushErrorRequest.setErrorDescription(errorDescription);

        PushErrorClient pushErrorClient = new PushErrorClient(clientNotificationEndpoint);
        pushErrorClient.setRequest(pushErrorRequest);
        PushErrorResponse pushErrorResponse = pushErrorClient.exec();

        log.debug("CIBA: push error result status " + pushErrorResponse.getStatus());
    }
}
