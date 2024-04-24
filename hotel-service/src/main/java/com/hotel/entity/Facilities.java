package com.hotel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "facilities")
public class Facilities {

	@Id
	@Column(name = "facility_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String facilityname;

	@Column(nullable = false)
	private String facilitydescription;

	@Column(nullable = false)
	private float facilitycost;

	@ManyToMany(mappedBy = "facilities")
	private Set<Hotel> hotels = new HashSet<Hotel>();
}