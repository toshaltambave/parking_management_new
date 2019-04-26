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
	
	public void validatecreditcarddetails(CreditCard cc,CreditCardError errorMsgs) {
		errorMsgs.setCardNumberError(validateCardNumber(cc.getCardNumber(),cc.getCardType()));
		errorMsgs.setCvvError(validateCVV(cc.getCvv()));
		errorMsgs.setMonthError(validateMonth(cc.getMonth()));
		errorMsgs.setYearError(validateYear(cc.getYear()));
		errorMsgs.setErrorMsg("error");
	}
	
	private String validateMonth(String expMonth) {
		if(expMonth.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(expMonth,2,2))
			{
				return "Month must be 2 digits.";
			}
			else
			{
				if(!isTextAnInteger(expMonth))
					return "Month must only digits.";
				else
				{
					int month = Integer.parseInt(expMonth);
					if(month ==00 || month >= 13)
						return "Month can only be between 01 to 12";
					else
						return "";
				}
			}
		}
	}

	private String validateCVV(String cvv) {
		if(cvv.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(cvv,3,3))
			{
				return "CVV must be 3 digits.";
			}
			else
			{
				if(!isTextAnInteger(cvv))
					return "CVV must only digits.";
				else
					return "";
			}
		}
	}

	private String validateCardNumber(String cardNumber, String cardType) {
		if(cardNumber.isEmpty())
			return "This field is required.";
		else
		{
			if(!isTextAnInteger(cardNumber))
				return "Card number must only digits.";
			else
			{
				if(cardType.equalsIgnoreCase("VISA") || cardType.equalsIgnoreCase("MASTERCARD") || cardType.equalsIgnoreCase("DISCOVER"))
				{
					if (!stringSize(cardNumber,16,16))
					{
						return "Card number must be 16 digits.";
					}
					else
					{ 
						if(cardType.equalsIgnoreCase("VISA"))
						{
							if(!cardNumber.substring(0,1).equalsIgnoreCase("4"))
							{
								return "This is not a VISA card as it starts with 4.";
							}
							else
								return "";
						}
						else if (cardType.equalsIgnoreCase("MASTERCARD"))
						{
							if(!(cardNumber.substring(0,2).equalsIgnoreCase("51")
									|| cardNumber.substring(0,2).equalsIgnoreCase("52")
									|| cardNumber.substring(0,2).equalsIgnoreCase("53")
									|| cardNumber.substring(0,2).equalsIgnoreCase("54")
									|| cardNumber.substring(0,2).equalsIgnoreCase("55")))
							{
								return "This is not a Master card as it starts with 51/52/53/54/55.";
							}
							else
								return "";
						}
						else 
						{
							if(!(cardNumber.substring(0,4).equalsIgnoreCase("6011")
									|| cardNumber.substring(0,2).equalsIgnoreCase("65")))
									return "This is not a Discover card as it starts with 6011/65.";						
								else
									return "";
									
						}
					}
				}
				else 
				{
					if (!stringSize(cardNumber,15,15))
					{
						return "Card number must be 15 digits.";
					}
					else
					{
						if(!(cardNumber.substring(0,2).equalsIgnoreCase("37")
								|| cardNumber.substring(0,2).equalsIgnoreCase("34")))
							return "This is not a AMEX card as it starts with 34/37.";						
						else
							return "";
					}
				}
				
			}
		}
	}

	private String validateYear(String expYear) {
		if(expYear.isEmpty())
			return "This field is required.";
		else
		{
			if (!stringSize(expYear,4,4))
			{
				return "Year must be 4 digits.";
			}
			else
			{
				if(!isTextAnInteger(expYear))
					return "Year must only digits.";
				else
					return "";
			}
		}
	}
	
	private boolean isTextAnInteger (String string) {
        boolean result;
		try
        {
            Long.parseLong(string);
            result=true;
        } 
        catch (NumberFormatException e) 
        {
            result=false;
        }
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}

}
