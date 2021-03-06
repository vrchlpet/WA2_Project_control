package org.cvut.wa2.projectcontrol.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.DocumentEntity;
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class TaskDAO {

	
	public static Task createSubTask(String taskName, String subTaskName, String owner, String responsible) {
		Task task = null;
		
		CompositeTask ct = getTask(taskName);
		
		if ( ct == null) return null;
		
		task = new Task();
		Key key1 = KeyFactory.createKey(Task.class.getSimpleName(),subTaskName);
		task.setTaskName(subTaskName);
		task.setTaskOwner(owner);
		task.setResponsible(responsible);
		task.setTaskStatus(Status.processing);
		task.setTaskKey(key1);
		
		PersistenceManager pm = PMF.get();
		
		ct.getSubtasks().add(task);
		
		pm.makePersistent(task);
		pm.makePersistent(ct);
		
		return task;
	}
	
	public static boolean removeTask(String taskName) {
		
		PersistenceManager pm = PMF.get();
		CompositeTask ct = getTask(taskName);
		if ( ct == null) return false;
		pm.deletePersistent(ct);
		
		
		return true;
	}
	

	public static CompositeTask getTask(String taskName) {
		CompositeTask task = null;
		
		PersistenceManager pm = PMF.get();
		
		try {
			task = pm.getObjectById(CompositeTask.class, taskName);
		} catch (JDOObjectNotFoundException e) {
			return null;
		}
		
		
		return task;
	}
	
	
	public static DocumentEntity getDoc(String docName) {
		DocumentEntity de = null;
		
		PersistenceManager pm = PMF.get();
		Key key1 = KeyFactory.createKey(DocumentEntity.class.getSimpleName(),docName);
		
		try {
			de = pm.getObjectById(DocumentEntity.class, key1);
		} catch (JDOObjectNotFoundException e) {
			return null;
		}
		
		
		return de;
	}
	
	
	public static DocumentEntity addDoc(String taskName, String docName, String href) {
		
		DocumentEntity de = null;
		
		CompositeTask task = null;
		
		PersistenceManager pm = PMF.get();
		
		try {
			task = pm.getObjectById(CompositeTask.class, taskName);
		} catch (JDOObjectNotFoundException e) {
			return null;
		}
		
		try {
			Key key1 = KeyFactory.createKey(DocumentEntity.class.getSimpleName(),docName);
			de = pm.getObjectById(DocumentEntity.class, key1);
		} catch (JDOObjectNotFoundException e) {
			de = new DocumentEntity();
			Key key1 = KeyFactory.createKey(DocumentEntity.class.getSimpleName(),docName);
			de.setDocKey(key1);
			de.setDocName(docName);
			de.setHref(href);
		}
		
		task.getDocEntity().add(de);
		
		pm.makePersistent(de);
		pm.makePersistent(task);
		
		return de;
	}
	
	
	public static List<CompositeTask> getTasks() {
		
		Query q = null;
		List<CompositeTask> tasks = null;
		PersistenceManager pm = PMF.get();
		q  = pm.newQuery(CompositeTask.class);
		try {
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
		
		//Key key3 = KeyFactory.createKey(CompositeTask.class.getSimpleName(),taskName);
		
		CompositeTask ct = new CompositeTask();
		ct.setOwner(team.getName());
		ct.setSubtasks(new ArrayList<Task>());
		ct.setTaskName(taskName);
		ct.setDateOfStartDelivery(dateOfStartDelivery);
		ct.setDocEntity(new ArrayList<DocumentEntity>());
		
		PersistenceManager manager = PMF.get();
		manager.makePersistent(ct);
		return ct;
	}
	
}
