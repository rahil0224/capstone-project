package com.bookings.controller;

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

import com.bookings.entity.Bookings;
import com.bookings.service.BookingsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:7080")
public class BookingsController {
	
    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> addBookings( @RequestBody List<Bookings> book) throws SQLException {
    	bookingsService.addBooking(book);
		return ResponseEntity.ok("Bookings added successfully");	    }

    @PutMapping("/bookings/{bookId}")
    public ResponseEntity<Bookings> updateBooking(@PathVariable Long bookId, @RequestBody Bookings bookings) {
        Bookings updatedbookings = bookingsService.updateBookings(bookId, bookings);
        return ResponseEntity.ok(updatedbookings);
    }

    @GetMapping("/bookings/{bookId}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable Long bookId) {
    	Bookings bookings = bookingsService.getBookingById(bookId);
        return ResponseEntity.ok(bookings);
    }

	@GetMapping("/bookings")
    public ResponseEntity<List<Bookings>> getAllBookings() {
        List<Bookings> bookings = bookingsService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBookingsById(@PathVariable("bookId") Long bookId) {
        bookingsService.deleteBookingsById(bookId);
        return ResponseEntity.ok("Booking deleted successfully with ID:"+bookId);
    }	

}
