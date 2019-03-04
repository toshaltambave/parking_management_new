package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UpdatedUserDetailsDAO;
import model.UpdatedUserDetails;
import model.UpdatedUserDetailsErrorMsgs;
import model.UserDetailsErrorMsgs;
import model.Users;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UpdatedUserDetails userdetails = new UpdatedUserDetails();
		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();
		String action = request.getParameter("action"), url = "";

		if (action.equalsIgnoreCase("update")) {
			String userName = request.getParameter("username");
			Integer userId = Integer.valueOf(request.getParameter("userId"));
			userdetails.setUserID(userId);
			url = handleUpdate(request, action, userName, session, userdetails, errorMsgs);
		} else {
			String type = request.getParameter("type");
			if (type != null) {
				if (type.equals("UserName")) {
					String value = request.getParameter("value");
					url = "/EditProfile.jsp?username=" + value;
				}
			}else{
				url = "/UpdateSelect.jsp";
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	private void getUpdatedUserDetailsParam(HttpServletRequest request, UpdatedUserDetails updatedUserdetails) {
		updatedUserdetails.setUpdatedUserDetails(request.getParameter("firstName"), request.getParameter("middleName"),
				request.getParameter("lastName"), request.getParameter("username"), request.getParameter("sex"),
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
			userdetails.setUserName(user.getUsername());
		getUpdatedUserDetailsParam(request, userdetails);
		userdetails.validateUserDetails(action, userdetails, errorMsgs);
		session.setAttribute("userdetails", userdetails);
		if (!errorMsgs.getErrorMsg().equals("")) {
			getUpdatedUserDetailsParam(request, userdetails);
			session.setAttribute("updatedUserDetailsErrorMsgs", errorMsgs);
			url = "/EditProfile.jsp?username=" + userName;
		} else {
			// if no error messages
			boolean isSuccessful = UpdatedUserDetailsDAO.updateUser(userdetails);
			request.setAttribute("isSuccessful", isSuccessful);
			request.setAttribute("username", userName);

			UpdatedUserDetailsErrorMsgs errorMsgsuser = new UpdatedUserDetailsErrorMsgs();
			session.setAttribute("updatedUserDetailsErrorMsgs", errorMsgsuser);
			url = "/AdminHomePage.jsp?username=" + userName;
		}
		return url;
	}

}