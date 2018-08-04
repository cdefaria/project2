package com.revature.controllers;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Article;
import com.revature.services.ArticleService;

// don't send back vague codes such as: 200, 300, 400, and 500 if it can be avoided.
// user plural when giving value to RequestMapping
// 201- created
@CrossOrigin
@RestController
@RequestMapping(value="/articles")
public class ArticleController {
	
	static {
		System.out.println("[DEBUG] - ArticleController instatiated...");
	}
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Article>> getAllArticles() {
		System.out.println("[DEBUG] - In ArticleController.getAllArticles()...");
		List<Article> allArticles = articleService.getAll();
		System.out.println("Got all articles and back in ArticleController");
		
		System.out.println("Articles: ");
		for (Article list: allArticles) {
			System.out.println(list);
		}
		
		return new ResponseEntity<>(allArticles, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Article getArticleById(@PathVariable int Id) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
	    Article article = articleService.getById(Id);	
	    
	    if(article == null) {
	    	return null;//throw error here
	    }
	    
	    return article;
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> addArticle(@RequestBody Article newArticle) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
		Article article = articleService.addArticle(newArticle);
		return new ResponseEntity<Article>(article, HttpStatus.CREATED); 
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> updateArticle(@RequestBody Article updatedArticle) {
		System.out.println("[DEBUG] - In ArticleController.updateArticle()...");
		Article article = articleService.updateArticle(updatedArticle);
		
		if (article == null) {
			return null;//throw error here
		}
		
		return new ResponseEntity<Article>(HttpStatus.OK); //code 200
	}
}
