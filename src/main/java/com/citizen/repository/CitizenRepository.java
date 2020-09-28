package com.citizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citizen.entities.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long>{
	
	

}
