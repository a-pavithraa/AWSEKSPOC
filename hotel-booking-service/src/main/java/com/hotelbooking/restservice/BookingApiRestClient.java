package com.hotelbooking.restservice;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotelbooking.model.hotels.HotelDescription;
import com.hotelbooking.model.hotels.HotelFacitlites;
import com.hotelbooking.model.hotels.HotelPhotos;
import com.hotelbooking.model.hotels.HotelReview;
import com.hotelbooking.model.hotels.Hotels;
import com.hotelbooking.model.location.Location;


@FeignClient(name = "hotelBookingApi", url = "https://booking-com.p.rapidapi.com/v1/hotels/", configuration = HotelBookingServiceFeignConfig.class)
public interface BookingApiRestClient {
	

	@GetMapping(value = "/locations")
	List<Location> getSearchLocations(@RequestParam(value="locale")String locale, @RequestParam(value="name")String name);
	
	@GetMapping(value="search")
	Hotels getSearchForHotels(@RequestParam Map<String, String> parameters);
	
	@GetMapping(value="/search-by-coordinates")
	Hotels getSearchByCoordinates(@RequestParam Map<String, String> parameters);
	
	@GetMapping(value="/description")
	HotelDescription getHotelDescription(@RequestParam(value="locale")String locale, @RequestParam(value="hotel_id")String hotelId);
	
	@GetMapping(value="/facilities")
	List<HotelFacitlites> getHotelFacilties(@RequestParam(value="locale")String locale, @RequestParam(value="hotel_id")String hotelId);

	@GetMapping(value="/photos")
	List<HotelPhotos> getHotelPhotos(@RequestParam(value="locale")String locale, @RequestParam(value="hotel_id")String hotelId);
//https://booking-com.p.rapidapi.com/v1/hotels/reviews?hotel_id=1676161&locale=en-gb&sort_type=SORT_MOST_RELEVANT&customer_type=solo_traveller%2Creview_category_group_of_friends&language_filter=en-gb%2Cde%2Cfr
	@GetMapping(value="/reviews?sort_type=SORT_MOST_RELEVANT&customer_type=solo_traveller%2Creview_category_group_of_friends&language_filter=en-gb%2Cde%2Cfr")
	HotelReview getHotelReviews(@RequestParam(value="locale")String locale, @RequestParam(value="hotel_id")String hotelId);

	
	
}
