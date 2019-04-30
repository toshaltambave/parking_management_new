package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.*;
import model.*;
import util.PasswordUtility;

@WebServlet("/UsersController")
public class UsersController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Users user = new Users(new UsersDAO());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");		
		listPermitTypes(request,response);
		listRoles(request,response);
		// List users
//		if(action != null)
//		{
//			if (action.equalsIgnoreCase("listUsers")) {
//				ArrayList<Users> usersInDB = new ArrayList<Users>();
//				usersInDB = UsersDAO.listUsers();
//				getServletContext().getRequestDispatcher("/RevokeUser.jsp").forward(request, response);
//			}
//			else if(action.equalsIgnoreCase("search")){
//				searchuserdetails(request);
//			} 
//			else // redirect all other gets to post
//				doPost(request, response);
//		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"), url = "";
		String userName = request.getParameter("username");
		HttpSession session = request.getSession();
		
		UsersErrorMsgs errorMsgs = new UsersErrorMsgs();
		if(action.equalsIgnoreCase("Login")){
			url = login(request, action, url, session, errorMsgs);
		} 
		else if (action.equalsIgnoreCase("logout"))
		{
			url = logout(request);
			getServletContext().getRequestDispatcher(url);
		
		} else if(action.equalsIgnoreCase("saveUser")){
			url = register(request, action, session, errorMsgs);
			listSex(request,response);
			if(url == "/formRegistration.jsp")
			{
				listPermitTypes(request,response);
				listRoles(request,response);
			}		
		}		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
//	private void searchuserdetails(HttpServletRequest request) {
//		String type = request.getParameter("type");
//		String query = request.getParameter("query");
//		
//		if ("UserName".equals(type)) {
//			List<Users> userList = new ArrayList<Users>();
//			userList = UsersDAO.searchByUsername(query);
//			for(Users user: userList){
//				System.out.println(user.getUsername());
//			}
//		} else if ("LastName".equals(type)) {
//			System.out.println("Search by LastName");
//		}
//	}
//	
	private void getUserParam(HttpServletRequest request) {
		user.setUser(request.getParameter("username"), request.getParameter("hashedPassword"),
				 request.getParameter("confirmPassword"),request.getParameter("role"),
				 request.getParameter("permitType"), false, "");
	}
	
	protected void listSex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ArrayList<Sex> listSex = new ArrayList<Sex>(Arrays.asList(Sex.values()));
		request.setAttribute("allSex", listSex);

    }
	
	protected void listPermitTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<PermitType> listPermitTypes = new ArrayList<PermitType>(Arrays.asList(PermitType.values()));
		request.setAttribute("allPermitTypes", listPermitTypes);
    }
	
	protected void listRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//request.getSession().invalidate();
		ArrayList<Role> listRoles = new ArrayList<Role>(Arrays.asList(Role.values()));
		request.setAttribute("allRoles", listRoles);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/formRegistration.jsp");
        dispatcher.forward(request, response);
    }
		

	private String register(HttpServletRequest request, String action, HttpSession session, UsersErrorMsgs errorMsgs) 
	{
		String url;
		getUserParam(request);
		user.validateUser(action,user,errorMsgs);
		session.setAttribute("user", user);
		if (!errorMsgs.getErrorMsg().equals(""))
		{// if error messages
			getUserParam(request);
			request.setAttribute("registererrorMsgs", errorMsgs);
			String role = request.getParameter("role");
	        request.setAttribute("selectedrole", role);
			String permitType = request.getParameter("permitType");
	        request.setAttribute("selectedpermitType", permitType);
			url = "/formRegistration.jsp";
		} 
		else 
		{// if no error messages
			UsersDAO.insertUser(user);
			UsersErrorMsgs errorMsgsuser = new UsersErrorMsgs();
			request.setAttribute("registererrorMsgs", errorMsgsuser);
			url = "/formUserDetails.jsp";
		}
		return url;
	}

	private String logout(HttpServletRequest request) throws ServletException {
		String url;
		url = "/index.jsp";

		user = new Users(new UsersDAO());
		request.logout();	
		request.getSession().setAttribute("User", null);
		request.getSession().invalidate();

		return url;
	}

	private String login(HttpServletRequest request, String action, String url, HttpSession session,
			UsersErrorMsgs errorMsgs) 
	{
        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtility.generatePassword(request.getParameter("hashedPassword"));		
		UsersDAO.userExists(request.getParameter("username"), mySecurePassword, user);
		//Set Attributes of Logged in User in session for further pages
		session.setAttribute("User", user);
		if(user.getUserID() != null)
		{
			if("Admin".equalsIgnoreCase(user.getRole()))
			{
				url = "/adminHomePage.jsp";
			}
			else if("ParkingManager".equalsIgnoreCase(user.getRole()))
			{
				url = "/parkingManagementHomePage.jsp";
			}
			else 
			{
				url = "/parkingUserHomePage.jsp";				
			}
					
		}
		//Login Failed
		else
		{
			user.validateLogin(action,null,errorMsgs);
			session.setAttribute("loginerrorMsgs", errorMsgs);
			url = "/index.jsp";

		}

		return url;

	}
}
