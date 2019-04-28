package model;

import data.*;

public class Users {

	// private static final long serialVersionUID = 3L;
	private Integer UserID;
	private String Username;
	private String HashedPassword;
	private String ConfirmPassword;
	private String Role;
	private Integer IsRevoked;
	private String Comment;
	private String PermitType;
	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.Comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		Comment = comment;
	}

	private UsersDAO usersDAO;

	public Users(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void setUser(String username, String hashedPassword, String confirmPassword, String role, String permitType,
			Boolean isRevoked,String comment) {
		setUsername(username);
		setHashedPassword(hashedPassword);
		setConfirmPassword(confirmPassword);
		setRole(role);
		setPermitType(permitType);
		setisRevoked(isRevoked);
		setComment(comment);
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

	public void validateUser(String action, Users user, UsersErrorMsgs errorMsgs) {
		if (action.equals("saveUser")) {
			errorMsgs.setusernameError(validateUsername(action, user.getUsername()));
			errorMsgs.setpasswordError(validatePassword(action, user.getHashedPassword()));
			errorMsgs.setconfirmpasswordError(
					validateConfirmPassword(user.getHashedPassword(), user.getConfirmPassword()));
			errorMsgs.setroleError(validateRole(user.getRole()));
			errorMsgs.setpermitTypeError(validatePermitType(user.getPermitType(), user.getRole()));
			errorMsgs.setErrorMsg(action);
		}
	}

	private String validateUsername(String action, String username) {
		String result = "";
		if (action.equals("saveUser")) {
			if (!stringSize(username, 4, 10))
				result = "Your username must between 4 and 10 characters";
			else if (!usersDAO.Usernameunique(username))
				result = "Username is already in database";
		} else if (action.equals("Login")) {
			result = "username or password is incorrect.";
		}
		return result;
	}

	private String validatePassword(String action, String password) {
		String result = "";
		if (action.equals("saveUser")) {
			if (!stringSize(password, 4, 10))
				result = "Your password must between 4 and 10 characters.";
			else {
				boolean hasUpper = password.matches(".*[A-Z].*");
				boolean hasNums = password.matches(".*[0-9].*");

				if (!hasNums) {
					result = "Password must contain at least one number.";
				}

				if (!hasUpper) {
					result = "Password must contain at least one uppercase.";
				}

				if (!hasUpper && !hasNums) {
					result = "Password must contain at least one uppercase and one number.";
				}
			}
		} else if (action.equals("Login")) {
			result = "username or password is incorrect.";
		}
		return result;
	}

	private String validateConfirmPassword(String password, String confirmPassword) {
		String result = "";
		if (!password.contentEquals(confirmPassword))
			result = "Passwords do not match.";
		else
			result = "";
		return result;
	}

	private String validateRole(String role) {
		String result = "";
		if (role.contentEquals("Select User Role"))
			result = "Select the role of the user.";
		else
			result = "";
		return result;
	}

	private String validatePermitType(String permitType, String role) {
		String result = "";
		if (role.contentEquals("ParkingUser") && permitType.contentEquals("Select Permit Type")) {
			result = "Permit type is mandatory for Parking User.";
		}

		return result;
	}

	public void validateLogin(String action, Users user, UsersErrorMsgs errorMsgs) {
		if (user == null) {
			errorMsgs.setusernameError(validateUsername(action, ""));
			errorMsgs.setpasswordError(validatePassword(action, ""));
			errorMsgs.setErrorMsg(action);
		}
	}

	// This section is for general purpose methods used internally in this class
	private boolean stringSize(String string, int min, int max) {
		return string.length() >= min && string.length() <= max;
	}	
	
	public String validateComment(String comment) {
		if (comment.equals(""))
			return "Comment is mandatory.";	
		else
			return "";
	}

}
