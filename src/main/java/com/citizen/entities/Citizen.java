package com.citizen.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "citizen")
@Data
public class Citizen extends AuditModel
{
	
	   @Id
	   @Column(name = "citizen_id")
	   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citizen_generator")
	   @SequenceGenerator(name="citizen_generator", sequenceName = "citizen_seq", allocationSize=50)
	   private Long citizenid;
	   
	   @OneToOne(fetch = FetchType.EAGER, optional = true,cascade = {CascadeType.ALL})
	   @JoinColumn(name = "citizen_address_id", nullable = true)
	   private CitizenAddress citizenAddress;
	   
	   @Column(name = "name")
		private String name;
	   
	   @Column(name = "age")
		private int age;

}
