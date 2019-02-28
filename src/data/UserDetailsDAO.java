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

public class UserDetailsDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<UserDetails> ReturnMatchingUsers (String queryString) {
		ArrayList<UserDetails> userListInDB = new ArrayList<UserDetails>();
		
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet usersList = stmt.executeQuery(queryString);
			while (usersList.next())
			{
				UserDetails userDetails = new UserDetails(); 
				userDetails.setUserID(usersList.getInt("User_Id"));
				userDetails.setFirstName(usersList.getString("FirstName"));
				userDetails.setMiddleName(usersList.getString("MiddleName"));
				userDetails.setLastName(usersList.getString("LastName"));  
				userDetails.setAddress(usersList.getString("Address"));
				userDetails.setDrivingLicenseExpiry(usersList.getDate("DL_Expiry").toString());
				userDetails.setDrivingLicenseNo(usersList.getString("DL_Number"));
				userDetails.setEmail(usersList.getString("Email"));
				userDetails.setBirthDate(usersList.getDate("DOB").toString());  
				userDetails.setSex(usersList.getString("Sex"));
				userDetails.setRegistrationNumber(usersList.getString("Reg_Number"));
				userDetails.setPhone(usersList.getString("Phone"));
				userDetails.setUta_Id(usersList.getString("uta_Id"));
				
				userListInDB.add(userDetails);					
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
		return userListInDB;
	}
	
	private static Boolean StoreListinDB (UserDetails userDetails,String queryString) {
		Statement stmt = null;
		Boolean isSuccess = false;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUserDetails = queryString + " VALUES ('"  
					+ userDetails.getUserID()  + "','"
					+ userDetails.getFirstName() + "','"	
					+ userDetails.getMiddleName() + "','"	
					+ userDetails.getLastName() + "','"	
					+ userDetails.getSex() + "','" 
					+ userDetails.getBirthDate() + "','"
					+ userDetails.getAddress() + "','"
					+ userDetails.getEmail() + "','"
					+ userDetails.getPhone() + "','"
					+ userDetails.getDrivingLicenseNo() + "','"
					+ userDetails.getDrivingLicenseExpiry() + "','"
					+ userDetails.getRegistrationNumber() + "','"
					+ userDetails.getUta_Id() + "'"
					+ ')';
			stmt.executeUpdate(insertUserDetails);	
			conn.commit(); 
			isSuccess  = true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			isSuccess = false;
		} 
		finally 
		{
			try 
			{
				conn.close();
				stmt.close();
				isSuccess = true;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				isSuccess = false;
			}
		};
		return isSuccess;
	}

	public static Boolean insertUserDetails(UserDetails userDetails) 	
	{  
		Boolean isSuccess = false;
		try
		{

			userDetails.setUserID(ReturnUserID(userDetails.getUsername()));
			isSuccess = StoreListinDB(userDetails,"INSERT INTO user_details (User_Id,FirstName,MiddleName,LastName,Sex,DOB, Address, Email, Phone, DL_Number,DL_Expiry,Reg_Number,uta_Id)");	
			return isSuccess;
		} 
		catch(Exception ex)
		{
			return isSuccess;
		}
	}
	public static ArrayList<UserDetails>  listUsers() 
	{  
		return ReturnMatchingUsers(" SELECT * from user_details ORDER BY User_Id");
	}
	
	//determine if companyID is unique
	public static Integer ReturnUserID(String username)  
	{  	
		int userID = 1;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		String queryString =  "SELECT User_Id,UserName from system_users WHERE UserName ='" + username +"'";
		try 
		{
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);
			result.next();
			userID=result.getInt("User_Id");
			System.out.println(userID);
			System.out.println(result.getString("UserName"));
			userID=result.getInt("User_Id");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally {
		try 
		{
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		return userID;
	}
	
	public static List<UserDetails> searchByUsername(String userName) {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int userId;
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.system_users where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("No data");
			} else if (rs.next()) {
				userId = rs.getInt("User_Id");
				stmt = conn.createStatement();
				PreparedStatement pst2 = null;
				String sql2 = "SELECT * FROM parking_management.user_details where user_id=?";
				pst2 = conn.prepareStatement(sql2);
				pst2.setInt(1, userId);
				ResultSet rs2 = pst2.executeQuery();
				if (!rs2.isBeforeFirst()) {
					System.out.println("No data");
				} else if(rs2.next()) {
					
					UserDetails userDetails = new UserDetails();
					userDetails.setAddress(rs2.getString("Address"));
					userDetails.setDrivingLicenseExpiry(rs2.getDate("DL_Expiry").toString());
					userDetails.setBirthDate(rs2.getDate("DOB").toString());
					userDetails.setFirstName(rs2.getString("FirstName"));
					userDetails.setLastName(rs2.getString("LastName"));
					userDetails.setSex(rs2.getString("Sex"));
					userDetails.setEmail(rs2.getString("Email"));
					userDetails.setPhone(rs2.getString("Phone"));
					userDetails.setDrivingLicenseNo(rs2.getString("DL_Number"));
					userDetails.setRegistrationNumber(rs2.getString("Reg_Number"));
					userDetails.setUta_Id(rs2.getString("uta_id"));
					
					userListInDB.add(userDetails);

				}
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
		return userListInDB;
	}
	
	public static List<UserDetails> searchByLastName(String lastName) {
		List<UserDetails> userListInDB = new ArrayList<UserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.user_details where lastName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, lastName);
			ResultSet rs = pst.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("No data");
			} else 
				while (rs.next()) {

					UserDetails userDetails = new UserDetails();
					userDetails.setAddress(rs.getString("Address"));
					userDetails.setDrivingLicenseExpiry(rs.getDate("DL_Expiry").toString());
					userDetails.setBirthDate(rs.getDate("DOB").toString());
					userDetails.setFirstName(rs.getString("FirstName"));
					userDetails.setLastName(rs.getString("LastName"));
					userDetails.setEmail(rs.getString("Email"));
					userDetails.setPhone(rs.getString("Phone"));
					userDetails.setDrivingLicenseNo(rs.getString("DL_Number"));
					userDetails.setRegistrationNumber(rs.getString("Reg_Number"));
					userDetails.setUta_Id(rs.getString("uta_id"));
					
					userListInDB.add(userDetails);

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
		return userListInDB;
	}
	
}
