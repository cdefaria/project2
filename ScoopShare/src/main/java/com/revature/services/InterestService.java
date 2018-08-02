package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.revature.beans.Article;
import com.revature.beans.Interest;
import com.revature.beans.User;
import com.revature.repositories.InterestRepository;

@Service
@Transactional
public class InterestService {
	
	static {
		System.out.println("[DEBUG] - InterestService instantiated...");
	}
	
	@Autowired
	InterestRepository interestRepo;
	
	public List<Interest> getAll() {
		System.out.println("[DEBUG] - In InterestService.getAll()...");
		return interestRepo.getAll();
	} 
	
	public Interest getById(int Id) {
		System.out.println("[DEBUG] - In InterestService.geById()...");
		return interestRepo.getById(Id);
	}
	
	public Interest addInterest(Interest newInterest) {
		System.out.println("[DEBUG] - In InterestService.addArticle()...");
		return interestRepo.addInterest(newInterest);
	}
	
	public Interest updateInterest(Interest updatedInterest) {
		System.out.println("[DEBUG] - In InterestService.updatedInterest()...");
		return interestRepo.updateInterest(updatedInterest);
	}
	
	public List<Interest> getAllInterest(int id) {
		System.out.println("[DEBUG] - In InterestService()...");
		return interestRepo.getAllInterest(id);
	}
}
