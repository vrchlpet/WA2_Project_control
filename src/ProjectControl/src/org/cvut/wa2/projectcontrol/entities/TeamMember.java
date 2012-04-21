package org.cvut.wa2.projectcontrol.entities;

import java.util.ArrayList;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TeamMember {

	@PrimaryKey
	private Key teamMemberKey;
	
	@Persistent
	private Team team;
	
	@Persistent
	private String name;
	
	@Persistent
	private ArrayList<AccessToken> accessTokens;
	
	@Persistent(mappedBy="teamMemberOwner")
	private ArrayList<Task> tasks;


	public TeamMember() {}
	
	
	public TeamMember(Key teamMemberKey, Team team, String name,
			ArrayList<AccessToken> accessTokens, ArrayList<Task> tasks) {
		super();
		this.tasks = tasks;
		this.teamMemberKey = teamMemberKey;
		this.team = team;
		this.name = name;
		this.accessTokens = accessTokens;
	}


	public ArrayList<Task> getTasks() {
		return tasks;
	}


	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}


	public Key getTeamMemberKey() {
		return teamMemberKey;
	}


	public void setTeamMemberKey(Key teamMemberKey) {
		this.teamMemberKey = teamMemberKey;
	}


	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<AccessToken> getAccessTokens() {
		return accessTokens;
	}


	public void setAccessTokens(ArrayList<AccessToken> accessTokens) {
		this.accessTokens = accessTokens;
	}
	
	
}
