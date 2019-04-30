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

import data.FetchParkingSpotsDAO;
import model.*;

@WebServlet("/SpotSearchController")
public class SpotSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		listAreas(request, response);	
	}

	private void listAreas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
		request.setAttribute("Areas", allAreas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchSpot.jsp");
        dispatcher.forward(request, response);

    }
	private void listFloorsForSelectedArea(HttpServletRequest request, HttpServletResponse response, Integer areaId) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
		Users user = (Users) session.getAttribute("User");
		ArrayList<ParkingAreaFloors> floorDetails = new ArrayList<ParkingAreaFloors>();
		if (user.getRole().equals(Role.ParkingManager.toString()))
			floorDetails = FetchParkingSpotsDAO.getFilteredFloorsbyParkingAreaId(areaId, "Premium");
		else
			floorDetails = FetchParkingSpotsDAO.getFloorsbyParkingAreaId(areaId, user.getPermitType());
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("allFloors", floorDetails);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchSpot_Floor.jsp");
        dispatcher.forward(request, response);

    }
	private void listSpotsForSelectedFloor
	(	
			HttpServletRequest request, 
			HttpServletResponse response, 
			Integer areaId, 
			Integer floorNumber, 
			String permitType 
	) throws ServletException, IOException 
	{

		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
		ArrayList<ParkingSpots> spotsList = FetchParkingSpotsDAO.getSpotsByAreaFloorPermitFromDb(areaId, floorNumber, permitType);
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("selectedFloorNumber", floorNumber);
		request.setAttribute("selectedPermitType", permitType);
		request.setAttribute("spotsList", spotsList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchSpot_SelectSpot.jsp");
        dispatcher.forward(request, response);

    }
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
	{
        String action = request.getParameter("action");
		if (action.equalsIgnoreCase("getSelectedArea") ) {  
			int areaId = Integer.parseInt(request.getParameter("areaDropDrown"));
	        request.setAttribute("selectedAreaId", areaId);
	        listFloorsForSelectedArea(request, response, areaId);
		}
		
		else if (action.equalsIgnoreCase("getSpotsForFloor") ) {  
			int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
			int selectedFloorNumber = Integer.parseInt(request.getParameter("selectedFloorNumber"));
			String selectedPermitType = request.getParameter("selectedPermitType");
	        request.setAttribute("selectedAreaId", areaId);
	        listSpotsForSelectedFloor(request, response, areaId, selectedFloorNumber, selectedPermitType);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
		
		
    }
	
}
