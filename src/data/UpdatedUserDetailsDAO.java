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

import com.sun.istack.internal.logging.Logger;
import model.UpdatedUserDetails;
import util.PasswordUtility;
import util.SQLConnection;

public class UpdatedUserDetailsDAO {
	
	private static final Logger LOG = Logger.getLogger(UpdatedUserDetailsDAO.class.getName(),UpdatedUserDetailsDAO.class);
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static List<UpdatedUserDetails> searchByUsername(String userName) {
		
		List<UpdatedUserDetails> userListInDB = new ArrayList<UpdatedUserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
//		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
//		int userId;
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT su.PermitType,su.Role,ud.* FROM parking_management.system_users su JOIN user_details ud ON su.user_id=ud.user_id where su.UserName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs2 = pst.executeQuery();

			while(rs2.next()) {
					UpdatedUserDetails userDetails = new UpdatedUserDetails(new UsersDAO());
					userDetails.setAddress(rs2.getString("Address"));
					userDetails.setDrivingLicenseExpiry(rs2.getString("DL_Expiry"));
					userDetails.setBirthDate(rs2.getString("DOB"));
					userDetails.setFirstName(rs2.getString("FirstName"));
					userDetails.setMiddleName(rs2.getString("MiddleName"));
					userDetails.setLastName(rs2.getString("LastName"));
					userDetails.setSex(rs2.getString("Sex"));
					userDetails.setEmail(rs2.getString("Email"));
					userDetails.setPhone(rs2.getString("Phone"));
					userDetails.setDrivingLicenseNo(rs2.getString("DL_Number"));
					userDetails.setRegistrationNumber(rs2.getString("Reg_Number"));
					userDetails.setUta_Id(rs2.getString("uta_id"));
					userDetails.setUserID(rs2.getInt("User_Id"));
					userDetails.setUserName(userName);
					userDetails.setOldusername(userName);
					userDetails.setPermitType(rs2.getString("PermitType"));
					userDetails.setRole(rs2.getString("Role"));
					userDetails.setConfirmPassword("");
					userDetails.setHashedPassword("");
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
	
	public static boolean updateUser(UpdatedUserDetails userDetails){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean isSuccessful = true;
		
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql =  "UPDATE parking_management.user_details " 
					+ "SET FirstName=?, MiddleName=?, LastName=?, Sex=?, DOB=?, Address=?, Email=?, Phone=?, DL_Number=?, DL_Expiry=?, Reg_Number=?, uta_Id =? "
					+ "WHERE User_Id =?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userDetails.getFirstName());
			pst.setString(2, userDetails.getMiddleName());
			pst.setString(3, userDetails.getLastName());
			pst.setString(4, userDetails.getSex());
			pst.setString(5, userDetails.getBirthDate());
			pst.setString(6, userDetails.getAddress());
			pst.setString(7, userDetails.getEmail());
			pst.setString(8, userDetails.getPhone());
			pst.setString(9, userDetails.getDrivingLicenseNo());
			pst.setString(10,  userDetails.getDrivingLicenseExpiry());
			pst.setString(11, userDetails.getRegistrationNumber());
			pst.setString(12, userDetails.getUta_Id());
			pst.setInt(13, userDetails.getUserID());
		
			pst.executeUpdate();
			conn.commit();
			stmt.close();
			
			stmt = conn.createStatement();
			pst = null;
			
			String sql2 =  "UPDATE parking_management.system_users " 
					+ "SET UserName=?, HashedPassword=?, Role=?, PermitType=? " 
					+ "WHERE User_Id=?";
			pst = conn.prepareStatement(sql2);
			String mySecurePassword = "";
			mySecurePassword = PasswordUtility.generatePassword(userDetails.getHashedPassword());
			pst.setString(1, userDetails.getUserName());
			pst.setString(2, mySecurePassword);
			pst.setString(3, userDetails.getRole());
			pst.setString(4, userDetails.getPermitType());
			pst.setInt(5, userDetails.getUserID());
			pst.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			isSuccessful = false;
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				isSuccessful = false;
				e.printStackTrace();
			}
		}
		
	return isSuccessful;
	}
}
