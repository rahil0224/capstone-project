package com.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.Facilities;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.repo.FacilitiesRepo;

@Service
public class FacilitiesService {
	
	@Autowired
	FacilitiesRepo faciRepo;

	/*
	 * List<Hotel> getAllHotels() {
	 * 
	 * return hotels.stream().map((hotel) -> mapToHotel(hotel))
	 * .collect(Collectors.toList()); }
	 */
	
	public List<Facilities> getAllFacility() {
		List<Facilities> faci = faciRepo.findAll();
		return faci.stream().map((fac) -> mapToFacility(fac))
                .collect(Collectors.toList());
	}

	public Facilities getFacility(long facId) {
		Facilities fac = faciRepo.findById(facId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id:" + facId));
		return mapToFacility(fac);
	}

//	public Facilities addFacility(Facilities facilities) {
//		faciRepo.save(facilities);
//		return facilities;
//	}
	
	public List<String> addFacilities(List<Facilities> facilitiesList) {
        List<String> results = new ArrayList<String>();
        for (Facilities facility : facilitiesList) {
            try {
                faciRepo.save(facility);
                results.add("Facility added successfully: " + facility.getFacilityname());
            } catch (Exception e) {
                results.add("Failed to add facility " + facility.getFacilityname() + ": " + e.getMessage());
            }
        }
        return results;
    }

	public Facilities updateFacility(Long facId,Facilities facilities) {
		Facilities existFacility = faciRepo.findById(facId).orElseThrow(() -> new ResourceNotFoundException(facId));

		existFacility.setFacilityname(facilities.getFacilityname());
		existFacility.setFacilitydescription(facilities.getFacilitydescription());
		existFacility.setFacilitycost(facilities.getFacilitycost());


		return faciRepo.save(existFacility);
	}
	
	private Facilities mapToFacility(Facilities fac){
		Facilities faci = new Facilities();
		faci.setId(fac.getId());
		faci.setFacilityname(fac.getFacilityname());
		faci.setFacilitydescription(fac.getFacilitydescription());
		faci.setFacilitycost(fac.getFacilitycost());
		return faci;
	}

	public void deleteFacility(Long facId) {
		if (!faciRepo.existsById(facId)) {
			throw new RuntimeException("Facility not found with id: " + facId);
		}
		faciRepo.deleteById(facId);		
	}
	
}

