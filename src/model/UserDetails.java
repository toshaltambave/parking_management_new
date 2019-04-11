package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

import com.sun.istack.internal.logging.Logger;

public class UserDetails {
	private static final Logger LOG = Logger.getLogger(UserDetails.class.getName(), UserDetails.class);
	// private static final long serialVersionUID = 3L;
	private Integer UserID;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private String Sex;
	private String birthDate;
	private String Address;
	private String Email;
	private String Phone;
	private String DrivingLicenseNo;
	private String DrivingLicenseExpiry;
	private String RegistrationNumber;
	private String uta_Id;
	private String username;

	public void setUserDetails(String firstname, String middlename, String lastname, String sex, String dob,
			String Address, String Email, String Phone, String DL_Number, String DL_Expiry, String Reg_Number,
			String uta_Id) {
		setFirstName(firstname);
		setMiddleName(middlename);
		setLastName(lastname);
		setSex(sex);
		setBirthDate(dob);
		setAddress(Address);
		setEmail(Email);
		setPhone(Phone);
		setDrivingLicenseNo(DL_Number);
		setDrivingLicenseExpiry(DL_Expiry);
		setRegistrationNumber(Reg_Number);
		setUta_Id(uta_Id);
	}

	public String getDrivingLicenseNo() {
		return DrivingLicenseNo;
	}

	public void setDrivingLicenseNo(String drivingLicenseNo) {
		DrivingLicenseNo = drivingLicenseNo;
	}

	public String getDrivingLicenseExpiry() {
		return DrivingLicenseExpiry;
	}

	public void setDrivingLicenseExpiry(String drivingLicenseExpiry) {
		this.DrivingLicenseExpiry = drivingLicenseExpiry;
	}

	public String getRegistrationNumber() {
		return RegistrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		RegistrationNumber = registrationNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(Integer userID) {
		UserID = userID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getUta_Id() {
		return uta_Id;
	}

	public void setUta_Id(String uta_Id) {
		this.uta_Id = uta_Id;
	}

	public void validateUserDetails(String action, UserDetails UserDetail, UserDetailsErrorMsgs errorMsgs) {
		if (action.equals("saveUserDetails")) {
			errorMsgs.setFirstNameError(validateName(UserDetail.getFirstName()));
			String middleName = UserDetail.getMiddleName();
			if (!middleName.isEmpty()) {
				errorMsgs.setMiddleNameError(validateName(UserDetail.getMiddleName()));
			}
			errorMsgs.setLastNameError(validateName(UserDetail.getLastName()));
			errorMsgs.setBirthDateError(validateDOB(UserDetail.getBirthDate()));
			errorMsgs.setAddressError(validateMandatory(UserDetail.getAddress()));
			errorMsgs.setEmailError(validateEmail(UserDetail.getEmail()));
			errorMsgs.setPhoneError(validateNumber(10, UserDetail.getPhone(), "PhoneNo"));
			errorMsgs.setDrivingLicenseError(validateNumber(8, UserDetail.getDrivingLicenseNo(), "DLNumber"));
			errorMsgs.setRegNumberError(validateRegNo(6, 10, UserDetail.getRegistrationNumber()));
			errorMsgs.setUtaIdError(validateUTAId(UserDetail.getUta_Id()));
			errorMsgs.setDrivingLicenseExpiry(validateMandatory(UserDetail.getDrivingLicenseExpiry()));

			errorMsgs.setErrorMsg(action);
		}
	}

	private String validateUTAId(String utaID) {
		if (utaID != null && !utaID.isEmpty()) {
			String result = "";
			if (!isTextAnInteger(utaID))
				result = "UTA ID must contain digits only.";
			else if (utaID.length() == 10) {
				String firstsub = utaID.substring(0, 3);
				if (!(firstsub.equals("100")))
					result = "UTA ID must start with 100.";
				else
					result = "";
			} else
				result = "UTA ID must be 10 digits long.";
			return result;
		} else {
			return "The field is mandatory.";
		}
	}

	private String validateName(String name) {
		if (name != null && !name.isEmpty()) {
			String result = "";

			String regex = "(.)*(\\d)(.)*";
			Pattern pattern = Pattern.compile(regex);
			boolean containsNumber = pattern.matcher(name).matches();
			if (containsNumber)
				result = "Your name must only contain alphabets.";
			else
				result = "";

			return result;
		} else {
			return "The field is mandatory.";
		}
	}

	private String validateMandatory(String value) {
		if (value != null && !value.isEmpty()) {
			return "";
		} else {
			return "The field is mandatory.";
		}
	}

	private String validateRegNo(Integer min, Integer max, String value) {
		if (value != null && !value.isEmpty()) {
			if (!stringSize(value, min, max))
				return "Registration number should be between " + min + " and " + max + " characters.";
			return "";
		} else {
			return "The field is mandatory.";
		}
	}

	private String validateEmail(String email) {
		if (email != null && !email.isEmpty()) {
			String result = "", extension = "";
			if (!email.contains("@"))
				result = "Email address needs to contain @";
			else if (!stringSize(email, 7, 45))
				result = "Email address must be between 7 and 45 characters long";
			else {
				extension = email.substring(email.length() - 4, email.length());
				if (!extension.equals(".org") && !extension.equals(".edu") && !extension.equals(".com")
						&& !extension.equals(".net") && !extension.equals(".gov") && !extension.equals(".mil"))
					result = "Invalid domain name";
			}
			return result;
		} else {
			return "The field is mandatory.";
		}
	}

	private String validateDOB(String DOB) {
		String result = "";
		if (DOB != null && !DOB.isEmpty()) {
			try {

				Date date;
				date = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
				Calendar cal = Calendar.getInstance();

				if (date.after(cal.getTime())) {
					result = "Date of birth can't be after the current date.";
				}

			} catch (java.text.ParseException e) {
				LOG.log(Level.SEVERE, "Date Error: ", e);
			}

		} else
			result = "The field is mandatory.";
		return result;
	}

	// This section is for general purpose methods used internally in this class
	private boolean stringSize(String string, int min, int max) {
		return string.length() >= min && string.length() <= max;
	}

	private boolean isTextAnInteger(String string) {
		boolean result;
		try {
			Long.parseLong(string);
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		return result;
	}

	private String validateNumber(Integer size, String value, String type) {
		if (value != null && !value.isEmpty()) {
			String result = "";
			if (!stringSize(value, size, size))
				if (type.equals("PhoneNo"))
					result = "Your phone number should be " + size + " digits. eg: 7283334567";
				else
					result = "This should be of length " + size + " digits.";
			else if (!isTextAnInteger(value))
				if (type.equals("PhoneNo"))
					result = "Phone number can only contain digits.";
				else
					result = "It can only contain digits.";
			return result;
		} else {
			return "The field is mandatory.";
		}
	}

}
