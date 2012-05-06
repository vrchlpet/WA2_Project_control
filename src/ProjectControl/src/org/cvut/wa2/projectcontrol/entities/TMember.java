package org.cvut.wa2.projectcontrol.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class TMember {

	@PrimaryKey
	private Key tMemberKey;
	
	@Persistent
	private String name;
	
	@Persistent
	private Team team;
	
	public TMember() {}

	public Key gettMemberKey() {
		return tMemberKey;
	}

	public void settMemberKey(Key tMemberKey) {
		this.tMemberKey = tMemberKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
}
