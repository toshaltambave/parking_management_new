package model;

public class UsersErrorMsgs {

	private String errorMsg;
	private String usernameError;
	private String passwordError;
	private String confirmpasswordError;
	private String roleError;
	private String permitTypeError;
	private String commentError;

	public UsersErrorMsgs() {
		this.errorMsg = "";
		this.usernameError = "";
		this.passwordError = "";
		this.confirmpasswordError = "";
		this.roleError = "";
		this.permitTypeError = "";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String action) {
		if (action.equals("saveUser")) {
			if (!usernameError.equals("") || !passwordError.equals("") || !confirmpasswordError.equals("")
					|| !roleError.equals("") || !permitTypeError.equals(""))
				this.errorMsg = "Please correct the following errors.";
		} else if (action.equals("Login")) {
			this.errorMsg = "Login Failed.";
		}
	}

	public String getusernameError() {
		return usernameError;
	}

	public void setusernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getpasswordError() {
		return passwordError;
	}

	public void setpasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public String getconfirmpasswordError() {
		return confirmpasswordError;
	}

	public void setconfirmpasswordError(String confirmpasswordError) {
		this.confirmpasswordError = confirmpasswordError;
	}

	public String getroleError() {
		return roleError;
	}

	public void setroleError(String roleError) {
		this.roleError = roleError;
	}

	public String getpermitTypeError() {
		return permitTypeError;
	}

	public void setpermitTypeError(String permitTypeError) {
		this.permitTypeError = permitTypeError;
	}

	/**
	 * @return the commentError
	 */
	public String getCommentError() {
		return commentError;
	}

	/**
	 * @param commentError the commentError to set
	 */
	public void setCommentError(String commentError) {
		this.commentError = commentError;
	}
}
