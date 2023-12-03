package com.gini.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {


    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
     //   http.csrf(x -> x.disable());
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user").permitAll()
                        .requestMatchers("css/**",  "/images/**").permitAll()
                        .anyRequest().permitAll()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(x -> x.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/home")
                );

        return http.build();
    }
}
