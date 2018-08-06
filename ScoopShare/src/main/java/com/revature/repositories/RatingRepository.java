package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.beans.Article;
import com.revature.beans.Rating;
import com.revature.beans.User;

@Repository
public class RatingRepository {

	static {
		System.out.println("[DEBUG] - RatingRepository instantiated...");
	}

	@Autowired
	SessionFactory sessionFactory;

	public List<Rating> getAll() {

		System.out.println("[DEBUG] - RatingRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Rating", Rating.class).getResultList();
	}

	public Rating getById(int id) {
		System.out.println("[DEBUG] - RatingRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Rating.class, id);
	}

	public Rating addRating(float rating, int articleId, int userId) {
		System.out.println("[DEBUG] - In RatingRepository.addRating()...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		//System.out.println("Article ID in Repository: " + articleId);
		Article addArticle = currentSession.get(Article.class, articleId);
		//System.out.println("Article: " + addArticle);
		
		
		User addUser = currentSession.get(User.class, userId);
		System.out.println("User: " + addUser);
		
		if (addArticle == null || addUser == null) {
			System.out.println("No ARTICLE or USER exist with those IDs");
			return null;
		}
		System.out.println("an Article and User was found");
		Rating userRating = new Rating();
		userRating.setRating(rating);
		
		userRating.setArticle(addArticle);
		userRating.setUser(addUser);
		
		
		currentSession.save(userRating);
		System.out.println("new Rating was saved successfully");
		return userRating;
	}

	public Rating updateRating(Rating updatedRating) {
		System.out.println("[DEBUG] - In RatingRepository.updateRating()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Rating rating = currentSession.get(Rating.class, updatedRating.getRatingId());

		if(rating == null) {
			return rating;
		}

		rating.setRating(updatedRating.getRating());
		return rating;
	}

	public List<Rating> getByArticleId(int articleId) {
		System.out.println("[DEBUG] - In RatingRepository.getByArticleId()...");
		Session currentSession = sessionFactory.getCurrentSession();

		Query query = currentSession.createQuery("from Rating r where r.articleId = ?");
		query.setParameter(0, articleId);

		@SuppressWarnings("unchecked")
		List<Rating> result = query.getResultList();

		if(result == null) {
			return new ArrayList<Rating>();
		}

		return result;
	}
	
	public List<Rating> getAllRatings(int id) {
		System.out.println("[DEBUG] - In UserCommentRepository.getAllRatings()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Article user = currentSession.get(Article.class, id);
		System.out.println("article: " + user);
		
		Hibernate.initialize(user.getRatings());//////////////////////////////////////
		List<Rating> allRatings = user.getRatings();
		
		if (allRatings.isEmpty()) {
			System.out.println("Article has no ratings so returning null");
			return null;
		}
		
		System.out.println("Sending found comments");
		return allRatings;
	}
}
