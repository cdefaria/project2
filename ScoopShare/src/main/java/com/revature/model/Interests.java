package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERESTS")
public class Interests {
	
	@Id
	@Column(name = "interest_id")
	private int interestId;
	
	@Column(name = "interest_name")
	private String interestName;
	
	
	public Interests() {}

	
	
	public Interests(String interestName) {
		super();
		this.interestName = interestName;
	}



	public Interests(int interestId, String interestName) {
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
		Interests other = (Interests) obj;
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
