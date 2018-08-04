package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Article;
import com.revature.beans.UserComment;
import com.revature.repositories.ArticleRepository;

@Service
@Transactional
public class ArticleService {
	
	static {
		System.out.println("[DEBUG] - Article instantiated...");
	}
	
	@Autowired
	ArticleRepository articleRepo;
	
	public List<Article> getAll() {
		System.out.println("[DEBUG] - In ArticleService.getAll()...");
		return articleRepo.getAll();
	}
	
	public Article getById(int Id) {
		System.out.println("[DEBUG] - In ArticleService.geById()...");
		return articleRepo.getById(Id);
	}
	
	public Article addArticle(Article newArticle) {
		System.out.println("[DEBUG] - In ArticleService.addArticle()...");
		return articleRepo.addArticle(newArticle);
	}
	
	public Article updateArticle(Article updatedArticle) {
		System.out.println("[DEBUG] - In ArticleService.updatedArticle()...");
		return articleRepo.updateArticle(updatedArticle);
	}
	
	public UserComment addComment(Article article, UserComment comment) {
		System.out.println("[DEBUG] - In ArticleService.addComment...");
		return articleRepo.addComment(article, comment);
	}
}
