package com.gini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan //need to add this to scan for configuration properties with records!!!
@SpringBootApplication
public class TrainsAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainsAuthServerApplication.class, args);
    }

}
