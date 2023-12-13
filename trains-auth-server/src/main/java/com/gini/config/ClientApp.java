package com.gini.config;

public record ClientApp(
        String registerId,
        String clientId,
        String clientSecret,
        String redirectUri,
        String logoutRedirectUri
) {
}
