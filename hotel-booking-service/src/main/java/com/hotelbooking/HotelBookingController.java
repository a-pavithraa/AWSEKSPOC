package com.hotelbooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.dto.HotelSearchDto;
import com.hotelbooking.model.hotels.HotelDescription;
import com.hotelbooking.model.hotels.HotelFacitlites;
import com.hotelbooking.model.hotels.HotelPhotos;
import com.hotelbooking.model.hotels.HotelReview;
import com.hotelbooking.model.hotels.Hotels;
import com.hotelbooking.model.hotels.Result;
import com.hotelbooking.model.location.Location;
import com.hotelbooking.restservice.BookingApiRestClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HotelBookingController {
	private Logger logger = LoggerFactory.getLogger(HotelBookingController.class);
	@Autowired
	private BookingApiRestClient restClient;
	
	@GetMapping("/Locations")
	@CircuitBreaker(name = "locationapi", fallbackMethod = "fetchSampleLocations")
	@RateLimiter(name="locationapi")
	public List<Location> getLocation(@RequestParam String locale,@RequestParam String locationName) {
		logger.info("Inside get location");
		
		List<Location> location=restClient.getSearchLocations(locale, locationName);
		return location;
		
	}
	
	public List<Location> fetchSampleLocations(Exception ex) {
		List<Location> locations = new ArrayList<>();
		return locations;
	}
	
	@GetMapping("/Hotels")
	public Hotels getHotelsList(@RequestParam  Map<String,String> paramMap){
		logger.info("Input Values=====");
		logger.info(paramMap.toString());
		return restClient.getSearchForHotels(paramMap);
		
	}
	
	@GetMapping("/HotelsByCoordinates")
	public Hotels getHotelsByCoordinates(@RequestParam  Map<String,String> paramMap) {
		return restClient.getSearchByCoordinates(paramMap);
	}
	@GetMapping("/HotelDescription")
	@CircuitBreaker(name="hoteldetails")
	public HotelDescription getHotelDescription(@RequestParam String locale,@RequestParam String hotelId) {
		return restClient.getHotelDescription(locale,hotelId);
	}
	@GetMapping("/HotelFacilities")
	@CircuitBreaker(name="hoteldetails")
	public List<HotelFacitlites> getHotelFacilities(@RequestParam String locale,@RequestParam String hotelId) {
		return restClient.getHotelFacilties(locale, hotelId);
		
	}
	
	@GetMapping("/HotelReviews")
	@CircuitBreaker(name="hoteldetails")
	public HotelReview getHotelReviews(@RequestParam String locale,@RequestParam String hotelId) {
		return restClient.getHotelReviews(locale, hotelId);
	}
	@GetMapping("/HotelPhotos")
	@CircuitBreaker(name="hoteldetails")
	public List<HotelPhotos> getHotelPhotos(@RequestParam String locale,@RequestParam String hotelId) {
		return restClient.getHotelPhotos(locale, hotelId);
	}
	
	
	

}
