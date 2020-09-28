package com.citizen.service;

import java.util.List;

import com.citizen.entities.Citizen;
import com.citizen.model.CitizenModel;

import io.reactivex.Single;

public interface CitizenService {
	
	Single<List<Citizen>> getAllCitizenDetail(int limit,int page);
	
	Citizen getCitizenDetailId(Long id);
	
	void deleteCitizenDetail(Long id);
	
	Citizen createOrupdateCitizenDetail(CitizenModel citizenModel);

}
