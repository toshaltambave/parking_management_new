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

public class ReservationsDAO {
static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static ArrayList<ParkingAreaFloors> getFloorSpotsCountByTime (Integer area_id, String start_time, String end_time) {

		ArrayList<ParkingAreaFloors> parkingAreasFloorsUpdatedCount = new ArrayList<ParkingAreaFloors>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			PreparedStatement ps = null;
			String query = "SELECT paf.Area_Id AS Area_Id,paf.Floor_Number AS Floor_Number ,paf.PermitType AS PermitType,(paf.No_Spots-Count(res.Spot_UID)) AS AvailableSpots "
							+"FROM parking_area_floors AS paf "
							+"LEFT JOIN parking_spots AS ps ON paf.Area_Id = ps.Area_Id and ps.Floor_Number = paf.Floor_number and paf.PermitType = ps.PermitType "
							+"LEFT JOIN reservations res ON ps.Spot_UID = res.Spot_UID AND res.Start_Time >= ? AND res.Start_Time < ? "
							+"where paf.Area_Id = ? GROUP BY paf.Area_Id,paf.Floor_Number,paf.PermitType ";
			ps = conn.prepareStatement(query);
			ps.setString(1, start_time);
			ps.setString(2, end_time);
			ps.setInt(3, area_id);
			ResultSet floorList = ps.executeQuery();
			if (!floorList.isBeforeFirst()) {
				System.out.println("No data");
			}
			while (floorList.next()) {
				ParkingAreaFloors floorWithUpdatedCount = new ParkingAreaFloors(); 
				floorWithUpdatedCount.setArea_Id(floorList.getInt("Area_Id"));
				floorWithUpdatedCount.setFloor_Number(floorList.getInt("Floor_Number"));
				floorWithUpdatedCount.setPermitType(floorList.getString("PermitType"));
				floorWithUpdatedCount.setNo_Spots(floorList.getInt("AvailableSpots"));
				parkingAreasFloorsUpdatedCount.add(floorWithUpdatedCount);					
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
		return parkingAreasFloorsUpdatedCount;
	}

	public static ArrayList<ParkingAreaFloors> getFloorSpotsCountByTimeFiltered(Integer area_id, String start_time, String end_time, String permitType){
		ArrayList<ParkingAreaFloors> filteredFloors = new ArrayList<ParkingAreaFloors>();
		Iterator <ParkingAreaFloors> floorsList = ReservationsDAO.getFloorSpotsCountByTime(area_id, start_time, end_time).iterator();
		while (floorsList.hasNext()) {
			ParkingAreaFloors currentFloor = floorsList.next();
			//TODO: Make PermitType mandatory
			if(permitType.equalsIgnoreCase("access")){
				if(currentFloor.getPermitType().equalsIgnoreCase("access"))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("premium")){
				if(currentFloor.getPermitType().equalsIgnoreCase("premium")
						|| currentFloor.getPermitType().equalsIgnoreCase("midrange")
						|| currentFloor.getPermitType().equalsIgnoreCase("basic"))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("midrange")){
				if(currentFloor.getPermitType().equalsIgnoreCase("midrange")
						|| currentFloor.getPermitType().equalsIgnoreCase("basic"))
				{
					filteredFloors.add(currentFloor);
				}
			}
			else if(permitType.equalsIgnoreCase("basic")){
				if(currentFloor.getPermitType().equalsIgnoreCase("basic"))
				{
					filteredFloors.add(currentFloor);
				}
			}
			
		}
		return filteredFloors;
	}

	public static ArrayList<ParkingSpots> getSpotsByAreaFloorPermitFromDb 
	(int areaId, 
	int floorNumber, 
	String permitType, 
	String startTime, 
	String endTime) {

		ArrayList<ParkingSpots> parkingSpotsInDb = new ArrayList<ParkingSpots>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			PreparedStatement ps = null;
			String query = "SELECT ps.Spot_Id AS Spot_Id, ps.Spot_UID AS Spot_UID from parking_spots AS ps "
							+"where ps.Spot_Id NOT IN( SELECT psn.Spot_Id AS Spot_Id FROM parking_spots AS psn "
							+"JOIN reservations res ON psn.Spot_UID = res.Spot_UID AND res.Start_Time >= ? AND res.End_Time <= ? "
							+"where psn.Area_Id = ? AND psn.Floor_Number = ? AND psn.PermitType = ?) "
							+"AND ps.Area_Id = ? AND ps.Floor_Number = ? AND ps.PermitType = ? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, startTime);
			ps.setString(2, endTime);
			ps.setInt(3, areaId);
			ps.setInt(4, floorNumber);
			ps.setString(5, permitType);
			ps.setInt(6, areaId);
			ps.setInt(7, floorNumber);
			ps.setString(8, permitType);
			ResultSet spotsList = ps.executeQuery();
			if (!spotsList.isBeforeFirst()) {
				System.out.println("No data");
			}
			else
				while (spotsList.next()) {
				ParkingSpots spot = new ParkingSpots(); 
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
			};
		}
		return parkingSpotsInDb;
	}

	public static void StoreReservationsInDB (Reservation reservations) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertReservation = "INSERT INTO reservations (User_Id, Spot_UID,Start_Time,End_Time,NoShow,OverStay,Cart,Camera,Total, History) VALUES ('"  
					+ reservations.getUserID()  + "','"
					+ reservations.getSpotUID() + "','"	
					+ reservations.getStartTime() + "','"	
					+ reservations.getEndTime() + "','"
					+ convertBoolToInt(reservations.getNoShow()) + "','"
					+ convertBoolToInt(reservations.getOverStay()) + "','"
					+ convertBoolToInt(reservations.getCart()) + "','"
					+ convertBoolToInt(reservations.getCamera()) + "','"
					+ reservations.getTotal() + "','"
					+ convertBoolToInt(reservations.getHistory()) + "'" + ')';
			stmt.executeUpdate(insertReservation);	
			conn.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}};
	}
	
	public static Boolean deleteReservationbyResId(Integer resId){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String delete = "Delete from parking_management.reservations Where Reservation_id ="+resId+";";
			stmt.executeUpdate(delete);	
			conn.commit(); 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		return true;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByName (String firstName, String lastName) {
		ArrayList<ReservationsHelper> ReservationsByName = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time"
							+"from reservations as r"
							+"Inner join system_users as s on r.User_ID=s.User_ID"
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID"
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID"
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id"
							+"where s_u.FirstName="+firstName+"and s_u.LastName="+lastName+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservavtion_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				ReservationsByName.add(reservation);	
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ReservationsByName;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByReservationId (Integer reservation_Id) {
		ArrayList<ReservationsHelper> ReservationsById = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time"
							+"from reservations as r"
							+"Inner join system_users as s on r.User_ID=s.User_ID"
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID"
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID"
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id"
							+"where r.Reservation_Id="+reservation_Id+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservavtion_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				ReservationsById.add(reservation);	
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ReservationsById;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByReservationDate (String current_date) {
		ArrayList<ReservationsHelper> ReservationsByDate = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time"
							+"from reservations as r"
							+"Inner join system_users as s on r.User_ID=s.User_ID"
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID"
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID"
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id"
							+"where r.Start_Time >="+current_date+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservavtion_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				ReservationsByDate.add(reservation);
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ReservationsByDate;
	}
	
	public static Integer convertBoolToInt(Boolean actual){
		if(actual == true){
			return 1;
		}
		else
			return 0;
	}
	
}

