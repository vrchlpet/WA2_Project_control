package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.entities.Team;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class EditTeamServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		RequestDispatcher disp = req.getRequestDispatcher("EditTeam.jsp");
		if(user!= null){
			String teamName =  req.getParameter("teamName");
			PersistenceManager manager = PMF.get();
			Team team = manager.getObjectById(Team.class,teamName);
			if(team == null){
				disp = req.getRequestDispatcher("Teams.jsp");
				disp.forward(req, resp);
			}else{
				req.setAttribute("team", team);
				disp.forward(req, resp);
			}
		}
		else{
			resp.sendRedirect("/projectcontrol");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
