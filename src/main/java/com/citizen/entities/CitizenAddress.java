package com.citizen.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "citizen_address")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CitizenAddress extends AuditModel
{
	
	   @Id
	   @Column(name = "citizen_address_id")
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citizen_address_generator")
	   @SequenceGenerator(name="citizen_address_generator", sequenceName = "citizen_address_seq", allocationSize=50)
	   @JsonIgnore
	   private Long id;
	   
	    
	    @Column(name = "Address_1")
		private String address1;
	    
	    @Column(name = "Address_2")
		private String address2;
	    
	    @Column(name = "city")
		private String city;
	    
	    @Column(name = "postal_code")
		private String postalCode;
	    
	    @Column(name = "country")
		private String country;

}
