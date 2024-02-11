package com.gini.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

    private final CorsConfig corsConfig;


    private final String[] endpoints = {
            "/user/**",
            "/users/**",
            "/delete/**",
            "/home/**",
            "/create",
            "/home-right",
            "/left"
    };

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        //   http.csrf(x -> x.disable());

        corsConfig.corsConfiguration(http);


        http
                .authorizeHttpRequests(
                        authorize ->
                                authorize
                                        .requestMatchers("/actuator/health/**").permitAll()
                                        .requestMatchers(endpoints).hasAuthority("ADMIN")
                                      //  .requestMatchers(endpoints).permitAll()
                                        .requestMatchers("/error").permitAll()
                                        .requestMatchers("/css/login.css", "/images/favicon/**", "/images/login.jpg").permitAll()
                                        //  .requestMatchers("/home").permitAll()
                                        //  .anyRequest().permitAll()
                                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(x -> x.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/home")
                ).logout(logout -> logout
                        // .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout"))
                .requestCache(RequestCacheConfigurer::disable); // -> so I don't have that fuuuuuuking ResourceHandlerError shit -> disables the ?continue in the url when i login to /home page


        return http.build();
    }
}
