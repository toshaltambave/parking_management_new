package controller;

import java.io.IOException;
import java.util.ArrayList;
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



@WebServlet("/UserDetailsController")
public class UserDetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	private void getUserDetailsParam (HttpServletRequest request, UserDetails userdetails)
	{	
					userdetails.setUserDetails(request.getParameter("firstname"), request.getParameter("middlename"),
					 request.getParameter("lastname"),request.getParameter("sex"),
					 request.getParameter("dob"), request.getParameter("address"),request.getParameter("email")
					 ,request.getParameter("phone"),request.getParameter("dlno"),request.getParameter("dlexpirydte")
					 ,request.getParameter("regno"),request.getParameter("utaid"));
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("listUsers")) {
			ArrayList<Users> usersInDB = new ArrayList<Users>();
			usersInDB= UsersDAO.listUsers(); 
			session.setAttribute("USERS", usersInDB);				
			getServletContext().getRequestDispatcher("/listUser.jsp").forward(request, response);
		} else if(action.equalsIgnoreCase("search")){
			String type = request.getParameter("type");
			String query = request.getParameter("query");
			
			if ("UserName".equals(type)) {
				List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
				userDetailsList = UserDetailsDAO.searchByUsername(query);
				request.setAttribute("details", userDetailsList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/userSearch.jsp");
				dispatcher.forward(request, response);
			} else if ("LastName".equals(type)) {
				List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
				userDetailsList = UserDetailsDAO.searchByLastName(query);
				request.setAttribute("details", userDetailsList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/userSearch.jsp");
				dispatcher.forward(request, response);
			}
		} else // redirect all other gets to post
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();		
		UserDetails userdetails = new UserDetails();
		UserDetailsErrorMsgs errorMsgs = new UserDetailsErrorMsgs();

		if (action.equalsIgnoreCase("saveUserDetails") )
		{  
			Users user = (Users) session.getAttribute("user");
			if(user != null && !user.getUsername().isEmpty())
				userdetails.setUsername(user.getUsername());
			getUserDetailsParam(request,userdetails);
			userdetails.validateUserDetails(action,userdetails,errorMsgs);
			session.setAttribute("userdetails", userdetails);
			if (!errorMsgs.getErrorMsg().equals("")) 
			{
				getUserDetailsParam(request,userdetails);
				session.setAttribute("userDetailsErrorMsgs", errorMsgs);
				url="/formUserDetails.jsp";
			}
			else 
			{// if no error messages
				Boolean isSuccess = false;
				isSuccess = UserDetailsDAO.insertUserDetails(userdetails);
				if(isSuccess)
				{
					request.setAttribute("isSuccess", isSuccess);
					UserDetailsErrorMsgs errorMsgsuser = new UserDetailsErrorMsgs();
					session.setAttribute("userDetailsErrorMsgs", errorMsgsuser);
					url="/index.jsp";
				}
			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
	
}
