package model;

import java.io.Serializable;

public class ParkingArea implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer Area_Id;
	private String Area_Name;
	public Integer getArea_Id() {
		return Area_Id;
	}
	public void setArea_Id(Integer area_Id) {
		Area_Id = area_Id;
	}
	public String getArea_Name() {
		return Area_Name;
	}
	public void setArea_Name(String area_Name) {
		Area_Name = area_Name;
	}
	
}