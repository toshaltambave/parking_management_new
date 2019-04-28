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

import data.*;
import model.*;

@WebServlet("/ModifyController")
public class ModifyController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String action = request.getParameter("action");        
        if(action.equalsIgnoreCase("setNoshow"))
        {
        	listNSReservations(request,response);
        }
        else if (action.equalsIgnoreCase("viewReservations"))
        {
        	listUserReservations(request,response);
        }
        else if (action.equalsIgnoreCase("viewNoShowViolation"))
        {
        	listUVReservations(request,response);
        }	
        else
        {
        	listODReservations(request,response);
        }
        
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{
    	int reservation_ID = Integer.parseInt(request.getParameter("reservationID"));
    	String username = request.getParameter("selectedUsername");
    	Integer userId = UsersDAO.getUserIdbyUsername(username);
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("setNoshow")) 
        { 
        	Boolean Result = MakeReservationsDAO.SetNoShow(reservation_ID, userId);
        	request.setAttribute("isNoShow", Result);
        	listNSReservations(request, response);	
        }
        else
        { 
        	Boolean Result = MakeReservationsDAO.SetOverdue(reservation_ID, userId);
        	request.setAttribute("isOverDue", Result);
        	listODReservations(request, response);	
        }        
    }
	
	
	private void listUserReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute("User");
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDAO.GetReservationsByUserId(user.getUserID());
			request.setAttribute("allreservations", allReservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ReservationsByUserId.jsp");
            dispatcher.forward(request, response);
    }
	
	private void listUVReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute("User");
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDAO.GetReservationsViolations(currentTime,user.getUserID());
			Integer count=allReservations.size();
			request.setAttribute("allreservations", allReservations);
			request.setAttribute("count", count);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/UserViolations.jsp");
            dispatcher.forward(request, response);
    }
	
	private void listNSReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);

			ArrayList<ReservationsHelper> allReservations = MakeReservationsDAO.GetReservationsByReservationNoShow(currentTime);
			request.setAttribute("allreservations", allReservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SetNoShow.jsp");
            dispatcher.forward(request, response);
    }
	
	
	private void listODReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
			ArrayList<ReservationsHelper> allReservations = MakeReservationsDAO.GetReservationsByReservationNoShow(currentTime);
			request.setAttribute("allreservations", allReservations);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/SetOverDue.jsp");
            dispatcher.forward(request, response);
    }
	

}