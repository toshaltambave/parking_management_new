package model;
import data.*;

public class Users 
{

//	private static final long serialVersionUID = 3L;
	private Integer UserID;
	private String Username = "";
	private String HashedPassword = "";
	private String ConfirmPassword = "";
	private String Role = "";
	private Integer IsRevoked = 0;
	private String PermitType = "";
	
	public void setUser (String username,String hashedPassword, 
			String confirmPassword, String role, String permitType, Boolean isRevoked)
	{
		setUsername(username);
		setHashedPassword(hashedPassword);
		setConfirmPassword(confirmPassword);
		setRole(role);
		setPermitType(permitType);
		setisRevoked(isRevoked);
	}
	
	
	public Integer getUserID() {
		return UserID;
	}


	public void setUserID(Integer userID) {
		UserID = userID;
	}


	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getHashedPassword() {
		return HashedPassword;
	}


	public void setHashedPassword(String hashedPassword) {
		HashedPassword = hashedPassword;
	}


	public String getConfirmPassword() {
		return ConfirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}


	public String getRole() {
		return Role;
	}


	public void setRole(String role) {
		Role = role;
	}


	public String getPermitType() {
		return PermitType;
	}


	public void setPermitType(String permitType) {
		PermitType = permitType;
	}


	public Integer getisRevoked() {
		return IsRevoked;
	}
	public void setisRevoked(Boolean isRevoked) {
		if (isRevoked)
			this.IsRevoked = 1;
		else
			this.IsRevoked = 0;

	}
		
	public void validateUser (String action, Users user, UsersErrorMsgs errorMsgs) {
		if (action.equals("saveUser")) 
		{
			errorMsgs.setusernameError(validateUsername(action,user.getUsername()));
			errorMsgs.setpasswordError(validatePassword(user.getHashedPassword()));
			errorMsgs.setconfirmpasswordError(validateConfirmPassword(user.getHashedPassword(),user.getConfirmPassword()));
			errorMsgs.setroleError(validateRole(user.getRole()));
			errorMsgs.setpermitTypeError(validatePermitType(user.getPermitType(),user.getRole()));
			errorMsgs.setErrorMsg(action);
		}
	}

	private String validateUsername(String action, String username) {
		String result="";
		if (action.equals("saveUser")) {
			if (!stringSize(username,4,10))
				result= "Your username must between 4 and 10 characters";
			else
				if (!UsersDAO.Usernameunique(username))
					result="Username is already in database";
		}
		return result;
	}
	
	private String validatePassword(String password) {
		String result="";
		if (!stringSize(password,4,10))
			result= "Your password must between 4 and 10 characters.";
		else
		{
		    char ch;
		    boolean capitalFlag = false;
		    boolean numberFlag = false;
		    for(int i=0;i < password.length();i++) 
		    {
		        ch = password.charAt(i);
		        if(Character.isDigit(ch)) {
		        	numberFlag = true;
		        	result = "";
		        }
		        else
		        {
			       if(!numberFlag)
			    	   result="Password must contain at least one number.";
		        }
		        if (Character.isUpperCase(ch)) {
		        	capitalFlag = true;
		        	result = "";

		        } 
		        else
		        {	
		        	if (!capitalFlag)
			        	result="Password must contain at least one uppercase.";
		        }
		        if(!numberFlag && !capitalFlag)
		        	result="Password must contain at least one uppercase and one number.";
		    }		    				
		}
		return result;		
	}
	
	private String validateConfirmPassword(String password, String confirmPassword) {
		String result="";
		if (!password.contentEquals(confirmPassword))
			result= "Passwords do not match.";
		else
			result = "";
		return result;		
	}
	
	private String validateRole(String role) {
		String result="";
		if (role.contentEquals("Select User Role"))
			result="Select the role of the user.";
		else
			result="";
		return result;		
	}
	
	private String validatePermitType(String permitType, String role) {
		String result="";
		if(role.contentEquals("ParkingUser"))
		{
			if (permitType.contentEquals("Select Permit Type"))
				result="Permit type is mandatory for Parking User.";
			else
				result = "";
		}
		return result;		
	}

//	This section is for general purpose methods used internally in this class	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	
}
