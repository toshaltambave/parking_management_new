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
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		int userId;
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.system_users where UserName=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();

			if (!rs.isBeforeFirst()) {
				LOG.info("No Data Available");
			} else if (rs.next()) {
				userId = rs.getInt("User_Id");
				stmt = conn.createStatement();
				PreparedStatement pst2 = null;
				String sql2 = "SELECT * FROM parking_management.user_details where user_id=?";
				pst2 = conn.prepareStatement(sql2);
				pst2.setInt(1, userId);
				ResultSet rs2 = pst2.executeQuery();
				if (!rs2.isBeforeFirst()) {
					LOG.info("No Data Available");
				} else if (rs2.next()) {

					UpdatedUserDetails userDetails = new UpdatedUserDetails(new UsersDAO());
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
					userDetails.setUserID(rs2.getInt("User_Id"));
					userDetails.setUserName(userName);
					userDetails.setPermitType(rs.getString("PermitType"));
					userDetails.setRole(rs.getString("Role"));
					userDetails.setConfirmPassword(rs.getString("HashedPassword"));
					userDetails.setHashedPassword(rs.getString("HashedPassword"));

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
			String mySecurePassword = PasswordUtility.generatePassword(userDetails.getHashedPassword());
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
