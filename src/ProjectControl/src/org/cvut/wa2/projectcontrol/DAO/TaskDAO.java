package org.cvut.wa2.projectcontrol.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TaskDAO {

	
	
	
	
	
	public static List<CompositeTask> getTasks() {
		
		Query q = null;
		List<CompositeTask> tasks = null;
		
		try {
			
			
			PersistenceManager manager = PMF.get().getPersistenceManager();
			PersistenceManager pm = PMF.get().getPersistenceManager();
			q  = pm.newQuery(Team.class);
			tasks = (List<CompositeTask>) q.execute();
		
		} finally {
			q.closeAll();
		}
		return tasks;
	}
	
	
	
	public static CompositeTask createCompositeTask(String taskName, Date dateOfStartDelivery, String teamName,
			String owner) {
		
		if (taskName == null) return null;
		
		taskName = taskName.trim();
		
		if (taskName.length() == 0) return null;
		
		Team team = TeamDAO.getTeam(teamName);
		
		if (team == null) return null;
		
		Key key3 = KeyFactory.createKey(CompositeTask.class.getSimpleName(),taskName);
		
		CompositeTask ct = new CompositeTask();
		ct.setDateOfStartDelivery(dateOfStartDelivery);
		ct.setDocLink("");
		ct.setOwner(team);
		ct.setSubtasks(new ArrayList<Task>());
		ct.setTaskKey(key3);
		ct.setTaskName(taskName);
		ct.setTaskStatus(Status.processing);
		ct.setTaskOwner(owner);
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		manager.makePersistent(ct);
		
		return ct;
	}
	
	
	
	
	
	
}
