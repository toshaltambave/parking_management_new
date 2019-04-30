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
@WebServlet("/ParkingSpotsController")
public class ParkingSpotsController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listAreas(request, response);	
	}

	private void listAreas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
		request.setAttribute("Areas", allAreas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ParkingArea.jsp");
        dispatcher.forward(request, response);

    }
	private void listFloorsForSelectedArea(HttpServletRequest request, HttpServletResponse response, Integer areaId) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
		Users user = (Users) session.getAttribute("User");
		ArrayList<ParkingAreaFloors> floorDetails = FetchParkingSpotsDAO.getFilteredFloorsbyParkingAreaId(areaId, user.getPermitType());
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("allFloors", floorDetails);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ParkingSpotFloors.jsp");
        dispatcher.forward(request, response);

    }
	private void listSpotsForSelectedFloor
	(HttpServletRequest request, HttpServletResponse response, Integer areaId, Integer floorNumber,String permitType ) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();

		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
		ArrayList<ParkingSpots> spotsList = FetchParkingSpotsDAO.getSpotsByAreaFloorPermitFromDb(areaId, floorNumber, permitType);
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("selectedFloorNumber", floorNumber);
		request.setAttribute("selectedPermitType", permitType);
		request.setAttribute("spotsList", spotsList);
		session.setAttribute("selectedArea", selectedArea);
		session.setAttribute("selectedFloorNumber", floorNumber);
		session.setAttribute("selectedPermitType", permitType);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ParkingSpots.jsp");
        dispatcher.forward(request, response);

    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		        String action = request.getParameter("action");
		if (action.equalsIgnoreCase("getSelectedArea") )
		{  
			int areaId = Integer.parseInt(request.getParameter("areaDropDrown"));
	        request.setAttribute("selectedAreaId", areaId);
	        listFloorsForSelectedArea(request, response, areaId);
		}
		else if (action.equalsIgnoreCase("getSpotsForFloor") ) {  
			int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
			int selectedFloorNumber = Integer.parseInt(request.getParameter("selectedFloorNumber"));
			String selectedPermitType = request.getParameter("selectedPermitType");
	        request.setAttribute("selectedAreaId", areaId);
	        session.setAttribute("selectedAreaId", areaId);
	        listSpotsForSelectedFloor(request, response, areaId, selectedFloorNumber, selectedPermitType);
		}
		else if(action.equalsIgnoreCase("toggleBlock")){
			int spotUID = Integer.parseInt(request.getParameter("selectedSpotUId"));
			int isBlocked = convertBoolToInt(request.getParameter("isBlocked"));
			int areaId = (int)session.getAttribute("selectedAreaId");
			int selectedFloorNumber = (int) session.getAttribute("selectedFloorNumber");
			String selectedPermitType = (String) session.getAttribute("selectedPermitType");
			toggleBlock(spotUID,isBlocked,request, response);
			listSpotsForSelectedFloor(request, response, areaId, selectedFloorNumber, selectedPermitType);
		}
    }
	
	public static Integer convertBoolToInt(String actual){
		if(actual.equalsIgnoreCase("true")){
			return 1;
		}
		else
			return 0;
	}
	
	private void toggleBlock(int spotUID,int isBlocked,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Boolean isblocksuccess = FetchParkingSpotsDAO.blockSpot(spotUID,isBlocked); 
	 	request.setAttribute("isblocksuccess", isblocksuccess);
	}
}
