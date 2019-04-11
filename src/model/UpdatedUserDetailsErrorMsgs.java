package model;

public class UpdatedUserDetailsErrorMsgs {

	private String errorMsg = "";
	private String FirstNameError;
	private String MiddleNameError = "";
	private String LastNameError;
	private String BirthDateError;
	private String AddressError;
	private String EmailError;
	private String PhoneError;
	private String DrivingLicenseError;
	private String DrivingLicenseExpiry;
	private String RegNumberError;
	private String utaIdError;
	private String usernameError;
	private String HashedPasswordError;
	private String ConfirmPasswordError;
	private String RoleError;
	private String PermitTypeError;


	public String getRoleError() {
		return RoleError;
	}

	public String getPermitTypeError() {
		return PermitTypeError;
	}

	public void setPermitTypeError(String permitTypeError) {
		PermitTypeError = permitTypeError;
	}

	public void setRoleError(String roleError) {
		RoleError = roleError;
	}

	public String getFirstNameError() {
		return FirstNameError;
	}

	public void setFirstNameError(String firstNameError) {
		FirstNameError = firstNameError;
	}

	public String getMiddleNameError() {
		return MiddleNameError;
	}

	public void setMiddleNameError(String middleNameError) {
		MiddleNameError = middleNameError;
	}

	public String getLastNameError() {
		return LastNameError;
	}

	public void setLastNameError(String lastNameError) {
		LastNameError = lastNameError;
	}

	public String getAddressError() {
		return AddressError;
	}

	public void setAddressError(String addressError) {
		AddressError = addressError;
	}

	public String getEmailError() {
		return EmailError;
	}

	public void setEmailError(String emailError) {
		EmailError = emailError;
	}

	public String getPhoneError() {
		return PhoneError;
	}

	public void setPhoneError(String phoneError) {
		PhoneError = phoneError;
	}

	public String getRegNumberError() {
		return RegNumberError;
	}

	public void setRegNumberError(String regNumberError) {
		RegNumberError = regNumberError;
	}

	public String getUtaIdError() {
		return utaIdError;
	}

	public void setUtaIdError(String utaIdError) {
		this.utaIdError = utaIdError;
	}

	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getHashedPasswordError() {
		return HashedPasswordError;
	}

	public void setHashedPasswordError(String hashedPasswordError) {
		HashedPasswordError = hashedPasswordError;
	}

	public String getConfirmPasswordError() {
		return ConfirmPasswordError;
	}

	public void setConfirmPasswordError(String confirmPasswordError) {
		ConfirmPasswordError = confirmPasswordError;
	}

	public String getDrivingLicenseError() {
		return DrivingLicenseError;
	}

	public void setDrivingLicenseError(String drivingLicenseError) {
		DrivingLicenseError = drivingLicenseError;
	}

	public String getDrivingLicenseExpiry() {
		return DrivingLicenseExpiry;
	}

	public void setDrivingLicenseExpiry(String drivingLicenseExpiry) {
		DrivingLicenseExpiry = drivingLicenseExpiry;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String getBirthDateError() {
		return BirthDateError;
	}

	public void setBirthDateError(String birthDateError) {
		BirthDateError = birthDateError;
	}

	public void setErrorMsg(String action) {
		if (!FirstNameError.equals("") || !MiddleNameError.equals("") || !LastNameError.equals("")
				|| !BirthDateError.equals("") || !AddressError.equals("") || !EmailError.equals("")
				|| !PhoneError.equals("") || !DrivingLicenseError.equals("") || !DrivingLicenseExpiry.equals("")
				|| !RegNumberError.equals("") || !utaIdError.equals("") || !usernameError.equals("")
				|| !HashedPasswordError.equals("") 
				|| !ConfirmPasswordError.equals("") 
				|| !RoleError.equals("")
				|| !PermitTypeError.equals(""))
			this.errorMsg = "Please correct the following errors";
	}

}
