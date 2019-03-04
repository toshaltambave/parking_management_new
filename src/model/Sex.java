package model;

public enum Sex {
	Male,
	Female,
	Other;
	
    private String sex;

    private Sex ( String s )
    {
    	sex = s;
    }
    
    private Sex()
    {
    }

    public String getSex()
    {
        return sex;
    }	
}
