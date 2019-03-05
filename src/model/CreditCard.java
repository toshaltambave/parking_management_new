package model;

public class CreditCard {

	private String CardNumber;
	private String Month;
	private String Year;
	private String cvv;
	private String CardType;
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return CardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return CardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return Month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		Month = month;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return Year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		Year = year;
	}
	/**
	 * @return the cvv
	 */
	public String getCvv() {
		return cvv;
	}
	/**
	 * @param cvv the cvv to set
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}
