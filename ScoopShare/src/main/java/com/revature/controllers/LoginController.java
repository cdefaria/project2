package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value="/login")
public class LoginController {

	static {
		System.out.println("[DEBUG] - In LoginController");
	}
	
	@Autowired
	private UserService userService;

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> login(@RequestBody User u) {
		System.out.println("[DEBUG] - In LoginController.login()");
		User found = userService.login(u.getUsername(), u.getPassword());
		
		
		if (found == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			
			return new ResponseEntity<User>(HttpStatus.ACCEPTED);
		}
	}
}
