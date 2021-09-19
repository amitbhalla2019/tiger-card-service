package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.data.annotation.Id;

public class CardSwipe {
	
	@Id
	private long epochSeconds;
	private LocalDateTime dateTimeOfCardSwipe;
	private String zone;
	private Traveller traveller;
	private Long travellerId;

	/**
	 * @return the travellerId
	 */
	public Long getTravellerId() {
		return travellerId;
	}
	
	CardSwipe() {}

	/**
	 * @param id
	 * @param zone
	 */
	public CardSwipe(Long travellerId, String zone) {
		super();	
		this.dateTimeOfCardSwipe = LocalDateTime.now();
		this.epochSeconds = dateTimeOfCardSwipe.toEpochSecond(ZoneOffset.UTC);
		this.travellerId = travellerId;
		this.traveller = new Traveller(travellerId);
		this.zone = zone;
	}

	/**
	 * @return the epochSeconds
	 */
	public long getEpochSeconds() {
		return epochSeconds;
	}

	/**
	 * @param epochSeconds the epochSeconds to set
	 */
	public void setEpochSeconds(long epochSeconds) {
		this.epochSeconds = epochSeconds;
	}

	/**
	 * @return the dateTimeOfTravel
	 */
	public LocalDateTime getDateTimeOfCardSwipe() {
		return dateTimeOfCardSwipe;
	}

	/**
	 * @param dateTimeOfCardSwipe the dateTimeOfTravel to set
	 */
	public void setDateTimeOfCardSwipe(LocalDateTime dateTimeOfCardSwipe) {
		this.dateTimeOfCardSwipe = dateTimeOfCardSwipe;
	}

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
	}

	/**
	 * @return the traveler
	 */
	public Traveller getTraveller() {
		return traveller;
	}

	/**
	 * @param traveller the traveler to set
	 */
	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}

	@Override
	public String toString() {
		return "CardSwipe [dateTimeOfCardSwipe=" + dateTimeOfCardSwipe + ", zone=" + zone + ", traveller=" + travellerId + "]";
	}
	
}