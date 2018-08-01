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

@Entity
@Table(name="USER_COMMENTS")
@SequenceGenerator(name="commentSeq", sequenceName="COMMENT_SEQ", allocationSize=1)
public class UserComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="commentSeq")
	private int commentId;

	@NotNull
	@ManyToOne(cascade={
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="user_id")
	private User user;

	@NotNull
	@ManyToOne(cascade={
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinColumn(name="article_id")
	private Article article;

	@NotNull
	@Column(name="u_comment")
	private String comments;

	public UserComment() {}

	public UserComment(int userId, int articleId, String comments) {
		super();
		this.user.setUserId(userId);
		this.article.setArticleId(articleId);
		this.comments = comments;
	}

	public UserComment(int commentId, int userId, int articleId, String comments) {
		super();
		this.commentId = commentId;
		this.user.setUserId(userId);
		this.article.setArticleId(articleId);
		this.comments = comments;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getUserId() {
		return user.getUserId();
	}

	public void setUserId(int userId) {
		this.user.setUserId(userId);
	}

	public int getArticleId() {
		return article.getArticleId();
	}

	public void setArticleId(int articleId) {
		this.article.setArticleId(articleId);
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
		result = prime * result + article.getArticleId();
		result = prime * result + commentId;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + this.user.getUserId();
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
		if (article.getArticleId() != other.article.getArticleId())
			return false;
		if (commentId != other.commentId)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (this.user.getUserId() != other.user.getUserId())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserComments [commentId=" + commentId + ", userId=" + user.getUserId() + ", articleId=" + article.getArticleId()
				+ ", comments=" + comments + "]";
	}

}
