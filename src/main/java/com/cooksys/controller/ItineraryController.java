package com.cooksys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Itinerary;
import com.cooksys.entity.MyFlight;
import com.cooksys.pojo.Flight;
import com.cooksys.service.ItineraryService;

@RestController
@RequestMapping("itinerary")
@CrossOrigin
public class ItineraryController {
	
	@Autowired
	private ItineraryService itineraryService;

	@RequestMapping
	public List<Itinerary> get() {
		return itineraryService.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Itinerary create(@RequestBody Itinerary itinerary) {
		return itineraryService.saveItinerary(itinerary);
	}
	
	
	@RequestMapping(value = "/{city1}/{city2}", method = RequestMethod.GET)
	public List<MyFlight> find( 
		@RequestParam String origin, 
		@RequestParam String destination,
		HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_CREATED);
		return itineraryService.findItinerary(origin, destination);
	}

}
