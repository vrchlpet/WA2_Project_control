package org.cvut.wa2.projectcontrol.entities;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Task {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key taskKey;
	
	@Persistent
	private TeamMember owner;
	
	@Persistent
	private TeamMember responsible;
	
	@Persistent
	private Date dateOfStartDelivery;
	
	@Persistent
	private String docLink;
	
	@Persistent
	private Status taskStatus;


	
	public Task() {}
	
	
	public Task(Key taskKey, TeamMember owner, TeamMember responsible,
			Date dateOfStartDelivery, String docLink, Status taskStatus) {
		super();
		this.taskKey = taskKey;
		this.owner = owner;
		this.responsible = responsible;
		this.dateOfStartDelivery = dateOfStartDelivery;
		this.docLink = docLink;
		this.taskStatus = taskStatus;
	}

	public Key getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(Key taskKey) {
		this.taskKey = taskKey;
	}

	public TeamMember getOwner() {
		return owner;
	}

	public void setOwner(TeamMember owner) {
		this.owner = owner;
	}

	public TeamMember getResponsible() {
		return responsible;
	}

	public void setResponsible(TeamMember responsible) {
		this.responsible = responsible;
	}

	public Date getDateOfStartDelivery() {
		return dateOfStartDelivery;
	}

	public void setDateOfStartDelivery(Date dateOfStartDelivery) {
		this.dateOfStartDelivery = dateOfStartDelivery;
	}

	public String getDocLink() {
		return docLink;
	}

	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	public Status getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}
	

	
	
	
	
}
