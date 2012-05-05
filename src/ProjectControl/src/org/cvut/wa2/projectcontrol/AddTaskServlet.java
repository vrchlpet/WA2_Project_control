package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;


import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AddTaskServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		
		RequestDispatcher disp = null;
		
		if (userService.isUserLoggedIn()) {
		
			
			String taskName = req.getParameter("taskName");
			String day = req.getParameter("day");
			String month = req.getParameter("month");
			String year = req.getParameter("year");
			String teamName = req.getParameter("teamsdropdown");
			
			Date d = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
			
			CompositeTask ct = TaskDAO.createCompositeTask(taskName, d, teamName, userService.getCurrentUser().getEmail());
			
			if (ct == null) {
				req.setAttribute("err", "cannot persist composite task");
				disp = req.getRequestDispatcher("/err.jsp");
				disp.forward(req, resp);
			} else {
				disp = req.getRequestDispatcher("/tasks");
				disp.forward(req, resp);
			}
			
		} else {
			disp = req.getRequestDispatcher("/projectcontrol");
			disp.forward(req, resp);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}