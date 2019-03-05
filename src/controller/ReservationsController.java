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
import java.text.SimpleDateFormat;  
import java.util.Date;  
import data.FetchParkingSpotsDAO;
import data.ReservationsDAO;
import model.*;

@WebServlet("/ReservationsController")
public class ReservationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		reservationLandingPage(request, response);	
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		//1st page - Get Start End Times & Area
		if (action.equalsIgnoreCase("Search") ) {  
			//TODO: Implement Request Spot , add Permit Type & Cart Attributes
			String startTime = request.getParameter("start_time");
			
			String endTime = request.getParameter("end_time");
			int areaId = Integer.parseInt(request.getParameter("areaDropDrown"));
	        request.setAttribute("selectedAreaId", areaId);
	        listFloorsAndAvailableSpots(request, response, areaId, startTime, endTime);

		}
		
		// 2nd Page - Floor & Permit Type
		if (action.equalsIgnoreCase("getSpotsForFloor") ) {  
			int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
			int selectedFloorNumber = Integer.parseInt(request.getParameter("selectedFloorNumber"));
			String selectedPermitType = request.getParameter("selectedPermitType");
			String selectedStartTime = request.getParameter("selectedStartTime");
			String selectedEndTime = request.getParameter("selectedEndTime");
	        request.setAttribute("selectedAreaId", areaId);
	        listSpotsForSelectedFloor(request, response, areaId, selectedFloorNumber, selectedPermitType, selectedStartTime, selectedEndTime);
		}
		
		// Get Spot UID & Spot Id 
		if (action.equalsIgnoreCase("startReservation") ) {  
			int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
			int selectedFloorNumber = Integer.parseInt(request.getParameter("selectedFloorNumber"));
			String selectedPermitType = request.getParameter("selectedPermitType");
			int spotUID = Integer.parseInt(request.getParameter("selectedSpotUID"));
			int spotId = Integer.parseInt(request.getParameter("selectedSpotId"));
			String selectedStartTime = request.getParameter("selectedStartTime");
			String selectedEndTime = request.getParameter("selectedEndTime");
	        includeOptions(request, response, areaId, selectedFloorNumber, selectedPermitType, spotUID, spotId, selectedStartTime, selectedEndTime);
		}
		
		// Get Options selected 
		if (action.equalsIgnoreCase("checkout") ) {  
			HttpSession session = request.getSession();
			String cart = request.getParameter("cart");
			String camera = request.getParameter("camera");
			String history = request.getParameter("history");
			session.setAttribute("cart", cart);
			session.setAttribute("camera", camera);
			session.setAttribute("history", camera);
			
	        double cart_price = Double.parseDouble(request.getParameter("cart_price"));
	        double total = calculateTotal(cart, camera, history, cart_price);
	        if(cart.equals("true")){
	        	request.setAttribute("finalCartPrice", cart_price );
			}
	        else
	        	request.setAttribute("finalCartPrice", 0.0 );
			if(camera.equals("true")){
				request.setAttribute("finalCameraPrice", 2.95 );
			}
			else
				request.setAttribute("finalCameraPrice", 0.0 );
			if(history.equals("true")){
				request.setAttribute("finalHistoryPrice", 1.95 );
			}
			else
				request.setAttribute("finalHistoryPrice", 0.0 );
			request.setAttribute("finalTotal", total );
			request.setAttribute("finalTax", total * 0.0825 );
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReserveComplete.jsp");
	        dispatcher.forward(request, response);
		}
		
		if (action.equalsIgnoreCase("makeReservation") ) {  
			HttpSession session = request.getSession();		
			Users user = (Users)session.getAttribute("User");
			String cardNumber = request.getParameter("cardNumber");
			Integer expMonth = Integer.parseInt(request.getParameter("expityMonth"));
			Integer expYear = Integer.parseInt(request.getParameter("expityYear"));
			String cardType = request.getParameter("cardType");
			Integer cvv = Integer.parseInt(request.getParameter("cvvCode"));
			Reservation reserve = new Reservation();
			if(((String)session.getAttribute("camera")).equals("true")){
				reserve.setCamera(true);
			}
			else
				reserve.setCamera(false);
			if(((String)session.getAttribute("cart")).equals("true")){
				reserve.setCart(true);
			}
			else
				reserve.setCart(false);
			if(((String)session.getAttribute("history")).equals("true")){
				reserve.setHistory(true);
			}
			else
				reserve.setHistory(false);
			reserve.setEndTime((String)session.getAttribute("resselectedEndTime"));
			reserve.setStartTime((String)session.getAttribute("resselectedStartTime"));
			reserve.setNoShow(false);
			reserve.setOverStay(false);
			reserve.setSpotUID((Integer)session.getAttribute("resspotUID"));
			reserve.setUserID(user.getUserID());
			ReservationsDAO.StoreReservationsInDB(reserve);
			
			Integer intnt = Integer.parseInt((String)session.getAttribute("resspotUID"));
			

		}

    }

	private double calculateTotal(String cart, String camera, String history, double cart_price) {
		double total = 0.0;
        double tax = 1.0825;
		if(cart.equals("true")){
			total += cart_price;
		}
		if(camera.equals("true")){
			total += 2.95;
		}
		if(history.equals("true")){
			total += 1.95;
		}
		return total = total * tax;
	}
	
	private void reservationLandingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
			request.setAttribute("Areas", allAreas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	private void listFloorsAndAvailableSpots(HttpServletRequest request, HttpServletResponse response, Integer areaId, String startTime, String endTime) throws ServletException, IOException 
	{
		try 
		{
			HttpSession session = request.getSession();
			ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
			Users user = (Users) session.getAttribute("User");
			ArrayList<ParkingAreaFloors> floorDetails = ReservationsDAO.getFloorSpotsCountByTimeFiltered(areaId, startTime, endTime, user.getPermitType());
			request.setAttribute("selectedArea", selectedArea);
			request.setAttribute("allFloors", floorDetails);
			request.setAttribute("startTime", startTime);
			request.setAttribute("endTime", endTime);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve_Floor.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	private void listSpotsForSelectedFloor
	(	
			HttpServletRequest request, 
			HttpServletResponse response, 
			Integer areaId, 
			Integer floorNumber, 
			String permitType ,
			String selectedStartTime ,
			String selectedEndTime
	) throws ServletException, IOException 
	{
		try 
		{
			ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
			ArrayList<ParkingSpots> spotsList = ReservationsDAO.getSpotsByAreaFloorPermitFromDb(areaId, floorNumber, permitType, selectedStartTime, selectedEndTime);
			request.setAttribute("selectedArea", selectedArea);
			request.setAttribute("selectedFloorNumber", floorNumber);
			request.setAttribute("selectedPermitType", permitType);
			request.setAttribute("selectedStartTime", selectedStartTime);
			request.setAttribute("selectedEndTime", selectedEndTime);
			request.setAttribute("spotsList", spotsList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve_Spot.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	private void includeOptions
	(HttpServletRequest request, 
			HttpServletResponse response,
			Integer areaId,
			Integer selectedFloorNumber,
			String selectedPermitType,
			Integer spotUID,
			Integer spotID,
			String startTime, 
			String endTime)throws ServletException, IOException
	{
		try{
			HttpSession session = request.getSession();
			ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
			session.setAttribute("resSelectedArea", selectedArea);
			session.setAttribute("resselectedFloorNumber", selectedFloorNumber);
			session.setAttribute("resselectedPermitType", selectedPermitType);
			session.setAttribute("resspotUID", spotUID);
			session.setAttribute("resspotID", spotID);
			session.setAttribute("resselectedStartTime", startTime);
			session.setAttribute("resselectedEndTime", endTime);
			  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
			Date startdate = formatter.parse(startTime);
			Date enddate = formatter.parse(endTime);
			double cartPrice = 15.95;
			double cameraPrice = 2.95;
			double historyPrice = 1.95;
			boolean normalHours = checkNormalHours(startdate, enddate);
			if(normalHours){
				request.setAttribute("cartPrice", cartPrice );
				request.setAttribute("cameraPrice", cameraPrice );
				request.setAttribute("historyPrice", historyPrice );
			}
			else{
				request.setAttribute("cartPrice", 2 * cartPrice );
				request.setAttribute("cameraPrice", cameraPrice );
				request.setAttribute("historyPrice", historyPrice );
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve_Options.jsp");
	        dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	private boolean checkNormalHours(Date startdate, Date enddate) {
		int startDay = startdate.getDay();
		int startHours = startdate.getHours();
		int endHours = enddate.getHours();
		boolean normalHours = true;
		if(5 <= startDay && startDay >=1 ){
			//Monday to Friday
			if(startHours >= 6 && (endHours <= 19 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
				// 6am to 7.59pm
				return normalHours;
			}
			else{
				normalHours = false;
				return normalHours;
			}
		}
		else if(startDay == 6 ){
			//Saturday
			if(startHours >= 8 && (endHours <= 4 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
				// 8am to 4.59pm
				return normalHours;
			}
			else{
				normalHours = false;
				return normalHours;
			}
		}
		else{
			//Sunday
			if(startHours >= 12 && (endHours <= 4 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
				// 8am to 4.59pm
				return normalHours;
			}
			else{
				normalHours = false;
				return normalHours;
			}
		}
	}
	
	
}
