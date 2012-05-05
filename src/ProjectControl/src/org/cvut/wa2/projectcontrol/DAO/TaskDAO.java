package org.cvut.wa2.projectcontrol.DAO;

import java.util.Date;

import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TaskDAO {

	
	
	
	
	public static CompositeTask createCompositeTask(String taskName, Date dateOfStartDelivery, String teamName,
			String teamMember) {
		
		if (taskName == null) return null;
		
		taskName = taskName.trim();
		
		if (taskName.length() == 0) return null;
		
		Team team = TeamDAO.getTeam(teamName);
		
		if (team == null) return null;
		
		TeamMember teamM = null;
		x:for (TeamMember tm : team.getMembers()) {
			if (tm.getName().equals(teamMember)) {
				teamM = tm;
				break x;
			}
		}
		
		if (teamM == null) return null;
		
		Key key3 = KeyFactory.createKey(Task.class.getSimpleName(),"Compositetask1");
		
		CompositeTask ct = new CompositeTask();
		ct.setDateOfStartDelivery(dateOfStartDelivery);
		ct.setDocLink("");
		ct.setOwner(team);
		ct.setResponsible(teamM);
		
		
		
		
		
		
		teamM.getTasks().add(ct);
		
		
		
		return ct;
	}
	
	
	
	
	
	
}
