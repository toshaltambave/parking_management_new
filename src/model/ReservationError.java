package model;

public class ReservationError {
	String startTimeError;
	String endTimeError;
	String compareError = "";
	String errorMsg;
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getStartTimeError() {
		return startTimeError;
	}
	public void setStartTimeError(String startTimeError) {
		this.startTimeError = startTimeError;
	}
	public String getEndTimeError() {
		return endTimeError;
	}
	public void setEndTimeError(String endTimeError) {
		this.endTimeError = endTimeError;
	}
	public String getCompareError() {
		return compareError;
	}
	public void setCompareError(String compareError) {
		this.compareError = compareError;
	}
	
}
