package org.cvut.wa2.projectcontrol.entities;

import java.util.Date;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Discriminator(strategy = DiscriminatorStrategy.CLASS_NAME)
public class Task {
	
	@PrimaryKey
	protected Key taskKey;
	
	@Persistent
	protected String taskName;
	
	@Persistent
	protected Date dateOfStartDelivery;
	
	@Persistent
	protected String docLink;
	
	@Persistent
	private Status taskStatus;


	
	public Task() {}



	public Task(Key taskKey, String taskName, Date dateOfStartDelivery, String docLink,
			Status taskStatus) {
		super();
		this.taskName = taskName;
		this.taskKey = taskKey;
		this.dateOfStartDelivery = dateOfStartDelivery;
		this.docLink = docLink;
		this.taskStatus = taskStatus;
	}


	

	public String getTaskName() {
		return taskName;
	}



	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}



	public Key getTaskKey() {
		return taskKey;
	}



	public void setTaskKey(Key taskKey) {
		this.taskKey = taskKey;
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
