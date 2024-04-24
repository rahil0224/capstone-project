package com.guest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guest.entity.Guest;
import com.guest.exception.ResourceNotFoundException;
import com.guest.repo.GuestRepo;

@Service
public class GuestService {

	@Autowired
	GuestRepo guestRepo;

	public List<Guest> getAllGuests() {
		List<Guest> guest = guestRepo.findAll();
		return guest.stream().map((g) -> mapToGuest(g)).collect(Collectors.toList());
	}

	public Guest getGuestById(long guestId) {
		Guest guest = guestRepo.findById(guestId)
				.orElseThrow(() -> new ResourceNotFoundException("Guests not found with id:" + guestId));
		return mapToGuest(guest);
	}

//	public Facilities addFacility(Facilities facilities) {
//		faciRepo.save(facilities);
//		return facilities;
//	}

	public List<String> addGuest(List<Guest> guestList) {
		List<String> results = new ArrayList<String>();
		for (Guest guest : guestList) {
			try {
				guestRepo.save(guest);
				results.add("Guests added successfully: " + guest.getGuestname());
			} catch (Exception e) {
				results.add("Failed to add Guest " + guest.getGuestname() + ": " + e.getMessage());
			}
		}
		return results;
	}

	public Guest updateGuest(Long guestId,Guest guest) {
		Guest existGuest = guestRepo.findById(guestId).orElseThrow(() -> new ResourceNotFoundException(guestId));

		existGuest.setGuestname(guest.getGuestname());
		existGuest.setAddress(guest.getAddress());

		return guestRepo.save(existGuest);
	}

	public void deleteGuestById(Long id) {
		if (!guestRepo.existsById(id)) {
			throw new RuntimeException("Guest not found with id: " + id);
		}
		guestRepo.deleteById(id);
	}
	
	private Guest mapToGuest(Guest guest) {
		Guest g = new Guest();
		g.setId(guest.getId());
		g.setGuestname(guest.getGuestname());
		g.setAddress(guest.getAddress());
		return g;
	}

}
