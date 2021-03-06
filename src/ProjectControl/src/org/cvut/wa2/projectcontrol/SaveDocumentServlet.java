package org.cvut.wa2.projectcontrol;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.TaskDAO;
import org.cvut.wa2.projectcontrol.entities.DocumentEntity;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SaveDocumentServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		
		RequestDispatcher disp = null;
		
		if (userService.isUserLoggedIn()) {
		
			String taskName = req.getParameter("taskName");
			int docCount = Integer.parseInt(req.getParameter("documentCount"));
			
			for (int i = 0; i < docCount; i++) {
					String docName = req.getParameter("doc" + i);
					if (docName != null) {
						String docHref = req.getParameter("href" + i);
						
						TaskDAO.addDoc(taskName, docName, docHref);
						
					}
			}
			
			resp.sendRedirect("/tasks");
			
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