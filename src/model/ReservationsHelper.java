package model;

public class ReservationsHelper 
{
	private Integer ReservationID;
	private String UserName;
	private String LastName;
	private String AreaName;
	private Integer Floor_Number;
	private Integer Spot_Id;
	private String Start_Time;
	private String End_Time;

	public Integer getReservationID() {
		return ReservationID;
	}
	public void setReservationID(Integer reservationID) {
		ReservationID = reservationID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAreaName() {
		return AreaName;
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public Integer getFloor_Number() {
		return Floor_Number;
	}
	public void setFloor_Number(Integer floor_Number) {
		Floor_Number = floor_Number;
	}
	public Integer getSpot_Id() {
		return Spot_Id;
	}
	public void setSpot_Id(Integer spot_Id) {
		Spot_Id = spot_Id;
	}
	public String getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(String start_Time) {
		Start_Time = start_Time;
	}
	public String getEnd_Time() {
		return End_Time;
	}
	public void setEnd_Time(String end_Time) {
		End_Time = end_Time;
	}
}