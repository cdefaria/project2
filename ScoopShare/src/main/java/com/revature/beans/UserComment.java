package com.revature.beans;

//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="USER_COMMENTS")
//@SequenceGenerator(name="userCommentSeq", sequenceName="USER_COMMENT_SEQ", allocationSize=1)
public class UserComment {

	@Id
	@Column(name="comment_id")
	//@GeneratedValue(strategy=GenerationType.IDENTITY, generator="userCommentSeq")
	private int commentId;

	@Column(name="u_comment")
	private String comments;

	@JoinColumn(name="user_id")
	private int userId;
	//	@ManyToOne(cascade = CascadeType.ALL)
	//	@JoinColumn(name="user_id")
	//	private User user; 
	//	
	@JoinColumn(name="article_id")
	private int articleId;
	//	@ManyToMany(mappedBy="instructor", cascade = CascadeType.ALL)
	//	@JoinColumn(name="article_id")
	//	private Set<Article> article;


	public UserComment() {}
	public UserComment(String comments, int userId, int articleId) {
		super();
		this.comments = comments;
		this.userId = userId;
		this.articleId = articleId;
	}
	public UserComment(int commentId, String comments, int userId, int articleId) {
		super();
		this.commentId = commentId;
		this.comments = comments;
		this.userId = userId;
		this.articleId = articleId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
		result = prime * result + commentId;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + userId;
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
		UserComment other = (UserComment) obj;
		if (articleId != other.articleId)
			return false;
		if (commentId != other.commentId)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserComment [commentId=" + commentId + ", comments=" + comments + ", userId=" + userId + ", articleId="
				+ articleId + "]";
	}






}
