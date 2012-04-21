package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.DAO.PMF;
import org.cvut.wa2.projectcontrol.entities.Team;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class TeamsServlet extends HttpServlet{

	private static final long serialVersionUID = -2505608701798341438L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		User user = service.getCurrentUser();
		if(user!= null){
			RequestDispatcher disp = req.getRequestDispatcher("CreateTeam.jsp");
			disp.forward(req, resp);
			
			
			
			
//			DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//			PersistenceManager pm = PMF.get().getPersistenceManager();
//			Query q  = pm.newQuery(Team.class);
//			q.setOrdering("name desc");
//			try {
//				List<Team> listOfTeams = (List<Team>) q.execute();
//				for (Team team : listOfTeams) {
//					System.out.println(team.getName());
//				}
//			} finally {
//			
//			}
		}
		else{
			resp.sendRedirect("/projectcontrol");
		}
		
	}



}
