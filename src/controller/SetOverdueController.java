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

@WebServlet("/SetOverdueController")
public class SetOverdueController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listReservations(request, response);	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("setOverDue") ) { 
        	int reservation_ID = Integer.parseInt(request.getParameter("reservationID"));
        	Users user = (Users)session.getAttribute("User");
        	Boolean Result = MakeReservationsDOA.SetOverdue(reservation_ID, user.getUserID());
        	request.setAttribute("isOverDue", Result);
        	listReservations(request, response);	
        }
    }
	

	private void listReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		try 
		{
			
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByReservationNoShow(currentTime);
			request.setAttribute("allreservations", allReservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SetOverDue.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }


}