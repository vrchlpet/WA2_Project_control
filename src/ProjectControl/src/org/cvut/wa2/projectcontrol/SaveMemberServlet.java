package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.ArrayList;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.entities.AccessToken;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.ContactsToken;
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

public class SaveMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		RequestDispatcher reqDisp;
		
		if (userService.isUserLoggedIn()) {
		
			String teamName = req.getParameter("teamName");
			int contactsCount = Integer.parseInt(req.getParameter("contactsCount"));
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Team team = pm.getObjectById(Team.class, teamName);
			
			
			for (int i = 0; i < contactsCount; i++) {
				String contact = req.getParameter("con" + i);
				if (contact != null) {
					Key key = KeyFactory.createKey(TeamMember.class.getSimpleName(),contact);
					TeamMember tm = null;
					try {
						tm = pm.getObjectById(TeamMember.class, key);
					} catch(JDOObjectNotFoundException e) {
						tm = new TeamMember();
						tm.setName(contact);
						tm.setTeamMemberKey(key);
						tm.setAccessTokens(new ArrayList<AccessToken>());
						tm.setTasks(new ArrayList<Task>());
						tm.setTeam(team);
						pm.makePersistent(tm);
					}
					team.getMembers().add(tm);
				}
			}
			pm.makePersistent(team);
			req.setAttribute("teamName", teamName);
			reqDisp = req.getRequestDispatcher("/editteam");
			try {
				reqDisp.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			reqDisp = req.getRequestDispatcher("LogIn.jsp");
			try {
				reqDisp.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
