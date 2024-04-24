package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.Facilities;

@Repository
public interface FacilitiesRepo extends JpaRepository<Facilities, Long>{

}
