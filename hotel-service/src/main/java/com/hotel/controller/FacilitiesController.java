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

import com.hotel.entity.Facilities;
import com.hotel.service.FacilitiesService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:7080")
public class FacilitiesController {

	@Autowired
	FacilitiesService facService;

	@GetMapping("/facility")
	public ResponseEntity<List<Facilities>> getFacility() {
		List<Facilities> faci = facService.getAllFacility();
		return ResponseEntity.ok(faci);
	}

	@GetMapping("/facility/{facId}")
	public ResponseEntity<Facilities> getHotel(@PathVariable("facId") Long facId) {
		Facilities fac = facService.getFacility(facId);
		return new ResponseEntity<Facilities>(fac, HttpStatus.OK);
	}

//	@PostMapping("/facility")
//	public Facilities addHotel(@RequestBody Facilities facilities) {
//		return this.facService.addFacility(facilities);
//	}

	@PostMapping("/facility")
	public ResponseEntity<String> addFacilities(@RequestBody List<Facilities> facilities) throws SQLException {
		facService.addFacilities(facilities);
		return ResponseEntity.ok("Facilities added successfully");
	}

	@PutMapping("/facility/{facId}")
	public ResponseEntity<Facilities> updateFacility(@RequestBody Facilities facilities,
			@PathVariable("facId") Long facId) {
		Facilities updatedFaci = facService.updateFacility(facId,facilities);
		return ResponseEntity.ok(updatedFaci);
	}
	
	@DeleteMapping("/facility/{facId}")
	public ResponseEntity<String> deleteFacility(@PathVariable("facId") Long facId) {
		facService.deleteFacility(facId);
		String message = "Facility with ID " + facId + " has been deleted";
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}

}
