package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class TasksServlet extends HttpServlet{
// listuje existujuce composite tasky
	private static final long serialVersionUID = -2239726230771945395L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		RequestDispatcher disp = null;
		if (userService.isUserLoggedIn()) {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		
		
		
		
		
		
		
		
		
		} else{
			resp.sendRedirect("/projectcontrol");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
