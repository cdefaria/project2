package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Article;
import com.revature.beans.Interest;
import com.revature.beans.Rating;
import com.revature.beans.User;
import com.revature.beans.UserComment;

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
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
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
	
	public UserComment addComment (User user, UserComment comment) {
		System.out.println("[DEBUG] - In UserRepository.addComment...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		User currentUser = currentSession.get(User.class, user.getUserId());
		UserComment addComment = currentSession.get(UserComment.class, comment.getCommentId());
		
		if (currentUser == null || addComment == null) {
			return null;
		}
		currentUser.addUserComments(addComment);
		return addComment; 
	}
	public List<Article> addFavorite (int favoriteId, int userId) {
		System.out.println("[DEBUG] - In UserRepository.addFavorite()...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		User u = currentSession.get(User.class, userId);
		Article favorite = currentSession.get(Article.class, favoriteId);
		
		Hibernate.initialize(u.getFavorites());
		
		List<Article> favorites = u.getFavorites();
		
		if(favorites == null) {
			favorites = new ArrayList<>();
		}
		
		favorites.add(favorite);
		
		return favorites;
	}
	
	public List<Article> getFavorites (int userId) {
		System.out.println("[DEBUG] - In User.Repository.getFavorites()...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		User u = currentSession.get(User.class, userId);
		
		Hibernate.initialize(u.getFavorites());
		
		List<Article> favorites = u.getFavorites();
		
		if(favorites == null) {
			return new ArrayList<>();
		}
		
		return favorites;
	}
	
	public Rating addRating (User user, Rating rating) {
		System.out.println("[DEBUG] - In UserRepository.addRating...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		User currentUser = currentSession.get(User.class, user.getUserId());
		Rating addRating = currentSession.get(Rating.class, rating.getRatingId());
		
		if (currentUser == null || addRating == null) {
			return null;
		}
		currentUser.addRating(addRating);
		return addRating; 
	}
	
}
