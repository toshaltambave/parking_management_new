package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
			listCreditCardTypes(request, response);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReserveComplete.jsp");
	        dispatcher.forward(request, response);
		}
		
		if (action.equalsIgnoreCase("makeReservation") ) {  
			Boolean isReservationSuccessful = true;
			HttpSession session = request.getSession();		
			double totalAmount = Double.parseDouble(request.getParameter("total"));
			request.setAttribute("finalTotal", totalAmount );
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
				errorMsgs = validatecreditcarddetails(cardNumber,expMonth,expYear,cardType,cvv,errorMsgs);
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
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/parkingUserHomePage.jsp");
			        dispatcher.forward(request, response);
				}
			}
			else
			{
				isReservationSuccessful = storeReservation(session, user);	
				request.setAttribute("isReservationSuccessful", isReservationSuccessful);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/parkingUserHomePage.jsp");
		        dispatcher.forward(request, response);
				
			}
		}

    }

	private CreditCardError validatecreditcarddetails(String cardNumber, String expMonth, String expYear, String cardType,
			String cvv,CreditCardError errorMsgs) {
		errorMsgs.setCardNumberError(validateCardNumber(cardNumber,cardType));
		errorMsgs.setCvvError(validateCVV(cvv));
		errorMsgs.setMonthError(validateMonth(expMonth));
		errorMsgs.setYearError(validateYear(expYear));
		errorMsgs.setErrorMsg("error");
		
		return errorMsgs;
	}

	private String validateYear(String expYear) {
		if(expYear.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(expYear,4,4))
			{
				return "Year must be 4 digits.";
			}
			else
			{
				if(!isTextAnInteger(expYear))
					return "Year must only digits.";
				else
					return "";
			}
		}
	}
	
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}

	private String validateMonth(String expMonth) {
		if(expMonth.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(expMonth,2,2))
			{
				return "Month must be 2 digits.";
			}
			else
			{
				if(!isTextAnInteger(expMonth))
					return "Month must only digits.";
				else
				{
					int month = Integer.parseInt(expMonth);
					if(month > 12)
						return "Month can only be between 01 to 12";
					else
						return "";
				}
			}
		}
	}

	private String validateCVV(String cvv) {
		if(cvv.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(cvv,3,3))
			{
				return "CVV must be 3 digits.";
			}
			else
			{
				if(!isTextAnInteger(cvv))
					return "CVV must only digits.";
				else
					return "";
			}
		}
	}

	private String validateCardNumber(String cardNumber, String cardType) {
		if(cardNumber.isEmpty())
			return "This field is required.";
		else
		{
			if(!isTextAnInteger(cardNumber))
				return "Card number must only digits.";
			else
			{
				if(cardType.equalsIgnoreCase("VISA") || cardType.equalsIgnoreCase("MASTERCARD") || cardType.equalsIgnoreCase("DISCOVER"))
				{
					if (!stringSize(cardNumber,16,16))
					{
						return "Card number must be 16 digits.";
					}
					else
					{ 
						if(cardType.equalsIgnoreCase("VISA"))
						{
							if(!cardNumber.substring(0,1).equalsIgnoreCase("4"))
							{
								return "This is not a VISA card as it starts with 4.";
							}
							else
								return "";
						}
						else if (cardType.equalsIgnoreCase("MASTERCARD"))
						{
							if(!(cardNumber.substring(0,2).equalsIgnoreCase("51")
									|| cardNumber.substring(0,2).equalsIgnoreCase("52")
									|| cardNumber.substring(0,2).equalsIgnoreCase("53")
									|| cardNumber.substring(0,2).equalsIgnoreCase("54")
									|| cardNumber.substring(0,2).equalsIgnoreCase("55")))
							{
								return "This is not a Master card as it starts with 51/52/53/54/55.";
							}
							else
								return "";
						}
						else if (cardType.equalsIgnoreCase("DISCOVER"))
						{
							if(!(cardNumber.substring(0,4).equalsIgnoreCase("6011")
									|| cardNumber.substring(0,2).equalsIgnoreCase("65")))
									return "This is not a Discover card as it starts with 6011/65.";						
								else
									return "";
									
						}
					}
				}
				else if(cardType.equalsIgnoreCase("AMEX"))
				{
					if (!stringSize(cardNumber,15,15))
					{
						return "Card number must be 15 digits.";
					}
					else
					{
						if(!(cardNumber.substring(0,2).equalsIgnoreCase("37")
								|| cardNumber.substring(0,2).equalsIgnoreCase("34")))
							return "This is not a AMEX card as it starts with 34/37.";						
						else
							return "";
					}
				}
			}
			return "";
		}
	}

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
		Boolean isReservationSuccessful = ReservationsDAO.StoreReservationsInDB(reserve);
		return isReservationSuccessful;
	}
	
	protected void listCreditCardTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<CreditCardTypes> listCreditCardTypes = new ArrayList<CreditCardTypes>(Arrays.asList(CreditCardTypes.values()));
			request.setAttribute("allCardTypes", listCreditCardTypes);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
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
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("User");
			Boolean isRevoked = MakeReservationsDOA.CheckRevoked(user.getUserID());
			Integer numberOfReservations = MakeReservationsDOA.CountReservationsInDay(user.getUserID());
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
			  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
