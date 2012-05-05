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
	protected Status taskStatus;
	
	@Persistent
	protected String taskOwner;


	
	public Task() {}



	public Task(Key taskKey, String taskName, Date dateOfStartDelivery,
			Status taskStatus, String tm) {
		super();
		this.taskOwner = tm;
		this.taskName = taskName;
		this.taskKey = taskKey;
		this.dateOfStartDelivery = dateOfStartDelivery;
		this.taskStatus = taskStatus;
	}


	

	public String getTaskOwner() {
		return taskOwner;
	}



	public void setTaskOwner(String teamMemberOwner) {
		this.taskOwner = teamMemberOwner;
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





	public Status getTaskStatus() {
		return taskStatus;
	}



	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	
	
		
}
