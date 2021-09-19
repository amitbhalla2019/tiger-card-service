package com.example.demo.domain;

import static com.example.demo.util.TigerCardConstants.ZONE_X;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.NoTransactionException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.extra.YearWeek;

import com.example.demo.model.CardSwipe;
import com.example.demo.model.Traveller;
import com.example.demo.repository.PeakHoursRepository;
import com.example.demo.repository.CardSwipesRepository;
import com.example.demo.repository.TariffPlansRepository;
import com.example.demo.repository.TravellersRepository;

@RestController
@RequestMapping("/card-swipes")
public class CardSwipesController {
	
	@Autowired
	private CardSwipesRepository cardSwipesRepository;
	
	@Autowired
	private TravellersController travellersController;
	
	@Autowired
	private TariffPlansRepository tariffRatesRepository;
	
	@Autowired
	private PeakHoursRepository peakHoursRepository;
	
	@Autowired
	private TravellersRepository travellersRepository;
	
	@RequestMapping(value = "/all")
	public ResponseEntity<List<CardSwipe>> getTravellers() {
		if (cardSwipesRepository.count()==0) throw new NoTransactionException("No Card Swipes Found .... !");
		return new ResponseEntity<>(cardSwipesRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> updateTravellerTravellingRecord(@RequestBody CardSwipe currentTigerCardSwipe) {
		long travellerId = currentTigerCardSwipe.getTravellerId();
		CardSwipe cardSwipe = new CardSwipe(travellerId, currentTigerCardSwipe.getZone());
		cardSwipesRepository.save(cardSwipe);

		List<CardSwipe> cardSwipes = cardSwipesRepository.findAllCardSwipesMadeByTraveller(cardSwipe.getTraveller());
		int totalNumOfTimesTheTigerCardIsSwipedSoFar = cardSwipes.size();
		System.out.println("Total number of times the Tiger Card is swiped so far: "  + totalNumOfTimesTheTigerCardIsSwipedSoFar);
		boolean itIsTheStarteOfTheJourney = totalNumOfTimesTheTigerCardIsSwipedSoFar % 2 != 0;

		if (itIsTheStarteOfTheJourney) {
			return new ResponseEntity<>("Metro-Service welcomes you [" + travellerId + "] !.... We wish you a very happy and safe journey !.... "
					+ "\nTravelling Record is inserted successfully and it's logged as the start of the journey", HttpStatus.OK);
		}

		CardSwipe rideDetailsAboutFromWhereTheJourneyWasStarted = cardSwipes.get(totalNumOfTimesTheTigerCardIsSwipedSoFar-2);
		System.out.println("START:\n" + rideDetailsAboutFromWhereTheJourneyWasStarted);
		System.out.println("END:\n" + cardSwipe);
		
		LocalDateTime dateTimeAtTheStartOfTheJourney = rideDetailsAboutFromWhereTheJourneyWasStarted.getDateTimeOfCardSwipe();
		String travelIsMadeBetweenWhichTwoZones = determineTheZoneFactor(
				rideDetailsAboutFromWhereTheJourneyWasStarted.getZone(), cardSwipe.getZone());
		System.out.println("Travel is made between which two zones [Either Zone-1, Zone-2 or Across These Two (Zone-X): " 
				+ travelIsMadeBetweenWhichTwoZones);
		
		double costOfRide = calculateTheRateForTheRide(dateTimeAtTheStartOfTheJourney, travelIsMadeBetweenWhichTwoZones);
		System.out.println("Ride is going to cost you Rs. " + costOfRide);
		System.out.println("Wait !... Let's check if we can offer you any rebate on the basis of Daily-Cap or Weekly-Cap, if you have attained");

		Traveller traveller =  travellersRepository.findById(travellerId).get();
		System.out.println("Traveller Details: " + traveller);
		int totalAmountSpentOnAllTravelsDuringTheDay =traveller.getMoneySpentOnRidesOverTheCurrentDay();
		int totalAmountSpentOnAllTravelsDuringTheWeek = traveller.getMoneySpentOnRidesOverTheCurrentWeek();
		String zoneOfTravelToConsiderForDailyCap =  traveller.getZoneOfTravelToConsiderForDailyCap();
		String zoneOfTravelToConsiderForWeeklyCap =  traveller.getZoneOfTravelToConsiderForWeeklyCap();
		zoneOfTravelToConsiderForDailyCap = zoneOfTravelToConsiderForDailyCap == null ? travelIsMadeBetweenWhichTwoZones
				: zoneOfTravelToConsiderForDailyCap;
		zoneOfTravelToConsiderForWeeklyCap = zoneOfTravelToConsiderForWeeklyCap == null ? travelIsMadeBetweenWhichTwoZones
				: zoneOfTravelToConsiderForWeeklyCap;
	
		int aggregatedDailySpents = (int) costOfRide;
		int aggregatedWeeklySpents = aggregatedDailySpents;
		
		int dailyCap = tariffRatesRepository.findById(1).get().getDailyPriceCapForZone(travelIsMadeBetweenWhichTwoZones);
		int weeklyCap = tariffRatesRepository.findById(1).get().getWeeklyPriceCapForZone(travelIsMadeBetweenWhichTwoZones);
		
		int dailyCapForAlreadyStoredZone = tariffRatesRepository.findById(1).get().getDailyPriceCapForZone(zoneOfTravelToConsiderForDailyCap);
		int weeklyCapForAlreadyStoredZone = tariffRatesRepository.findById(1).get().getWeeklyPriceCapForZone(zoneOfTravelToConsiderForWeeklyCap);

		if (dailyCap > dailyCapForAlreadyStoredZone) {
			zoneOfTravelToConsiderForDailyCap = travelIsMadeBetweenWhichTwoZones;
		} else {
			dailyCap = dailyCapForAlreadyStoredZone;
		}
		
		if (weeklyCap > weeklyCapForAlreadyStoredZone) {
			zoneOfTravelToConsiderForWeeklyCap = travelIsMadeBetweenWhichTwoZones;
		} else {
			weeklyCap = weeklyCapForAlreadyStoredZone;
		}
		
		if (totalNumOfTimesTheTigerCardIsSwipedSoFar >= 4) {
			CardSwipe rideDetailsAboutThePreviousCompletedRideStartSwipe = cardSwipes.get(totalNumOfTimesTheTigerCardIsSwipedSoFar-3);
			if (rideDetailsAboutThePreviousCompletedRideStartSwipe.getDateTimeOfCardSwipe().toLocalDate()
					.equals(dateTimeAtTheStartOfTheJourney.toLocalDate())) {
				aggregatedDailySpents = (int) (totalAmountSpentOnAllTravelsDuringTheDay + costOfRide);
				if ( aggregatedDailySpents > dailyCap) {
					costOfRide = dailyCap - totalAmountSpentOnAllTravelsDuringTheDay;
					costOfRide = costOfRide < 0 ? 0 : costOfRide;
					System.out.println("Great !... You just won the rebate over attaining the Daily Limit Cap [" + dailyCap + "]"
							+ "\nYour effective cost of Travel is brought down to Rs. " + costOfRide);
					aggregatedDailySpents = dailyCap;
				} else {
					System.out.println("Daily-Cap [" + dailyCap + "] Not Applicable as yet. Sorry ... ! It's yet: " + aggregatedDailySpents);
				}
				aggregatedWeeklySpents = aggregatedDailySpents;
			} else {
				if (YearWeek.from(rideDetailsAboutThePreviousCompletedRideStartSwipe.getDateTimeOfCardSwipe().toLocalDate())
						.equals(YearWeek.from(dateTimeAtTheStartOfTheJourney.toLocalDate())) ) {
						aggregatedWeeklySpents = (int) (totalAmountSpentOnAllTravelsDuringTheWeek + costOfRide);
				}
			}
		}

		if ( aggregatedWeeklySpents > weeklyCap) {
			costOfRide = weeklyCap - totalAmountSpentOnAllTravelsDuringTheWeek;
			costOfRide = costOfRide < 0 ? 0 : costOfRide;
			System.out.println("Great !... You just won the rebate over attaining the Weekly Limit Cap [" + weeklyCap + "]"
					+ "\nYour effective cost of Travel is brought down to Rs. " + costOfRide);
			aggregatedWeeklySpents = weeklyCap;
		} else {
			System.out.println("Weekly-Cap [" + weeklyCap + "] Not Applicable as yet. Sorry ... ! It's yet: " + aggregatedWeeklySpents);
		}
		
		traveller.setMoneySpentOnRidesOverTheCurrentDay(aggregatedDailySpents);
		traveller.setMoneySpentOnRidesOverTheCurrentWeek(aggregatedWeeklySpents);
		traveller.setZoneOfTravelToConsiderForDaillyCap(zoneOfTravelToConsiderForDailyCap);
		traveller.setZoneOfTravelToConsiderForWeeklyCap(zoneOfTravelToConsiderForWeeklyCap);
		
		travellersRepository.save(traveller);

		travellersController.updateTravellerRemainingBalance(traveller, costOfRide);
		return new ResponseEntity<>("We hope we were able to serve you better, see you again soon !... "
				+ "\nTraveller travelling record is saved successfully", HttpStatus.OK);
	}

	private int calculateTheRateForTheRide(LocalDateTime startDateTimeOfTheTravel, String zoneDeterminer) {
		return peakHoursRepository.findById(1).get().isRideBeingMadeBetweenPeakHours(startDateTimeOfTheTravel)?
				tariffRatesRepository.findById(1).get().getPeakHoursPriceForZone(zoneDeterminer)
			  : tariffRatesRepository.findById(1).get().getNonPeakHoursPriceForZone(zoneDeterminer);
	}
	
	private String determineTheZoneFactor(String zoneFirst, String zoneSecond) {
		System.out.println("Starting Point: " + zoneFirst +	" | Ending Point: " + zoneSecond);
		return zoneFirst.equals(zoneSecond) ? 	zoneSecond : ZONE_X;
	}

}