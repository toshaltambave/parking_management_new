package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

import model.*;
import util.SQLConnection;

public class UserDetailsDAO {

	private static final Logger LOG = Logger.getLogger(UserDetailsDAO.class.getName(), UserDetailsDAO.class);
	static SQLConnection DBMgr = SQLConnection.getInstance();

	private static Boolean StoreListinDB(UserDetails userDetails, String queryString) {
		Statement stmt = null;
		Boolean isSuccess = false;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			String insertUserDetails = queryString + " VALUES ('" + userDetails.getUserID() + "','"
					+ userDetails.getFirstName() + "','" + userDetails.getMiddleName() + "','"
					+ userDetails.getLastName() + "','" + userDetails.getSex() + "','" + userDetails.getBirthDate()
					+ "','" + userDetails.getAddress() + "','" + userDetails.getEmail() + "','" + userDetails.getPhone()
					+ "','" + userDetails.getDrivingLicenseNo() + "','" + userDetails.getDrivingLicenseExpiry() + "','"
					+ userDetails.getRegistrationNumber() + "','" + userDetails.getUta_Id() + "'" + ')';
			stmt.executeUpdate(insertUserDetails);
			conn.commit();
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} finally {
			try {
				conn.close();
				stmt.close();
				isSuccess = true;
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	public static Boolean insertUserDetails(UserDetails userDetails) {
		Boolean isSuccess = false;
		try {

			userDetails.setUserID(ReturnUserID(userDetails.getUsername()));
			isSuccess = StoreListinDB(userDetails,
					"INSERT INTO user_details (User_Id,FirstName,MiddleName,LastName,Sex,DOB, Address, Email, Phone, DL_Number,DL_Expiry,Reg_Number,uta_Id)");
			return isSuccess;
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "Sql Error: ", ex);
			return isSuccess;
		}
	}

	// determine if companyID is unique
	public static Integer ReturnUserID(String username) {
		int userID = 1;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		String queryString = "SELECT User_Id,UserName from system_users WHERE UserName ='" + username + "'";
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
			result.next();
			userID = result.getInt("User_Id");
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}

		}
		return userID;
	}

	public static List<UserDetails> searchByUsername(String userName) {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT su.PermitType,su.Role,ud.* FROM parking_management.system_users su JOIN user_details ud ON su.user_id=ud.user_id where su.UserName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs2 = pst.executeQuery();
			while (rs2.next()) {
				UserDetails userDetails = new UserDetails();
				userDetails.setAddress(rs2.getString("Address"));
				userDetails.setDrivingLicenseExpiry(df.format(rs2.getDate("DL_Expiry")));
				userDetails.setBirthDate(df.format(rs2.getDate("DOB")));
				userDetails.setFirstName(rs2.getString("FirstName"));
				userDetails.setMiddleName(rs2.getString("MiddleName"));
				userDetails.setLastName(rs2.getString("LastName"));
				userDetails.setSex(rs2.getString("Sex"));
				userDetails.setEmail(rs2.getString("Email"));
				userDetails.setPhone(rs2.getString("Phone"));
				userDetails.setDrivingLicenseNo(rs2.getString("DL_Number"));
				userDetails.setRegistrationNumber(rs2.getString("Reg_Number"));
				userDetails.setUta_Id(rs2.getString("uta_id"));
				userDetails.setUsername(userName);
				userListInDB.add(userDetails);
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}
		}
		return userListInDB;
	}

	public static List<UserDetails> getLastNames() {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		LOG.info("Getting LastNames.....");
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT LastName FROM parking_management.user_details";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				UserDetails userDetails = new UserDetails();
				userDetails.setLastName(rs.getString("LastName"));
				userListInDB.add(userDetails);
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}
		}
		return userListInDB;
	}

	public static List<UserDetails> getUserNames() {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT UserName FROM parking_management.system_users";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				UserDetails userDetails = new UserDetails();
				userDetails.setUsername(rs.getString("UserName"));
				userListInDB.add(userDetails);

			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}
		}
		return userListInDB;
	}

	public static boolean revokeUser(String type, String value, Boolean isRevoked, String comment) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean isSuccessful = true;

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "UPDATE parking_management.system_users SET IsRevoked=?,comment=? where UserName=?";
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, isRevoked);
			pst.setString(2, comment);
			pst.setString(3, value);
			pst.executeUpdate();

		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
			isSuccessful = false;
		} finally {
			try {
				conn.commit();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
				isSuccessful = false;
			}
		}

		return isSuccessful;
	}

	public static boolean changeRole(String type, String value, String role) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean isSuccessful = true;

		try {
			stmt = conn.createStatement();

			PreparedStatement pst = null;

			String sql = "UPDATE parking_management.system_users SET Role=? where UserName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, role);
			pst.setString(2, value);
			pst.executeUpdate();

		} catch (SQLException e) {
			isSuccessful = false;
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.commit();
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				isSuccessful = false;
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}
		}

		return isSuccessful;
	}

	public static List<UserDetails> searchByLastName(String lastName) {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT su.UserName,su.Role,ud.* FROM parking_management.user_details ud JOIN system_users su ON su.user_id=ud.user_id where ud.LastName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, lastName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String dob = df.format(rs.getDate("DOB"));
				LOG.info("DOB: " + dob);
				UserDetails userDetails = new UserDetails();
				userDetails.setAddress(rs.getString("Address"));
				userDetails.setDrivingLicenseExpiry(df.format(rs.getDate("DL_Expiry")));
				userDetails.setBirthDate(df.format(rs.getDate("DOB")));
				userDetails.setFirstName(rs.getString("FirstName"));
				userDetails.setMiddleName(rs.getString("MiddleName"));
				userDetails.setLastName(rs.getString("LastName"));
				userDetails.setSex(rs.getString("Sex"));
				userDetails.setEmail(rs.getString("Email"));
				userDetails.setPhone(rs.getString("Phone"));
				userDetails.setDrivingLicenseNo(rs.getString("DL_Number"));
				userDetails.setRegistrationNumber(rs.getString("Reg_Number"));
				userDetails.setUta_Id(rs.getString("uta_id"));
				userDetails.setUsername(rs.getString("UserName"));
				userListInDB.add(userDetails);
			}

		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Sql Error: ", e);
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				LOG.log(Level.SEVERE, "Sql Error: ", e);
			}
		}
		return userListInDB;
	}

}
