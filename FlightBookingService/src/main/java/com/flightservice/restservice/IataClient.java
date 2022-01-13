package com.flightservice.restservice;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "iataClient", url = "${api.iataUrl}")
public interface IataClient {
	
	@PostMapping("/CityNameFromCode")
	public  Map<String, String> getCityNameFromCode(@RequestBody Set<String> codes);
	
	@GetMapping("/CodeFromCity")
	public String getCodeFromCityName(@RequestParam String city);

}
