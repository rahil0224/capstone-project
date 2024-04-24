package com.hotel.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="hotel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel {
	
	@Id
	@Column(name="hotel_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(nullable=false)
    private String hotelname;

    @Column(nullable=false)
    private String location;
    
    @JsonIgnoreProperties("hotels")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hotel_facility",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private Set<Facilities> facilities;
    
    public Hotel() {
        this.facilities = new HashSet<>();
    }
}
