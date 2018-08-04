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

import com.revature.beans.Rating;
import com.revature.services.RatingService;

@RestController
@RequestMapping(value="/ratings")
public class RatingController {
	
	static {
		System.out.println("[DEBUG] - RatingController instatiated...");
	}
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Rating> getAllRating() {
		System.out.println("[DEBUG] - In RatingController.getAllRating()...");
		return ratingService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Rating getRatingById(@PathVariable int Id) {
		System.out.println("[DEBUG] - In RatingController.getRatingById()...");
	    Rating rating = ratingService.getById(Id);	
	    
	    if(rating == null) {
	    	return null;//throw error here
	    }
	    
	    return rating;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> addRating(@RequestBody Rating newRating) {
		System.out.println("[DEBUG] - In RatingController.getRatingById()...");
		Rating rating = ratingService.addRating(newRating);
		return new ResponseEntity<Rating>(rating, HttpStatus.CREATED); 
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> updateRating(@RequestBody Rating updatedRating) {
		System.out.println("[DEBUG] - In ArticleController.updateArticle()...");
		Rating rating = ratingService.updateRating(updatedRating);
		
		if (rating == null) {
			return null;//throw error here
		}
		
		return new ResponseEntity<Rating>(HttpStatus.OK); //code 200
	}
	
	@GetMapping(value="/article-{articleId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Rating>> getByArticleId(@PathVariable int articleId) {
		System.out.println("[DEBUG] - In ArticleController.getByArticleId()...");
		List<Rating> ratings = ratingService.getByArticleId(articleId);
		
		if(ratings == null) {
			// Throw error here
			return null;
		}
		
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
	}
}
