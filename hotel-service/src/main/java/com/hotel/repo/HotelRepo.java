package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Long> {

//	List<Hotel> findAllWithFacilities();

}

