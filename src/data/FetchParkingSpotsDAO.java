package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;
import util.SQLConnection;

public class FetchParkingSpotsDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static ArrayList<ParkingArea> getAllParkingAreas() {

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
			}
			;
		}
		return parkingAreasInDb;
	}

	public static ParkingArea getspecificParkingArea(Integer areaId) {
		ParkingArea filteredArea = new ParkingArea();
		Iterator<ParkingArea> AreaList = FetchParkingSpotsDAO.getAllParkingAreas().iterator();
		while (AreaList.hasNext()) {
			ParkingArea currentArea = AreaList.next();
			if (currentArea.getArea_Id() == areaId) {
				filteredArea.setArea_Id(currentArea.getArea_Id());
				filteredArea.setArea_Name(currentArea.getArea_Name());
			} else {
				System.out.println("Do Nothing.");
			}
		}
		return filteredArea;
	}

	public static ArrayList<ParkingAreaFloors> getFloorsbyParkingAreaId(int areaId, String permitType) {
		ArrayList<ParkingAreaFloors> filteredFloors = new ArrayList<ParkingAreaFloors>();
		Iterator<ParkingAreaFloors> floorsList = FetchParkingSpotsDAO.getFilteredParkingAreaFloors().iterator();
		while (floorsList.hasNext()) {
			ParkingAreaFloors currentFloor = floorsList.next();
			// TODO: Make PermitType mandatory
			if (permitType.equalsIgnoreCase("access")) {
				if (currentFloor.getArea_Id().equals(areaId)
						&& (currentFloor.getPermitType().equalsIgnoreCase("access"))) {
					filteredFloors.add(currentFloor);
				} else {
					System.out.println("Do Nothing.");
				}
			} else if (permitType.equalsIgnoreCase("premium")) {
				if (currentFloor.getArea_Id().equals(areaId)
						&& (currentFloor.getPermitType().equalsIgnoreCase("premium")
								|| currentFloor.getPermitType().equalsIgnoreCase("midrange")
								|| currentFloor.getPermitType().equalsIgnoreCase("basic"))) {
					filteredFloors.add(currentFloor);
				} else {
					System.out.println("Do Nothing.");
				}
			} else if (permitType.equalsIgnoreCase("midrange")) {
				if (currentFloor.getArea_Id().equals(areaId)
						&& (currentFloor.getPermitType().equalsIgnoreCase("midrange")
								|| currentFloor.getPermitType().equalsIgnoreCase("basic"))) {
					filteredFloors.add(currentFloor);
				} else {
					System.out.println("Do Nothing.");
				}
			} else {
				if (currentFloor.getArea_Id().equals(areaId)
						&& currentFloor.getPermitType().equalsIgnoreCase("basic")) {
					filteredFloors.add(currentFloor);
				} else {
					System.out.println("Do Nothing.");
				}
			}
		}
		return filteredFloors;
	}

	public static ArrayList<ParkingAreaFloors> getFilteredFloorsbyParkingAreaId(int areaId, String permitType) {
		ArrayList<ParkingAreaFloors> filteredFloors = new ArrayList<ParkingAreaFloors>();
		Iterator<ParkingAreaFloors> floorsList = FetchParkingSpotsDAO.getFilteredParkingAreaFloors().iterator();
		while (floorsList.hasNext()) {
			ParkingAreaFloors currentFloor = floorsList.next();
			if (currentFloor.getArea_Id().equals(areaId)) {
				filteredFloors.add(currentFloor);
			} else {
				System.out.println("Do Nothing.");
			}
		}
		return filteredFloors;
	}

	public static ArrayList<ParkingAreaFloors> getFilteredParkingAreaFloors() {

		ArrayList<ParkingAreaFloors> parkingAreaFloorsInDb = new ArrayList<ParkingAreaFloors>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String query = "SELECT ps.Area_Id as Area_Id,ps.Floor_Number AS Floor_Number, ps.PermitType AS PermitType, Count(ps.Spot_Id) AS No_Spots FROM parking_area_floors AS paf "
					+ " JOIN parking_spots ps ON ps.Area_Id = paf.Area_Id AND ps.PermitType= paf.PermitType"
					+ " AND ps.Floor_Number = paf.Floor_Number AND ps.IsBlocked= 0"
					+ " group by ps.Area_Id,ps.Floor_Number, ps.PermitType;";
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
			}
			;
		}
		return parkingAreaFloorsInDb;
	}

	public static ArrayList<ParkingSpots> getSpotsByAreaFloorPermitFromDb(int areaId, int floorNumber,
			String permitType) {

		ArrayList<ParkingSpots> parkingSpotsInDb = new ArrayList<ParkingSpots>();

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String query = "SELECT * from parking_spots WHERE Area_Id = '" + areaId + "' AND Floor_Number = '"
					+ floorNumber + "' AND PermitType ='" + permitType + "' ";
			ResultSet spotsList = stmt.executeQuery(query);
			while (spotsList.next()) {
				ParkingSpots spot = new ParkingSpots();
				spot.setArea_Id(spotsList.getInt("Area_Id"));
				spot.setFloor_Number(spotsList.getInt("Floor_Number"));
				spot.setIsBlocked(spotsList.getBoolean("isBlocked"));
				spot.setPermitType(spotsList.getString("permitType"));
				spot.setSpot_Id(spotsList.getInt("Spot_Id"));
				spot.setSpot_UID(spotsList.getInt("Spot_UID"));
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
			}
			;
		}
		return parkingSpotsInDb;
	}

	public static Boolean blockSpot(int spotUID, int isBlocked) {
		Connection conn = SQLConnection.getDBConnection();
		try {
			if (isBlocked == 1) {
				isBlocked = 0;
			} else {
				isBlocked = 1;
			}
			PreparedStatement pst3 = null;
			String queryString = "UPDATE `parking_spots` SET `IsBlocked` = ? WHERE `Spot_UID` = ?";
			pst3 = conn.prepareStatement(queryString);
			pst3.setInt(1, isBlocked);
			pst3.setInt(2, spotUID);
			pst3.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;

	}

	public static Boolean updateParkingAreaName(int areaId, String areaName) {
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement pst3 = null;
			String queryString = "UPDATE `parking_area` SET `Area_Name` = ? WHERE `Area_Id` = ?";
			pst3 = conn.prepareStatement(queryString);
			pst3.setString(1, areaName);
			pst3.setInt(2, areaId);
			pst3.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
