package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class TasksServlet extends HttpServlet {
	// listuje existujuce composite tasky
	private static final long serialVersionUID = -2239726230771945395L;
	private static final String attName = "listOfTasks";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();

		RequestDispatcher disp = req.getRequestDispatcher("Tasks.jsp");
		if (userService.isUserLoggedIn()) {
			UserService service = UserServiceFactory.getUserService();
			User user = service.getCurrentUser();
			List<CompositeTask> listOfTasks = TaskDAO.getTasks();
			if (listOfTasks == null) {
				listOfTasks = new ArrayList<CompositeTask>();
			}
			req.setAttribute(attName, listOfTasks);
			disp.forward(req, resp);
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
