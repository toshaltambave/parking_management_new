package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.istack.internal.logging.Logger;

import data.*;
import model.*;

@WebServlet("/UserDetailsController")
public class UserDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserDetailsController.class.getName(),
			UserDetailsController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		listSex(request,response);
		String action = request.getParameter("action");
//		if (action.equalsIgnoreCase("listUsers")) {
//			handleListUser(request, response, session);
//		} 
		if (action.equalsIgnoreCase("search")) {
			handleSearch(request, response);
		} else // redirect all other gets to post
			doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		UserDetails userdetails = new UserDetails();
		UserDetailsErrorMsgs errorMsgs = new UserDetailsErrorMsgs();

		switch (action) {
		case "saveUserDetails":
			url = handleSaveUserDetails(request, action, url, session, userdetails, errorMsgs);
			listSex(request,response);
			getServletContext().getRequestDispatcher(url).forward(request, response);
			break;
		case "revoke":
			url = handleRevoke(request, userdetails,session);
			getServletContext().getRequestDispatcher(url).forward(request, response);
			break;
		case "unrevoke":
			url = handleUnRevoke(request, userdetails);
			getServletContext().getRequestDispatcher(url).forward(request, response);
			break;
		case "role":
			url = handleRoleChange(request);
			getServletContext().getRequestDispatcher(url).forward(request, response);
			break;
		case "LastName":
			handleByLastName(response);
			break;
		case "UserName":
			handleByUserName(response);
			break;
		default:
			LOG.log(Level.WARNING, "Action Unknown: " + action);
		}

	}
	
	protected void listSex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			ArrayList<Sex> listSex = new ArrayList<Sex>(Arrays.asList(Sex.values()));
			request.setAttribute("allSex", listSex);

	}


	private void getUserDetailsParam(HttpServletRequest request, UserDetails userdetails) {
		userdetails.setUserDetails(request.getParameter("firstname"), request.getParameter("middlename"),
				request.getParameter("lastname"), request.getParameter("sex"), request.getParameter("dob"),
				request.getParameter("address"), request.getParameter("email"), request.getParameter("phone"),
				request.getParameter("dlno"), request.getParameter("dlexpirydte"), request.getParameter("regno"),
				request.getParameter("utaid"));
	}

//	private void handleListUser(HttpServletRequest request, HttpServletResponse response, HttpSession session)
//			throws ServletException, IOException {
//		ArrayList<Users> usersInDB = new ArrayList<Users>();
//		usersInDB = UsersDAO.listUsers();
//		session.setAttribute("USERS", usersInDB);
//		getServletContext().getRequestDispatcher("/listUser.jsp").forward(request, response);
//	}

	private String handleSaveUserDetails(HttpServletRequest request, String action, String url, HttpSession session,
			UserDetails userdetails, UserDetailsErrorMsgs errorMsgs) {
		Users user = (Users) session.getAttribute("user");
			userdetails.setUsername(user.getUsername());		
		getUserDetailsParam(request, userdetails);
		userdetails.validateUserDetails(action, userdetails, errorMsgs);
		request.setAttribute("userdetails", userdetails);
		if (!errorMsgs.getErrorMsg().equals("")) {
			getUserDetailsParam(request, userdetails);
			request.setAttribute("userDetailsErrorMsgs", errorMsgs);
			url = "/formUserDetails.jsp";
		} else {// if no error messages
			Boolean isSuccess = false;
			isSuccess = UserDetailsDAO.insertUserDetails(userdetails);
			request.setAttribute("isSuccessful", isSuccess);
			UserDetailsErrorMsgs errorMsgsuser = new UserDetailsErrorMsgs();
			request.setAttribute("userDetailsErrorMsgs", errorMsgsuser);
			url = "/index.jsp";	
		}
		return url;
	}

	private String handleRevoke(HttpServletRequest request, UserDetails userdetails,HttpSession session) {
		String url;
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String comment = request.getParameter("txtComment");
		UsersErrorMsgs error = new UsersErrorMsgs();
		Users user = (Users) session.getAttribute("User");
		boolean isSuccessful = false;
		error.setCommentError(user.validateComment(comment));
		if(error.getCommentError().isEmpty())
		{
			isSuccessful = UserDetailsDAO.revokeUser(type, value, Boolean.TRUE,comment);
			request.setAttribute("isSuccess", isSuccessful);
		}
		else
		{
			request.setAttribute("revokeerrorMsgs", error);
		}
		url = "/RevokeUser.jsp";
		return url;
	}

	private String handleUnRevoke(HttpServletRequest request, UserDetails userdetails) {
		String url;
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String comment = request.getParameter("txtComment");
		boolean isSuccessful = false;
		isSuccessful = UserDetailsDAO.revokeUser(type, value, Boolean.FALSE,comment);
		request.setAttribute("isSuccess", isSuccessful);
		url = "/UnrevokeUser.jsp";
		return url;
	}

	private String handleRoleChange(HttpServletRequest request) {
		String url;
		String type = request.getParameter("type");
		String value = request.getParameter("value");
		String role = request.getParameter("role");
		boolean isSuccessful = false;
		isSuccessful = UserDetailsDAO.changeRole(type, value, role);
		request.setAttribute("isSuccess", isSuccessful);
		url = "/ChangeUserRole.jsp";
		return url;
	}

	private void handleByUserName(HttpServletResponse response) throws IOException {
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		userDetailsList = UserDetailsDAO.getUserNames();
		for (UserDetails userDetail : userDetailsList) {
			response.getWriter().println("<option>" + userDetail.getUsername() + "</option>");
		}
	}

	private void handleByLastName(HttpServletResponse response) throws IOException {
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		userDetailsList = UserDetailsDAO.getLastNames();
		for (UserDetails userDetail : userDetailsList) {
			response.getWriter().println("<option>" + userDetail.getLastName() + "</option>");
		}
	}

	private void handleSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String query = request.getParameter("query");

		if ("UserName".equals(type)) {
			List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
			userDetailsList = UserDetailsDAO.searchByUsername(query);
			request.setAttribute("details", userDetailsList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserSearch.jsp");
			dispatcher.forward(request, response);
		} else if ("LastName".equals(type)) {
			List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
			userDetailsList = UserDetailsDAO.searchByLastName(query);
			request.setAttribute("details", userDetailsList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserSearch.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserSearch.jsp");
			dispatcher.forward(request, response);
		}

	}

}
