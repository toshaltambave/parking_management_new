package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import model.*;
import util.SQLConnection;


public class ParkingAreaDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static Boolean saveArea(ParkingAreaHelper area) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
				stmt = conn.createStatement();
				PreparedStatement pst2 = null;
				String checkArea = "SELECT Area_Id FROM parking_area where Area_Name=?";
				pst2 = conn.prepareStatement(checkArea);
				pst2.setString(1, area.getAreaname());
				ResultSet rs2 = pst2.executeQuery();
				if (!rs2.isBeforeFirst()) {
					System.out.println("No data");
					insertparkingareas(area, conn, pst2);	
				} 
				else if(rs2.next()) {
					insertparkingspots(area, conn, rs2);
			}
		}
			catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		finally
		{
			try
			{
				conn.close();
				stmt.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				return false;
			}};
			return true;
	}
	public static void insertparkingareas(ParkingAreaHelper area, Connection conn, PreparedStatement pst2)
			throws SQLException {
		PreparedStatement pst3 = null;
		String insertArea = "INSERT INTO parking_area (Area_Name) VALUES (?)";
		pst3 = conn.prepareStatement(insertArea);
		pst3.setString(1, area.getAreaname());
		pst3.executeUpdate();
		conn.commit();
		
		ResultSet rs3 = pst2.executeQuery();
		if (!rs3.isBeforeFirst()) {	
			System.out.println("No data");
		}
		else if(rs3.next())
		{					
			insertparkingspots(area, conn, rs3);
		}
		else
		{
			System.out.println("Do Nothing.");
		}		
	}

	public static void insertparkingspots(ParkingAreaHelper area, Connection conn, ResultSet rs3) throws SQLException {
		PreparedStatement pst4 = null;
		String parkingareafloors = "INSERT INTO parking_area_floors (`Area_Id`,`Floor_Number`,`PermitType`,`No_Spots`)VALUES(?,?,?,?)";
		pst4 = conn.prepareStatement(parkingareafloors);
		pst4.setString(1, rs3.getString("Area_Id"));
		pst4.setInt(2, area.getFloornumber());
		pst4.setString(3, area.getPermittype());
		pst4.setInt(4, area.getNumberofspots());
		pst4.executeUpdate();
		conn.commit();					
		String parkingareaspots = "INSERT INTO parking_spots(`Area_Id`,`Floor_Number`,`Spot_Id`,`IsBlocked`,`PermitType`)VALUES(?,?,?,?,?)";
		for (int i = 1; i <= area.getNumberofspots(); i++) {
			PreparedStatement pst5 = null;
			pst5 = conn.prepareStatement(parkingareaspots);
			pst5.setString(1, rs3.getString("Area_Id"));
			pst5.setInt(2, area.getFloornumber());
			pst5.setInt(3, i);
			pst5.setInt(4, 0);
			pst5.setString(5, area.getPermittype());
			pst5.executeUpdate();
			conn.commit();
		}
	}
	public static Boolean addParkingSpot(int areaId, int floorNumber, String permitType) {
		Connection conn = SQLConnection.getDBConnection();  
		try {

				PreparedStatement pst2 = null;
				String getSpots = "SELECT No_Spots FROM parking_area_floors where Area_Id = ? and Floor_Number = ? and PermitType = ?";
				pst2 = conn.prepareStatement(getSpots);
				pst2.setInt(1, areaId);
				pst2.setInt(2, floorNumber);
				pst2.setString(3, permitType);
				ResultSet rs = pst2.executeQuery();
				if(rs.next()) 
				{
					int NoSpots = rs.getInt("No_Spots");
					NoSpots = NoSpots + 1;				
					PreparedStatement pst4 = null;
					String parkingareafloors = "UPDATE parking_area_floors SET `No_Spots` = ? WHERE `Area_Id` = ? AND `Floor_Number` =  ? AND `PermitType` = ? ;";
					pst4 = conn.prepareStatement(parkingareafloors);
					pst4.setInt(1, NoSpots);
					pst4.setInt(2, areaId);
					pst4.setInt(3, floorNumber);
					pst4.setString(4, permitType);
					pst4.executeUpdate();
					conn.commit();	
		
					String parkingareaspots = "INSERT INTO parking_spots(`Area_Id`,`Floor_Number`,`Spot_Id`,`IsBlocked`,`PermitType`)VALUES(?,?,?,?,?)";
					PreparedStatement pst5 = null;
					pst5 = conn.prepareStatement(parkingareaspots);
					pst5.setInt(1, areaId);
					pst5.setInt(2, floorNumber);
					pst5.setInt(3, NoSpots);
					pst5.setInt(4, 0);
					pst5.setString(5, permitType);
					pst5.executeUpdate();
					conn.commit();
				}
				else
				{
					System.out.println("Do Nothing.");
				}		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
}