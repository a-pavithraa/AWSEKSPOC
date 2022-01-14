package com.iata;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iata.model.Airports;
import com.iata.model.Iata;
import com.iata.repository.AirportsRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class IataController {
	
	private Logger logger = LoggerFactory.getLogger(IataController.class);
	
	@Autowired
	AirportsRepository airportRepo;
	@GetMapping("{city}")
	public List<Airports> getLocations(@PathVariable String city) {
		logger.info(city);
	   List<Airports> airports= airportRepo.findByCityNameIgnoreCaseContaining(city.toUpperCase());	   
	   return airports;
	}
	
	@PostMapping("/CityNameFromCode")
	public  Map<String, String> getCityNameFromCode(@RequestBody List<String> codes){
		System.out.println(codes);
		List<Airports> airports= airportRepo.findByCodeIn(codes);
		 Map<String, String> codeMapping =airports.stream().collect(Collectors.toMap(Airports::getCode,Airports::getCityName));
		 System.out.println(codeMapping);
		 return codeMapping;
	}
	
	@GetMapping("/CodeFromCity")
	public String getCodeFromCityName(@RequestParam String city) {
		Airports airport = airportRepo.findByCityName(city);
		System.out.println("city=="+city);
		if(airport!=null && airport.getCityCode()!=null)
			return airport.getCityCode();
		else
			return "MAA";
	}

}
