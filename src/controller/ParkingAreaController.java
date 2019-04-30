package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.*;
import model.*;

@WebServlet("/ParkingAreaController")
public class ParkingAreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");	
		
		if(action != null)
		{
			if (action.equalsIgnoreCase("editParkingArea")) {
				showParkingAreaEdit(req, resp);
			}
			else
			{
				System.out.println("Do Nothing.");
			}		
		}
		else
		{
			listAreas(req, resp);
			listPermitTypes(req, resp);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/CreatingParkingArea.jsp");
			dispatcher.forward(req, resp);
		}
		
	}

	private void showParkingAreaEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		listAreas(req, resp);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/EditParkingArea.jsp");
        dispatcher.forward(req, resp);
			
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String url = "";
		HttpSession session = req.getSession();
		if (action.equalsIgnoreCase("addtoList")) 
		{
			url = addtoList(req, resp);
			listPermitTypes(req, resp);
		} 
		else if (action.equalsIgnoreCase("saveArea")) {
			url = saveArea(req, resp);
			listPermitTypes(req, resp);
		}
		 else if (action.equalsIgnoreCase("getAreaFloors"))
		 {
			 listAreas(req, resp);
			 listPermitTypes(req, resp);
			 int areaId = Integer.parseInt(req.getParameter("areaDropDrown"));
		     req.setAttribute("selectedAreaId", areaId);
			 url = listFloorsForSelectedArea(req, resp,areaId);
		 }
		 else if (action.equalsIgnoreCase("getFloorSpots"))
		 {
			 listAreas(req, resp);
			 int areaId = Integer.parseInt(req.getParameter("selectedAreaId"));
			 int selectedFloorNumber = Integer.parseInt(req.getParameter("selectedFloorNumber"));
			 String selectedPermitType = req.getParameter("selectedPermitType");
			 req.setAttribute("selectedAreaId", areaId);
		     session.setAttribute("selectedAreaId", areaId);
		     session.setAttribute("selectedFloorNumber", selectedFloorNumber);
		     session.setAttribute("selectedPermitType", selectedPermitType);
			 url = listSpotsForSelectedFloor(req, resp,areaId,selectedFloorNumber,selectedPermitType);
		 }
		 else if(action.equalsIgnoreCase("toggleBlock")){
			listAreas(req, resp);
			int spotUID = Integer.parseInt(req.getParameter("selectedSpotUId"));
			int isBlocked = convertBoolToInt(req.getParameter("isBlocked"));
			int areaId = (int)session.getAttribute("selectedAreaId");
			int selectedFloorNumber = (int) session.getAttribute("selectedFloorNumber");
			String selectedPermitType = (String) session.getAttribute("selectedPermitType");
			req.setAttribute("selectedAreaId", areaId);
		    session.setAttribute("selectedAreaId", areaId);
		    session.setAttribute("selectedFloorNumber", selectedFloorNumber);
		    session.setAttribute("selectedPermitType", selectedPermitType);
			toggleBlock(spotUID,isBlocked,req, resp);
			url = listSpotsForSelectedFloor(req, resp, areaId, selectedFloorNumber, selectedPermitType);
		}
		 else if (action.equalsIgnoreCase("addSpot"))
		 {
			 listAreas(req, resp);
			 int areaId = (int)session.getAttribute("selectedAreaId");
			 int selectedFloorNumber = (int) session.getAttribute("selectedFloorNumber");
			 String selectedPermitType = (String) session.getAttribute("selectedPermitType");
			 req.setAttribute("selectedAreaId", areaId);
		     session.setAttribute("selectedAreaId", areaId);
		     session.setAttribute("selectedFloorNumber", selectedFloorNumber);
		     session.setAttribute("selectedPermitType", selectedPermitType);
			 addParkingSpot(req,resp,areaId,selectedFloorNumber,selectedPermitType);
			 url = listSpotsForSelectedFloor(req, resp,areaId,selectedFloorNumber,selectedPermitType);
		 }
		else if (action.equalsIgnoreCase("editAreaName")) {
			listAreas(req, resp);
			url = editAreaName(req, resp);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
//		else if (action.equalsIgnoreCase("editPermitType")) {
//			listAreas(req, resp);
//			url = editPermitType(req, resp);
//		}
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	private void listAreas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
		request.setAttribute("Areas", allAreas);
	}
	

	private void addParkingSpot(HttpServletRequest request, HttpServletResponse response,int AreaId, int FloorNumber, String PermitType)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Boolean isadded = true;
		isadded = ParkingAreaDAO.addParkingSpot(AreaId,FloorNumber,PermitType);
		request.setAttribute("isParkingSpotAdded", isadded);		
	}
	
	public static Integer convertBoolToInt(String actual){
		if(actual.equalsIgnoreCase("true"))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	private String editAreaName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String areaName = request.getParameter("txteditAreaName");
		String url = "";
		if(areaName.isEmpty())
		{
			String areanameError="Please select area first.";
			request.setAttribute("areanameError", areanameError);
			url = "/EditParkingArea.jsp";
		}
		else
		{
			int areaId = Integer.parseInt(request.getParameter("txteditAreaNumber"));
			Boolean isParkingAreaUpdate = FetchParkingSpotsDAO.updateParkingAreaName(areaId, areaName); 
			request.setAttribute("isParkingAreaUpdate", isParkingAreaUpdate);	
		 	showParkingAreaEdit(request,response);
		}
		return url;
	}
	
