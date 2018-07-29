package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.User;
import com.revature.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	static {
		System.out.println("[DEBUG] - UserService instantiated...");
	}
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getAll() {
		System.out.println("[DEBUG] - In UserService.getAll()...");
		return userRepo.getAll();
	}
	
	public User getById(int id) {
		System.out.println("[DEBUG] - In UserService.getById()...");
		return userRepo.getById(id);
	}
	
	public User addUser(User u) {
		System.out.println("[DEBUG] - In UserService.addUser()...");
		return userRepo.addUser(u);
	}
	
	public User login(String username, String password) {
		System.out.println("[DEBUG] - In UserService.login()...");
		return userRepo.login(username, password);
	}
}
