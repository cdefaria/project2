package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Article;
import com.revature.beans.User;

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
		return session.createQuery("from Article", Article.class).getResultList();
	}

	public Article getById(int id) {

		System.out.println("[DEBUG] - ArticleRepository.getById...");
		Session session = sessionFactory.getCurrentSession();
		return session.get(Article.class, id);

	}

	public Article getByTitle(String title) {

		System.out.println("[DEBUG] - ArticleRepository.getByTitle...");
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Article> article = currentSession.createQuery("from Article article Where article.title=:title")
				.setParameter("title", title).getResultList();
		
		if (article.isEmpty()) {
			System.out.println("Article with the name " + title + " was not found");
			return null;
		} else {
			return article.remove(0);
		}	
	
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

}
