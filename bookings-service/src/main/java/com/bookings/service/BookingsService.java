package com.bookings.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookings.entity.Bookings;
import com.bookings.exception.ResourceNotFoundException;
import com.bookings.repo.BookingsRepo;

@Service
public class BookingsService {

	@Autowired
	BookingsRepo bookingsRepo;

	public List<Bookings> getAllBookings() {
		List<Bookings> bookings = bookingsRepo.findAll();
		return bookings.stream().map((g) -> mapToBookings(g)).collect(Collectors.toList());
	}

	public Bookings getBookingById(long bookId) {
		Bookings bookings = bookingsRepo.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("BOoking not found with id:" + bookId));
		return mapToBookings(bookings);
	}

	public List<String> addBooking(List<Bookings> bookList) {
		List<String> results = new ArrayList<String>();
		for (Bookings book : bookList) {
			try {
				bookingsRepo.save(book);
				results.add("Bookings added successfully: " + book.getId());
			} catch (Exception e) {
				results.add("Failed to add Bookings " + book.getId() + ": " + e.getMessage());
			}
		}
		return results;
	}

	public Bookings updateBookings(Long bookId, Bookings book) {
		Bookings existBookings = bookingsRepo.findById(bookId).orElseThrow(() -> new ResourceNotFoundException(bookId));

		existBookings.setCheckintime(book.getCheckintime());
		existBookings.setCheckouttime(book.getCheckouttime());
		existBookings.setHotel_id(book.getHotel_id());
		existBookings.setFacility_id(book.getFacility_id());
		existBookings.setGuest_id(book.getGuest_id());
		return bookingsRepo.save(existBookings);
	}

	public void deleteBookingsById(Long id) {
		if (!bookingsRepo.existsById(id)) {
			throw new RuntimeException("Guest not found with id: " + id);
		}
		bookingsRepo.deleteById(id);
	}

	private Bookings mapToBookings(Bookings bookings) {
		Bookings b = new Bookings();
		b.setId(bookings.getId());
		b.setCheckintime(bookings.getCheckintime());
		b.setCheckouttime(bookings.getCheckouttime());
		b.setHotel_id(bookings.getHotel_id());
		b.setGuest_id(bookings.getGuest_id());
		b.setFacility_id(bookings.getFacility_id());
		return b;
	}

}
