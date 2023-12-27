package com.gini.util;

import gini.trainscore.model.CustomerRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@UtilityClass
public class GenerateCustomer {

    public CustomerRequest generateCustomer() {
        var y = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        var tokenAttributes = y.getTokenAttributes();

        var userId = tokenAttributes.get("userId").toString();
        var email = tokenAttributes.get("sub").toString();

        return new CustomerRequest(userId, email);

    }

}
