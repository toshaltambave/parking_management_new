package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.FetchParkingSpotsDAO;
import data.MakeReservationsDOA;
import data.ReservationsDAO;
import data.UsersDAO;
import model.*;

@WebServlet("/ModifyReservationController")
public class ModifyReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");	
		
		if(action != null)
		{
			if (action.equalsIgnoreCase("editReservation")) {
				showReservationsForEdit(request, response);
			}
			
		}
		showRelevantReservations(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{
        String action = request.getParameter("action");
		if (action.equalsIgnoreCase("deleteReservation") ) {  
			int resId = Integer.parseInt(request.getParameter("reservationID"));
			Boolean result = ReservationsDAO.deleteReservationbyResId(resId);
			request.setAttribute("isNoShow", result);
			showRelevantReservations(request, response);
		}
		
		if (action.equalsIgnoreCase("editReservation") ) { 
			HttpSession session = request.getSession();
			int resId = Integer.parseInt(request.getParameter("reservationID"));
			session.setAttribute("editReservationId", resId);
			ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
			request.setAttribute("Areas", allAreas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve.jsp");
            dispatcher.forward(request, response);
			
			
		}

    }
	
	private void showRelevantReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("User");
			if(user.getRole().equals("ParkingUser")){
				ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByUserId(user.getUserID());
				request.setAttribute("allreservations", allReservations);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteReservation.jsp");
	            dispatcher.forward(request, response);
			}
			else if(user.getRole().equals("ParkingManager")){
				String timeStamp = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(Calendar.getInstance().getTime());
				ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByReservationDate(timeStamp);
				request.setAttribute("allreservations", allReservations);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/DeleteReservation.jsp");
	            dispatcher.forward(request, response);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }

	private void showReservationsForEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("User");
			if(user.getRole().equals("ParkingUser")){
				ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByUserId(user.getUserID());
				request.setAttribute("allreservations", allReservations);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditReservation.jsp");
	            dispatcher.forward(request, response);
			}
			else if(user.getRole().equals("ParkingManager")){
				String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
				ArrayList<ReservationsHelper> allReservations = MakeReservationsDOA.GetReservationsByReservationDate(timeStamp);
				request.setAttribute("allreservations", allReservations);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EditReservation.jsp");
	            dispatcher.forward(request, response);
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
}
