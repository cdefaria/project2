package com.revature.beans;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="INTERESTS")
@SequenceGenerator(name="interestSeq", sequenceName="INTEREST_SEQ", allocationSize=1)
public class Interest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "interest_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="interestSeq")
	private int interestId;
	
	@Column(name = "interest_name",unique=true)
	private String interestName;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= {
			CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH
	})
	@JoinTable(
			name="USER_INTERESTS",
			joinColumns=@JoinColumn(name="interest_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> users;
	
	public Interest() {}
	
	public Interest(String interestName) {
		super();
		this.interestName = interestName;
	}

	public Interest(int interestId, String interestName) {
		super();
		this.interestId = interestId;
		this.interestName = interestName;
	}


	public int getInterestId() {
		return interestId;
	}


	public void setInterestId(int interestId) {
		this.interestId = interestId;
	}


	public String getInterestName() {
		return interestName;
	}

	public void setInterestName(String interestName) {
		this.interestName = interestName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interestId;
		result = prime * result + ((interestName == null) ? 0 : interestName.hashCode());
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
		Interest other = (Interest) obj;
		if (interestId != other.interestId)
			return false;
		if (interestName == null) {
			if (other.interestName != null)
				return false;
		} else if (!interestName.equals(other.interestName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interests [interestId=" + interestId + ", interestName=" + interestName + "]";
	}
	
}
