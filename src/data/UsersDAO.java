package data;

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

public class UsersDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Users> ReturnMatchingUsers (String queryString) {
		ArrayList<Users> userListInDB = new ArrayList<Users>();		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet usersList = stmt.executeQuery(queryString);
			while (usersList.next()) {
				Users user = new Users(new UsersDAO()); 
				user.setUsername(usersList.getString("UserName"));
				user.setHashedPassword(usersList.getString("HashedPassword"));
				user.setRole(usersList.getString("Role"));
				user.setisRevoked(usersList.getBoolean("IsRevoked"));  
				user.setPermitType(usersList.getString("PermitType"));
				user.setUserID(usersList.getInt("User_Id"));	
				userListInDB.add(user);					
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return userListInDB;
	}
	
	private static void storeUser (Users user,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
        // Protect user's password. The generated value can be stored in DB.       
        String mySecurePassword = PasswordUtility.generatePassword(user.getHashedPassword());
		try {
			stmt = conn.createStatement();
			String insertUser = queryString + " VALUES ('"  
					+ user.getUsername()  + "','"
					+ mySecurePassword + "','"	
					+ user.getRole() + "','"	
					+ user.getisRevoked() + "','"	
					+ user.getPermitType() + "'" + ')';
			stmt.executeUpdate(insertUser);	
			conn.commit();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}


	public static void userExists(String username, String password, Users user) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM system_users where username=? and hashedpassword=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) 
			{
				user.setUsername(rs.getString("UserName"));
				user.setHashedPassword(rs.getString("HashedPassword"));
				user.setRole(rs.getString("Role"));
				user.setisRevoked(rs.getBoolean("IsRevoked"));
				user.setPermitType(rs.getString("PermitType"));
				user.setUserID(rs.getInt("User_Id"));
				user.setComment(rs.getString("comment"));
			}
			else
				user = null;
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void insertUser(Users user) {  
		storeUser(user,"INSERT INTO system_users (UserName,HashedPassword,Role,IsRevoked,PermitType) ");
	} 
	
	//determine if username is unique
	public Boolean Usernameunique(String username)  {  
			return (ReturnMatchingUsers(" SELECT * from system_users WHERE UserName = '"+username+"' ORDER BY UserName").isEmpty());
	}
	
	
	public static Integer getUserIdbyUsername(String userName) {
		Integer user_Id = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.system_users where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("No data");
			} 
			else
			{
				rs.next();
				user_Id = rs.getInt("User_Id");	
			}
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_Id;
	}
}