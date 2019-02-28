package model;

public enum PermitType {
	Basic,
	Midrange,
	Premium,
	Access;
	
    private String permittype;

    private PermitType( String s )
    {
    	permittype = s;
    }
    
    private PermitType()
    {
    }

    public String getPermitType()
    {
        return permittype;
    }
}
