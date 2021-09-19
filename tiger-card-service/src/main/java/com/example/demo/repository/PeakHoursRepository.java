package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.PeakHours;

public interface PeakHoursRepository extends MongoRepository<PeakHours, Integer> {

}