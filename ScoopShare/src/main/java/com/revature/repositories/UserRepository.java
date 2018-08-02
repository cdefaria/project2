package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Interest;
import com.revature.beans.User;

@Repository
public class UserRepository {
	static {
		System.out.println("[DEBUG] - UserRepository instantiated");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	// Just for testing
	public List<User> getAll() {
		System.out.println("[DEBUG] - In UserRepository.getAll()...");
		Session s  = sessionFactory.getCurrentSession();
		return s.createQuery("from User", User.class).getResultList();
	}
	
	public User getById(int id) {
		System.out.println("[DEBUG] - In UserRepository.getById()...");
		Session s = sessionFactory.getCurrentSession();
		return s.get(User.class, id);
	}
	
	@SuppressWarnings("unused")
	public User login(String username, String password) {
		System.out.println("[DEBUG] - In UserRepository.login()...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<User> user= currentSession.createQuery("from User user Where user.username=:username AND user.password=:password")
				.setParameter("username", username).setParameter("password", password).getResultList();
		
		if (user.isEmpty()) {
			System.out.println("No user was found with those credentials so returning a null value");
			return null;
		} else {
	       //currentSession.			
	       return user.remove(0);
		}	
	}
	
	public User addUser(User u) {
		System.out.println("[DEBUG] - In UserRepository.addUser()...");
		Session currentSession= sessionFactory.getCurrentSession();
		
		String username = u.getUsername();
		String email = u.getEmail();
		
		List<User> user= currentSession.createQuery("from User user Where user.username=:username")
				.setParameter("username", username).getResultList();
		
		List<User> user2= currentSession.createQuery("from User user Where user.email=:email")
				.setParameter("email", email).getResultList();
		
		if (user.isEmpty()) {
			System.out.println("Username is available");
			
			if(user2.isEmpty()) {
				System.out.println("Email is available");
				currentSession.save(u);
				return u;
			} else {
				System.out.println("Email is NOT available");
				u.setUserId(-2);
				return u;
			}
			
		} else {
			System.out.println("Username is NOT available");
			u.setUserId(-1);
			return u;
		}
			
	}
	
	public List<Interest> addInterest (Interest interest, User user) {
		System.out.println("[DEBUG] - In UserRepository.addInterest...");
		Session currentSession= sessionFactory.getCurrentSession();
		int userId = user.getUserId();
		String interestName = interest.getInterestName();
		
		User u = currentSession.get(User.class, userId);
		
		List<Interest> i = currentSession.createQuery("from Interest interest Where interest.interestName=:interestName")
				.setParameter("interestName", interestName).getResultList();
		
		if (i.isEmpty()) {
			System.out.println("Interest was not added properly");
			return null;
		}
		
		u.addInterest(i.remove(0));
		
		return u.getInterests();
	}
	
}
