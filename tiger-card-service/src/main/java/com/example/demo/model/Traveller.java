package com.example.demo.model;

import org.springframework.data.annotation.Id;

public class Traveller {

	@Id
	private long id;
	private double creditBalance;
	private int moneySpentOnRidesOverTheCurrentDay;
	private int moneySpentOnRidesOverTheCurrentWeek;
	private String zoneOfTravelToConsiderForWeeklyCap;
	private String zoneOfTravelToConsiderForDailyCap;

	public Traveller(long id) {
		super();
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the money
	 */
	public double getCreditBalance() {
		return creditBalance;
	}

	/**
	 * @param money the money to set
	 */
	public void setCreditBalance(double money) {
		this.creditBalance = money;
	}

	/**
	 * @return the moneySpentOnRidesOverTheCurrentDay
	 */
	public int getMoneySpentOnRidesOverTheCurrentDay() {
		return moneySpentOnRidesOverTheCurrentDay;
	}

	/**
	 * @param moneySpentOnRidesOverTheCurrentDay the moneySpentOnRidesOverTheCurrentDay to set
	 */
	public void setMoneySpentOnRidesOverTheCurrentDay(int moneySpentOnRidesOverTheCurrentDay) {
		this.moneySpentOnRidesOverTheCurrentDay = moneySpentOnRidesOverTheCurrentDay;
	}

	/**
	 * @return the moneySpentOnRidesOverTheCurrentWeek
	 */
	public int getMoneySpentOnRidesOverTheCurrentWeek() {
		return moneySpentOnRidesOverTheCurrentWeek;
	}

	/**
	 * @param moneySpentOnRidesOverTheCurrentWeek the moneySpentOnRidesOverTheCurrentWeek to set
	 */
	public void setMoneySpentOnRidesOverTheCurrentWeek(int moneySpentOnRidesOverTheCurrentWeek) {
		this.moneySpentOnRidesOverTheCurrentWeek = moneySpentOnRidesOverTheCurrentWeek;
	}

	/**
	 * @return the zoneOfTravelToConsiderForWeeklyCap
	 */
	public String getZoneOfTravelToConsiderForWeeklyCap() {
		return zoneOfTravelToConsiderForWeeklyCap;
	}

	/**
	 * @param zoneOfTravelToConsiderForWeeklyCap the zoneOfTravelToConsiderForWeeklyCap to set
	 */
	public void setZoneOfTravelToConsiderForWeeklyCap(String zoneOfTravelToConsiderForWeeklyCap) {
		this.zoneOfTravelToConsiderForWeeklyCap = zoneOfTravelToConsiderForWeeklyCap;
	}

	/**
	 * @return the zoneOfTravelToConsiderForDaillyCap
	 */
	public String getZoneOfTravelToConsiderForDailyCap() {
		return zoneOfTravelToConsiderForDailyCap;
	}

	/**
	 * @param zoneOfTravelToConsiderForDaillyCap the zoneOfTravelToConsiderForDaillyCap to set
	 */
	public void setZoneOfTravelToConsiderForDaillyCap(String zoneOfTravelToConsiderForDaillyCap) {
		this.zoneOfTravelToConsiderForDailyCap = zoneOfTravelToConsiderForDaillyCap;
	}

	@Override
	public String toString() {
		return "Traveller [id=" + id + ", creditBalance=" + creditBalance + ", moneySpentOnRidesOverTheCurrentDay="
				+ moneySpentOnRidesOverTheCurrentDay + ", moneySpentOnRidesOverTheCurrentWeek="
				+ moneySpentOnRidesOverTheCurrentWeek + ", zoneOfTravelToConsiderForWeeklyCap="
				+ zoneOfTravelToConsiderForWeeklyCap + ", zoneOfTravelToConsiderForDailyCap="
				+ zoneOfTravelToConsiderForDailyCap + "]";
	}

}