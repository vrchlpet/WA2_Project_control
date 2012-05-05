package org.cvut.wa2.projectcontrol.entities;

import java.util.Date;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Task {
	
	@PrimaryKey
	protected Key taskKey;
	
	@Persistent
	protected String taskName;
	
	@Persistent
	protected Status taskStatus;
	
	@Persistent
	protected String taskOwner;
	
	@Persistent
	protected String responsible;


	
	public Task() {}



	public Task(Key taskKey, String taskName,
			Status taskStatus, String tm, String responsible) {
		this.taskKey = taskKey;
		this.responsible = responsible;
		this.taskOwner = tm;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
	}


	

	public Key getTaskKey() {
		return taskKey;
	}



	public void setTaskKey(Key taskKey) {
		this.taskKey = taskKey;
	}



	public String getResponsible() {
		return responsible;
	}



	public void setResponsible(String responsible) {
		this.responsible = responsible;
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

	public Status getTaskStatus() {
		return taskStatus;
	}



	public void setTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	
	
		
}
