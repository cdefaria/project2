package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@Table(name="ARTICLES")
@SequenceGenerator(name="articleSeq", sequenceName="ARTICLE_SEQ", allocationSize=1)
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="article_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="articleSeq")
	private int articleId;
	
	@Column(name="title")
	private String title;

	@Column(name="description")
	private String description;

	@NotNull
	@Column(name="url",unique=true,nullable=false)
	private String url;
	
	@JsonIgnore
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	private List<UserComment> userComments;
	
	@JsonIgnore
	@OneToMany(mappedBy="article", cascade=CascadeType.ALL)
	private List<Rating> ratings;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinTable(
			name="FAVORITES",
			joinColumns=@JoinColumn(name="article_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> favorites;
	
	public Article() {
		System.out.println("[DEBUG] - Article instantiated...");
	}

	public Article(String title, String description, String url) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public Article(int articleId, String title, String description, String url) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<UserComment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
	
	// Add user comments
	public void addUserComments(UserComment userComment) {
		if(this.userComments == null) {
			userComments = new ArrayList<>();
		}
		
		userComments.add(userComment);
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	// Add ratings
	public void addRatings(Rating rating) {
		if(this.ratings == null) {
			ratings = new ArrayList<>();
		}
		
		ratings.add(rating);
	}

	public List<User> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<User> favorites) {
		this.favorites = favorites;
	}
	
	
	// Add favorites
	public void addFavorites(User favorite) {
		if(this.favorites == null) {
			favorites = new ArrayList<>();
		}
		
		favorites.add(favorite);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Article other = (Article) obj;
		if (articleId != other.articleId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=" + title + ", description=" + description + ", url="
				+ url + "]";
	}
}
