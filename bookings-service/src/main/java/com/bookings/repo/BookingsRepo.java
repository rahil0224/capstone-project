package com.bookings.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookings.entity.Bookings;

@Repository
public interface BookingsRepo extends JpaRepository<Bookings, Long>{

}
