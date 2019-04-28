package test.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ReservationsHelper;
import model.Users;
import util.PasswordUtility;
import util.SQLConnection;

public class TestDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();


	public static void deleteUser(String username) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "DELETE FROM parking_management.system_users WHERE username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean userExists(String username) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.system_users where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) 
			{
				return true;
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
		}
		return false;
	}
	
	public static void deleteReservation(String username) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "DELETE FROM parking_management.reservations WHERE User_Id = (SELECT User_Id FROM parking_management.system_users where username=?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteSpot(String areaName) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "DELETE FROM parking_management.parking_spots WHERE Area_Id = (SELECT Area_Id FROM parking_management.parking_area where Area_Name=?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, areaName);
			pst.executeUpdate();
			pst = null;
			sql = "DELETE FROM parking_management.parking_area_floors WHERE Area_Id = (SELECT Area_Id FROM parking_management.parking_area where Area_Name=?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, areaName);
			pst.executeUpdate();
			pst = null;
			sql = "DELETE FROM parking_management.parking_area WHERE Area_Name=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, areaName);
			pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Integer getAreaId() {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		Integer areaId = 0;
		try {
			
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT Count(*) As NoofAreas FROM parking_management.parking_area";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) 
			{
				areaId = rs.getInt("NoofAreas");
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
		}
		return areaId+1;
	}

	public static ArrayList<ReservationsHelper> GetReservationsByUsername (String username) {
		ArrayList<ReservationsHelper> ReservationsNoShow = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.* FROM parking_management.reservations r join system_users s on s.user_id=r.user_id where s.username='"+username+"';";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservation_Id"));
				ReservationsNoShow.add(reservation);
			
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
		return ReservationsNoShow;
	}
}
