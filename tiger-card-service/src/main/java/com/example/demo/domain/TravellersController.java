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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TravellerNotFoundException;
import com.example.demo.model.Traveller;
import com.example.demo.repository.TravellersRepository;


@RestController
@RequestMapping("/travellers")
public class TravellersController {
	
	@Autowired
	private TravellersRepository travellersRepository;

	@PostMapping
	public ResponseEntity<String> createTraveller(@RequestBody Traveller traveller) {
		travellersRepository.insert(traveller);
		return new ResponseEntity<>("Traveller is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Optional<Traveller>> getTraveller(@PathVariable("id") Long id) {
		if (!travellersRepository.existsById(id)) throw new TravellerNotFoundException();
		return new ResponseEntity<>(travellersRepository.findById(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<Traveller>> getTravellers() {
		if (travellersRepository.count()==0) throw new TravellerNotFoundException();
		return new ResponseEntity<>(travellersRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping
	public ResponseEntity<List<Traveller>> getTravellerThruReqParamId(@RequestParam List<Long> ids) {
		List<Traveller> Travellers= (List<Traveller>) travellersRepository.findAllById(ids);
		if (Travellers.isEmpty()) throw new TravellerNotFoundException();
		return new ResponseEntity<>(Travellers, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateTravellerRemainingBalance(@RequestBody Traveller traveller, Double requiredBal) {
		traveller.setCreditBalance(requiredBal);
		return new ResponseEntity<>("Traveller's Card Balance is updated successsfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteParticularTraveller(@PathVariable("id") Long id) {
		if (!travellersRepository.existsById(id)) throw new TravellerNotFoundException();
		travellersRepository.deleteById(id);
		return new ResponseEntity<>("Traveller is deleted successsfully", HttpStatus.OK);
	}

}