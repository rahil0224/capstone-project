package com.hotel.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.Facilities;
import com.hotel.entity.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repo.FacilitiesRepo;
import com.hotel.repo.HotelRepo;

@Service
public class HotelService {
	
	Logger logger;
	
	@Autowired
	HotelRepo hotelRepo;
	
	@Autowired
	FacilitiesRepo faciRepo;

	@Autowired
    public HotelService(HotelRepo hotelRepo,FacilitiesRepo faciRepo) {
        this.hotelRepo = hotelRepo;
        this.faciRepo = faciRepo;
    }

//	List<Hotel> list;

//	public HotelService() {
//		list = new ArrayList<>();
//		list.add(new Hotel(1L, "JW Marriot", "Jodhpur"));
//		list.add(new Hotel(2L, "Fern Residency", "Jodhpur"));}

	public List<Hotel> getAllHotels() {
		List<Hotel> hotels = hotelRepo.findAll();
		return hotels.stream().map((hotel) -> mapToHotel(hotel)).collect(Collectors.toList());
	}
	


	    public List<Hotel> getAllHotelWithFacility() {

	        List<Hotel> hotels = hotelRepo.findAll();
	       	        
	        return hotels;

	    }
	

	public Hotel getHotel(long hotelId) {
//		Hotel h = null;
//		for (Hotel hotel : list) {
//			if (hotel.getId() == hotelId) {
//				h = hotel;
//				break;
//			}
//		}
		Hotel hotel = hotelRepo.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + hotelId));
		return mapToHotel(hotel);
	}

//	public List<String> addHotels(List<Hotel> hotels) throws SQLException {
//		List<String> results = new ArrayList<>();
//        for (Hotel hotel : hotels) {
//            try {
//                Hotel savedHotel = addHotelWithFacilities(hotel);
//                results.add("Hotel added successfully: " + savedHotel.getHotelname());
//            } catch (Exception e) {
//                results.add("Failed to add hotel " + hotel.getHotelname() + ": " + e.getMessage());
//            }
//        }
//        return results;
//	}
	
	public List<String> addHotelsWithFacilities(List<Hotel> hotelRequests) {
        List<String> results = new ArrayList<>();
        for (Hotel hotelRequest : hotelRequests) {
            try {
                Hotel savedHotel = addHotelWithFacilities(hotelRequest);
                results.add("Hotel added successfully: " + savedHotel.getHotelname());
            } catch (Exception e) {
                results.add("Failed to add hotel " + hotelRequest.getHotelname() + ": " + e.getMessage());
            }
        }
        return results;
    }
	
	private Hotel addHotelWithFacilities(Hotel hotelRequest) {
	    Hotel hotel = new Hotel();
	    hotel.setHotelname(hotelRequest.getHotelname());
	    hotel.setLocation(hotelRequest.getLocation());

	    List<Facilities> facilitiesList = new ArrayList<>();
	    for (Facilities facility : hotelRequest.getFacilities()) {
	        Facilities facilityEntity = faciRepo.getById(facility.getId());
	        facilitiesList.add(facilityEntity);
	    }

	    hotel.setFacilities(new HashSet<>(facilitiesList));
	    return hotelRepo.save(hotel);
	}

//	public Hotel addHotelWithFacilities(Hotel hotel) {
//        Hotel savedHotel = hotelRepo.save(hotel);
//        for (Facilities facility : hotel.getFacilities()) {
//            Facilities savedFacility = faciRepo.save(facility);
//            savedHotel.getFacilities().add(savedFacility);
//        }
//
//        return savedHotel;
//    }
	
	

//	public Hotel updateHotel(Hotel hotel) {
////		list.forEach(e -> {
////			if (e.getId() == hotel.getId()) {
////				e.setHotelname(hotel.getHotelname());
////				e.setLocation(hotel.getLocation());
////			}
////		});
//		if (!hotelRepo.existsById(hotel.getId())) {
//            throw new ResourceNotFoundException("Hotel not found with id: " + hotel.getId());
//        }
//		
//		hotelRepo.save(hotel);
//		return hotel;
//	}

	public Hotel updateHotel(Long hotelId, Hotel hotel) {
		// Additional validation can be added here before updating the member
		Hotel existHotel = hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException(hotelId));

		existHotel.setHotelname(hotel.getHotelname());
		existHotel.setLocation(hotel.getLocation());

		return hotelRepo.save(existHotel);
	}

	public void deleteHotel(Long id) {
		if (!hotelRepo.existsById(id)) {
			throw new RuntimeException("Hotel not found with id: " + id);
		}
		hotelRepo.deleteById(id);
	}

	private Hotel mapToHotel(Hotel hotel) {
		Hotel hotel1 = new Hotel();
		hotel1.setId(hotel.getId());
		hotel1.setHotelname(hotel.getHotelname());
		hotel1.setLocation(hotel.getLocation());
		return hotel1;
	}
}