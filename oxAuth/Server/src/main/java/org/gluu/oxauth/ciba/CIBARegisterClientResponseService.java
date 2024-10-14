/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.model.registration.Client;
import org.gluu.oxauth.model.util.Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import static org.gluu.oxauth.model.register.RegisterRequestParam.*;

/**
 * @author Javier Rojas Blum
 * @version August 20, 2019
 */
@ApplicationScoped
public class CIBARegisterClientResponseService {

    private final static Logger log = LoggerFactory.getLogger(CIBARegisterClientResponseService.class);

    public void updateResponse(JSONObject responseJsonObject, Client client) {
        try {
            Util.addToJSONObjectIfNotNull(responseJsonObject, BACKCHANNEL_TOKEN_DELIVERY_MODE.toString(), client.getBackchannelTokenDeliveryMode());
            Util.addToJSONObjectIfNotNull(responseJsonObject, BACKCHANNEL_CLIENT_NOTIFICATION_ENDPOINT.toString(), client.getBackchannelClientNotificationEndpoint());
            Util.addToJSONObjectIfNotNull(responseJsonObject, BACKCHANNEL_AUTHENTICATION_REQUEST_SIGNING_ALG.toString(), client.getBackchannelAuthenticationRequestSigningAlg());
            Util.addToJSONObjectIfNotNull(responseJsonObject, BACKCHANNEL_USER_CODE_PARAMETER.toString(), client.getBackchannelUserCodeParameter());
        } catch (JSONException e) {
            log.error("Failed to update response.", e);
        }
    }
}