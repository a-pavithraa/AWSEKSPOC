package com.flightservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightservice.model.RouteDetails;
import com.flightservice.model.Routes;
import com.flightservice.restservice.BookingApiRestClient;
import com.flightservice.restservice.IataClient;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FlightBookingController {
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private BookingApiRestClient bookingClient;
	@Autowired
	private IataClient iataClient;
	private Logger logger = LoggerFactory.getLogger(FlightBookingController.class);

	@GetMapping("/TopRoutes")
	public List<RouteDetails> getTopBookings(String origin, String currency) {
		
		String code = iataClient.getCodeFromCityName(origin);
		System.out.println("code==="+code);
		Routes routes = bookingClient.getImportantRoutes(code, currency);
		Map<String, String> cityCodeMapping = iataClient.getCityNameFromCode(routes.getData().keySet());
		List<RouteDetails> routeList = new ArrayList<>();
		routes.getData().entrySet().forEach(entry -> {
			entry.getValue().setDestinationCity(cityCodeMapping.get(entry.getKey()));
			routeList.add(entry.getValue());

		});
		return routeList;

	}

	@GetMapping("/CheapestFlights")
	public List<RouteDetails> getCheapestFlights(@RequestParam Map<String, String> paramMap) {
		List<RouteDetails> availableFlights = bookingClient.getCheapPriceRoutes(paramMap).getData().values().stream()
				.flatMap(m -> m.values().stream()).collect(Collectors.toList());
		return availableFlights;

	}

}
