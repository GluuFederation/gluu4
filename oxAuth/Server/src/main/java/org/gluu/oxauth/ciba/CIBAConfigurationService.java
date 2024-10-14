/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;

import static org.gluu.oxauth.model.configuration.ConfigurationResponseClaim.*;

/**
 * @author Javier Rojas Blum
 * @version August 20, 2019
 */
@ApplicationScoped
public class CIBAConfigurationService {

    private final static Logger log = LoggerFactory.getLogger(CIBAConfigurationService.class);

    @Inject
    private AppConfiguration appConfiguration;

    public void processConfiguration(JSONObject jsonConfiguration) {
        try {
            jsonConfiguration.put(BACKCHANNEL_AUTHENTICATION_ENDPOINT, appConfiguration.getBackchannelAuthenticationEndpoint());

            JSONArray backchannelTokenDeliveryModesSupported = new JSONArray();
            for (String item : appConfiguration.getBackchannelTokenDeliveryModesSupported()) {
                backchannelTokenDeliveryModesSupported.put(item);
            }
            if (backchannelTokenDeliveryModesSupported.length() > 0) {
                jsonConfiguration.put(BACKCHANNEL_TOKEN_DELIVERY_MODES_SUPPORTED, backchannelTokenDeliveryModesSupported);
            }

            JSONArray backchannelAuthenticationRequestSigningAlgValuesSupported = new JSONArray();
            for (String item : appConfiguration.getBackchannelAuthenticationRequestSigningAlgValuesSupported()) {
                backchannelAuthenticationRequestSigningAlgValuesSupported.put(item);
            }
            if (backchannelAuthenticationRequestSigningAlgValuesSupported.length() > 0) {
                jsonConfiguration.put(BACKCHANNEL_AUTHENTICATION_REQUEST_SIGNING_ALG_VALUES_SUPPORTED, backchannelAuthenticationRequestSigningAlgValuesSupported);
            }

            jsonConfiguration.put(BACKCHANNEL_USER_CODE_PAREMETER_SUPPORTED, appConfiguration.getBackchannelUserCodeParameterSupported());
        } catch (Exception e) {
            log.error("Failed to process CIBA configuration.", e);
        }
    }
}