package com.gini.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

    private final OAuth2Error error = new OAuth2Error("401", "IncorrectAudience", null);

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
       if(token.getAudience().contains("trains-api")){
           return OAuth2TokenValidatorResult.success();
       }
        return OAuth2TokenValidatorResult.failure(error);
    }
}
