package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Article;
import com.revature.beans.User;
import com.revature.beans.UserComment;
import com.revature.repositories.ArticleRepository;
import com.revature.repositories.UserCommentRepository;

@Service
@Transactional
public class UserCommentService {
	
	static {
		System.out.println("[DEBUG] - Article instantiated...");
	}
	
	@Autowired
	UserCommentRepository userCommentRepo;
	
	public List<UserComment> getAll() {
		System.out.println("[DEBUG] - In ArticleService.getAll()...");
		return userCommentRepo.getAll();
	}
	
	public UserComment getById(int Id) {
		System.out.println("[DEBUG] - In ArticleService.geById()...");
		return userCommentRepo.getById(Id);
	}
	
	public UserComment addUserComment(UserComment newUserComment, int articleId, int userId) {
		System.out.println("[DEBUG] - In ArticleService.addArticle()...");
		return userCommentRepo.addUserComment(newUserComment, articleId, userId);
	}
	
	public UserComment updateUserComment(UserComment updatedUserComment) {
		System.out.println("[DEBUG] - In ArticleService.updatedArticle()...");
		return userCommentRepo.updateUserComment(updatedUserComment);
	}
}
