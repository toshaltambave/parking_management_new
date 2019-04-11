package model;

import java.io.Serializable;

public class ParkingAreaFloors implements Serializable {
	private static final long serialVersionUID = 3L;
	private Integer Area_Id;
	private Integer Floor_Number;
	private String PermitType;
	private Integer No_Spots;
	
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
	public String getPermitType() {
		return PermitType;
	}
	public void setPermitType(String permitType) {
		PermitType = permitType;
	}
	public Integer getNo_Spots() {
		return No_Spots;
	}
	public void setNo_Spots(Integer no_Spots) {
		No_Spots = no_Spots;
	}
	
	
}

