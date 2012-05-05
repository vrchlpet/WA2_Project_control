package org.cvut.wa2.projectcontrol;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cvut.wa2.projectcontrol.entities.ContactsToken;
import org.cvut.wa2.projectcontrol.entities.Team;
import org.cvut.wa2.projectcontrol.entities.TeamMember;

import org.cvut.wa2.projectcontrol.DAO.PMF;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthHelper;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthSigner;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.util.ServiceException;

public class AddMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		
		RequestDispatcher disp = null;
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			
			
			PersistenceManager pm = PMF.get().getPersistenceManager();
			ContactsToken token = null;
			try {
				token = pm.getObjectById(ContactsToken.class, user.getEmail());
				
				String teamName = req.getParameter("teamName");
				if (teamName != null) {
					PersistenceManager manager = PMF.get().getPersistenceManager();
					Team team = manager.getObjectById(Team.class, teamName);
					
					if (team != null) {
						
						
						//Get calendars
						GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
						oauthParameters.setOAuthConsumerKey("anonymous");
						oauthParameters.setOAuthConsumerSecret("anonymous");
						oauthParameters.setOAuthToken(token.getToken());
						oauthParameters.setOAuthTokenSecret(token.getTokenSecret());
						OAuthSigner signer = new OAuthHmacSha1Signer();

						ContactsService service = new ContactsService("ContactFeedDemo");
						List<ContactEntry> result = null;
						List<String> mails = null;
						try {
							service.setOAuthCredentials(oauthParameters, signer);
							ContactFeed resultFeed = service.getFeed(
									new URL("https://www.google.com/m8/feeds/contacts/default/full"), 
									ContactFeed.class);

							result = new ArrayList<ContactEntry>(resultFeed.getEntries());
							mails = new ArrayList<String>();
							for (ContactEntry ce : result) {
								if (ce.getNickname().toString().length() == 0)
								for (Email em : ce.getEmailAddresses())
									mails.add(em.getAddress());
							}
							
							/*
							for (TeamMember tm : team.getMembers()) {
								mails.remove(tm.getName());
							}*/
							
						} 
						catch (OAuthException e) {
							e.printStackTrace();
						} 
						catch (ServiceException e) {
							e.printStackTrace();
						}
						// end get contact list
						
						for (TeamMember member : team.getMembers()) {
							mails.remove(member.getName());
						}
						
						List<TeamMember> teamMembers = team.getMembers();
						req.setAttribute("teamName", teamName);
						//req.setAttribute("teamMembers", teamMembers);
			        	req.setAttribute("contacts", mails);
			        	RequestDispatcher rd = req.getRequestDispatcher("/AddMember.jsp");
			        	rd.forward(req, resp);
						
					} else {
						String errText = "team with team name " + teamName + "doesnt exist.";
						req.setAttribute("err", errText);
						disp = req.getRequestDispatcher("err.jsp");
						disp.forward(req, resp);
					}
					
				} else {
					String errText = "parametru teamName is null";
					req.setAttribute("err", errText);
					disp = req.getRequestDispatcher("err.jsp");
					disp.forward(req, resp);
				}
				
				
				
			}
			catch(JDOObjectNotFoundException e) {
				String consumerKey = "anonymous";
				String consumerSecret = "anonymous";
				String scope = "https://www.google.com/m8/feeds";
				String callback = "http://vrchlpet-projectcontrol.appspot.com/callbackservlet";

				GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();	
				oauthParameters.setOAuthConsumerKey(consumerKey);
				oauthParameters.setOAuthConsumerSecret(consumerSecret);
				oauthParameters.setScope(scope);
				oauthParameters.setOAuthCallback(callback);

				OAuthSigner signer = new OAuthHmacSha1Signer();
				GoogleOAuthHelper oauthHelper = new GoogleOAuthHelper(signer);

				try {
					oauthHelper.getUnauthorizedRequestToken(oauthParameters);
					String approvalPageUrl = oauthHelper.createUserAuthorizationUrl(oauthParameters);
					req.getSession().setAttribute("tokenSecret", oauthParameters.getOAuthTokenSecret());
					resp.sendRedirect(approvalPageUrl);
					return;
				} catch (OAuthException ee) {
					ee.printStackTrace();
				}
			}
			finally {
				pm.close();
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
