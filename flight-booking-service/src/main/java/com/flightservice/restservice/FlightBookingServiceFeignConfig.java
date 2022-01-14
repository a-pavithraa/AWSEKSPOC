package com.flightservice.restservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.Retryer;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.HashSet;
import java.util.Set;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;


public class FlightBookingServiceFeignConfig {
	 @Value("${api.travelPayoutKey}")
	 private String apiKey;
	
	 @Bean
	  public Retryer retryer() {
	    return new Retryer.Default(100, SECONDS.toMillis(1), 3);
	  }

	  @Bean
	  public RequestInterceptor feignRequestInterceptor() {
		  
	    return t -> t.header("X-Access-Token", apiKey);
	  }

	  @Bean
	  public ErrorDecoder errorDecoder() {
		
	    Set<Integer> retryableStatusCodes = new HashSet<>();
	    retryableStatusCodes.add(500);
	    retryableStatusCodes.add(503);
	    
	    return new FeignErrorDecoder(retryableStatusCodes);
	  }

}
