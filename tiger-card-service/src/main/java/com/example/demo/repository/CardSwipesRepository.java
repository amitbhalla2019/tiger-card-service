package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.CardSwipe;
import com.example.demo.model.Traveller;

public interface CardSwipesRepository extends MongoRepository<CardSwipe, Long> {
	
	List<CardSwipe> findAllCardSwipesMadeByTraveller(Traveller traveller);

}