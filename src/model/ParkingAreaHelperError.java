package model;

public class ParkingAreaHelperError {
	
	private String errorMsg = "";
	private String AreaNameError = "";
	private String FloorNumberError = "";
	private String PermitTypeError = "";
	private String NumberofSpotsError = "";
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) 
	{
			if (!AreaNameError.equals("") || !FloorNumberError.equals("") 
					|| !PermitTypeError.equals("") || !NumberofSpotsError.equals(""))
				this.errorMsg="Please correct the following errors";
	}
	/**
	 * @return the areaNameError
	 */
	public String getAreaNameError() {
		return AreaNameError;
	}
	/**
	 * @param areaNameError the areaNameError to set
	 */
	public void setAreaNameError(String areaNameError) {
		AreaNameError = areaNameError;
	}
	/**
	 * @return the floorNumberError
	 */
	public String getFloorNumberError() {
		return FloorNumberError;
	}
	/**
	 * @param floorNumberError the floorNumberError to set
	 */
	public void setFloorNumberError(String floorNumberError) {
		FloorNumberError = floorNumberError;
	}
	/**
	 * @return the permitTypeError
	 */
	public String getPermitTypeError() {
		return PermitTypeError;
	}
	/**
	 * @param permitTypeError the permitTypeError to set
	 */
	public void setPermitTypeError(String permitTypeError) {
		PermitTypeError = permitTypeError;
	}
	/**
	 * @return the numberofSpotsError
	 */
	public String getNumberofSpotsError() {
		return NumberofSpotsError;
	}
	/**
	 * @param numberofSpotsError the numberofSpotsError to set
	 */
	public void setNumberofSpotsError(String numberofSpotsError) {
		NumberofSpotsError = numberofSpotsError;
	}
	
}
