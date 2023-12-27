package com.gini.configuration.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

/**
 *  This interceptor is attached to Rest client. When I do a request to trains-core microservice
 *  it attaches the token generated for this trains-api service.
 *
 *
 * */

@RequiredArgsConstructor
public class TokenInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        var principalName = SecurityContextHolder.getContext().getAuthentication().getName();

        OAuth2AuthorizeRequest req = OAuth2AuthorizeRequest.withClientRegistrationId("train-api")
                .principal(principalName) //can be any string value here
                .build();
        OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(req); //request to auth server -> it cashes the token, and use it until it expires so no cals to auth server will be made

        var token = client.getAccessToken().getTokenValue();

        request.getHeaders().add("Authorization", "Bearer " + token);

        return execution.execute(request, body);
    }
}
