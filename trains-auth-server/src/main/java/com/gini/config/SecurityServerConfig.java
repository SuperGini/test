package com.gini.config;

import com.gini.config.clients.ClientApp;
import com.gini.config.clients.Clients;
import com.gini.security.UserSecurity;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
//@EnableWebSecurity(debug = true)// -> for logging security
@RequiredArgsConstructor
public class SecurityServerConfig {

    @Value("${trains-api.clientId}")
    private String trainsApiClientId;

    private static final String ANGULAR = "angular";
    private static final String TRAIN_API = "train-api";

    private final Clients clients;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        //   http.csrf(x -> x.disable());

        /**
         *  http // necesara ca sa avem acces la http://localhost:8080/.well-known/openid-configuration din postman
         *             .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
         *             .oidc(Customizer.withDefaults()); //OpenId connect
         *             A Spring Security filter chain for the Protocol Endpoints.
         * */
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                )
                // Accept access tokens for User Info and/or Client Registration
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults()));

        return http.build();
    }


    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        var registeredClients = new ArrayList<RegisteredClient>();

        clients.clientApps().stream()
                .filter(x -> ANGULAR.equals(x.registerId()))
                .forEach(x -> {
                    var client = registeredFontClient(x);
                    registeredClients.add(client);
                });

        clients.clientApps().stream()
                .filter(x -> TRAIN_API.equals(x.registerId()))
                .forEach(x -> {
                    var client = registeredBackEndClient(x);
                    registeredClients.add(client);
                });

        return new InMemoryRegisteredClientRepository(registeredClients);
    }

    @Bean // -> generate public and private key
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // -> type of algorithm
            keyPairGenerator.initialize(2048); // -> length of the key
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean // use Bcrypt in practice
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean //main settings of the server where we can override the endpoints
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return context -> {
            //if I want to get one of the claims from the token
            var x = context
                    .getClaims()
                    .build()
                    .getClaims()
                    .get("sub")
                    .toString();

            var y = (UserSecurity) context.getPrincipal().getPrincipal();

            var authorities = context.getPrincipal().getAuthorities();
            /**
             * setting claims and audience on the access token and open_id token
             * */
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) && x.equals(trainsApiClientId)) {
                context.getClaims()
                        .claim("authorities", authorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                        ).audience(List.of("trains-core"));
            }

            if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
                context.getClaims()
                        .claim("authorities", authorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                        )
                        .claim("userId", y.user().getId())
                        .audience(List.of("trains-api"));

            }
        };
    }


    private RegisteredClient registeredFontClient(ClientApp clientApp) {
        return RegisteredClient.withId(clientApp.registerId())
                .clientId(clientApp.clientId())
                .clientSecret(clientApp.clientSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri(clientApp.redirectUri())
                .postLogoutRedirectUri(clientApp.logoutRedirectUri())
                .scope(OidcScopes.OPENID) //I only need one scope
                .scope(OidcScopes.PROFILE) //not necessary -> can be deleted
                //   .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(
                        // -> modify the token settings
                        TokenSettings.builder()
                                .accessTokenTimeToLive(Duration.ofMinutes(30))
                                .refreshTokenTimeToLive(Duration.ofHours(8))
                                .build()
                )
                .build();
    }

    private RegisteredClient registeredBackEndClient(ClientApp clientApp) {
        return RegisteredClient.withId(clientApp.registerId())
                .clientId(clientApp.clientId())
                .clientSecret(clientApp.clientSecret())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofHours(10))
                        .build())
                .scope(OidcScopes.OPENID)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
    }

}
