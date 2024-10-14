package org.gluu.oxauth.client;

import javax.ws.rs.core.MediaType;

import org.gluu.oxauth.model.common.AuthenticationMethod;
import org.gluu.oxauth.model.util.QueryBuilder;

/**
 * @author Yuriy Zabrovarnyy
 */
public class RevokeSessionRequest extends ClientAuthnRequest {

    private String userCriterionKey;
    private String userCriterionValue;

    public RevokeSessionRequest() {
        setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setAuthenticationMethod(AuthenticationMethod.CLIENT_SECRET_BASIC);
    }

    public RevokeSessionRequest(String userCriterionKey, String userCriterionValue) {
        this.userCriterionKey = userCriterionKey;
        this.userCriterionValue = userCriterionValue;
        setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setAuthenticationMethod(AuthenticationMethod.CLIENT_SECRET_BASIC);
    }

    public String getUserCriterionKey() {
        return userCriterionKey;
    }

    public void setUserCriterionKey(String userCriterionKey) {
        this.userCriterionKey = userCriterionKey;
    }

    public String getUserCriterionValue() {
        return userCriterionValue;
    }

    public void setUserCriterionValue(String userCriterionValue) {
        this.userCriterionValue = userCriterionValue;
    }

    @Override
    public String getQueryString() {
        QueryBuilder builder = QueryBuilder.instance();

        builder.append("user_criterion_key", userCriterionKey);
        builder.append("user_criterion_value", userCriterionValue);
        appendClientAuthnToQuery(builder);

        return builder.toString();
    }
}
