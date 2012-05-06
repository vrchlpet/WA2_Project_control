package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cvut.wa2.projectcontrol.DAO.TeamDAO;
import org.cvut.wa2.projectcontrol.entities.Team;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class EditTeamServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		RequestDispatcher disp = req.getRequestDispatcher("EditTeam.jsp");
		if(service.isUserLoggedIn()){
			String teamName =  req.getParameter("teamName");
			Team team = TeamDAO.getTeam(teamName);
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
