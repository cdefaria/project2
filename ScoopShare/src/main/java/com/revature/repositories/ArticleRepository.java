package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Article;
import com.revature.beans.UserComment;
import com.revature.beans.Rating;

import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {
	
	static {
		System.out.println("[DEBUG] - ArticleRepository instantiated...");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Article> getAll() {
		
		System.out.println("[DEBUG] - ArticleRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		List<Article> allArticles = session.createQuery("from Article", Article.class).getResultList();
		if (allArticles.isEmpty()) {
			return null;
		}
		
		return allArticles;
	}
	
	public Article getById(int id) {
		
		System.out.println("[DEBUG] - ArticleRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		System.out.println(id);
		return session.get(Article.class, id);
		
	}
	
	public Article addArticle(Article newArticle) {
		
		System.out.println("[DEBUG] - In FlashCardRepository.addArticle()...");
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(newArticle);
		return newArticle;
	}
	
	public Article updateArticle(Article updatedArticle) {
		
		System.out.println("[DEBUG] - In FlashCardRepository.updateArticle()...");
		Session currentSession = sessionFactory.getCurrentSession();
		Article article = currentSession.get(Article.class, updatedArticle.getArticleId());
		
		if(article == null) {
			return article;
		}
		
		article = updatedArticle;
		return article;
	}
	
	public UserComment addComment(Article article, UserComment comment) {
		System.out.println("[DEBUG] - In ArticleRepository.addComment...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		Article currentArticle = currentSession.get(Article.class, article.getArticleId());
		UserComment addComment = currentSession.get(UserComment.class, comment.getCommentId());
		
		if (currentArticle == null || addComment == null) {
			return null;
		}
		currentArticle.addUserComments(addComment);
		return addComment;
	}
	
	public Rating addRating(Article article, Rating rating) {
		System.out.println("[DEBUG] - In ArticleRepository.addRating...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		Article currentArticle = currentSession.get(Article.class, article.getArticleId());
		Rating addRating = currentSession.get(Rating.class, rating.getRatingId());
		
		if (currentArticle == null || addRating == null) {
			return null;
		}
		currentArticle.addRating(addRating);
		return addRating;
	}
}
