package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import util.SQLConnection;

public class FetchParkingSpotsDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static ArrayList<ParkingArea> getAllParkingAreas () {

		ArrayList<ParkingArea> parkingAreasInDb = new ArrayList<ParkingArea>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM parking_area";
			ResultSet areaList = stmt.executeQuery(query);
			while (areaList.next()) {
				ParkingArea area = new ParkingArea(); 
				area.setArea_Id(areaList.getInt("Area_Id"));
				area.setArea_Name(areaList.getString("Area_Name"));
				parkingAreasInDb.add(area);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return parkingAreasInDb;
	}
	
	public static ParkingArea getspecificParkingArea(Integer areaId){
		ParkingArea filteredArea = new ParkingArea();
		Iterator <ParkingArea> AreaList = FetchParkingSpotsDAO.getAllParkingAreas().iterator();
		while (AreaList.hasNext()) {
			ParkingArea currentArea = AreaList.next();
			if(currentArea.getArea_Id() == areaId)
			{
				filteredArea.setArea_Id(currentArea.getArea_Id());
				filteredArea.setArea_Name(currentArea.getArea_Name());
			}
		}
		return filteredArea;
	}
	
	public static ArrayList<ParkingAreaFloors> getAllParkingAreaFloors () {

		ArrayList<ParkingAreaFloors> parkingAreaFloorsInDb = new ArrayList<ParkingAreaFloors>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM parking_area_floors;";
			ResultSet floorList = stmt.executeQuery(query);
			while (floorList.next()) {
				ParkingAreaFloors floor = new ParkingAreaFloors(); 
				floor.setArea_Id(floorList.getInt("Area_Id"));
				floor.setFloor_Number(floorList.getInt("Floor_Number"));
				floor.setNo_Spots(floorList.getInt("No_Spots"));
				floor.setPermitType(floorList.getString("PermitType"));
				parkingAreaFloorsInDb.add(floor);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return parkingAreaFloorsInDb;
	}

	public static ArrayList<ParkingAreaFloors> getFloorsbyParkingAreaId(int areaId, String permitType){
		ArrayList<ParkingAreaFloors> filteredFloors = new ArrayList<ParkingAreaFloors>();
		Iterator <ParkingAreaFloors> floorsList = FetchParkingSpotsDAO.getAllParkingAreaFloors().iterator();
		while (floorsList.hasNext()) {
			ParkingAreaFloors currentFloor = floorsList.next();
			//TODO: Make PermitType mandatory
			if(permitType.equalsIgnoreCase("access")){
				if(currentFloor.getArea_Id().equals(areaId) && (currentFloor.getPermitType().equalsIgnoreCase("access")))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("premium")){
				if(currentFloor.getArea_Id().equals(areaId) && (currentFloor.getPermitType().equalsIgnoreCase("premium")
						|| currentFloor.getPermitType().equalsIgnoreCase("midrange")
						|| currentFloor.getPermitType().equalsIgnoreCase("basic")))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("midrange")){
				if(currentFloor.getArea_Id().equals(areaId) && (currentFloor.getPermitType().equalsIgnoreCase("midrange")
						|| currentFloor.getPermitType().equalsIgnoreCase("basic")))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("basic")){
				if(currentFloor.getArea_Id().equals(areaId) && currentFloor.getPermitType().equalsIgnoreCase("basic"))
				{
					filteredFloors.add(currentFloor);
				}
			}
			
		}
		return filteredFloors;
	}

//	public static ArrayList<ParkingAreaFloors> getFloorsbyAvailability(int areaId, String permitType, String start_time, String end_time){
//		ArrayList<ParkingAreaFloors> filteredFloors = new ArrayList<ParkingAreaFloors>();
//		Iterator <ParkingAreaFloors> FloorList = FetchParkingSpotsDAO.getFloorsbyParkingAreaId(areaId, permitType).iterator();
//		while (FloorList.hasNext()) {
//			ParkingAreaFloors currentFloor = FloorList.next();
//			if(currentFloor.getArea_Id().equals(areaId) && currentFloor.getFloor_Number() == floorNumber)
//			{
//				currentFloor.
//				filteredFloors.add(currentFloor);
//			}
//		}
//		return filteredFloors;
//	}
	
	
	public static ArrayList<ParkingSpots> getAllSpots () {

		ArrayList<ParkingSpots> parkingSpotsInDb = new ArrayList<ParkingSpots>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM parking_spots";
			ResultSet spotsList = stmt.executeQuery(query);
			while (spotsList.next()) {
				ParkingSpots spot = new ParkingSpots(); 
				spot.setArea_Id(spotsList.getInt("Area_Id"));
				spot.setFloor_Number(spotsList.getInt("Floor_Number"));
				spot.setIsBlocked(spotsList.getBoolean("isBlocked"));
				spot.setPermitType(spotsList.getString("permitType"));
				spot.setSpot_Id(spotsList.getInt("Spot_Id"));
				parkingSpotsInDb.add(spot);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return parkingSpotsInDb;
	}
	
	public static ArrayList<ParkingSpots> getSpotsByAreaFloorPermitFromDb (int areaId, int floorNumber, String permitType) {

		ArrayList<ParkingSpots> parkingSpotsInDb = new ArrayList<ParkingSpots>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String query = "SELECT * from parking_spots WHERE Area_Id = '"+areaId+"' AND Floor_Number = '"+floorNumber+"' AND PermitType ='"+permitType+"' ";
			ResultSet spotsList = stmt.executeQuery(query);
			while (spotsList.next()) {
				ParkingSpots spot = new ParkingSpots(); 
				spot.setArea_Id(spotsList.getInt("Area_Id"));
				spot.setFloor_Number(spotsList.getInt("Floor_Number"));
				spot.setIsBlocked(spotsList.getBoolean("isBlocked"));
				spot.setPermitType(spotsList.getString("permitType"));
				spot.setSpot_Id(spotsList.getInt("Spot_Id"));
				parkingSpotsInDb.add(spot);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		return parkingSpotsInDb;
	}
	
	public static ArrayList<ParkingSpots> getSpotsByAreaAndFloor(int areaId, int floorNumber){
		ArrayList<ParkingSpots> filteredSpots = new ArrayList<ParkingSpots>();
		Iterator <ParkingSpots> spotsList = FetchParkingSpotsDAO.getAllSpots().iterator();
		while (spotsList.hasNext()) {
			ParkingSpots currentSpot = spotsList.next();
			if(currentSpot.getArea_Id().equals(areaId) && currentSpot.getFloor_Number() == floorNumber)
			{
				filteredSpots.add(currentSpot);
			}
		}
		return filteredSpots;
	}
}
