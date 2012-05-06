package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import javax.jdo.JDOObjectNotFoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cvut.wa2.projectcontrol.DAO.TeamDAO;
import org.cvut.wa2.projectcontrol.entities.Team;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CreateTeamServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		if (user != null) {
			String teamName = req.getParameter("teamName");
			RequestDispatcher disp = req.getRequestDispatcher("CreateTeam.jsp");
			if (teamName.trim().equals("")) {
				disp.forward(req, resp);
			} else {
				Team team = TeamDAO.getTeam(teamName);
				if (team == null) {
					Team newTeam = TeamDAO.createNewTeam(teamName);
					req.setAttribute("team", newTeam);
					disp = req.getRequestDispatcher("EditTeam.jsp");
					disp.forward(req, resp);
				} else {
					team = TeamDAO.getTeam(teamName);
					disp.forward(req, resp);
				}
			}
		} else {
			resp.sendRedirect("/projectcontrol");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
