package com.cooksys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Itinerary;
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

}
