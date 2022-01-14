package com.flightservice.restservice;


import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightservice.model.CheapPriceRoutes;
import com.flightservice.model.Routes;



@FeignClient(name = "travelPayoutApi", url = "${api.travelPayputUrl}", configuration = FlightBookingServiceFeignConfig.class)
public interface BookingApiRestClient {
	@GetMapping(value = "/city-directions")
	public Routes getImportantRoutes(@RequestParam(value="origin")String origin,@RequestParam(value="currency")String currency);
	
	@GetMapping(value="/prices/cheap")
	public CheapPriceRoutes getCheapPriceRoutes(@RequestParam  Map<String,String> paramMap);

	
	
	
}
