package com.revature.beans;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@Table(name="RATINGS")
@SequenceGenerator(name="ratingSeq", sequenceName="RATING_SEQ", allocationSize=1)
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name="rating_id",nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ratingSeq")
	private int ratingId;
	
	@Column(name="rating")
	private float rating;
	
	@JsonIgnore
	//@MapsId("userId")
	@NotNull
	@ManyToOne(cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="user_id",nullable=false)
	User user;
	
	@JsonIgnore
	//@MapsId("articleId")
	@NotNull
	@ManyToOne(cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="article_id",nullable=false)
	Article article;
	
	public Rating() {
		
	}

	public Rating(int rating) {
		super();
		this.rating = rating;
	}

	public Rating(int rating, @NotNull User user, @NotNull Article article) {
		super();
		this.rating = rating;
		this.user = user;
		this.article = article;
	}

	public int getRatingId() {
		return ratingId;
	}

//	public void setRatingId(int ratingId) {
//		this.ratingId = ratingId;
//	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		result = prime * result + Float.floatToIntBits(rating);
		result = prime * result + ratingId;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		if (ratingId != other.ratingId)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", rating=" + rating + ", user=" + user + ", article=" + article + "]";
	}
}
