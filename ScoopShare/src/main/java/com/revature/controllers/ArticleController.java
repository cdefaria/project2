package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Article;
import com.revature.beans.User;
import com.revature.services.ArticleService;

// don't send back vague codes such as: 200, 300, 400, and 500 if it can be avoided.
// user plural when giving value to RequestMapping
// 201- created
@RestController
@RequestMapping(value="/articles")
public class ArticleController {
	
	static {
		System.out.println("[DEBUG] - ArticleController instatiated...");
	}
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Article> getAllArticles() {
		System.out.println("[DEBUG] - In ArticleController.getAllArticles()...");
		return articleService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> getArticleById(@PathVariable int id) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
	    Article article = articleService.getById(id);	
	    
	    if(article == null) {
	    	System.out.println("article not found");
	    	//return null;//throw error here
	    	return new ResponseEntity<Article> (HttpStatus.NOT_FOUND);
	    }
	    
	    System.out.println("article found");
	    return new ResponseEntity<Article>(article, HttpStatus.OK);
	    //return article;
	}
	
	@PostMapping(value="/search", consumes=MediaType.TEXT_PLAIN_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> search(@RequestBody String findArticle) {
		System.out.println("[DEBUG] - In ArticleController.search()...");
		System.out.println("Trying to find: " + findArticle);
		
		Article article = articleService.getByTitle(findArticle);
		
		if (article == null) {
			System.out.println("No article was found with the name: " + findArticle);
			return new ResponseEntity<Article> (HttpStatus.NOT_FOUND);
		}
		System.out.println("Found article with the name: " + findArticle);
		return new ResponseEntity<Article> (article, HttpStatus.OK);
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
