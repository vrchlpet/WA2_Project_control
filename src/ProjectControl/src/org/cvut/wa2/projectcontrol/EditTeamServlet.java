package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.entities.Team;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class EditTeamServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		if(user!= null){
			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
			Team team = (Team) req.getAttribute("team");
			RequestDispatcher disp = req.getRequestDispatcher("EditTeam.jsp");
			disp.forward(req, resp);
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
