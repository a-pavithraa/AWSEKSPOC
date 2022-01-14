package com.awsservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {

    @Autowired
    private AwsProperties awsProperties;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(awsProperties.getRegion()))
                .build();
    }
}