//	private String editPermitType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		 String url = "";
//		 int areaId = Integer.parseInt(request.getParameter("selectedAreaId"));
//		 int floorno = Integer.parseInt(request.getParameter("selectedFloorNumber"));
//		 int spots = Integer.parseInt(request.getParameter("selectedSpots"));
//		 String permitType = request.getParameter("permitType");
//		 String oldpermitType = request.getParameter("selectedPermitType");
//		 Boolean isPermitTypeUpdate = FetchParkingSpotsDAO.updateParkingPermitType(areaId, floorno,permitType,oldpermitType,spots); 
//		 if(isPermitTypeUpdate)
//		 {
//		 	request.setAttribute("isPermitUpdate", isPermitTypeUpdate);
//		 }
//		 else
//		 {
//			request.setAttribute("isPermitError", true);
//		 }
//		 showParkingAreaEdit(request,response);
//		 return url;
//	}
	
	private void toggleBlock(int spotUID,int isBlocked,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Boolean isblocksuccess = FetchParkingSpotsDAO.blockSpot(spotUID,isBlocked); 
	 	request.setAttribute("isblocksuccess", isblocksuccess);
	
	}
	
	
	private String listFloorsForSelectedArea(HttpServletRequest request, HttpServletResponse response, Integer areaId) throws ServletException, IOException 
	{
		String url = "";

//			HttpSession session = request.getSession();
		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
//			Users user = (Users) session.getAttribute("User");
		ArrayList<ParkingAreaFloors> floorDetails = FetchParkingSpotsDAO.getFilteredFloorsbyParkingAreaId(areaId,"Premium");
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("allFloors", floorDetails);
		request.setAttribute("selectedAreaName", selectedArea.getArea_Name());
		url = "/EditParkingArea.jsp";
		return url;
    }
	private String listSpotsForSelectedFloor( HttpServletRequest request, HttpServletResponse response, Integer areaId, Integer floorNumber ,String permitType ) throws ServletException, IOException 
	{
		String url = "";
		ParkingArea selectedArea = FetchParkingSpotsDAO.getspecificParkingArea(areaId);
		ArrayList<ParkingSpots> spotsList = FetchParkingSpotsDAO.getSpotsByAreaFloorPermitFromDb(areaId, floorNumber, permitType);
		request.setAttribute("selectedArea", selectedArea);
		request.setAttribute("selectedFloorNumber", floorNumber);
		request.setAttribute("selectedPermitType", permitType);
		request.setAttribute("spotsList", spotsList);
		url = "/EditParkingSpots.jsp";
		return url;
    }

	protected void listPermitTypes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<PermitType> listPermitTypes = new ArrayList<PermitType>(Arrays.asList(PermitType.values()));
		request.setAttribute("allPermitTypes", listPermitTypes);
	}

	private String addtoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url = "/CreatingParkingArea.jsp";
		ParkingAreaHelperError error = new ParkingAreaHelperError();
		getError(request, session, error, action);
		
		if (error.getAreaNameError().isEmpty() && error.getFloorNumberError().isEmpty()
				&& error.getNumberofSpotsError().isEmpty())
		{
			String areaName = request.getParameter("parkingareaname");
			String permitType = request.getParameter("permitType");
			Integer numberofSpots = Integer.parseInt(request.getParameter("numberofSpots"));
			Integer floorno = Integer.parseInt(request.getParameter("floornumber"));
			request.setAttribute("selectedpermitType", permitType);

			ParkingAreaHelper parkingArea = new ParkingAreaHelper();
			parkingArea.setDetails(areaName, permitType, numberofSpots, floorno);
			request.setAttribute("parkingArea", parkingArea);
			ArrayList<ParkingAreaHelper> uniqueListAreas = null;
			ArrayList<ParkingAreaHelper> copy = new ArrayList<ParkingAreaHelper>();
			copy = (ArrayList<ParkingAreaHelper>) session.getAttribute("areastobeadded");

			if (copy == null || copy.isEmpty()) 
			{
				uniqueListAreas = new ArrayList<ParkingAreaHelper>();
				uniqueListAreas.add(parkingArea);
				// uniqueListAreas.addAll(Collections.singleton(parkingArea));
				//request.setAttribute("areastobeadded", uniqueListAreas);
				session.setAttribute("areastobeadded", uniqueListAreas);
			} 
			else 
			{
				copy.add(parkingArea);
				int index = 0;
				for (ListIterator<ParkingAreaHelper> iterator = copy.listIterator(); iterator.hasNext();) {
					ParkingAreaHelper area = iterator.next();
					
					CompareArea(area, parkingArea, copy, index );
		
					index++;
				}
				HashSet<ParkingAreaHelper> listToSet = new HashSet<ParkingAreaHelper>(copy);
				// Creating Arraylist without duplicate values
				List<ParkingAreaHelper> listWithoutDuplicates = new ArrayList<ParkingAreaHelper>(listToSet);
				// copy.addAll(Collections.singleton(parkingArea));
				//request.setAttribute("areastobeadded", listWithoutDuplicates);
				session.setAttribute("areastobeadded", listWithoutDuplicates);
			}
		}
		else
			request.setAttribute("parkingAreaError", error);
		return url;
	}

	public void getError(HttpServletRequest request, HttpSession session, ParkingAreaHelperError error, String action) {
		request.setAttribute("isAreaListEmpty", false);
		error.setAreaNameError(error.validateEmpty(request.getParameter("parkingareaname")));
		error.setNumberofSpotsError(error.validateEmpty(request.getParameter("numberofSpots")));
		error.setFloorNumberError(error.validateEmpty(request.getParameter("floornumber")));
		if (action.equals("addtoList"))
		{
			error.setErrorMsg(action);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
	}

	private String saveArea(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "";
		Boolean isadded = true;
		ArrayList<ParkingAreaHelper> copy = new ArrayList<ParkingAreaHelper>();
		copy = (ArrayList<ParkingAreaHelper>) session.getAttribute("areastobeadded");
		if (copy == null) 
		{
			request.setAttribute("isAreaListEmpty", true);
			url="/CreatingParkingArea.jsp";
		} 
		else 
		{
			for (ListIterator<ParkingAreaHelper> iterator = copy.listIterator(); iterator.hasNext();)
			{
				ParkingAreaHelper area = iterator.next();
				isadded = ParkingAreaDAO.saveArea(area);
				url="/CreatingParkingArea.jsp";
			}

			session.setAttribute("areastobeadded", new ArrayList<ParkingAreaHelper>());
			request.setAttribute("isAreaAdded", isadded);
	
		}
		return url;

	}

	public void CompareArea(ParkingAreaHelper area, ParkingAreaHelper parkingArea, ArrayList<ParkingAreaHelper> copy, Integer index ){
		if (area.getAreaname().equals(parkingArea.getAreaname()))
		{
			if (area.getFloornumber().equals(parkingArea.getFloornumber()))
			{
				if (area.getPermittype().equals(parkingArea.getPermittype()))
				{
					area = parkingArea;
					copy.set(index, area);
				}	
			}		
		}
	}
}
