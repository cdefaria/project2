package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Article;
import com.revature.beans.Interest;
import com.revature.beans.Rating;
import com.revature.beans.User;
import com.revature.beans.UserComment;
import com.revature.repositories.UserRepository;
import com.revature.util.EmailUtility;

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
	
	public List<Interest> addInterest(Interest interest, User user) {
		System.out.println("[DEBUG] - In UserService.addInterest()...");
		return userRepo.addInterest(interest, user);
	}
	
	public UserComment addComment(User user, UserComment comment) {
		System.out.println("[DEBUG] - In UserService.addComment");
		return userRepo.addComment(user, comment);
	}
	
	public List<Article> addFavorite(int favoriteId, int userId) {
		System.out.println("[DEBUG] - In UserService.addFavorite()...");
		return userRepo.addFavorite(favoriteId, userId);
	}
	
	public List<Article> getFavorites(int userId) {
		System.out.println("[DEBUG] - In UserService.getFavorites()...");
		return userRepo.getFavorites(userId);
	}
	
	public int sendEmail(String[] values) {
		EmailUtility email = new EmailUtility();
		email.setTo(values[0]);
		email.setFrom(values[1]);
		email.setSubject(values[2]);
		email.setBody(values[3]);
		try {
			return email.sendEmail();
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
		
	public Rating addRating(User user, Rating rating) {
		System.out.println("[DEBUG] - In UserService.addRating");
		return userRepo.addRating(user, rating);
	}
}
