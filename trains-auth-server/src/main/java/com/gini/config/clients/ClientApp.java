package com.gini.config.clients;

public record ClientApp(
        String registerId,
        String clientId,
        String clientSecret,
        String redirectUri,
        String logoutRedirectUri
) {
}
