package model;

public enum CreditCardTypes {
	VISA,
	MASTERCARD,
	DISCOVER,
	AMEX;

	
    private String creditcardtype;

    private CreditCardTypes ( String s )
    {
    	creditcardtype = s;
    }
    
    private CreditCardTypes()
    {
    }

    public String getCreditCardTypes()
    {
        return creditcardtype;
    }
}
