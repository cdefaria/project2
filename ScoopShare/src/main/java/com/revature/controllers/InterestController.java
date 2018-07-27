package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Interest;
import com.revature.services.InterestService;

@RestController
@RequestMapping(value="/interests")
public class InterestController {
	
	static {
		System.out.println("[DEBUG] - InterestController instatiated...");
	}
	
	@Autowired
	InterestService interestService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Interest> getAllInterest() {
		System.out.println("[DEBUG] - In InterestController.getAllInterest()...");
		return interestService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Interest getInterestById(@PathVariable int Id) {
		System.out.println("[DEBUG] - In InterestController.getInterestById()...");
		Interest interest = interestService.getById(Id);	
	    
	    if(interest == null) {
	    	return null;//throw error here
	    }
	    
	    return interest;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Interest> addInterest(@RequestBody Interest newInterest) {
		System.out.println("[DEBUG] - In InterestController.getInterestById()...");
		Interest interest = interestService.addInterest(newInterest);
		return new ResponseEntity<Interest>(interest, HttpStatus.CREATED); 
	}

	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Interest> updateInterest(@RequestBody Interest updatedInterest) {
		System.out.println("[DEBUG] - In InterestController.updateInterest()...");
		Interest interest = interestService.updateInterest(updatedInterest);
		
		if (interest == null) {
			return null;//throw error here
		}
		
		return new ResponseEntity<Interest>(HttpStatus.OK); //code 200
	}
	
}
