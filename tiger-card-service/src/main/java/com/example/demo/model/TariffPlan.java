package com.example.demo.model;

import static com.example.demo.util.TigerCardConstants.DAILY_CAP;
import static com.example.demo.util.TigerCardConstants.NON_PEAK_HOURS_RATE;
import static com.example.demo.util.TigerCardConstants.PEAK_HOURS_RATE;
import static com.example.demo.util.TigerCardConstants.WEEKLY_CAP;

import java.util.Map;

import org.springframework.data.annotation.Id;

//@JsonIgnoreProperties("hibernateLazyInitializer")
public class TariffPlan {

	@Id
	private int id;
	private Map<String, Map<String, Integer>> perZoneRateMap;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param maxRateCapPerDay
	 * @param maxRateCapPerWeek
	 * @param mapOfTariffs
	 */
	public TariffPlan(int id, Map<String, Map<String, Integer>> perZoneRateMap) {
		super();
		this.id = id;
		this.perZoneRateMap = perZoneRateMap;
	}

	/**
	 * @param perZoneRateMap the perZoneRateMap to set
	 */
	public void setPerZoneRateMap(Map<String, Map<String, Integer>> perZoneRateMap) {
		this.perZoneRateMap = perZoneRateMap;
	}

	/**
	 * @return the perZoneRateMap
	 */
	public Map<String, Map<String, Integer>> getPerZoneRateMap() {
		return perZoneRateMap;
	}
	
	public int getPeakHoursPriceForZone(String zone) {
		int rate = perZoneRateMap.get(zone).get(PEAK_HOURS_RATE);
		System.out.println("Peak Hours | Zone: " + zone + " | Rate: " + rate);
		return rate;
	}

	public int getNonPeakHoursPriceForZone(String zone) {
		int rate =perZoneRateMap.get(zone).get(NON_PEAK_HOURS_RATE);
		System.out.println("Non-Peak Hours | Zone: " + zone + " | Rate: " + rate);
		return rate;
	}
	
	public int getDailyPriceCapForZone(String zone) {
		return perZoneRateMap.get(zone).get(DAILY_CAP);
	}
	
	public int getWeeklyPriceCapForZone(String zone) {
		return perZoneRateMap.get(zone).get(WEEKLY_CAP);
	}

}