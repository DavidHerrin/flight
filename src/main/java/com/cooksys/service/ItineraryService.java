package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.entity.Itinerary;
import com.cooksys.repository.ItineraryRepository;

@Service
public class ItineraryService {
	
	@Autowired
	ItineraryRepository repo;
	
	public List<Itinerary> getAll()
	{
		return repo.findAll();
	}
	
	public Itinerary saveItinerary(Itinerary itinerary) {
		return repo.save(itinerary);
	}

}
