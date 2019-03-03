package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UpdatedUserDetails;
import util.SQLConnection;

public class UpdatedUserDetailsDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static List<UpdatedUserDetails> searchByUsername(String userName) {
		
		List<UpdatedUserDetails> userListInDB = new ArrayList<UpdatedUserDetails>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int userId;
		try {
			stmt = conn.createStatement();
			PreparedStatement pst = null;
			String sql = "SELECT * FROM parking_management.system_users where UserName=?";
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
				} else if (rs2.next()) {

					UpdatedUserDetails userDetails = new UpdatedUserDetails();
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
}
