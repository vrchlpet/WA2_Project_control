package org.cvut.wa2.projectcontrol.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import org.cvut.wa2.projectcontrol.entities.Team;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import org.cvut.wa2.projectcontrol.entities.TMember;

public class TeamDAO {

	
	
	public static TMember addTeamMember(String teamName, String member) {
		
		Team team = getTeam(teamName);
		
		if (team == null) return null;
		
		PersistenceManager manager = PMF.get();
		
		try {
			TMember tmember = manager.getObjectById(TMember.class, (teamName+member));
			return null;
		} catch (JDOObjectNotFoundException e) {
			TMember tmember = new TMember();
			tmember.settMemberKey(KeyFactory.createKey(TMember.class.getSimpleName(),(teamName+member)));
			tmember.setTeam(team);
			tmember.setName(member);
			team.getMembers().add(tmember);
			manager.makePersistent(tmember);
			manager.makePersistent(team);
			return tmember;
		}
	}
	
	
	public static List<Team> getTeams(){
		
		Query q = null;
		PersistenceManager pm = null;
		pm = PMF.get();
		q  = pm.newQuery(Team.class);
		try {
			List<Team> list = (List<Team>) q.execute();
			return list;
		} finally {
			q.closeAll();
		}
	}
	
	public static boolean updateTeam(Team team) {
		PersistenceManager manager = PMF.get();
		
		manager.refresh(team);
		
		//manager.makePersistent(team);
		
		return true;
	}
	
	
	public static Team getTeam(String teamName) {
		PersistenceManager manager = PMF.get();
		
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
	
	public static Team createNewTeam(String teamName) {
		
		if (teamName == null) return null;
		
		teamName = teamName.trim();
		
		if (teamName.length() == 0) return null;
		
		Team team = getTeam(teamName);
		
		if (team != null) {
			return null;
		}
		
		team = new Team();
		team.setName(teamName);
		team.setMembers(new ArrayList<TMember>());
		Key key1 = KeyFactory.createKey(Team.class.getSimpleName(),teamName);
		team.setTeamKey(key1);
		
		PersistenceManager manager = PMF.get();
		Team newTeam = manager.makePersistent(team);
		
		
		return newTeam;
	}
	
	
	
	
}
