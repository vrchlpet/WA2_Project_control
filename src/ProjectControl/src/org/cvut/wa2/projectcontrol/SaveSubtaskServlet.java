package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import org.cvut.wa2.projectcontrol.entities.Task;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SaveSubtaskServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		
		RequestDispatcher disp = null;
		
		if (userService.isUserLoggedIn()) {
			String subtaskName = req.getParameter("subtaskname");
			String responsible = req.getParameter("responsible");
			String taskName = req.getParameter("taskName");
			
			Task task = TaskDAO.createSubTask(taskName, subtaskName, userService.getCurrentUser().getEmail(), responsible);
			
			if (task == null) {
				String errText = "Cannot create subtask: " + subtaskName;
				req.setAttribute("err", errText);
				disp = req.getRequestDispatcher("err.jsp");
				disp.forward(req, resp);
			} else {
				resp.sendRedirect("/tasks");
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