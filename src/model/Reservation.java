package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import data.*;

public class Reservation implements Serializable
{

	private static final long serialVersionUID = 3L;
	private Integer ReservationID;
	private Integer UserID;
	private Integer SpotUID;
	private String 	StartTime;
	private String 	EndTime;
	private Boolean NoShow;
	private Boolean	OverStay;
	private Boolean Cart;
	private Boolean Camera;
	private Boolean History;
	private Double Total;
	
	public void makeReservation (Integer userID,Integer spotUID,String startTime, 
			String endTime, Boolean noShow, Boolean overStay, Boolean cart,Boolean camera,Boolean history, Double total)
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
		setTotal(total);
	}
	
	
	public Double getTotal() {
		return Total;
	}


	public void setTotal(Double total) {
		Total = total;
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
	
	public void validateDateTime(String startTime, String endTime, ReservationError resError) 
	{
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(startTime.isEmpty())
		{
			resError.setStartTimeError("This field is required.");
		}
		else
		{
			try
			{
			Date startdate = formatter.parse(startTime);	
			Date date = new Date();
			int startHours = startdate.getHours();
			int startMins =  startdate.getMinutes();
			int currentHours = date.getHours();
			int currentMins = date.getMinutes();
				if(startHours < currentHours)
				{
					resError.setStartTimeError("Start time cannot be before current time.");
				}
				else
				{
					if(startHours == currentHours && startMins < currentMins)
					{
						resError.setStartTimeError("Start time cannot be before current time.");
					}
					else
					{
						resError.setStartTimeError("");
					}
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		if(endTime.isEmpty())
		{
			resError.setEndTimeError("This field is required.");
		}
		else
		{
			try
			{
			Date enddate = formatter.parse(endTime);
			Date date  = new Date();
			int endHours = enddate.getHours();
			int endMins =  enddate.getMinutes();
			int currentHours = date.getHours();
			int currentMins = date.getMinutes();
			
			if(endHours < currentHours)
			{
				resError.setEndTimeError("End time cannot be before current time.");
			}
			else
			{
				if(endHours == currentHours && endMins < currentMins)
				{
					resError.setEndTimeError("End time cannot be before current time.");
				}
				else
				{
					resError.setEndTimeError("");	
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		if(!endTime.isEmpty() && !startTime.isEmpty())
		{
			Date enddate;
			try {
				enddate = formatter.parse(endTime);
			
			Date startdate;
				startdate = formatter.parse(startTime);
			int endHours = enddate.getHours();
			int endMins =  enddate.getMinutes();
			int startHours = startdate.getHours();
			int startMins =  startdate.getMinutes();
			//int diffHours = endHours - startHours;
			int diffMins = (endHours*60 + endMins) - (startHours*60 + startMins);
			
			
			if(startdate.after(enddate))
			{
				resError.setCompareError("Start time cannot be after end time.");
			}
			else if(startdate.equals(enddate))
			{
				resError.setCompareError("Start time and end time cannot be same.");
			}
//			else if(diffHours >3)
//			{
//				resError.setCompareError("Reservation cannot be for more than 3 hours.");
//			}
			//TODO: Maybe remove this  
			else if(diffMins >180)
			{
				resError.setCompareError("Reservation cannot be for more than 3 hours.");
			}
			else
			{
				resError.setCompareError("");
			}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if(!resError.getCompareError().isEmpty() || !resError.getStartTimeError().isEmpty() || !resError.getEndTimeError().isEmpty())
			resError.setErrorMsg("There are time errors.");
		else
			resError.setErrorMsg("");
	}
	
}