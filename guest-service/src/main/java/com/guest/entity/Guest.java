package com.guest.entity;

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
@Table(name="guest")
public class Guest {

	@Id
	@Column(name="guest_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(nullable=false)
    private String guestname;

    @Column(nullable=false)
    private String address;
	
}