package com.guest.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.guest.entity.Guest;
import com.guest.service.GuestService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:7080")
public class GuestController {

	    private final GuestService guestService;

	    public GuestController(GuestService guestService) {
	        this.guestService = guestService;
	    }

	    @PostMapping("/guests")
	    public ResponseEntity<String> addGuest( @RequestBody List<Guest> guest) throws SQLException {
	        guestService.addGuest(guest);
			return ResponseEntity.ok("Guests added successfully");	    }

	    @PutMapping("/guest/{guestId}")
	    public ResponseEntity<Guest> updateGuest(@PathVariable Long guestId, @RequestBody Guest guest) {
	        Guest updatedGuest = guestService.updateGuest(guestId, guest);
	        return ResponseEntity.ok(updatedGuest);
	    }

	    @GetMapping("/guest/{guestId}")
	    public ResponseEntity<Guest> getGuestById(@PathVariable Long guestId) {
	        Guest guest = guestService.getGuestById(guestId);
	        return ResponseEntity.ok(guest);
	    }

		@GetMapping("/guests")
	    public ResponseEntity<List<Guest>> getAllGuests() {
	        List<Guest> guests = guestService.getAllGuests();
	        return ResponseEntity.ok(guests);
	    }

	    @DeleteMapping("/delete/{guestId}")
	    public ResponseEntity<String> deleteGuestById(@PathVariable("guestId") Long guestId) {
	        guestService.deleteGuestById(guestId);
	        return ResponseEntity.ok("Guest deleted successfully with ID:"+guestId);
	    }	
	
}
