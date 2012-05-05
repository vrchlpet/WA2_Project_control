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
import org.cvut.wa2.projectcontrol.entities.Status;
import org.cvut.wa2.projectcontrol.entities.Task;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class FilterByStatusServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		RequestDispatcher disp = null;
		if (userService.isUserLoggedIn()) {
			String status = (String) req.getParameter("statusdropdown");
			List<CompositeTask> listOfTask = TaskDAO.getTasks();
			List<CompositeTask> toReturn = new ArrayList<CompositeTask>();
			if (status.equals(Status.finished)) {
				for (CompositeTask compositeTask : listOfTask) {
					if (compositeTask.getSubtasks().size() != 0) {
						if (areAllSubtasksFinished(compositeTask)) {
							toReturn.add(compositeTask);
						}
					}
				}
			} else {
				for (CompositeTask compositeTask : listOfTask) {
					if (compositeTask.getSubtasks().size() != 0) {
						if (!areAllSubtasksFinished(compositeTask)) {
							toReturn.add(compositeTask);
						}
					}
				}
			}
			disp = req.getRequestDispatcher("Tasks.jsp");
			req.setAttribute("listOfTasks", toReturn);
			disp.forward(req, resp);
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

	private boolean areAllSubtasksFinished(CompositeTask ct) {
		List<Task> subTasks = ct.getSubtasks();
		for (Task task : subTasks) {
			if (task.getTaskStatus().equals(Status.processing));
			return false;
		}
		return true;
	}
}
