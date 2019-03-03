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
	
	Users user = new Users();
	private void getUserParam(HttpServletRequest request) {
		user.setUser(request.getParameter("username"), request.getParameter("hashedPassword"),
				 request.getParameter("confirmPassword"),request.getParameter("role"),
				 request.getParameter("permitType"), false);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");		
		listPermitTypes(request,response);
		listRoles(request,response);
		// List users
		if(action != null)
		{
			if(action.equalsIgnoreCase("search")){
				searchuserdetails(request);
			} 
			else // redirect all other gets to post
				doPost(request, response);
		}
	}
private void searchuserdetails(HttpServletRequest request) {
	String type = request.getParameter("type");
	String query = request.getParameter("query");
	
	if ("UserName".equals(type)) {
		List<Users> userList = new ArrayList<Users>();
		userList = UsersDAO.searchByUsername(query);
		for(Users user: userList){
			System.out.println(user.getUsername());
		}
	} else if ("LastName".equals(type)) {
		System.out.println("Search by LastName");
	}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		
		UsersErrorMsgs errorMsgs = new UsersErrorMsgs();
		if(action.equalsIgnoreCase("Login")){
			url = login(request, action, url, session, errorMsgs);
		} 
		else if (action.equalsIgnoreCase("logout"))
		{
			url = logout(request);
		
		} else if(action.equalsIgnoreCase("saveUser")){
			url = register(request, action, session, errorMsgs);
			listSex(request,response);
			listPermitTypes(request,response);
			listRoles(request,response);
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	protected void listSex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<Sex> listSex = new ArrayList<Sex>(Arrays.asList(Sex.values()));
			request.setAttribute("allSex", listSex);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	protected void listPermitTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<PermitType> listPermitTypes = new ArrayList<PermitType>(Arrays.asList(PermitType.values()));
			request.setAttribute("allPermitTypes", listPermitTypes);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	protected void listRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<Role> listRoles = new ArrayList<Role>(Arrays.asList(Role.values()));
			request.setAttribute("allRoles", listRoles);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/formRegistration.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
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
			session.setAttribute("registererrorMsgs", errorMsgs);
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
			session.setAttribute("registererrorMsgs", errorMsgsuser);
			url = "/formUserDetails.jsp";
		}
		return url;
	}

	private String logout(HttpServletRequest request) {
		String url;
		url = "/index.jsp";
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
		if(user.getUserID() != null){
			if("Admin".equalsIgnoreCase(user.getRole()))
			{
				url = "/adminHomePage.jsp";
			}
			else if("ParkingManager".equalsIgnoreCase(user.getRole()))
			{
				url = "/parkingManagementHomePage.jsp";
			}
			else if("ParkingUser".equalsIgnoreCase(user.getRole()))
			{
				url = "/parkingUserHomePage.jsp";
			}
		}
		//Login Failed
		else
		{
			if(user.getUserID() == null)
			{
				user.validateLogin(action,null,errorMsgs);
				session.setAttribute("loginerrorMsgs", errorMsgs);
				url = "/index.jsp";
			}
		}
		return url;
	}
}
