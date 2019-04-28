package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;

import com.sun.istack.internal.logging.Logger;

import data.*;

public class Reservation implements Serializable {

	private static final Logger LOG = Logger.getLogger(Reservation.class.getName(), Reservation.class);
	private static final long serialVersionUID = 3L;
	private Integer ReservationID;
	private Integer UserID;
	private Integer SpotUID;
	private String StartTime;
	private String EndTime;
	private Boolean NoShow;
	private Boolean OverStay;
	private Boolean Cart;
	private Boolean Camera;
	private Boolean History;
	private Double Total;

	public void makeReservation(Integer userID, Integer spotUID, String startTime, String endTime, Boolean noShow,
			Boolean overStay, Boolean cart, Boolean camera, Boolean history, Double total) {
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

	public void validateDateTime(String startTime, String endTime, ReservationError resError) {

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (startTime.isEmpty()) {
				resError.setStartTimeError("This field is required.");
			} else {
				Date startdate = formatter.parse(startTime);
				Date date = new Date();

				if (startdate.before(date)) {
					resError.setStartTimeError("Start time cannot be before current time.");
				} else {
						resError.setStartTimeError("");	
				}
			}
			if (endTime.isEmpty()) {
				resError.setEndTimeError("This field is required.");
			} else {

				Date enddate = formatter.parse(endTime);
				Date date = new Date();

				if (enddate.before(date)) {
					resError.setEndTimeError("End time cannot be before current time.");
				} else {
					resError.setEndTimeError("");
				}

			}
			if (!endTime.isEmpty() && !startTime.isEmpty()) {
			
				Date startdate;
				Date enddate;
				
				startdate = formatter.parse(startTime);
				enddate = formatter.parse(endTime);
				
		        DateTime start = new DateTime(startdate.getTime());
		        DateTime end = new DateTime(enddate.getTime());

				int minutes = Minutes.minutesBetween(start,end).getMinutes();
				if (startdate.after(enddate)) {
					resError.setCompareError("Start time cannot be after end time.");
				} else if (startdate.equals(enddate)) {
					resError.setCompareError("Start time and end time cannot be same.");
				} else if (minutes > 180) {
					resError.setCompareError("Reservation cannot be for more than 3 hours.");
				} 
			}

			if (!resError.getCompareError().isEmpty() || !resError.getStartTimeError().isEmpty()
					|| !resError.getEndTimeError().isEmpty())
				resError.setErrorMsg("There are time errors.");
			else
				resError.setErrorMsg("");
		} catch (ParseException e) {
			LOG.info("ERROR: ", e);
		}
	}

	public boolean checkNormalHours(Date startdate, Date enddate) {
		int startDay = startdate.getDay();
		int startHours = startdate.getHours();
		int endHours = enddate.getHours();
		boolean normalHours = true;

		if (startDay == 6) {
			// Saturday
			if (startHours < 8 || endHours > 16) {
				normalHours = false;
			}
		} else if (startDay == 0) {
			// Sunday
			if (startHours < 12 || endHours > 16) {
				normalHours = false;
			}
		} else {
			// Monday to Friday
			if (startHours < 6 || endHours > 19) {
				normalHours = false;
			}
		}

		return normalHours;
	}

}