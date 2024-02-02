package com.capital.api.java.samples.rest;

import com.capital.api.java.samples.common.Enviroment;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.capital.api.java.samples.common.Constants.*;

public abstract class AbstractApiClient {
    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    private String prodURL;

    @Autowired
    private String demoURL;

    private Enviroment env;

    protected HttpEntity<Object> buildHttpEntity(@NonNull ConversationContext context, Object request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        requestHeaders.set(CAP_APP_KEY, context.getApiKey());
        requestHeaders.set(CLIENT_SSO_TOKEN_NAME, context.getClientSecurityToken());
        requestHeaders.set(ACCOUNT_SSO_TOKEN_NAME, context.getAccountSecurityToken());

        return new HttpEntity<>(request, requestHeaders);
    }

    public void setEnviroment(Enviroment env) {
        this.env = env;
    }

    public String getApiDomainURL() {
        if (env == Enviroment.PROD) {
            return prodURL;
        }
        return demoURL;
    }
}
