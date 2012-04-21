package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.entities.AccessToken;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;
import org.cvut.wa2.projectcontrol.entities.TokenType;

import javax.servlet.RequestDispatcher;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ProjectControlServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		RequestDispatcher reqDisp;
		
		try {
			if (user == null) {
				reqDisp = req.getRequestDispatcher("LogIn.jsp");
				reqDisp.forward(req, resp);
			} else {
				reqDisp = req.getRequestDispatcher("TeamsTask.jsp");
				reqDisp.forward(req, resp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}



/*

package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.entities.AccessToken;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;
import org.cvut.wa2.projectcontrol.entities.TokenType;

import javax.servlet.RequestDispatcher;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ProjectControlServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		RequestDispatcher reqDisp = req
				.getRequestDispatcher("TeamsTask.jsp");
		
		
		
		try {
			Team team = new Team();
			team.setName("Team2");
			team.setMembers(new ArrayList<TeamMember>());
			Key key1 = KeyFactory.createKey(Team.class.getSimpleName(),"Team2");
			team.setTeamKey(key1);
			pm.makePersistent(team);
			TeamMember tm = new TeamMember();
			Key key2 = KeyFactory.createKey(TeamMember.class.getSimpleName(),"Vrchli");
			tm.setTeamMemberKey(key2);
			team.getMembers().add(tm);
			tm.setName("Vrchli");
			tm.setTeam(team);
			tm.setAccessTokens(new ArrayList<AccessToken>());
			AccessToken at = new AccessToken();
			Key key = KeyFactory.createKey(AccessToken.class.getSimpleName(),"1");
			at.setAccessTokenKey(key);
			at.setTokenText("testing token");
			at.setTokenType(TokenType.calendar);
			tm.getAccessTokens().add(at);
			CompositeTask ct = new CompositeTask();
			Key key3 = KeyFactory.createKey(Task.class.getSimpleName(),"Compositetask1");
			ct.setTaskKey(key3);
			ct.setTaskName("CompositeTask1");
			ct.setDateOfStartDelivery(new Date());
			ct.setDocLink("linkToTestingDoc");
			ct.setOwner("Vrchli");
			ct.setResponsible("Vrchli");
			ct.setTaskStatus(Status.processing);
			Task t1 = new Task();
			Key key4 = KeyFactory.createKey(Task.class.getSimpleName(),"t1");
			t1.setTaskKey(key4);
			Task t2 = new Task();
			Key key5 = KeyFactory.createKey(Task.class.getSimpleName(),"t2");
			t2.setTaskKey(key5);
			t1.setTaskName("t1");
			t1.setDateOfStartDelivery(new Date());
			t1.setDocLink("docLink1");
			t1.setTaskStatus(Status.problem);
			t2.setTaskName("t2");
			t2.setDateOfStartDelivery(new Date());
			t2.setDocLink("docLink2");
			t2.setTaskStatus(Status.finished);
			ct.setSubtasks(new ArrayList<Task>());
			ct.getSubtasks().add(t1);
			ct.getSubtasks().add(t2);

			pm.makePersistent(at);
			pm.makePersistent(tm);
			pm.makePersistent(team);
			pm.makePersistent(ct);
			pm.makePersistent(t1);
			pm.makePersistent(t2);
			
			CompositeTask compTask =pm.getObjectById(CompositeTask.class, "Compositetask1");
			Task t = compTask.getSubtasks().get(0);
			String str = compTask.getDocLink();
			req.setAttribute("user",t.getDocLink());
			try {
				reqDisp.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} finally {
			pm.close();
		}

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			resp.sendRedirect("/LogIn.jsp");
		} else {
			resp.sendRedirect("/TeamsTask.jsp");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}


 * */

