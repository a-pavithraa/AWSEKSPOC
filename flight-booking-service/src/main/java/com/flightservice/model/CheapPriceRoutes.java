package com.flightservice.model;

import java.util.Map;

import lombok.Data;

@Data
public class CheapPriceRoutes {
	
	
	  private boolean success;
	  private Map<String,Map<String,RouteDetails>> data;
	  private String currency;

}
