package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.*;
import util.SQLConnection;

public class MakeReservationsDOA{
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	public static void StoreReservationsInDB (Reservations reservations,String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertReservation = queryString + " VALUES ('"  
					+ reservations.getUserID()  + "','"
					+ reservations.getSpotUID() + "','"	
					+ reservations.getStartTime() + "','"	
					+ reservations.getEndTime() + "','"
					+ reservations.getNoShow() + "','"
					+ reservations.getOverStay() + "','"
					+ reservations.getCart() + "','"
					+ reservations.getCamera() + "','"
					+ reservations.getHistory() + "'" + ')';
			stmt.executeUpdate(insertReservation);	
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
	public static ArrayList<ReservationsHelper> GetReservationsByName (String firstName, String lastName) {
		ArrayList<ReservationsHelper> ReservationsByName = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time"
							+"from reservations as r"
							+"Inner join system_users as s on r.User_ID=s.User_ID"
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID"
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID"
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id"
							+"where s_u.FirstName="+firstName+"and s_u.LastName="+lastName+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservation_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				ReservationsByName.add(reservation);	
			
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
		return ReservationsByName;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByReservationId (Integer reservation_Id) {
		ArrayList<ReservationsHelper> ReservationsById = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time"
							+"from reservations as r"
							+"Inner join system_users as s on r.User_ID=s.User_ID"
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID"
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID"
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id"
							+"where r.Reservation_Id="+reservation_Id+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservation_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				ReservationsById.add(reservation);	
			
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
		return ReservationsById;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByReservationDate (String current_date) {
		ArrayList<ReservationsHelper> ReservationsByDate = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time, r.NoShow, r.OverStay "
							+"from reservations as r "
							+"Inner join system_users as s on r.User_ID=s.User_ID "
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID "
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID "
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id;";
							//+"where r.Start_Time >="+current_date+";";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservation_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				reservation.setisNoShow(reservationList.getInt("NoShow"));
				reservation.setisOverDue(reservationList.getInt("OverStay"));
				ReservationsByDate.add(reservation);
			
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
		return ReservationsByDate;
	}
	
	public static ArrayList<ReservationsHelper> GetReservationsByReservationNoShow (String current_date) {
		ArrayList<ReservationsHelper> ReservationsNoShow = new ArrayList<ReservationsHelper>();
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try{
			stmt=conn.createStatement();
			String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time,r.NoShow,r.OverStay "
							+"from reservations as r "
							+"Inner join system_users as s on r.User_ID=s.User_ID "
							+"Inner join user_details as s_u on r.User_ID=s_u.User_ID "
							+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID "
							+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id "
							+"where r.End_Time <='"+current_date+"';";
			ResultSet reservationList = stmt.executeQuery(queryString);
			while (reservationList.next()) {
				ReservationsHelper reservation = new ReservationsHelper();
				reservation.setReservationID(reservationList.getInt("Reservation_Id"));
				reservation.setUserName(reservationList.getString("UserName"));
				reservation.setLastName(reservationList.getString("LastName"));
				reservation.setAreaName(reservationList.getString("Area_Name"));
				reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
				reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
				reservation.setStart_Time(reservationList.getString("Start_Time"));
				reservation.setEnd_Time(reservationList.getString("End_Time"));
				reservation.setisNoShow(reservationList.getInt("NoShow"));
				reservation.setisOverDue(reservationList.getInt("OverStay"));
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
 
	public static Boolean SetNoShow(Integer reservationID, Integer user_id){
	 Statement stmt = null;
	 Connection conn = SQLConnection.getDBConnection();
	 try{
		 	
			stmt=conn.createStatement();
			String fetchqueryString="select NoShow from reservations where Reservation_Id="+reservationID+";";
			ResultSet rs = stmt.executeQuery(fetchqueryString);
			if(rs.next()) 
			{			
				int NoShow = rs.getInt("NoShow");
				if(NoShow == 0)
				{
					NoShow = 1;
				}
				else
				{
					NoShow = 0;
				}			
				String queryString="update reservations set NoShow="+NoShow+" where Reservation_Id="+reservationID+";";
				stmt.executeUpdate(queryString);
				conn.commit();
				String queryString1="select count(*) AS Count from reservations where reservations.User_Id="+user_id+" and reservations.NoShow=1";
				ResultSet count=stmt.executeQuery(queryString1);
				count.next();
				Integer totalCount = count.getInt("Count");
				if (totalCount>2){
					String queryString2="Update System_users set isRevoked=1,comment=" +"'No Show Violation'"+" where user_id="+user_id+";";
					stmt.executeUpdate(queryString2);
				}
				else
				{
					String queryString2="Update System_users set isRevoked=0,comment=" +"''"+" where user_id="+user_id+";";
					stmt.executeUpdate(queryString2);
				}
				conn.commit();
			}
	 }catch (SQLException e) {
		 e.printStackTrace();
		 return false;
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	 
	 return true;
	 
 }
 
	public static Boolean SetOverdue(Integer reservationID, Integer user_id) {
	 Statement stmt = null;
	 Connection conn = SQLConnection.getDBConnection();
	 try{
			stmt=conn.createStatement();
			String fetchqueryString="select OverStay from reservations where Reservation_Id="+reservationID+";";
			ResultSet rs = stmt.executeQuery(fetchqueryString);
			if(rs.next()) 
			{			
				int OverStay = rs.getInt("OverStay");
				if(OverStay == 0)
				{
					OverStay = 1;
				}
				else
				{
					OverStay = 0;
				}	
				String queryString="update reservations set OverStay="+OverStay+" where Reservation_Id="+reservationID+";";
				stmt.executeUpdate(queryString);
				conn.commit();
				String queryString1="select count(*) AS Count from reservations where reservations.User_Id="+user_id+" and reservations.OverStay=1";
				ResultSet count=stmt.executeQuery(queryString1);
				count.next();
				Integer totalCount = count.getInt("Count");
				if (totalCount>0)
				{
					String queryString2="Update System_users set isRevoked=1,comment=" +"'Overstayed'"+"  where user_id="+user_id+";";
					stmt.executeUpdate(queryString2);
				}
				else
				{
					String queryString2="Update System_users set isRevoked=0,comment=" +"''"+"  where user_id="+user_id+";";
					stmt.executeUpdate(queryString2);
				}
				conn.commit();
			}
	 }catch (SQLException e) {
		 e.printStackTrace();
		 return false;
	 } finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	 
	 return true;
}
	
	public static Boolean CheckRevoked(Integer user_id) {
		 Statement stmt = null;
		 Connection conn = SQLConnection.getDBConnection();
		 try{
				stmt=conn.createStatement();
				String queryString="select IsRevoked AS Count from system_users where User_Id ="+user_id+";";
				ResultSet count=stmt.executeQuery(queryString);
				count.next();
				Integer isRevoked = count.getInt("Count");
				if(isRevoked == 0){
					return false;
				}
				else if (isRevoked == 1){
					return true;
				}
				
				
		 }catch (SQLException e) {
			 e.printStackTrace();
			 return false;
		 } finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		 
		 return true;
	}
	public static ArrayList<ReservationsHelper> GetReservationsByUserId (Integer user_id) {
	ArrayList<ReservationsHelper> ReservationsById = new ArrayList<ReservationsHelper>();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();
	try{
		stmt=conn.createStatement();
		String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time,r.NoShow,r.OverStay "
						+"from reservations as r "
						+"Inner join system_users as s on r.User_ID=s.User_ID "
						+"Inner join user_details as s_u on r.User_ID=s_u.User_ID "
						+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID "
						+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id "
						+"where s.User_Id ="+user_id+";";
		ResultSet reservationList = stmt.executeQuery(queryString);
		while (reservationList.next()) {
			ReservationsHelper reservation = new ReservationsHelper();
			reservation.setReservationID(reservationList.getInt("Reservation_Id"));
			reservation.setUserName(reservationList.getString("UserName"));
			reservation.setLastName(reservationList.getString("LastName"));
			reservation.setAreaName(reservationList.getString("Area_Name"));
			reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
			reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
			reservation.setStart_Time(reservationList.getString("Start_Time"));
			reservation.setEnd_Time(reservationList.getString("End_Time"));
			reservation.setisNoShow(reservationList.getInt("NoShow"));
			reservation.setisOverDue(reservationList.getInt("OverStay"));
			ReservationsById.add(reservation);
		
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
	return ReservationsById;
}

public static ArrayList<ReservationsHelper> GetReservationsViolations (String current_date, Integer user_id) {
	ArrayList<ReservationsHelper> ReservationsViolations = new ArrayList<ReservationsHelper>();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();
	try{
		stmt=conn.createStatement();
		String queryString="SELECT r.Reservation_Id,s.UserName,s_u.LastName,p_a.Area_Name, p.Floor_Number,p.Spot_Id,r.Start_Time,r.End_Time,r.NoShow,r.OverStay "
						+"from reservations as r "
						+"Inner join system_users as s on r.User_ID=s.User_ID "
						+"Inner join user_details as s_u on r.User_ID=s_u.User_ID "
						+"Inner join parking_spots as p on r.Spot_UID=p.Spot_UID "
						+"Inner join parking_area as p_a on p.Area_Id=p_a.Area_Id "
						+"where r.End_Time <='"+current_date+"' "
						+ "And (r.NoShow=1 or r.OverStay=1) "
						+"And s.User_Id="+user_id+";";
		ResultSet reservationList = stmt.executeQuery(queryString);
		while (reservationList.next()) {
			ReservationsHelper reservation = new ReservationsHelper();
			reservation.setReservationID(reservationList.getInt("Reservation_Id"));
			reservation.setUserName(reservationList.getString("UserName"));
			reservation.setLastName(reservationList.getString("LastName"));
			reservation.setAreaName(reservationList.getString("Area_Name"));
			reservation.setFloor_Number(reservationList.getInt("Floor_Number"));
			reservation.setSpot_Id(reservationList.getInt("Spot_Id"));
			reservation.setStart_Time(reservationList.getString("Start_Time"));
			reservation.setEnd_Time(reservationList.getString("End_Time"));
			reservation.setisNoShow(reservationList.getInt("NoShow"));
			reservation.setisOverDue(reservationList.getInt("OverStay"));
			ReservationsViolations.add(reservation);
		
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
	return ReservationsViolations;
}
 
	public static Integer CountReservationsInDay(Integer user_id){
		 Statement stmt = null;
		 Connection conn = SQLConnection.getDBConnection();
		 Integer totalCount=0;
		 try{
			 	
				stmt=conn.createStatement();
				java.util.Date dt = new java.util.Date();

				java.text.SimpleDateFormat sdf = 
				     new java.text.SimpleDateFormat("yyyy-MM-dd");
				String currentTime = sdf.format(dt);
				String queryString="select count(*) as Count " 
						+"from reservations " 
						+"where Start_Time>='"+currentTime+"%' "
						+"and User_Id="+user_id+";";
		
				ResultSet count=stmt.executeQuery(queryString);
				count.next();
				totalCount = count.getInt("Count");
				
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
		return totalCount;
		 
	}

}
