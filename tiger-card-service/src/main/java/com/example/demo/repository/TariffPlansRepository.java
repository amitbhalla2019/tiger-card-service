package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.TariffPlan;

public interface TariffPlansRepository extends MongoRepository<TariffPlan, Integer> {

}