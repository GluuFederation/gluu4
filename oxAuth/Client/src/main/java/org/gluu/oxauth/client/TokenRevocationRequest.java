/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.client;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.gluu.oxauth.model.common.AuthenticationMethod;
import org.gluu.oxauth.model.common.TokenTypeHint;
import org.gluu.oxauth.model.token.TokenRevocationRequestParam;
import org.gluu.oxauth.model.util.QueryBuilder;

/**
 * @author Javier Rojas Blum
 * @version January 16, 2019
 */
public class TokenRevocationRequest extends ClientAuthnRequest {

    private static final Logger LOG = Logger.getLogger(TokenRevocationRequest.class);

    private String token;
    private TokenTypeHint tokenTypeHint;

    /**
     * Constructs a token revocation request.
     */
    public TokenRevocationRequest() {
        super();

        setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setAuthenticationMethod(AuthenticationMethod.CLIENT_SECRET_BASIC);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenTypeHint getTokenTypeHint() {
        return tokenTypeHint;
    }

    public void setTokenTypeHint(TokenTypeHint tokenTypeHint) {
        this.tokenTypeHint = tokenTypeHint;
    }

    /**
     * Returns a query string with the parameters of the toke revocation request.
     * Any <code>null</code> or empty parameter will be omitted.
     *
     * @return A query string of parameters.
     */
    @Override
    public String getQueryString() {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.append(TokenRevocationRequestParam.TOKEN, token);
        queryBuilder.append(TokenRevocationRequestParam.TOKEN_TYPE_HINT, tokenTypeHint != null ? tokenTypeHint.toString() : null);
        queryBuilder.append("client_id", getAuthUsername());
        return queryBuilder.toString();
    }
}
