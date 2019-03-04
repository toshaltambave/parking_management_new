package controller;

import java.io.IOException;
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
		listAreas(req, resp);	
		listPermitTypes(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String url = "";
		if (action.equalsIgnoreCase("addtoList") ) {  
			url = addtoList(req,resp);
			listPermitTypes(req,resp);
		}
		else if (action.equalsIgnoreCase("saveArea") ) 
		{  
			url = saveArea(req,resp);
			listPermitTypes(req,resp);
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
	
	private void listAreas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<ParkingArea> allAreas = FetchParkingSpotsDAO.getAllParkingAreas();
			request.setAttribute("Areas", allAreas);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }
	
	protected void listPermitTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			ArrayList<PermitType> listPermitTypes = new ArrayList<PermitType>(Arrays.asList(PermitType.values()));
			request.setAttribute("allPermitTypes", listPermitTypes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CreatingParkingArea.jsp");
            dispatcher.forward(request, response);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException(e);
		}
    }

	private String addtoList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {	
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String mandatory = "This field is mandatory.";
        String url ="/CreatingParkingArea.jsp";
		ParkingAreaHelperError error = new ParkingAreaHelperError();
		session.setAttribute("isAreaListEmpty", false);
		if(request.getParameter("parkingareaname").isEmpty())
			error.setAreaNameError(mandatory);	
		else
			error.setAreaNameError("");	
		if(request.getParameter("numberofSpots").isEmpty())
			error.setNumberofSpotsError(mandatory);
		else
			error.setNumberofSpotsError("");
		if(request.getParameter("floornumber").isEmpty())
			error.setFloorNumberError(mandatory);
		else
			error.setFloorNumberError("");
		ParkingAreaDAO.validateAreaDetails(action,error);
		session.setAttribute("parkingAreaError",error);
		if(error.getAreaNameError().isEmpty() && error.getFloorNumberError().isEmpty() && error.getNumberofSpotsError().isEmpty())
		{
			String areaName = request.getParameter("parkingareaname");
			String permitType = request.getParameter("permitType");
			Integer numberofSpots = Integer.parseInt(request.getParameter("numberofSpots"));
			Integer floorno = Integer.parseInt(request.getParameter("floornumber"));;
	        request.setAttribute("selectedpermitType", permitType);
			
			ParkingAreaHelper parkingArea = new ParkingAreaHelper();
			parkingArea.setDetails(areaName,permitType,numberofSpots,floorno);			
	        request.setAttribute("parkingArea", parkingArea);
	        ArrayList<ParkingAreaHelper> uniqueListAreas = null;      
	        ArrayList<ParkingAreaHelper> copy = new ArrayList<ParkingAreaHelper>();      
	        copy = (ArrayList<ParkingAreaHelper>) session.getAttribute("areastobeadded");
	        
	        if(copy == null || copy.isEmpty())
			{
	            uniqueListAreas = new ArrayList<ParkingAreaHelper>();
	        	uniqueListAreas.add(parkingArea);
	//        	uniqueListAreas.addAll(Collections.singleton(parkingArea));
	    		request.setAttribute("areastobeadded", uniqueListAreas);
	    		session.setAttribute("areastobeadded", uniqueListAreas);
			}
	        else
	        {
	            copy.add(parkingArea);
	        	int index=0;
	            for (ListIterator<ParkingAreaHelper> iterator = copy.listIterator(); iterator.hasNext();)
	            {            	
	            	ParkingAreaHelper area = iterator.next();
	            	if(area.getAreaname().equals(parkingArea.getAreaname()))
	            	{
	            		if(area.getFloornumber().equals(parkingArea.getFloornumber()))
		                {
	                        if(area.getPermittype().equals(parkingArea.getPermittype()))
	    	                {
	    	                	area = parkingArea;
	    	                	copy.set(index, area);
	    	                }
		                }
	            	}
	            	index++;
	            }
	            HashSet<ParkingAreaHelper> listToSet = new HashSet<ParkingAreaHelper>(copy);
	            //Creating Arraylist without duplicate values
	            List<ParkingAreaHelper> listWithoutDuplicates = new ArrayList<ParkingAreaHelper>(listToSet);
	//            copy.addAll(Collections.singleton(parkingArea));
	        	request.setAttribute("areastobeadded", listWithoutDuplicates);
	    		session.setAttribute("areastobeadded", listWithoutDuplicates);
	        }
		}
		return url;		
    }
	
	private String saveArea(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {		
		HttpSession session = request.getSession();	
		String url = "";
		Boolean isadded = true;
        ArrayList<ParkingAreaHelper> copy = new ArrayList<ParkingAreaHelper>();      
        copy = (ArrayList<ParkingAreaHelper>) session.getAttribute("areastobeadded");
        if(copy == null)
        {
        	session.setAttribute("isAreaListEmpty", true);
        }
        else
        {
			for (ListIterator<ParkingAreaHelper> iterator = copy.listIterator(); iterator.hasNext();)
	         {            	
	         	ParkingAreaHelper area = iterator.next();
	         	isadded = ParkingAreaDAO.saveArea(area); 
	         }
	     	if(isadded)
	     	{
	     		session.setAttribute("areastobeadded", new ArrayList<ParkingAreaHelper>());
	     		session.setAttribute("isAreaAdded", isadded);
	     	}
        }
		return url;
		
    }
}
