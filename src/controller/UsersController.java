package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.*;
import model.Users;
import model.UsersErrorMsgs;



@WebServlet("/UsersController")
public class UsersController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private void getUserParam(HttpServletRequest request, Users user) {
		user.setUser(request.getParameter("username"), request.getParameter("hashedPassword"),
				 request.getParameter("confirmPassword"),request.getParameter("role"),
				 request.getParameter("permitType"), false);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		// List users
		if (action.equalsIgnoreCase("listUsers")) {
			ArrayList<Users> usersInDB = new ArrayList<Users>();
			usersInDB = UsersDAO.listUsers();
			getServletContext().getRequestDispatcher("/RevokeUser.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("search")){
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
		} else // redirect all other gets to post
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		Users user = new Users();
		UsersErrorMsgs errorMsgs = new UsersErrorMsgs();
		if(action.equalsIgnoreCase("Login")){
			getUserParam(request, user);
			session.setAttribute("user", user);
			String role = UsersDAO.userExists(user);
			if("Admin".equalsIgnoreCase(role))
			{
				url = "/adminHomePage.jsp";
			}
			else if("ParkingManager".equalsIgnoreCase(role))
			{
				url = "/parkingManagementHomePage.jsp";
			}
			else if("ParkingUser".equalsIgnoreCase(role))
			{
				url = "/parkingUserHomePage.jsp";
			}
			else
			{
				url = "/index.jsp";
			}
		} else if (action.equalsIgnoreCase("logout")){
			url = "/index.jsp";
			request.getSession().invalidate();
		
		} else if(action.equalsIgnoreCase("saveUser")){
			getUserParam(request, user);
			user.validateUser(action,user,errorMsgs);
			session.setAttribute("user", user);
			if (!errorMsgs.getErrorMsg().equals(""))
			{// if error messages
				getUserParam(request, user);
				session.setAttribute("errorMsgs", errorMsgs);
				url = "/formRegistration.jsp";
			} 
			else 
			{// if no error messages
				UsersDAO.insertUser(user);
				UsersErrorMsgs errorMsgsuser = new UsersErrorMsgs();
				session.setAttribute("errorMsgs", errorMsgsuser);
				url = "/formUserDetails.jsp";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
