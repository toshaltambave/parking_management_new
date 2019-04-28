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
		if (action.equalsIgnoreCase("Search") )
		{  
			String startTime = request.getParameter("start_time");		
			String endTime = request.getParameter("end_time");
			ReservationError error = new ReservationError();
			Reservation reservation = new Reservation();
			
			reservation.validateDateTime(startTime,endTime, error);
			request.setAttribute("endTimeError", error.getEndTimeError());
			request.setAttribute("startTimeError", error.getStartTimeError());
			request.setAttribute("compareError", error.getCompareError());
			
			if(error.getErrorMsg().equals(""))
			{
				int areaId = Integer.parseInt(request.getParameter("areaDropDrown"));
		        request.setAttribute("selectedAreaId", areaId);
		        listFloorsAndAvailableSpots(request, response, areaId, startTime, endTime);
			}
			else
			{
				reservationLandingPage(request, response);	
			}

		}
		// 2nd Page - Floor & Permit Type
		else if (action.equalsIgnoreCase("getSpotsForFloor") ) {  
			int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
			int selectedFloorNumber = Integer.parseInt(request.getParameter("selectedFloorNumber"));
			String selectedPermitType = request.getParameter("selectedPermitType");
			String selectedStartTime = request.getParameter("selectedStartTime");
			String selectedEndTime = request.getParameter("selectedEndTime");
	        request.setAttribute("selectedAreaId", areaId);
	        listSpotsForSelectedFloor(request, response, areaId, selectedFloorNumber, selectedPermitType, selectedStartTime, selectedEndTime);
		}
		
		// Get Spot UID & Spot Id 
		else if (action.equalsIgnoreCase("startReservation") ) {  
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
		else if (action.equalsIgnoreCase("checkout") ) {  
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
			listCreditCardTypes(request, response);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReserveComplete.jsp");
	        dispatcher.forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("makeReservation") ) {  
			Boolean isReservationSuccessful = true;
			HttpSession session = request.getSession();		
			double totalAmount = Double.parseDouble(request.getParameter("total"));
			request.setAttribute("finalTotal", totalAmount );
			session.setAttribute("finalTotal", totalAmount );
			Users user = (Users)session.getAttribute("User");
			
			if(totalAmount > 0.0)
			{
				String cardNumber = request.getParameter("cardNumber");
				String expMonth = request.getParameter("expiryMonth");
				String expYear = request.getParameter("expiryYear");
				String cardType = request.getParameter("cardType");
				String cvv = request.getParameter("cvvCode");
				CreditCard creditcard = new CreditCard();
				creditcard.setCardNumber(cardNumber);
				creditcard.setCardType(cardType);
				creditcard.setCvv(cvv);
				creditcard.setMonth(expMonth);
				creditcard.setYear(expYear);
		        request.setAttribute("creditcard", creditcard);
				CreditCardError errorMsgs = new CreditCardError();
				creditcard.validatecreditcarddetails(creditcard,errorMsgs);
				if (!errorMsgs.getErrorMsg().equals(""))
				{
					session.setAttribute("creditcarderrorMsgs", errorMsgs);
			        request.setAttribute("selectedcreditcard", cardType);
					String permitType = request.getParameter("permitType");
			        request.setAttribute("selectedpermitType", permitType);

					listCreditCardTypes(request, response);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReserveComplete.jsp");
			        dispatcher.forward(request, response);
				}
				
				else
				{
					isReservationSuccessful = storeReservation(session, user);
					request.setAttribute("isReservationSuccessful", isReservationSuccessful);
					String url = "";
					if (user.getRole().equals("ParkingManager")){
						url="/parkingManagementHomePage.jsp";
					}
					else
					{
						url="/parkingUserHomePage.jsp";
					}
			        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			        dispatcher.forward(request, response);
				}
			}
			else
			{
				isReservationSuccessful = storeReservation(session, user);	
				request.setAttribute("isReservationSuccessful", isReservationSuccessful);
				String url = "";
				if (user.getRole().equals("ParkingManager")){
					url="/parkingManagementHomePage.jsp";
				}
				else
				{
					url="/parkingUserHomePage.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		        dispatcher.forward(request, response);
				
			}
		}
		else
		{
			System.out.println("Do Nothing.");
		}		

    }

//	public String validateDateTime(String startTime, String endTime,HttpServletRequest request) {
//		String startTimeError ="";
//		String endTimeError ="";
//		String compareError ="";
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		if(startTime.isEmpty())
//		{
//			startTimeError = "This field is required.";
//		}
//		else
//		{
//			try
//			{
//			Date startdate = formatter.parse(startTime);	
//			Date date = new Date();
//			int startHours = startdate.getHours();
//			int startMins =  startdate.getMinutes();
//			int currentHours = date.getHours();
//			int currentMins = date.getMinutes();
//				if(startHours < currentHours)
//				{
//					startTimeError = "Start time cannot be before current time.";
//				}
//				else
//				{
//					if(startHours == currentHours && startMins < currentMins)
//					{
//						startTimeError = "Start time cannot be before current time.";
//					}
//					else
//					{
//					    startTimeError ="";	
//					}
//				}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		}
//		if(endTime.isEmpty())
//		{
//			endTimeError = "This field is required.";
//		}
//		else
//		{
//			try
//			{
//			Date enddate = formatter.parse(endTime);
//			Date date  = new Date();
//			int endHours = enddate.getHours();
//			int endMins =  enddate.getMinutes();
//			int currentHours = date.getHours();
//			int currentMins = date.getMinutes();
//			
//			if(endHours < currentHours)
//			{
//				endTimeError = "End time cannot be before current time.";
//			}
//			else
//			{
//				if(endHours == currentHours && endMins < currentMins)
//				{
//					endTimeError = "End time cannot be before current time.";
//				}
//				else
//				{
//					endTimeError ="";	
//				}
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		}
//		if(!endTime.isEmpty() && !startTime.isEmpty())
//		{
//			Date enddate;
//			try {
//				enddate = formatter.parse(endTime);
//			
//			Date startdate;
//				startdate = formatter.parse(startTime);
//			int endHours = enddate.getHours();
//			int endMins =  enddate.getMinutes();
//			int startHours = startdate.getHours();
//			int startMins =  startdate.getMinutes();
//			int diffHours = endHours - startHours;
//			int diffMins = (endHours*60 + endMins) - (startHours*60 + startMins);
//			
//			
//			if(startdate.after(enddate))
//			{
//				compareError = "Start time cannot be after end time.";
//			}
//			else if(startdate.equals(enddate))
//			{
//				compareError = "Start time and end time cannot be same.";
//			}
//			else if(diffHours >3)
//			{
//				compareError = "Reservation cannot be for more than 3 hours.";
//			}
//			//TODO: Maybe remove this  
//			else if(diffMins >180)
//			{
//				compareError = "Reservation cannot be for more than 3 hours.";
//			}
//			else
//			{
//				compareError = "";
//			}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			
//		}
//		
//		if(request != null){
//			request.setAttribute("endTimeError", endTimeError);
//			request.setAttribute("startTimeError", startTimeError);
//			request.setAttribute("compareError", compareError);
//		}
//		
//		if(!compareError.isEmpty() || !startTimeError.isEmpty() || !endTimeError.isEmpty())
//			return "There are time errors.";
//		else
//			return "";
//	}

	
	private Boolean storeReservation(HttpSession session, Users user) {
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
		Integer resIdForEdit = (Integer) session.getAttribute("editReservationId");
		Boolean previousReservationDeleted = false;
		reserve.setTotal((Double)(session.getAttribute("finalTotal")));
		if(resIdForEdit != null){
			previousReservationDeleted = ReservationsDAO.deleteReservationbyResId(resIdForEdit);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
		Boolean isReservationSuccessful = ReservationsDAO.StoreReservationsInDB(reserve);
		return isReservationSuccessful;
	}
	
	protected void listCreditCardTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ArrayList<CreditCardTypes> listCreditCardTypes = new ArrayList<CreditCardTypes>(Arrays.asList(CreditCardTypes.values()));
		request.setAttribute("allCardTypes", listCreditCardTypes);

    }

	private double calculateTotal(String cart, String camera, String history, double cart_price) {
		double total = 0.0;
        double tax = 1.0825;
		if(cart.equals("true")){
			total += cart_price;
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
		if(camera.equals("true")){
			total += 2.95;
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
		if(history.equals("true")){
			total += 1.95;
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
		return total = total * tax;
	}
	
	private void reservationLandingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("User");
		Boolean isRevoked = MakeReservationsDAO.CheckRevoked(user.getUserID());
		Integer numberOfReservations = MakeReservationsDAO.CountReservationsInDay(user.getUserID());
		if(isRevoked == true){
			request.setAttribute("isRevoked", isRevoked );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/parkingUserHomePage.jsp");
            dispatcher.forward(request, response);
		}
		else if(numberOfReservations >= 3){
			request.setAttribute("isMax", true );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/parkingUserHomePage.jsp");
            dispatcher.forward(request, response);
		}
		else{
			ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
			request.setAttribute("Areas", allAreas);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reserve.jsp");
            dispatcher.forward(request, response);
		}
		

    }
	
	private void listFloorsAndAvailableSpots(HttpServletRequest request, HttpServletResponse response, Integer areaId, String startTime, String endTime) throws ServletException, IOException 
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
			  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startdate = formatter.parse(startTime);
			Date enddate = formatter.parse(endTime);
			double cartPrice = 15.95;
			double cameraPrice = 2.95;
			double historyPrice = 1.95;
			Reservation res = new Reservation();
			boolean normalHours = res.checkNormalHours(startdate, enddate);
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

//	private boolean checkNormalHours(Date startdate, Date enddate) {
//		int startDay = startdate.getDay();
//		int startHours = startdate.getHours();
//		int endHours = enddate.getHours();
//		boolean normalHours = true;
//		if(5 <= startDay && startDay >=1 ){
//			//Monday to Friday
//			if(startHours >= 6 && (endHours <= 19 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
//				// 6am to 7.59pm
//				return normalHours;
//			}
//			else{
//				normalHours = false;
//				return normalHours;
//			}
//		}
//		else if(startDay == 6 ){
//			//Saturday
//			if(startHours >= 8 && (endHours <= 4 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
//				// 8am to 4.59pm
//				return normalHours;
//			}
//			else{
//				normalHours = false;
//				return normalHours;
//			}
//		}
//		else{
//			//Sunday
//			if(startHours >= 12 && (endHours <= 4 && enddate.getMinutes() <= 59 && enddate.getSeconds() <= 59)){
//				// 8am to 4.59pm
//				return normalHours;
//			}
//			else{
//				normalHours = false;
//				return normalHours;
//			}
//		}
//	}
	
	
}
