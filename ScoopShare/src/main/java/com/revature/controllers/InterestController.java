package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Interest;
import com.revature.beans.User;
import com.revature.services.InterestService;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/interests")
public class InterestController {
	
	static {
		System.out.println("[DEBUG] - InterestController instatiated...");
	}
	
	@Autowired
	UserService userService;
	
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
	public ResponseEntity<Interest> /*List<Interest>*/addInterest(@RequestBody String[] newInterest) {
		System.out.println("[DEBUG] - In InterestController.getInterestById()...");
		 
		User user = new User();
		user.setUserId(Integer.parseInt(newInterest[0]));
		
		Interest interest = new Interest();
		interest.setInterestName(newInterest[1]);
		
		System.out.println("newInterest[0]: " + newInterest[0] + " and newInterest[1]: " + newInterest[1]);
		interestService.addInterest(interest);
		
		List<Interest> allUserInterest = userService.addInterest(interest, user);
		
		return new ResponseEntity<Interest>(interest, HttpStatus.CREATED); 
		//return allUserInterest;
	}

	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Interest> updateInterest(@RequestBody Interest updatedInterest) {
		System.out.println("[DEBUG] - In InterestController.updateInterest()...");
		Interest interest = interestService.updateInterest(updatedInterest);
		
		if (interest == null) {
			System.out.println("Interest field is empty.");
			return null;//throw error here
		}
		
		return new ResponseEntity<Interest>(updatedInterest, HttpStatus.OK); //code 200
	}
	
	@PostMapping(value="/get", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Interest> getUserInterest(@RequestBody String[] id) {
		System.out.println("[DEBUG] - In InterestController.getUserinterest()...");
			
		int userId = Integer.parseInt(id[0]);
		//System.out.println("User id: " + userId);
		
	    List<Interest> allInterest = interestService.getAllInterest(userId);
	    
	    if (allInterest == null) {
	    	//System.out.println("allInterest: " + allInterest);
	    	return null;
	    }
	    
	    //System.out.println("sending interest");
		return allInterest;
	}
	
}
