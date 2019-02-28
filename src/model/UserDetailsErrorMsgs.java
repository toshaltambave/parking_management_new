package model;

public class UserDetailsErrorMsgs {

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

	public String getSexError() {
		return SexError;
	}

	public void setSexError(String sexError) {
		SexError = sexError;
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
	private String errorMsg = "";
	private String FirstNameError = "";
	private String MiddleNameError = "";
	private String LastNameError = "";
	private String SexError = "";
	private String BirthDateError = "";
	private String AddressError = "";
	private String EmailError = "";
	private String PhoneError = "";
	private String DrivingLicenseError = "";
	private String DrivingLicenseExpiry = "";
	private String RegNumberError = "";
	
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
	private String utaIdError = "";	

	
	public UserDetailsErrorMsgs () {
		this.errorMsg="";
		this.FirstNameError="";
		this.MiddleNameError="";
		this.LastNameError="";
		this.SexError="";
		this.BirthDateError="";
		this.AddressError="";
		this.EmailError="";
		this.PhoneError="";
		this.DrivingLicenseError="";
		this.DrivingLicenseExpiry="";
		this.RegNumberError="";
		this.utaIdError="";	
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String action) {
		if (action.equals("saveUserDetails")) {
			if (!FirstNameError.equals("") || !MiddleNameError.equals("") 
					|| !LastNameError.equals("") || !SexError.equals("")
					|| !BirthDateError.equals("") || !AddressError.equals("")
					|| !EmailError.equals("") || !PhoneError.equals("")
					|| !DrivingLicenseError.equals("") || !DrivingLicenseExpiry.equals("")
					|| !RegNumberError.equals("") || !utaIdError.equals(""))
				this.errorMsg="Please correct the following errors";
		}
		else
			if (action.equals("searchUserDetails")) 
			{
				if (!FirstNameError.equals("")) 
					this.errorMsg="Please correct the following errors";
			}
			else // action=listEmployee
				if (!LastNameError.equals("")) 
					this.errorMsg="Please correct the following errors";				
	}

	public String getBirthDateError() {
		return BirthDateError;
	}

	public void setBirthDateError(String birthDateError) {
		BirthDateError = birthDateError;
	}
	
	
}
