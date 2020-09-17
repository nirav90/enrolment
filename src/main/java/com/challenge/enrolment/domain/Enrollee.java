package com.challenge.enrolment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Enrollee")
public class Enrollee {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrolleeId;
	
	private String name;
	
    private boolean status;
    private String phoneNo;
    

	public Long getEnrolleeId() {
		return enrolleeId;
	}

	public void setEnrolleeId(Long enrolleeId) {
		this.enrolleeId = enrolleeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
