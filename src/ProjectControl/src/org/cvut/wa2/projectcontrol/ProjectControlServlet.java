package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

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