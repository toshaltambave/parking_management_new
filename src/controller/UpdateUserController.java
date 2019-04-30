package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.*;
import model.*;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		listPermitTypes(request,response);
		listRoles(request,response);
		if (action.equals("getList")) {
			// get parameters from the request
			Users user = (Users) request.getSession().getAttribute("User");
			java.util.List<UpdatedUserDetails> userList = UpdatedUserDetailsDAO.searchByUsername(user.getUsername());
			UpdatedUserDetails updatedUserDetails = userList.get(0);
			request.getSession().setAttribute("oldusername", updatedUserDetails.getOldusername());
			String role = updatedUserDetails.getRole();
	        request.setAttribute("selectedrole", role);
			String permitType = updatedUserDetails.getPermitType();
	        request.setAttribute("selectedpermitType", permitType);
			request.setAttribute("updatedUserDetails", updatedUserDetails);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/EditProfile.jsp");
			dispatcher.forward(request, response);
		}
		else
			doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isDispatch = false;
		HttpSession session = request.getSession();
		UpdatedUserDetails userdetails = new UpdatedUserDetails(new UsersDAO());
		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();
		String action = request.getParameter("action"), url = "";

		if (action.equalsIgnoreCase("update")) {
			String userName = request.getParameter("username");
			//if (request.getParameter("userId") != null && !request.getParameter("userId").isEmpty()) {
				Integer userId = Integer.valueOf(request.getParameter("userId"));
				userdetails.setUserID(userId);
				listPermitTypes(request,response);
				listRoles(request,response);
				url = handleUpdate(request, action, userName, session, userdetails, errorMsgs);
			//} 
		} 
		else
		{
			String type = request.getParameter("type");
			if (type != null) {
				isDispatch = true;
				listPermitTypes(request,response);
				listRoles(request,response);
				String value = request.getParameter("value");
				java.util.List<UpdatedUserDetails> userList = UpdatedUserDetailsDAO.searchByUsername(value);
				UpdatedUserDetails updatedUserDetails = userList.get(0);
				request.getSession().setAttribute("oldusername", updatedUserDetails.getOldusername());
				String role = updatedUserDetails.getRole();
		        request.setAttribute("selectedrole", role);
				String permitType = updatedUserDetails.getPermitType();
		        request.setAttribute("selectedpermitType", permitType);
				request.setAttribute("updatedUserDetails", updatedUserDetails);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditProfile.jsp");
				dispatcher.forward(request, response);

			} 
			else
			{
				url = "/UpdateSelect.jsp";
			}
		}
		if (!isDispatch) {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
	}
	
	protected void listPermitTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			ArrayList<PermitType> listPermitTypes = new ArrayList<PermitType>(Arrays.asList(PermitType.values()));
			request.setAttribute("allPermitTypes", listPermitTypes);
    }
	
	protected void listRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<Role> listRoles = new ArrayList<Role>(Arrays.asList(Role.values()));
		request.setAttribute("allRoles", listRoles);
    }

	private void getUpdatedUserDetailsParam(HttpServletRequest request, UpdatedUserDetails updatedUserdetails) {
		updatedUserdetails.setUpdatedUserDetails(request.getParameter("firstname"), request.getParameter("middlename"),
				request.getParameter("lastname"), request.getParameter("username"), request.getParameter("sex"),
				request.getParameter("dob"), request.getParameter("address"), request.getParameter("email"),
				request.getParameter("phone"), request.getParameter("dlno"), request.getParameter("dlexpirydte"),
				request.getParameter("regno"), request.getParameter("utaid"), request.getParameter("hashpass"),
				request.getParameter("confirmpass"), request.getParameter("role"), request.getParameter("permitType"));
	}

	private String handleUpdate(HttpServletRequest request, String action, String userName, HttpSession session,
			UpdatedUserDetails userdetails, UpdatedUserDetailsErrorMsgs errorMsgs) {
		String url;
		Users user = (Users) session.getAttribute("User");

		userdetails.setUserName(user.getUsername());
		userdetails.setRole(user.getRole());
	
		getUpdatedUserDetailsParam(request, userdetails);
		
		String role = userdetails.getRole();
        request.setAttribute("selectedrole", role);
		String permitType = userdetails.getPermitType();
        request.setAttribute("selectedpermitType", permitType);
        userdetails.setOldusername((String)request.getSession().getAttribute("oldusername"));
		userdetails.validateUserDetails(action, userdetails, errorMsgs);
		request.setAttribute("updatedUserDetails", userdetails);
		if (!errorMsgs.getErrorMsg().equals("")) {
			getUpdatedUserDetailsParam(request, userdetails);
			request.setAttribute("updatedUserDetailsErrorMsgs", errorMsgs);
			url = "/EditProfile.jsp?username=" + userName;
		}
		else 
		{
			// if no error messages
			boolean isSuccessful = UpdatedUserDetailsDAO.updateUser(userdetails);
			request.setAttribute("isSuccessful", isSuccessful);
			request.setAttribute("username", userName);

			UpdatedUserDetailsErrorMsgs errorMsgsuser = new UpdatedUserDetailsErrorMsgs();
			request.setAttribute("updatedUserDetailsErrorMsgs", errorMsgsuser);
			
			url = "";

			if (user.getRole().equals("ParkingManager")){
				url="/parkingManagementHomePage.jsp";
			}
			else if (user.getRole().equals("Admin")){
				url="/adminHomePage.jsp";
			}
			else
			{
				url="/parkingUserHomePage.jsp";
			}		

		}
		return url;
	}

}