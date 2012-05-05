package org.cvut.wa2.projectcontrol.entities;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class CompositeTask extends Task {
	
	@Persistent
	private Team owner;
	
	@Persistent
	private TeamMember responsible;
	
	@Persistent
	private ArrayList<Task> subtasks;
	
	@Persistent
	protected String docLink;

	public String getDocLink() {
		return docLink;
	}

	public void setDocLink(String docLink) {
		this.docLink = docLink;
	}

	public CompositeTask() {}
	
	public CompositeTask(Team owner, TeamMember responsible,
			ArrayList<Task> subtasks, String docLink) {
		super();
		this.owner = owner;
		this.responsible = responsible;
		this.subtasks = subtasks;
	}

	public Team getOwner() {
		return owner;
	}

	public void setOwner(Team owner) {
		this.owner = owner;
	}

	public TeamMember getResponsible() {
		return responsible;
	}

	public void setResponsible(TeamMember responsible) {
		this.responsible = responsible;
	}

	public ArrayList<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(ArrayList<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	

}
