package org.cvut.wa2.projectcontrol.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class CompositeTask {
	
	@PrimaryKey
	private String taskName;
	
	@Persistent
	private String owner;
	
	@Persistent
	private ArrayList<Task> subtasks;
	
	@Persistent
	protected Date dateOfStartDelivery;
	
	@Persistent
	protected List<DocumentEntity> docEntity;


	public List<DocumentEntity> getDocEntity() {
		return docEntity;
	}

	public void setDocEntity(List<DocumentEntity> docEntity) {
		this.docEntity = docEntity;
	}

	public CompositeTask() {}
	
	public CompositeTask(String owner, String compositeTaskName, Date dateOfStartDelivery,
			ArrayList<Task> subtasks) {
		this.dateOfStartDelivery = dateOfStartDelivery;
		this.taskName = compositeTaskName;
		this.owner = owner;
		this.subtasks = subtasks;
	}

	public Date getDateOfStartDelivery() {
		return dateOfStartDelivery;
	}

	public void setDateOfStartDelivery(Date dateOfStartDelivery) {
		this.dateOfStartDelivery = dateOfStartDelivery;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String compositeTaskName) {
		this.taskName = compositeTaskName;
	}

	public ArrayList<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(ArrayList<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	

}
