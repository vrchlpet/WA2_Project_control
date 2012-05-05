package org.cvut.wa2.projectcontrol.DAO;

import java.util.ArrayList;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TeamDAO {

	
	
	
	public static boolean updateTeam(Team team) {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		manager.makePersistent(team);
		
		return true;
	}
	
	
	public static Team getTeam(String teamName) {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		if (teamName == null) return null;
		
		teamName = teamName.trim();
		
		if (teamName.length() == 0) return null;
		
		Team team = null;
		
		try {
			team = manager.getObjectById(Team.class, teamName);
		} catch (JDOObjectNotFoundException e) {
			return null;
		}
		
		
		return team;
	}
	
	public static boolean createNewTeam(String teamName) {
		
		if (teamName == null) return false;
		
		teamName = teamName.trim();
		
		if (teamName.length() == 0) return false;
		
		Team team = getTeam(teamName);
		
		if (team != null) {
			return false;
		}
		
		team = new Team();
		team.setName(teamName);
		team.setMembers(new ArrayList<TeamMember>());
		Key key1 = KeyFactory.createKey(Team.class.getSimpleName(),teamName);
		team.setTeamKey(key1);
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		manager.makePersistent(team);
		
		return true;
	}
	
	
	
	
}
