package com.cooksys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Itinerary;
import com.cooksys.entity.MyFlight;
import com.cooksys.pojo.Flight;
import com.cooksys.repository.ItineraryRepository;

@Service
public class ItineraryService {
	
	@Autowired
	ItineraryRepository repo;
	
	@Autowired
	FlightService flightService;
	
	public List<Itinerary> getAll()
	{
		return repo.findAll();
	}
	
	public Itinerary saveItinerary(Itinerary itinerary) {
		return repo.save(itinerary);
	}
	
	public List<MyFlight> findItinerary(String origin, String destination) {
		List<Itinerary> itinList = new ArrayList<Itinerary>();
		List<Flight> flightList = flightService.getDailyFlightList();
		Itinerary currItin = new Itinerary();
		List<MyFlight> myFlightList = new ArrayList<MyFlight>();
		
		for (Flight flight : flightList) {
			if (origin.compareTo(flight.getOrigin()) == 0 && destination.compareTo(flight.getDestination()) == 0) {
				MyFlight currFlight = new MyFlight();
				currFlight.setOrigin(flight.getOrigin());
				currFlight.setDestination(flight.getDestination());
				currFlight.setFlightOffset(flight.getOffset());
				currFlight.setFlightTime(flight.getFlightTime());
				myFlightList.add(currFlight);
			} else {
				if (origin.compareTo(flight.getOrigin()) == 0) {
					myFlightList = checkForTransfer(myFlightList, flightList, flight, destination);
				}
			}
		}
		currItin.setFlights(myFlightList);
		itinList.add(currItin);
		
		return myFlightList;
	}
	
	public List<MyFlight> checkForTransfer(List<MyFlight> myFlightList, List<Flight> flightList, Flight flight, String destination) {
		for (Flight flightConnect : flightList) {
			if (flight.getDestination().compareTo(flightConnect.getOrigin()) == 0 && 
					flightConnect.getDestination().compareTo(destination) == 0 &&
					(flight.getOffset() + flight.getFlightTime()) < flightConnect.getOffset()) {
				MyFlight currFlight = new MyFlight();
				currFlight.setOrigin(flight.getOrigin());
				currFlight.setDestination(flight.getDestination());
				currFlight.setConnection(flight.getDestination());
				currFlight.setFlightOffset(flight.getOffset());
				currFlight.setFlightTime(flight.getFlightTime());
				myFlightList.add(currFlight);
				MyFlight currFlightConnect = new MyFlight();
				currFlightConnect.setOrigin(flightConnect.getOrigin());
				currFlightConnect.setDestination(flightConnect.getDestination());
				currFlightConnect.setFlightOffset(flightConnect.getOffset());
				currFlightConnect.setFlightTime(flightConnect.getFlightTime());
				myFlightList.add(currFlightConnect);
			}
		}
		
		return myFlightList;
	}
 
}
