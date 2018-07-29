package com.revature.beans;

//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Unique;


import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="USERS")
@SequenceGenerator(name="userSeq", sequenceName="USER_SEQ", allocationSize=1)
public class User {	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="userSeq")
	private int user_id;
	
	@Column(name="username")
	private String username;
	
	@NotNull
	//@Unique
	@Email
	@Column(name="email")	
	private String email;

	
	@Column(name="firstname")
	private String firstname;

	
	@Column(name="lastname")
	private String lastname;

//	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
//	private Set<UserComment> userComment;
	
	
	
	public User() {
		System.out.println("[DEBUG] - User instantiated...");
	}

	public User(String username, String email, String firstname, String lastname) {
		super();
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(int user_id, String username, String email, String firstname, String lastname) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

//	public Set<UserComment> getUserComment() {
//		return userComment;
//	}

//	public void setUserComment(Set<UserComment> userComment) {
//		this.userComment = userComment;
//	}

	
}
