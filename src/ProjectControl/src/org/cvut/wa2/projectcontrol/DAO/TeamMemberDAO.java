package org.cvut.wa2.projectcontrol.DAO;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import org.cvut.wa2.projectcontrol.entities.AccessToken;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TeamMemberDAO {

	
	
	
	
	public static boolean addTeamMember(String teamName, String teamMemberName) {
		if (teamMemberName == null) return false;

		teamMemberName = teamMemberName.trim();
		
		if (teamMemberName.length() == 0) return false;
		
		Team team = TeamDAO.getTeam(teamName);
		
		if (team == null) return false;
		
		TeamMember tm = new TeamMember();
		tm = new TeamMember();
		tm.setName(teamMemberName);
		Key key = KeyFactory.createKey(TeamMember.class.getSimpleName(),teamMemberName);
		tm.setTeamMemberKey(key);
		tm.setAccessTokens(new ArrayList<AccessToken>());
		tm.setTasks(new ArrayList<Task>());
		tm.setTeam(team);
		
		PersistenceManager manager = PMF.get();
		manager.makePersistent(tm);
		
		team.getMembers().add(tm);
		manager.makePersistent(team);
		
		
		return true;
	}
	
	
}
