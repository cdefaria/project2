package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="USER_COMMENTS")
public class UserComments {

	@Id
	@Column(name="comment_id")
	private int commentId;
	
	@JoinColumn(name="user_id")
	private int userId;
	
	@JoinColumn(name="article_id")
	private int articleId;
	
	@Column(name="u_comment")
	private String comments;
	
	public UserComments() {}
	
	public UserComments(int userId, int articleId, String comments) {
		super();
		this.userId = userId;
		this.articleId = articleId;
		this.comments = comments;
	}

	public UserComments(int commentId, int userId, int articleId, String comments) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.articleId = articleId;
		this.comments = comments;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
		UserComments other = (UserComments) obj;
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
		return "UserComments [commentId=" + commentId + ", userId=" + userId + ", articleId=" + articleId
				+ ", comments=" + comments + "]";
	}
	
}
