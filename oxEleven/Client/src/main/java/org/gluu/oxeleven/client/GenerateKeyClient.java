/*
 * oxEleven is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */

package org.gluu.oxeleven.client;

import static org.gluu.oxeleven.model.GenerateKeyRequestParam.EXPIRATION_TIME;
import static org.gluu.oxeleven.model.GenerateKeyRequestParam.SIGNATURE_ALGORITHM;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.google.common.base.Strings;

/**
 * @author Javier Rojas Blum
 * @version March 20, 2017
 */
public class GenerateKeyClient extends BaseClient<GenerateKeyRequest, GenerateKeyResponse> {

    public GenerateKeyClient(String url) {
        super(url);
    }

    @Override
    public String getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public GenerateKeyRequest getRequest() {
        if (request instanceof GenerateKeyRequest) {
            return (GenerateKeyRequest) request;
        } else {
            return null;
        }
    }

    @Override
    public void setRequest(GenerateKeyRequest request) {
        super.request = request;
    }

    @Override
    public GenerateKeyResponse getResponse() {
        if (response instanceof GenerateKeyResponse) {
            return (GenerateKeyResponse) response;
        } else {
            return null;
        }
    }

    @Override
    public void setResponse(GenerateKeyResponse response) {
        super.response = response;
    }

    @Override
    public GenerateKeyResponse exec() throws Exception {
    	ResteasyClient resteasyClient = (ResteasyClient) ResteasyClientBuilder.newClient();
    	WebTarget webTarget = resteasyClient.target(url);

        Builder clientRequest = webTarget.request();

        clientRequest.header("Content-Type", getRequest().getMediaType());
//        clientRequest.setHttpMethod(getRequest().getHttpMethod());
        if (!Strings.isNullOrEmpty(getRequest().getAccessToken())) {
            clientRequest.header("Authorization", "Bearer " + getRequest().getAccessToken());
        }

        if (!Strings.isNullOrEmpty(getRequest().getSignatureAlgorithm())) {
            addRequestParam(SIGNATURE_ALGORITHM, getRequest().getSignatureAlgorithm());
        }
        if (getRequest().getExpirationTime() != null) {
            addRequestParam(EXPIRATION_TIME, getRequest().getExpirationTime());
        }

        // Call REST Service and handle response
        if (HttpMethod.POST.equals(request.getHttpMethod())) {
            clientResponse = clientRequest.buildPost(Entity.entity("{}", getRequest().getMediaType())).invoke();
        } else {
            clientResponse = clientRequest.buildGet().invoke();
        }

        try {
        	setResponse(new GenerateKeyResponse(clientResponse));
        } finally {
        	clientResponse.close();
        }

        return getResponse();
    }
}
