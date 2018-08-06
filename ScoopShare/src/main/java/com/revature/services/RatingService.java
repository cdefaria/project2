package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Article;
import com.revature.beans.Rating;
import com.revature.repositories.ArticleRepository;
import com.revature.repositories.RatingRepository;

@Service
@Transactional
public class RatingService {

	static {
		System.out.println("[DEBUG] - Rating instantiated...");
	}
	
	@Autowired
	RatingRepository ratingRepo;
	
	public List<Rating> getAll() {
		System.out.println("[DEBUG] - In RatingService.getAll()...");
		return ratingRepo.getAll();
	}
	
	public Rating getById(int Id) {
		System.out.println("[DEBUG] - In RatingService.geById()...");
		return ratingRepo.getById(Id);
	}
	
//	public Rating addRating(Rating newRating) {
//		System.out.println("[DEBUG] - In RatingService.addArticle()...");
//		return ratingRepo.addRating(newRating);
//	}
	
	public Rating updateRating(Rating updatedRating) {
		System.out.println("[DEBUG] - In RatingService.updatedRating()...");
		return ratingRepo.updateRating(updatedRating);
	}
	
	public List<Rating> getByArticleId(int articleId) {
		ArticleRepository articleRepo = new ArticleRepository();
		Article article = articleRepo.getById(articleId);
		System.out.println(article);
		if(article == null) {
			return null;
		} else {
			return ratingRepo.getByArticleId(articleId);
		}
	}
	
	public Rating addRating(float rating, int articleId, int userId) {
		System.out.println("[DEBUG] - In RatingService.addRating()...");
		return ratingRepo.addRating(rating, articleId, userId);
	}

	public List<Rating> getAllRatings(int id) {
		System.out.println("[DEBUG] - In RatingService()...");
		return ratingRepo.getAllRatings(id);
	}
}
