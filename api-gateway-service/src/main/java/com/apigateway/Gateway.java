package com.apigateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;



@Configuration
@ConfigurationProperties(prefix = "bean")
@Data
public class Gateway {

	private String message;


	
}
