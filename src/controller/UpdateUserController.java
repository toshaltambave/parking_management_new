package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import data.UpdatedUserDetailsDAO;
import data.UsersDAO;
import model.UpdatedUserDetails;
import model.UpdatedUserDetailsErrorMsgs;
import model.UserDetailsErrorMsgs;
import model.Users;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("getList")) {
			// get parameters from the request
			Users user = (Users) request.getSession().getAttribute("User");
			java.util.List<UpdatedUserDetails> userList = UpdatedUserDetailsDAO.searchByUsername(user.getUsername());
			UpdatedUserDetails updatedUserDetails = userList.get(0);
			request.setAttribute("updatedUserDetails", updatedUserDetails);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/EditProfile.jsp");
			dispatcher.forward(request, response);
		}
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
			if (request.getParameter("userId") != null && !request.getParameter("userId").isEmpty()) {
				Integer userId = Integer.valueOf(request.getParameter("userId"));

				userdetails.setUserID(userId);
				url = handleUpdate(request, action, userName, session, userdetails, errorMsgs);
			} else {
				userdetails.validateUserDetails(action, userdetails, errorMsgs);
				session.setAttribute("updatedUserDetailsErrorMsgs", errorMsgs);
				url = "/EditProfile.jsp?username=" + userName;
			}
		} else {
			String type = request.getParameter("type");
			if (type != null) {
				isDispatch = true;
				String value = request.getParameter("value");
				java.util.List<UpdatedUserDetails> userList = UpdatedUserDetailsDAO.searchByUsername(value);
				UpdatedUserDetails updatedUserDetails = userList.get(0);
				request.setAttribute("updatedUserDetails", updatedUserDetails);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditProfile.jsp");
				dispatcher.forward(request, response);

			} else {
				url = "/UpdateSelect.jsp";
			}
		}
		if (!isDispatch) {
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
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
		Users user = (Users) session.getAttribute("user");
		if (user != null && !user.getUsername().isEmpty())
			{
				userdetails.setUserName(user.getUsername());
				userdetails.setRole(user.getRole());
			}
		getUpdatedUserDetailsParam(request, userdetails);
//		userdetails.validateUserDetails(action, userdetails, errorMsgs);
		session.setAttribute("userdetails", userdetails);
		if (!errorMsgs.getErrorMsg().equals("")) {
			getUpdatedUserDetailsParam(request, userdetails);
			session.setAttribute("updatedUserDetailsErrorMsgs", errorMsgs);
			url = "/EditProfile.jsp?username=" + userName;
		}
		else 
		{
			// if no error messages
			boolean isSuccessful = UpdatedUserDetailsDAO.updateUser(userdetails);
			request.setAttribute("isSuccessful", isSuccessful);
			request.setAttribute("username", userName);

			UpdatedUserDetailsErrorMsgs errorMsgsuser = new UpdatedUserDetailsErrorMsgs();
			session.setAttribute("updatedUserDetailsErrorMsgs", errorMsgsuser);
			
			url = "";
			if(session.getAttribute("User") != null)
			{
				if(user.getRole().equals("ParkingUser")){
				url="/parkingUserHomePage.jsp";
				}
				else if (user.getRole().equals("ParkingManager")){
					url="/parkingManagementHomePage.jsp";
				}
				else if (user.getRole().equals("Admin")){
					url="/adminHomePage.jsp";
				}			
			}

		}
		return url;
	}

}
