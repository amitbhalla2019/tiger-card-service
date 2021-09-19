package com.example.demo.model;

import static com.example.demo.util.TigerCardConstants.END;
import static com.example.demo.util.TigerCardConstants.EVENING;
import static com.example.demo.util.TigerCardConstants.HOURS;
import static com.example.demo.util.TigerCardConstants.MINUTES;
import static com.example.demo.util.TigerCardConstants.MORNING;
import static com.example.demo.util.TigerCardConstants.START;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class PeakHours {
	
	@Id
	private int id;
	private Map<DayOfWeek, Map<String, Map<String, Map<String, Integer>>>> valuesDefined;
	
	public PeakHours() {
		id = 1;
		valuesDefined = Map.of(

				DayOfWeek.MONDAY, 		 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES, 7,   HOURS, 0), 
																										END,	     Map.of(MINUTES, 10, HOURS, 30)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 17, HOURS, 0), 
																										END,	     Map.of(MINUTES, 20, HOURS, 0))),
				DayOfWeek.TUESDAY, 		 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES, 7,   HOURS, 0), 
																										END,	     Map.of(MINUTES, 10, HOURS, 30)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 17, HOURS, 0), 
																										END,	     Map.of(MINUTES, 20, HOURS, 0))),
				DayOfWeek.WEDNESDAY, Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES, 7,   HOURS, 0), 
																										END,	     Map.of(MINUTES, 10, HOURS, 30)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 17, HOURS, 0), 
																										END,	     Map.of(MINUTES, 20, HOURS, 0))),
				DayOfWeek.THURSDAY, 	 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES, 7,   HOURS, 0), 
																										END,	     Map.of(MINUTES, 10, HOURS, 30)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 17, HOURS, 0), 
																										END,	     Map.of(MINUTES, 20, HOURS, 0))),
				DayOfWeek.FRIDAY, 		 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES, 7,   HOURS, 0), 
																										END,	     Map.of(MINUTES, 10, HOURS, 30)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 17, HOURS, 0), 
																										END,	     Map.of(MINUTES, 20, HOURS, 0))),
				DayOfWeek.SATURDAY, 	 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES,  9,  HOURS, 0), 
																										END,	     Map.of(MINUTES, 11, HOURS, 0)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 18, HOURS, 0), 
																										END,	     Map.of(MINUTES, 22, HOURS, 0))),
				DayOfWeek.SUNDAY, 		 Map.of(MORNING, 	Map.of( START,	 Map.of(MINUTES,  9,  HOURS, 0), 
																										END,	     Map.of(MINUTES, 11, HOURS, 0)),
																	EVENING, 	Map.of( START,	 Map.of(MINUTES, 18, HOURS, 0), 
																										END,	     Map.of(MINUTES, 22, HOURS, 0)))
				);
	}

	/**
	 * @param valuesDefined
	 */
	public PeakHours(int id, Map<DayOfWeek, Map<String, Map<String, Map<String, Integer>>>> valuesDefined) {
		super();
		this.id = id;
		this.valuesDefined = valuesDefined;
	}

	/**
	 * @return the valuesDefined
	 */
	public Map<DayOfWeek, Map<String, Map<String, Map<String, Integer>>>> getValuesDefined() {
		return valuesDefined;
	}

	/**
	 * @param valuesDefined the valuesDefined to set
	 */
	public void setValuesDefined(Map<DayOfWeek, Map<String, Map<String, Map<String, Integer>>>> valuesDefined) {
		this.valuesDefined = valuesDefined;
	}
	
	public boolean isRideBeingMadeBetweenPeakHours(LocalDateTime dateTimeOfTravel) {
		LocalTime time = dateTimeOfTravel.toLocalTime();
		DayOfWeek day = dateTimeOfTravel.getDayOfWeek();
		String morOrEve = time.isBefore(LocalTime.NOON) ? MORNING : EVENING;
		Map<String, Map<String, Integer>> values = valuesDefined.get(day).get(morOrEve);
		LocalTime peakHoursStartTime = LocalTime.of(values.get(START).get(HOURS), values.get(START).get(MINUTES));
		LocalTime peakHoursEndTime = LocalTime.of(values.get(END).get(HOURS), values.get(END).get(MINUTES));
		return (time.equals(peakHoursStartTime) 
				|| time.equals(peakHoursEndTime)
				|| (time.isAfter(peakHoursStartTime) && time.isBefore(peakHoursEndTime))
				);
	}

	@Override
	public String toString() {
		return "PeakHours [valuesDefined=" + valuesDefined + "]";
	}

}