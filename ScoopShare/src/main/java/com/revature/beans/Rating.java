package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@Table(name="RATINGS")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	UserArticles id;
	
	@Column(name="rating")
	private int rating;
	
	@JsonIgnore
	@MapsId("userId")
	@NotNull
	@ManyToOne(cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="user_id",nullable=false)
	User user;
	
	@JsonIgnore
	@MapsId("articleId")
	@NotNull
	@ManyToOne(cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="article_id",nullable=false)
	Article article;
	
	public Rating() {
		
	}

	public Rating(int userId, int articleId, int rating) {
		super();
		this.id.setUserId(userId);
		this.id.setArticleId(articleId);
		this.user.setUserId(userId);
		this.article.setArticleId(articleId);
		this.rating = rating;
	}

	public UserArticles getId() {
		return id;
	}

	public void setId(UserArticles id) {
		this.id = id;
	}

	public int getUserId() {
		return id.getUserId();
	}

	public void setUserId(int userId) {
		this.id.setUserId(userId);
	}

	public int getArticleId() {
		return id.getArticleId();
	}

	public void setArticleId(int articleId) {
		this.id.setArticleId(articleId);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rating;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		if (id.getArticleId() != other.id.getArticleId())
			return false;
		if (rating != other.rating)
			return false;
		if (id.getUserId() != other.id.getUserId())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ratings [userId=" + id.getUserId() + ", articleId=" + id.getArticleId() + ", rating=" + rating + "]";
	}
	
}
