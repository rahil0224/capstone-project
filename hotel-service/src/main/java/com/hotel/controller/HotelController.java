package com.hotel.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.Hotel;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:7080")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("/hotels")
	public ResponseEntity<List<Hotel>> getHotels() {
		List<Hotel> hotels = hotelService.getAllHotelWithFacility();
		return ResponseEntity.ok(hotels);
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") Long hotelId) {
		Hotel hotel = hotelService.getHotel(hotelId);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

//	@PostMapping("/hotels")
//	public List<Hotel> addHotels(@RequestBody List<Hotel> hotels) {
//	    return this.hotelService.addHotels(hotels);
//	}
	@PostMapping("/hotels")
	public ResponseEntity<String> addHotels(@RequestBody List<Hotel> hotels) throws SQLException {
		hotelService.addHotelsWithFacilities(hotels);
		return ResponseEntity.ok("Hotels added successfully");
	}

	@PutMapping("/hotel/{hotelId}")
	public Hotel updateHotel(@PathVariable("hotelId") Long hotelId, @RequestBody Hotel hotel) {
		return hotelService.updateHotel(hotelId, hotel);
	}

	@DeleteMapping("/hotel/{hotelId}")
	public ResponseEntity<String> deleteHotel(@PathVariable("hotelId") Long hotelId) {
		hotelService.deleteHotel(hotelId);
		String message = "Hotel with ID " + hotelId + " has been deleted";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
