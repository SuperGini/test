package com.gini.config.clients;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * https://stackoverflow.com/questions/66696828/how-to-use-configurationproperties-with-records
 * need to add -> @ConfigurationPropertiesScan for this to work
 * */
@ConfigurationProperties(prefix = "clients")
public record Clients(List<ClientApp> clientApps) {
}
