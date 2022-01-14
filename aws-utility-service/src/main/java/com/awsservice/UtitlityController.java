package com.awsservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.awsservice.model.BookmarkedHotels;
import com.awsservice.model.HotelDetails;
import com.awsservice.repository.BookmarkedHotelsRepository;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UtitlityController {
	
	private Logger logger = LoggerFactory.getLogger(UtitlityController.class);
	@Autowired
	private BookmarkedHotelsRepository bookmarkedHotelsRepository;

	@GetMapping("/{userName}")
	public BookmarkedHotels getTopBookings(@PathVariable String userName) {
	
	
		BookmarkedHotels hotels = bookmarkedHotelsRepository.findById(userName).orElse(null);
		if(hotels!=null)
			logger.info(hotels.toString());
		return hotels;
		
	
	}
	
	@PostMapping("/{userName}")
	public BookmarkedHotels getTopBookings(@PathVariable String userName,@RequestBody HotelDetails hotelDetails) {
	
	
		BookmarkedHotels hotels = bookmarkedHotelsRepository.findById(userName).orElse(null);
		if(hotels!=null)
		{
			logger.info(hotels.toString());
			List<HotelDetails> detailsList=hotels.getHotelDetails();
			detailsList.add(hotelDetails);
			
		}
		else {
			hotels=new BookmarkedHotels();
			List<HotelDetails> detailsList=new ArrayList<>();
			detailsList.add(hotelDetails);
			hotels.setHotelDetails(detailsList);
		}
		bookmarkedHotelsRepository.save(hotels);
			
		return hotels;
		
	
	}

}
