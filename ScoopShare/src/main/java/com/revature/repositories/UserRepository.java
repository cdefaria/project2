package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public User addUser(User u) {
		System.out.println("[DEBUG] - In UserRepository.addUser()...");
		Session currentSession= sessionFactory.getCurrentSession();
		
		String username = u.getUsername();
		String password = u.getPassword();
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
				u.setUser_id(-2);
				return u;
			}
			
		} else {
			System.out.println("Username is NOT available");
			u.setUser_id(-1);
			return u;
		}
		
		
		
		//currentSession.save(u);
		//return u;
	}
	
}
