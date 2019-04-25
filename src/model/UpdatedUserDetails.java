package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.regex.Pattern;

import com.sun.istack.internal.logging.Logger;

import data.UsersDAO;

public class UpdatedUserDetails {
	private static final Logger LOG = Logger.getLogger(UpdatedUserDetails.class.getName(), UpdatedUserDetails.class);
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
	private String oldusername;
	/**
	 * @return the oldusername
	 */
	public String getOldusername() {
		return oldusername;
	}

	/**
	 * @param oldusername the oldusername to set
	 */
	public void setOldusername(String oldusername) {
		this.oldusername = oldusername;
	}

	private String HashedPassword;
	private String ConfirmPassword;
	private String Role;
	private String PermitType;

	private UsersDAO usersDAO;

	public UpdatedUserDetails(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void setUpdatedUserDetails(String firstName, String middleName, String lastName, String userName, String sex,
			String dob, String address, String email, String phone, String dlNumber, String dlExpiry, String regNumber,
			String utaId, String hashPass, String confirmPass, String role, String permitType) {
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setUserName(userName);
		setSex(sex);
		setBirthDate(dob);
		setAddress(address);
		setEmail(email);
		setPhone(phone);
		setDrivingLicenseNo(dlNumber);
		setDrivingLicenseExpiry(dlExpiry);
		setRegistrationNumber(regNumber);
		setUta_Id(utaId);
		setHashedPassword(hashPass);
		setConfirmPassword(confirmPass);
		setRole(role);
		setPermitType(permitType);
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
		DrivingLicenseExpiry = drivingLicenseExpiry;
	}

	public String getRegistrationNumber() {
		return RegistrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		RegistrationNumber = registrationNumber;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
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

	public void validateUserDetails(String action, UpdatedUserDetails UserDetail,
			UpdatedUserDetailsErrorMsgs errorMsgs) {
		LOG.info("VALIDATING.........");
		LOG.info("USERNAME: " + UserDetail.getUserName());

		errorMsgs.setFirstNameError(validateName(UserDetail.getFirstName()));
		String middleName = UserDetail.getMiddleName();
		if (middleName != null && !middleName.isEmpty()) {
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
		if(!UserDetail.getUserName().equals(UserDetail.getOldusername()))
			errorMsgs.setUsernameError(validateUsername(UserDetail.getUserName()));
		else
		{
			String value = "";
			errorMsgs.setUsernameError(value);
		}
		errorMsgs.setHashedPasswordError(validatePassword(UserDetail.getHashedPassword()));
		errorMsgs.setConfirmPasswordError(validateConfirmPassword(UserDetail.getHashedPassword(), UserDetail.getConfirmPassword()));
		errorMsgs.setRoleError(validateRole(UserDetail.getRole()));
		errorMsgs.setPermitTypeError(validatePermitType(UserDetail.getPermitType(), UserDetail.getRole()));

		errorMsgs.setErrorMsg(action);

		LOG.info("ACTION: " + action);
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
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
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
					result = "This should be of length " + size + "digits.";
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

	private String validateUsername(String username) {
		String result = "";
		if (!stringSize(username, 4, 10))
			result = "Your username must between 4 and 10 characters";
		else if (!usersDAO.Usernameunique(username))
			result = "Username is already in database";
		return result;
	}

	private String validatePassword(String password) {
		String result = "";
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

}
