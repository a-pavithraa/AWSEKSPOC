package com.flightservice.model;

import java.util.Date;

import lombok.Data;

@Data
public class RouteDetails {
	
	private String origin;
	private String destination;
	private int price;
	private String airline;
	private int flight_number;
	private Date departure_at;
	private Date return_at;
	private int transfers;
	private Date expires_at;
	private String originCity;
	private String destinationCity;
	

}
