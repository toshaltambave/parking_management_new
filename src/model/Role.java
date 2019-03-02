package model;

public enum Role {
	ParkingUser,
	ParkingManager,
	Admin;
	
    private String role;

    private Role ( String s )
    {
    	role = s;
    }
    
    private Role()
    {
    }

    public String getRole()
    {
        return role;
    }
}
