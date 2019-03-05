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

@WebServlet("/GetReservationByUser")
public class GetReservationByUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listReservations(request, response);	
	}
	private void listReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		try 
		{
			Users user = (Users)session.getAttribute("User");
			user.getUserID();
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByUserId(user.getUserID());
			request.setAttribute("allreservations", allReservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ReservationsByUserId.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
}