package com.revature.repositories;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Interest;
import com.revature.beans.User;

@Repository
public class InterestRepository {

	static {
		System.out.println("[DEBUG] - InterestRepository instantiated...");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Interest> getAll() {
		
		System.out.println("[DEBUG] - InterestRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Interest", Interest.class).getResultList();
	
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
		
		System.out.println("Interest found and updated");
		interest.setInterestName(updatedInterest.getInterestName());
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
	
	public List<Interest> getAllInterest(int id) {
		System.out.println("[DEBUG] - In InterestRepository.getAllInterest()...");
		Session currentSession = sessionFactory.getCurrentSession();
		User user = currentSession.get(User.class, id);
		System.out.println("user: " + user);
		
		List<Interest> allInterest = user.getInterests();
		
//		System.out.println("Interest List: ");
//	    for (Interest interest: allInterest) {
//	    	System.out.println(interest);
//	    }
		
		return allInterest;
	}
}
