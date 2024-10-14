/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.ciba;

import org.apache.commons.lang.BooleanUtils;
import org.apache.logging.log4j.util.Strings;
import org.gluu.oxauth.model.common.BackchannelTokenDeliveryMode;
import org.gluu.oxauth.model.configuration.AppConfiguration;
import org.gluu.oxauth.model.error.DefaultErrorResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.gluu.oxauth.model.ciba.BackchannelAuthenticationErrorResponseType.*;

/**
 * @author Javier Rojas Blum
 * @version April 22, 2020
 */
@ApplicationScoped
public class CIBAAuthorizeParamsValidatorService {

    @Inject
    private AppConfiguration appConfiguration;

    public DefaultErrorResponse validateParams(
            List<String> scopeList, String clientNotificationToken, BackchannelTokenDeliveryMode tokenDeliveryMode,
            String loginHintToken, String idTokenHint, String loginHint, String bindingMessage,
            Boolean backchannelUserCodeParameter, String userCodeParam, String userCode, Integer requestedExpirity) {
        if (tokenDeliveryMode == null) {
            DefaultErrorResponse errorResponse = new DefaultErrorResponse();
            errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorResponse.setType(UNAUTHORIZED_CLIENT);
            errorResponse.setReason(
                    "Clients registering to use CIBA must indicate a token delivery mode.");

            return errorResponse;
        }

        if (scopeList == null || !scopeList.contains("openid")) {
            DefaultErrorResponse errorResponse = new DefaultErrorResponse();
            errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorResponse.setType(INVALID_SCOPE);
            errorResponse.setReason(
                    "CIBA authentication requests must contain the openid scope value.");

            return errorResponse;
        }

        if (!validateOneParamNotBlank(loginHintToken, idTokenHint, loginHint)) {
            DefaultErrorResponse errorResponse = new DefaultErrorResponse();
            errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorResponse.setType(INVALID_REQUEST);
            errorResponse.setReason(
                    "It is required that the Client provides one (and only one) of the hints in the authentication " +
                            "request, that is login_hint_token, id_token_hint or login_hint.");

            return errorResponse;
        }

        if (tokenDeliveryMode == BackchannelTokenDeliveryMode.PING || tokenDeliveryMode == BackchannelTokenDeliveryMode.PUSH) {
            if (Strings.isBlank(clientNotificationToken)) {
                DefaultErrorResponse errorResponse = new DefaultErrorResponse();
                errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorResponse.setType(INVALID_REQUEST);
                errorResponse.setReason(
                        "The client notification token is required if the Client is registered to use Ping or Push modes.");

                return errorResponse;
            }
        }

        if(Strings.isNotBlank(bindingMessage)) {
            final Pattern pattern = Pattern.compile(appConfiguration.getBackchannelBindingMessagePattern());
            if (!pattern.matcher(bindingMessage).matches()) {
                DefaultErrorResponse errorResponse = new DefaultErrorResponse();
                errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorResponse.setType(INVALID_BINDING_MESSAGE);
                errorResponse.setReason("The provided binding message is unacceptable. It must match the pattern: " + pattern.pattern());

                return errorResponse;
            }
        }

        if (BooleanUtils.isTrue(backchannelUserCodeParameter)) {
            if (Strings.isBlank(userCodeParam)) {
                DefaultErrorResponse errorResponse = new DefaultErrorResponse();
                errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorResponse.setType(INVALID_USER_CODE);
                errorResponse.setReason("The user code is required.");

                return errorResponse;
            } else if (Strings.isBlank(userCode)) {
                DefaultErrorResponse errorResponse = new DefaultErrorResponse();
                errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorResponse.setType(INVALID_USER_CODE);
                errorResponse.setReason("The user code is not set.");

                return errorResponse;
            } else if (!userCode.equals(userCodeParam)) {
                DefaultErrorResponse errorResponse = new DefaultErrorResponse();
                errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
                errorResponse.setType(INVALID_USER_CODE);
                errorResponse.setReason("The user code is not valid.");

                return errorResponse;
            }
        }

        if (requestedExpirity != null && (requestedExpirity < 1
                || requestedExpirity > appConfiguration.getCibaMaxExpirationTimeAllowedSec())) {
            DefaultErrorResponse errorResponse = new DefaultErrorResponse();
            errorResponse.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            errorResponse.setType(INVALID_REQUEST);
            errorResponse.setReason("Requested expirity is not allowed.");

            return errorResponse;
        }

        return null;
    }

    private boolean validateOneParamNotBlank(String... params) {
        List<String> notBlankParams = new ArrayList<>();

        for (String param : params) {
            if (Strings.isNotBlank(param)) {
                notBlankParams.add(param);
            }
        }

        return notBlankParams.size() == 1;
    }

}