package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Interest;

public class InterestRepository {

	static {
		System.out.println("[DEBUG] - InterestRepository instantiated...");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Interest> getAll() {
		
		System.out.println("[DEBUG] - InterestRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Article", Interest.class).getResultList();
	}
	
	public Interest getById(int id) {
		
		System.out.println("[DEBUG] - InterestRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Interest.class, id);
		
	}
	
	public Interest addInterest(Interest newInterest) {
		
		System.out.println("[DEBUG] - In InterestRepository.addInterest()...");
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(newInterest);
		return newInterest;
	}
	
	public Interest updateInterest(Interest updatedInterest) {
		
		System.out.println("[DEBUG] - In InterestRepository.updateInterest()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Interest interest = currentSession.get(Interest.class, updatedInterest.getInterestId());
		
		if(interest == null) {
			return interest;
		}
		
		interest = updatedInterest;
		return interest;
	}
	
	public int deleteInterest(int id) {
		
		System.out.println("[DEBUG] - In deleteInterest.deleteInterest()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Interest interest = currentSession.get(Interest.class, id);
		
		if (interest == null) {
			return -1;
		}
		
		currentSession.delete(interest);
		return 1;
	}
}
