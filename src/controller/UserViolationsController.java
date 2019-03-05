package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.MakeReservationsDOA;
import model.*;

@WebServlet("/UserViolationsController")
public class UserViolationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listReservations(request, response);	
	}

	private void listReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		HttpSession session = request.getSession();
		try 
		{
			Users user = (Users)session.getAttribute("User");
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsViolations(currentTime,user.getUserID());
			Integer count=allReservations.size();
			request.setAttribute("allreservations", allReservations);
			request.setAttribute("count", count);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserViolations.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }


}