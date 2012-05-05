package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RemoveTaskServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		if(service.isUserLoggedIn()){
			
			String taskName = req.getParameter("taskName");
			
			TaskDAO.removeTask(taskName);
			
			resp.sendRedirect("/tasks");
			
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
