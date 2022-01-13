package com.flightservice.model;

import java.util.Map;

import lombok.Data;

@Data
public class Routes {
	
	  private boolean success;
	  private Map<String,RouteDetails> data;
	  private String currency;

}
