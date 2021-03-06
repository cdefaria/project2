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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Component
@Table(name="USERS",
uniqueConstraints=
@UniqueConstraint(columnNames={"username","email"})
		)
@SequenceGenerator(name="userSeq", sequenceName="USER_SEQ", allocationSize=1)
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="userSeq")
	private int userId;

	@NotNull
	@Column(name="username",nullable=false)
	private String username;

	@NotNull
	@Column(name="password",nullable=false)
	private String password;

	@NotNull
	@Email
	@Column(name="email",nullable=false)	
	private String email;

	@NotNull
	@Column(name="firstname",nullable=false)
	private String firstname;

	@NotNull
	@Column(name="lastname",nullable=false)
	private String lastname;

	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<UserComment> userComments;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Rating> ratings;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinTable(
			name="USER_INTERESTS",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="interest_id")
	)
	private List<Interest> interests;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	
	@JoinTable(
			name="FAVORITES",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="article_id")
	)
	private List<Article> favorites;

	public User() {
		System.out.println("[DEBUG] - User instantiated...");
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String email, String firstname, String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(int userId, String username, String password, String email, String firstname, String lastname) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<UserComment> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<UserComment> userComments) {
		this.userComments = userComments;
	}
	
	// add comment to user
	public void addUserComments(UserComment userComment) {
		if (userComments == null) {
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
	
	// add rating to user
	public void addRating(Rating rating) {
		if (ratings == null) {
			ratings = new ArrayList<>();
		}
		  
		ratings.add(rating);
	}

	public List<Article> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Article> favorites) {
		this.favorites = favorites;
	}
	
	// add favorites to user
	public void addFavorites(Article favorite) {
		if (favorites == null) {
			favorites = new ArrayList<>();
		}
		  
		favorites.add(favorite);
	}
	
	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	// add interest to user
	public void addInterest(Interest interest) {
		if (interests == null) {
			interests = new ArrayList<>();
		}
		  
		interests.add(interest);
	}

	public boolean checkNull() {
		if (this.email==null || this.firstname==null || this.lastname==null || this.password==null || this.username==null) {
			System.out.println("something was null user = " + this);
			return true;
		}
		
		System.out.println("NOTHING was null user = " + this);
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
