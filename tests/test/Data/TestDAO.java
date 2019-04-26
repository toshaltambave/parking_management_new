package test.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
}
