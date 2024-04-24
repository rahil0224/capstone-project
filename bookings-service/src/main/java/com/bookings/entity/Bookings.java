package com.bookings.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booking")
public class Bookings {
	
	@Id
	@Column(name="booking_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(nullable=false)
    private String checkintime;

    @Column(nullable=false)
    private String checkouttime;
    
    private Long hotel_id;
    
    private Long facility_id;

    private Long guest_id;

}