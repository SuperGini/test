package com.gini.configuration;

import com.gini.error.decoder.RestClientErrorDecoder;
import com.gini.gateway.TrainsCoreGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Value("${trains-core.url}")
    private String trainsCoreUrl;

    /**
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/client/support/InterceptingHttpAccessor.html#getRequestFactory()
     */
    @Bean //create http client
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        var factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(20));
        factory.setReadTimeout(Duration.ofSeconds(20));
        return factory;
    }

    @Bean //set http client and error decoder on rest client
    public RestClient trainsCoreRestClient(ClientHttpRequestFactory clientHttpRequestFactory) {
        return RestClient.builder()
                .baseUrl(trainsCoreUrl)
                .defaultStatusHandler(new RestClientErrorDecoder())
                .requestFactory(clientHttpRequestFactory)
                .build();
    }

    @Bean //create proxy factory using rest client and HTTP interfaces
    public TrainsCoreGateway trainsCoreGateway(RestClient trainsCoreRestClient) {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(trainsCoreRestClient))
                .build()
                .createClient(TrainsCoreGateway.class);

    }

}
