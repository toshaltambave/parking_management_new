package model;

public class Reservations 
{

//	private static final long serialVersionUID = 3L;
	private Integer ReservationID;
	private Integer UserID;
	private Integer SpotUID;
	private String 	StartTime="";
	private String 	EndTime="";
	private Boolean NoShow=false;
	private Boolean	OverStay=false;
	private Boolean Cart=false;
	private Boolean Camera=false;
	private Boolean History=false;
	private Double Total=0.0;
	
	public void makeReservations (Integer userID,Integer spotUID,String startTime, 
			String endTime, Boolean noShow, Boolean overStay, Boolean cart,Boolean camera,Boolean history)
	{
		setUserID(userID);
		setSpotUID(spotUID);
		setStartTime(startTime);
		setEndTime(endTime);
		setNoShow(noShow);
		setOverStay(overStay);
		setCart(cart);
		setCamera(camera);
		setHistory(history);
	}
	
	
	public Integer getReservationID() {
		return ReservationID;
	}
	public void setReservationID(Integer reservationID) {
		ReservationID = reservationID;
	}
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public Integer getSpotUID() {
		return SpotUID;
	}
	public void setSpotUID(Integer spotUID) {
		SpotUID = spotUID;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public Boolean getNoShow() {
		return NoShow;
	}
	public void setNoShow(Boolean noShow) {
		NoShow = noShow;
	}
	public Boolean getOverStay() {
		return OverStay;
	}
	public void setOverStay(Boolean overStay) {
		OverStay = overStay;
	}
	public Boolean getCart() {
		return Cart;
	}
	public void setCart(Boolean cart) {
		Cart = cart;
	}
	public Boolean getCamera() {
		return Camera;
	}
	public void setCamera(Boolean camera) {
		Camera = camera;
	}
	public Boolean getHistory() {
		return History;
	}
	public void setHistory(Boolean history) {
		History = history;
	}
	
	public void calculateTotal() {
		if (Cart==true){
			Total+=15.95;
		}
		if (Camera==true){
			Total+=2.95;
		}	
		if(History==true){
			Total+=1.95;
		}
	}
}