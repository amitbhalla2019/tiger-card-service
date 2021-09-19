package com.example.demo;

import static com.example.demo.util.TigerCardConstants.DAILY_CAP;
import static com.example.demo.util.TigerCardConstants.NON_PEAK_HOURS_RATE;
import static com.example.demo.util.TigerCardConstants.PEAK_HOURS_RATE;
import static com.example.demo.util.TigerCardConstants.WEEKLY_CAP;
import static com.example.demo.util.TigerCardConstants.ZONE_1;
import static com.example.demo.util.TigerCardConstants.ZONE_2;
import static com.example.demo.util.TigerCardConstants.ZONE_X;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.PeakHours;
import com.example.demo.model.TariffPlan;
import com.example.demo.model.Traveller;
import com.example.demo.repository.PeakHoursRepository;
import com.example.demo.repository.TariffPlansRepository;
import com.example.demo.repository.TravellersRepository;

@SpringBootApplication
public class TigerCardServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TigerCardServiceApplication.class, args);
	}
	
	@Autowired
	private TravellersRepository travellerRepository;
	
	@Autowired
	private TariffPlansRepository tariffPlanRepository;
	
	@Autowired
	private PeakHoursRepository peakHoursRepository;

	@Override
	public void run(String... args) throws Exception {

		travellerRepository.save(new Traveller(121L));
		travellerRepository.save(new Traveller(111L));
		travellerRepository.save(new Traveller(101L));
		
		Map<String, Map<String, Integer>> mapOfTariffs = Map.of(
			ZONE_1, Map.of(PEAK_HOURS_RATE, 30, NON_PEAK_HOURS_RATE, 25, DAILY_CAP, 100, WEEKLY_CAP, 500), 
			ZONE_2, Map.of(PEAK_HOURS_RATE, 25, NON_PEAK_HOURS_RATE, 20, DAILY_CAP,   80, WEEKLY_CAP, 400), 
			ZONE_X, Map.of(PEAK_HOURS_RATE, 35, NON_PEAK_HOURS_RATE, 30, DAILY_CAP, 120, WEEKLY_CAP, 600)
		);

		tariffPlanRepository.save(new TariffPlan(1, mapOfTariffs));
		peakHoursRepository.save(new PeakHours());
	}

}