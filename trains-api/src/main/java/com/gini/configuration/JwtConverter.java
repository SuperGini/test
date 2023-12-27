package com.gini.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

//not used
public class JwtConverter implements Converter<Jwt, JwtAuthenticationToken> {


    @Override
    public JwtAuthenticationToken convert(Jwt source) {

      //  JwtAuthenticationToken jwt = new JwtAuthenticationToken();

        return null;
    }
}
