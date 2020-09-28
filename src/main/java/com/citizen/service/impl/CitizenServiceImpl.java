package com.citizen.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.citizen.entities.Citizen;
import com.citizen.entities.CitizenAddress;
import com.citizen.model.CitizenModel;
import com.citizen.repository.CitizenRepository;
import com.citizen.service.CitizenService;

import io.reactivex.Completable;
import io.reactivex.Single;

@Service
public class CitizenServiceImpl implements CitizenService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CitizenServiceImpl.class);

	@Autowired
	private CitizenRepository citizenRepository;

	public Single<List<Citizen>> getAllCitizenDetail(int limit,int page) {
		 return Single.create(singleSubscriber -> {
	            List<Citizen> Citizen = citizenRepository.findAll(PageRequest.of(page, limit)).getContent();
	            singleSubscriber.onSuccess(Citizen);
	        });
	}

	public Citizen getCitizenDetailId(Long id) {
		
		return citizenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found ID:"+Long.toString(id)));
		
	}

	
	public void deleteCitizenDetail(Long id) {
		 getCitizenDetailId(id);
		citizenRepository.deleteById(id);
	}

	public Citizen createOrupdateCitizenDetail(CitizenModel citizenModel) {
		Citizen citizen = new Citizen();
		if(citizenModel.getCitizenid()!=null)
		{
		citizen = getCitizenDetailId(citizenModel.getCitizenid());
		}
		CitizenAddress citizenAddress = new CitizenAddress();
		BeanUtils.copyProperties(citizenModel, citizenAddress);
		BeanUtils.copyProperties(citizenModel, citizen);
		citizen.setCitizenAddress(citizenAddress);
		return citizenRepository.save(citizen);
	}
	
	
	/*public Completable test(CitizenModel citizenModel, Long id)
	{
		return Completable.create(completableSubscriber -> {
			Optional<Citizen> optionalCitizen = citizenRepository.findById(id);
        if (!optionalCitizen.isPresent())
        	completableSubscriber.onError(new EntityNotFoundException());
        else {
        	Citizen citizen = optionalCitizen.get();
        	CitizenAddress citizenAddress = citizen.getCitizenAddress();
        	BeanUtils.copyProperties(citizenModel, citizenAddress);
    		BeanUtils.copyProperties(citizenModel, citizen);
    		citizen.setCitizenAddress(citizenAddress);
    		citizenRepository.save(citizen);
            completableSubscriber.onComplete();
        }
    });
    }*/

}
