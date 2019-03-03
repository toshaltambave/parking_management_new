package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;
import util.SQLConnection;

public class MakeReservationsDOA{
	
	
	private static void StoreReservationsInDB (Reservations reservations,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertReservation = queryString + " VALUES ('"  
					+ reservations.getUserID()  + "','"
					+ reservations.getSpotUID() + "','"	
					+ reservations.getStartTime() + "','"	
					+ reservations.getEndTime() + "','"
					+ reservations.getNoShow() + "','"
					+ reservations.getOverStay() + "','"
					+ reservations.getCart() + "','"
					+ reservations.getCamera() + "','"
					+ reservations.getHistory() + "'" + ')';
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
	private static ArrayList<ReservationsHelper> GetReservationsByName (String firstName, String lastName) {
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
	
	private static ArrayList<ReservationsHelper> GetReservationsByReservationId (Integer reservation_Id) {
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
	
	private static ArrayList<ReservationsHelper> GetReservationsByReservationDate (String current_date) {
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
}
