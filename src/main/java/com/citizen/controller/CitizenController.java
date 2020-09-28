package com.citizen.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citizen.entities.Citizen;
import com.citizen.exception.CustomerGenericException;
import com.citizen.model.CitizenModel;
import com.citizen.rx.BaseWebResponse;
import com.citizen.service.CitizenService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


@RestController
public class CitizenController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CitizenController.class);

	@Autowired
	private CitizenService citizenService;

	@RequestMapping("/")
	public String index() {
		LOGGER.info("Testing service");
		return "Testing service";
	}

	@GetMapping("/getCitizenDetail/{id}")
	public ResponseEntity<Citizen> getCitizenDetail(@PathVariable("id") Long id) {
		LOGGER.info("In getCitizenDetail");
		return new ResponseEntity<>(citizenService.getCitizenDetailId(id),HttpStatus.OK);

	}
	//RxJava used in this controller
	@GetMapping("/getAllCitizenDetail")
	public Single<ResponseEntity<BaseWebResponse<List<Citizen>>>> getAllCitizenDetail(@RequestParam(value = "limit", defaultValue = "5") int limit,
                                                             @RequestParam(value = "page", defaultValue = "0") int page) {
		LOGGER.info("In getAllCitizenDetail");
		return citizenService.getAllCitizenDetail(limit,page)
				.subscribeOn(Schedulers.io())
				.map(citizen -> ResponseEntity.ok(BaseWebResponse.successWithData(citizen)));

	}
	
	@PostMapping("/createCitizen")
	public ResponseEntity<Citizen> postCitizenDetail(@RequestBody CitizenModel citizenModel) {
		LOGGER.info("In createCitizen");
		return new ResponseEntity<>(citizenService.createOrupdateCitizenDetail(citizenModel),HttpStatus.OK);
	}
	
	@PostMapping("/UpdateCitizen")
	public ResponseEntity<Citizen> updateCitizenDetail(@RequestBody CitizenModel citizenModel) {
		LOGGER.info("In UpdateCitizen");
		if(citizenModel.getCitizenid()==null)
			throw new CustomerGenericException("please enter CitizenID");
		return new ResponseEntity<>(citizenService.createOrupdateCitizenDetail(citizenModel),HttpStatus.OK);
	}

	@DeleteMapping("/deleteCitizenDetail/{id}")
	public ResponseEntity<String>  deleteCitizenDetail(@PathVariable("id") Long id) {
		LOGGER.info("In deleteCitizenDetail");
		citizenService.deleteCitizenDetail(id);
		return new ResponseEntity<>("Success",HttpStatus.OK);
				
	}
}
