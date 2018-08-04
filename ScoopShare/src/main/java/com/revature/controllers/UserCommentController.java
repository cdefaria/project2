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
import com.revature.beans.UserComment;
import com.revature.services.ArticleService;
import com.revature.services.UserCommentService;
import com.revature.services.UserService;

// don't send back vague codes such as: 200, 300, 400, and 500 if it can be avoided.
// user plural when giving value to RequestMapping
// 201- created
@RestController
@RequestMapping(value="/comments")
public class UserCommentController {
	
	static {
		System.out.println("[DEBUG] - ArticleController instatiated...");
	}
	
	@Autowired
	private UserCommentService userCommentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserComment> getAll() {
		System.out.println("[DEBUG] - In ArticleController.getAllArticles()...");
		return userCommentService.getAll();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public UserComment getUserCommentById(@PathVariable int Id) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
	    UserComment userComment = userCommentService.getById(Id);	
	    
	    if(userComment == null) {
	    	return null;//throw error here
	    }
	    
	    return userComment;
	}
	
	// 
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserComment> addUserComment(@RequestBody String[] info) {
		System.out.println("[DEBUG] - In ArticleController.getArticleById()...");
		
		System.out.println("received: ");
//		for (String s : info) {
//			System.out.println(s);
//		}
		int userId = Integer.parseInt(info[0]);
		int articleId = Integer.parseInt(info[1]);
		String comment = info[2];
	
		UserComment userComment = new UserComment(); 
		userComment.setComments(comment);

		Article article = new Article();
		
		article.setArticleId(articleId);
		
		User user = new User();
		user.setUserId(userId);
		
		// add comment info.
		userCommentService.addUserComment(comment, articleId, userId);
		
		System.out.println("Back in userController");
		// add comment to article info.
		articleService.addComment(article, userComment);
		
		// add comment to user info.
		userService.addComment(user, userComment);
		System.out.println("last part of userController");
		return new ResponseEntity<UserComment>(userComment, HttpStatus.CREATED); 
	}
	
	@PostMapping(value="/get", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserComment> getUserComment(@RequestBody String[] id) { 
		System.out.println("[DEBUG] - In UserCommentController.getUserComment()...");

		int userId = Integer.parseInt(id[0]);
		
		List<UserComment> allComments = userCommentService.getAllUserComments(userId);
		
		if (allComments == null) {
	    	System.out.println("allComments: " + allComments);

			return null;
		}
		System.out.println("sending comments");
		return allComments;
		
	}
	
	
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserComment> updateUserComment(@RequestBody UserComment updatedUserComment) {
		System.out.println("[DEBUG] - In ArticleController.updateArticle()...");
		UserComment userComment = userCommentService.updateUserComment(updatedUserComment);
		
		if (userComment == null) {
			return null;//throw error here
		}
		
		return new ResponseEntity<UserComment>(HttpStatus.OK); //code 200
	}
}
