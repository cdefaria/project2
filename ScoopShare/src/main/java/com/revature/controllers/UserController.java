package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Article;
import com.revature.beans.User;
import com.revature.errors.InvalidParametersErrorResponse;
import com.revature.exceptions.InvalidParametersException;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UserController {
	static {
		System.out.println("[DEBUG] - In UserController");
	}
	
	@Autowired
	private UserService userService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAll() {
		System.out.println("[DEBUG] - In UserController.getAll()");
		return userService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getById(@PathVariable int id) {
		System.out.println("[DEBUG] - In UserController.getById()");
		User u;
		u = userService.getById(id);
		if(u == null) {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<User>(u, HttpStatus.OK);
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addUser(@RequestBody User u) {
		System.out.println("[DEBUG] - In UserController.addUser()");
		
		if (u.checkNull()) {
			System.out.println("Controller something was null");
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		
		User user = userService.addUser(u);
		
		System.out.println("user Id = " + user.getUserId());
		if (user.getUserId() == -1 || user.getUserId() == -2) {
			System.out.println("Conflict with username or password");
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<User>(HttpStatus.CREATED);
		
	}
	
	@PostMapping(value="/add-favorite",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Article>> addFavorite(@RequestBody String[] values){
		if(values.length == 2) {
			try {
				int userId = Integer.parseInt(values[0]);
				int favoriteId = Integer.parseInt(values[1]);

				List<Article> favorites = userService.addFavorite(favoriteId, userId);
				
				return new ResponseEntity<>(favorites,HttpStatus.ACCEPTED);
			} finally { } // TODO: Catch invalid Integer Parsing
		} else {
			throw new InvalidParametersException("String length was " + values.length + ", expecting a length of 2. [userId,articleId]");
		}
	}
	
	@PostMapping(value="/favorites",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Article>> getFavorites(@RequestBody String[] values){
		if(values.length == 1) {
			try {
				int userId = Integer.parseInt(values[0]);

				List<Article> favorites = userService.getFavorites(userId);
				
				return new ResponseEntity<>(favorites,HttpStatus.ACCEPTED);
			} finally { } // TODO: Catch invalid Integer Parsing
		} else {
			throw new InvalidParametersException("String length was " + values.length + ", expecting a length of 1. [userId]");
		}
	}
	
	@PostMapping(value="/email",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> sendEmail(@RequestBody String[] values) {
		if(values.length == 4) {
			int result = userService.sendEmail(values);
			if(result == 1) {
				return new ResponseEntity<String>("Email sent.",HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Email not sent.",HttpStatus.CONFLICT);
			}
		} else {
			throw new InvalidParametersException("String length was " + values.length + ", expecting a length of 4. [userEmail,reciepientEmail,subject,body]");
		}
	}
	@ExceptionHandler
	public ResponseEntity<InvalidParametersErrorResponse> invalidParameters(InvalidParametersException e) {
		InvalidParametersErrorResponse error = new InvalidParametersErrorResponse();
		
		error.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<InvalidParametersErrorResponse>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
