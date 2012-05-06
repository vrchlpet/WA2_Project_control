package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import org.cvut.wa2.projectcontrol.entities.CompositeTask;
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class FinishTaskServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		
		RequestDispatcher disp = null;
		
		if (userService.isUserLoggedIn()) {
			
			String taskName = req.getParameter("taskName");
			String subtaskName = req.getParameter("subtaskName");
			
			CompositeTask ct = TaskDAO.getTask(taskName);
			
			if (ct != null) {
				
				Task t = null;
				
				x:for (Task task : ct.getSubtasks()) {
					if (task.getTaskName().equals(subtaskName)) {
						t = task;
						break x;
					}
				}
				
				if (t != null) {
					t.setTaskStatus(Status.finished);
					PersistenceManager pm = PMF.get();
					pm.makePersistent(ct);
					disp = req.getRequestDispatcher("/tasks");
					disp.forward(req, resp);
				} else {
					String errText = "Subtask " + subtaskName + " doesnt exist.";
					req.setAttribute("err", errText);
					disp = req.getRequestDispatcher("err.jsp");
					disp.forward(req, resp);
				}
				
				
				
				
			} else {
				String errText = "Composite task " + taskName + " doesnt exist.";
				req.setAttribute("err", errText);
				disp = req.getRequestDispatcher("err.jsp");
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