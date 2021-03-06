package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cvut.wa2.projectcontrol.DAO.TeamDAO;
import org.cvut.wa2.projectcontrol.entities.Team;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class CreateTaskServlet extends HttpServlet {

	private static final long serialVersionUID = -258284310678221536L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		RequestDispatcher disp = null;
		if (userService.isUserLoggedIn()) {
			String owner = userService.getCurrentUser().getEmail();
			List<Team> listOfTeams = TeamDAO.getTeams();
			req.setAttribute("listOfTeams", listOfTeams);
			req.setAttribute("owner", owner);
			disp = req.getRequestDispatcher("/CreateTask.jsp");
			disp.forward(req, resp);
		} else {
			disp = req.getRequestDispatcher("/projectcontrol");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
