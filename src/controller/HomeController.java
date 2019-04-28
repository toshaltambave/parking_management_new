package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import data.*;
import data.ReservationsDAO;
import model.*;


@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String url = "";
		if(session.getAttribute("User") != null)
		{
			Users user = (Users) session.getAttribute("User");
			
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
		getServletContext().getRequestDispatcher(url).forward(req, resp);

	}


	
}
