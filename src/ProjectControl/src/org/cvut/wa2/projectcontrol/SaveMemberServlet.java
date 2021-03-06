package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.cvut.wa2.projectcontrol.DAO.TeamDAO;
import org.cvut.wa2.projectcontrol.entities.Team;
import javax.servlet.RequestDispatcher;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SaveMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		UserService userService = UserServiceFactory.getUserService();
		RequestDispatcher reqDisp;
		
		if (userService.isUserLoggedIn()) {
		
			String teamName = req.getParameter("teamName");
			int contactsCount = Integer.parseInt(req.getParameter("contactsCount"));
			
			Team team = TeamDAO.getTeam(teamName);
			
			for (int i = 0; i < contactsCount; i++) {
				String contact = req.getParameter("con" + i);
				if (contact != null) {
					TeamDAO.addTeamMember(teamName, contact);
				}
			}
			req.setAttribute("teamName", teamName);
			reqDisp = req.getRequestDispatcher("/editteam");
			try {
				reqDisp.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			reqDisp = req.getRequestDispatcher("LogIn.jsp");
			try {
				reqDisp.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
