package org.cvut.wa2.projectcontrol.entities;

import java.util.ArrayList;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Team {

	@PrimaryKey
	private Key teamKey;
	
	@Persistent
	private String name;
	
	@Persistent(mappedBy = "team")
	private ArrayList<TeamMember> members;

	public Team() {}

	public Team(Key key, String name, ArrayList<TeamMember> members) {
		super();
		this.teamKey = key;
		this.name = name;
		this.members = members;
	}
	
	
	
	public Key getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(Key teamKey) {
		this.teamKey = teamKey;
	}

	public ArrayList<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<TeamMember> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
