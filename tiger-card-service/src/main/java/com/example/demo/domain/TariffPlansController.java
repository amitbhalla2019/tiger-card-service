package com.example.demo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TariffPlan;
import com.example.demo.repository.TariffPlansRepository;


@RestController
@RequestMapping("/tariffPlans")
public class TariffPlansController {

	@Autowired
	public TariffPlansRepository tariffRatesRepository;
	
	@PostMapping
	public ResponseEntity<String> createTariffPlan(@RequestBody TariffPlan tariffPlan) {
		tariffRatesRepository.insert(tariffPlan);
		return new ResponseEntity<>("tariffPlan is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Optional<TariffPlan>> getTariffPlan(@PathVariable("id") int id) {
		return new ResponseEntity<>(tariffRatesRepository.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<TariffPlan>> getTariffPlans() {
		return new ResponseEntity<>(tariffRatesRepository.findAll(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<String> updateTariffPlan(@RequestBody TariffPlan tariffPlan) {
		tariffRatesRepository.insert(tariffPlan);
		return new ResponseEntity<>("tariffPlan is updated successsfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteTariffPlan(@PathVariable("id") int id) {
		tariffRatesRepository.deleteById(id);
		return new ResponseEntity<>("tariffPlan is deleted successsfully", HttpStatus.OK);
	}
	
}