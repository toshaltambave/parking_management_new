package model;

public class CreditCardError {
	
	private String CardNumberError;
	private String MonthError;
	private String YearError;
	private String cvvError;
	private String errorMsg = "";
	
	
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		
		if (!CardNumberError.equals("") || !MonthError.equals("") 
				|| !YearError.equals("") || !cvvError.equals(""))
			this.errorMsg="Please correct the following errors.";		
	}

	/**
	 * @return the cardNumberError
	 */
	public String getCardNumberError() {
		return CardNumberError;
	}
	/**
	 * @param cardNumberError the cardNumberError to set
	 */
	public void setCardNumberError(String cardNumberError) {
		CardNumberError = cardNumberError;
	}
	/**
	 * @return the monthError
	 */
	public String getMonthError() {
		return MonthError;
	}
	/**
	 * @param monthError the monthError to set
	 */
	public void setMonthError(String monthError) {
		MonthError = monthError;
	}
	/**
	 * @return the yearError
	 */
	public String getYearError() {
		return YearError;
	}
	/**
	 * @param yearError the yearError to set
	 */
	public void setYearError(String yearError) {
		YearError = yearError;
	}
	/**
	 * @return the cvvError
	 */
	public String getCvvError() {
		return cvvError;
	}
	/**
	 * @param cvvError the cvvError to set
	 */
	public void setCvvError(String cvvError) {
		this.cvvError = cvvError;
	}

}
