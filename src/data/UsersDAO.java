package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Users;
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
				Users user = new Users(); 
				user.setUsername(usersList.getString("UserName"));
				user.setHashedPassword(usersList.getString("HashedPassword"));
				user.setRole(usersList.getString("Role"));
				user.setisRevoked(usersList.getBoolean("IsRevoked"));  
				user.setPermitType(usersList.getString("PermitType"));
	
				userListInDB.add(user);					
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
	
	private static void StoreListinDB (Users user,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertUser = queryString + " VALUES ('"  
					+ user.getUsername()  + "','"
					+ user.getHashedPassword() + "','"	
					+ user.getRole() + "','"	
					+ user.getisRevoked() + "','"	
					+ user.getPermitType() + "'" + ')';
			stmt.executeUpdate(insertUser);	
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


	public static String userExists(Users user) {
		String role = "";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM system_users where username=? and hashedpassword=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getHashedPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) 
			{
				role = rs.getString("Role");
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
		return role;
	}
	
	public static void insertUser(Users user) {  
		StoreListinDB(user,"INSERT INTO system_users (UserName,HashedPassword,Role,IsRevoked,PermitType) ");
	} 
	
	public static ArrayList<Users>  listUsers() {  
			return ReturnMatchingUsers(" SELECT * from system_users ORDER BY UserName");
	}
	
	//determine if companyID is unique
	public static Boolean Usernameunique(String username)  {  
			return (ReturnMatchingUsers(" SELECT * from system_users WHERE UserName = '"+username+"' ORDER BY UserName").isEmpty());
	}

	public static List<Users> searchByUsername(String userName) {
		ArrayList<Users> userListInDB = new ArrayList<Users>();
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
			} else

				while (rs.next()) {
					Users user = new Users();
					user.setUsername(rs.getString("UserName"));
					user.setHashedPassword(rs.getString("HashedPassword"));
					user.setRole(rs.getString("Role"));
					user.setisRevoked(rs.getBoolean("IsRevoked"));
					user.setPermitType(rs.getString("PermitType"));
					userListInDB.add(user);
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
