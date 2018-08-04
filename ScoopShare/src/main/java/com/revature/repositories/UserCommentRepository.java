package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Article;
import com.revature.beans.User;
import com.revature.beans.UserComment;

import org.springframework.stereotype.Repository;

@Repository
public class UserCommentRepository {
	
	static {
		System.out.println("[DEBUG] - ArticleRepository instantiated...");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<UserComment> getAll() {
		
		System.out.println("[DEBUG] - ArticleRepository.getAll...");
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from UserComment", UserComment.class).getResultList();
	}
	
	public UserComment getById(int id) {
		
		System.out.println("[DEBUG] - ArticleRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		return session.get(UserComment.class, id);
		
	}
	
	public UserComment addUserComment(String comment, int articleId, int userId) {
		
		System.out.println("[DEBUG] - In FlashCardRepository.addArticle()...");
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
		UserComment userComment = new UserComment();
		userComment.setComments(comment);
		
		userComment.addArticle(addArticle);
		userComment.addUser(addUser);
		
		currentSession.save(userComment);
		

		
		
		System.out.println("new UserComment was saved successfully");
		return userComment;
	}
	
	public UserComment updateUserComment (UserComment updatedUserComment) {
		
		System.out.println("[DEBUG] - In FlashCardRepository.updateArticle()...");
		Session currentSession = sessionFactory.getCurrentSession();
		UserComment userComment = currentSession.get(UserComment.class, updatedUserComment.getCommentId());
		
		if(userComment == null) {
			return userComment;
		}
		
		userComment = updatedUserComment;
		return userComment;
	}
	
}
