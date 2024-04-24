package com.guest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guest.entity.Guest;

public interface GuestRepo extends JpaRepository<Guest, Long>{

}
