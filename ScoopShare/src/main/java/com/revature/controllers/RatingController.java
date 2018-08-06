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

import com.revature.beans.Article;
import com.revature.beans.Rating;
import com.revature.beans.User;
import com.revature.services.ArticleService;
import com.revature.services.RatingService;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/ratings")
public class RatingController {
	
	static {
		System.out.println("[DEBUG] - RatingController instatiated...");
	}
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
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
	
	//rating, userId, and articleId
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rating> addRating(@RequestBody String [] info) {
		System.out.println("[DEBUG] - In RatingController.getRatingById()...");
		
		System.out.println("received: ");
		
		for (String s : info) {
			System.out.println(s);
		}
		
		int userId = Integer.parseInt(info[0]);
		int articleId = Integer.parseInt(info[1]);
		float rating = Float.parseFloat(info[2]); 
		
		Rating userRating = new Rating();
		userRating.setRating(rating);
		
		Article article = new Article();
		article.setArticleId(articleId);
		
		User user = new User();
		user.setUserId(userId);
		
		// add rating info
		 ratingService.addRating(rating, articleId, userId);
		System.out.println("Ratings added and back in userController");
		
		// add rating to article info.
		articleService.addRating(article, userRating);
		System.out.println("rating added to article");
		
		// add rating to user info.
		userService.addRating(user, userRating);
		System.out.println("rating added to user, last part of userController");
		return new ResponseEntity<Rating>(userRating, HttpStatus.CREATED); 
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
	
	@PostMapping(value="/get", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Rating> getRating(@RequestBody String[] id) { 
		System.out.println("[DEBUG] - In UserCommentController.getUserComment()...");

		int articleId = Integer.parseInt(id[0]);
		
		List<Rating> allRatings = ratingService.getAllRatings(articleId);
		
		if (allRatings == null) {
	    	System.out.println("allComments: " + allRatings);

			return null;
		}
		System.out.println("sending comments");
		return allRatings;
		
	}
}
