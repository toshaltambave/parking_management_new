	package model;

	import java.io.Serializable;
public class ParkingAreaHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private String areaname;
	private String permittype;
	private Integer floornumber;
	private Integer numberofspots;
	
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	public String getPermittype() {
		return permittype;
	}
	public void setPermittype(String permittype) {
		this.permittype = permittype;
	}
	public Integer getFloornumber() {
		return floornumber;
	}
	public void setFloornumber(Integer floornumber) {
		this.floornumber = floornumber;
	}
	public Integer getNumberofspots() {
		return numberofspots;
	}
	public void setNumberofspots(Integer numberofspots) {
		this.numberofspots = numberofspots;
	}
	public void setDetails(String areaName2, String permitType2, Integer numberofSpots2, Integer floorno) {
		this.setAreaname(areaName2);
		this.setPermittype(permitType2);
		this.setNumberofspots(numberofSpots2);
		this.setFloornumber(floorno);		
	}
				
}
