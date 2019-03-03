package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UpdatedUserDetails;
import model.UpdatedUserDetailsErrorMsgs;
import model.Users;

@WebServlet("/UpdatedUserDetailsController")
public class UpdateUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private void getUpdatedUserDetailsParam(HttpServletRequest request, UpdatedUserDetails userdetails) {
		userdetails.setUpdatedUserDetails(request.getParameter("firstName"), request.getParameter("middleName"),
				request.getParameter("lastName"), request.getParameter("sex"), request.getParameter("dob"),
				request.getParameter("address"), request.getParameter("email"), request.getParameter("phone"),
				request.getParameter("dlno"), request.getParameter("dlexpirydte"), request.getParameter("regno"),
				request.getParameter("utaid"), request.getParameter("hashpass"), request.getParameter("confirmpass"),
				request.getParameter("role"), Integer.valueOf(request.getParameter("isrevoked")),
				request.getParameter("permitType"));
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action"), url = "";
		HttpSession session = request.getSession();
		UpdatedUserDetails userdetails = new UpdatedUserDetails();
		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();

		if (action.equalsIgnoreCase("saveUserDetails")) {
			Users user = (Users) session.getAttribute("user");
			if (user != null && !user.getUsername().isEmpty())
				userdetails.setUsername(user.getUsername());
			getUpdatedUserDetailsParam(request, userdetails);
			userdetails.validateUserDetails(action, userdetails, errorMsgs);
			session.setAttribute("userdetails", userdetails);
			if (!errorMsgs.getErrorMsg().equals("")) {
				getUpdatedUserDetailsParam(request, userdetails);
				session.setAttribute("userDetailsErrorMsgs", errorMsgs);
				url = "/formUserDetails.jsp";
			} else {// if no error messages

			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
