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
			return user.remove(0);
		}	
	}
	
	public User addUser(User u) {
		System.out.println("[DEBUG] - In UserRepository.getById()...");
		Session s= sessionFactory.getCurrentSession();
		
		if (u.checkNull()) {
			System.out.println("userRepo Something was null!");
			return null;
		}
		
		System.out.println("userRepo nothing was null");
		s.save(u);
		return u;
	}
}
