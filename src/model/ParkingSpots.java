package model;

public class ParkingSpots {
	
	private Integer Floor_Number;
	private Integer Spot_Id;
	private Boolean isBlocked;
	private String PermitType;
	private Integer Area_Id;
	private Integer Spot_UID;
	
	public Integer getSpot_UID() {
		return Spot_UID;
	}
	public void setSpot_UID(Integer spot_UID) {
		Spot_UID = spot_UID;
	}
	public Integer getArea_Id() {
		return Area_Id;
	}
	public void setArea_Id(Integer area_Id) {
		Area_Id = area_Id;
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
	public Boolean getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public String getPermitType() {
		return PermitType;
	}
	public void setPermitType(String permitType) {
		PermitType = permitType;
	}
	
	
}
