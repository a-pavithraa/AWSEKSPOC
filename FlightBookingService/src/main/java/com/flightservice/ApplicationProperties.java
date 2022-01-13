package com.flightservice;
import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix="api")
public class ApplicationProperties {
private String travelPayoutKey;
private String travelPayputUrl;
private String test;
private String iataUrl;
}