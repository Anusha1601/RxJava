package com.citizen.model;

import lombok.Data;

@Data
public class CitizenModel {
	
	private Long citizenid;
	private String name;
	private int age;
	private String address1;
	private String address2;
	private String city;
	private String postalCode;
	private String country;

}
