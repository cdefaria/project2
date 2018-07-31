package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.User;
import com.revature.services.UserService;

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
		User user = userService.addUser(u);
		
		//System.out.println("user Id = " + user.getUser_id());
		if (user.getUser_id() == -1) {
			//System.out.println("working properly");
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<User>(HttpStatus.CREATED);
		
	}
}
