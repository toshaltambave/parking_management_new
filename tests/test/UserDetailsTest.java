package test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.UserDetailsDAO;
import data.UsersDAO;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.UserDetails;
import model.UserDetailsErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class UserDetailsTest {

	private UserDetails userDetails;
	private UserDetailsErrorMsgs userErrors;

	@Before
	public void setUp() throws Exception {
		userDetails = new UserDetails();
		userErrors = new UserDetailsErrorMsgs();
	}
	
	public String checkForNull(String attribute) {
		if ("null".equals(attribute)) {
			return null;
		}
		return attribute;
	}

	@Test
	@FileParameters("tests/test/UserDetailsTest.csv")
	public void test(String action, String firstName, String middleName, String lastName, Integer userId, String userName, String sex, String dob,
			String address, String email, String phone, String dlNumber, String dlExpiry, String regNumber,
			String utaId, String expectedErrorMsg, String expectedFirstNameError, String expectedMiddleNameError,
			String expectedLastNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneError, String expectedDlError, String expectedDlExpiryError,
			String expectedRegNumberError, String expectedUtaIdError) {

		userDetails.setUserDetails(checkForNull(firstName), checkForNull(middleName), checkForNull(lastName), checkForNull(sex), checkForNull(dob), checkForNull(address), checkForNull(email), checkForNull(phone), checkForNull(dlNumber), checkForNull(dlExpiry),
				checkForNull(regNumber), checkForNull(utaId));
		userDetails.setUserID(userId);
		userDetails.setUsername(userName);
		userDetails.validateUserDetails(action, userDetails, userErrors);

		assertEquals(expectedErrorMsg, userErrors.getErrorMsg());
		assertEquals(expectedFirstNameError, userErrors.getFirstNameError());
		assertEquals(expectedMiddleNameError, userErrors.getMiddleNameError());
		assertEquals(expectedLastNameError, userErrors.getLastNameError());
		assertEquals(expectedDobError, userErrors.getBirthDateError());
		assertEquals(expectedAddressError, userErrors.getAddressError());
		assertEquals(expectedEmailError, userErrors.getEmailError());
		assertEquals(expectedPhoneError, userErrors.getPhoneError());
		assertEquals(expectedDlError, userErrors.getDrivingLicenseError());
		assertEquals(expectedDlExpiryError, userErrors.getDrivingLicenseExpiry());
		assertEquals(expectedRegNumberError, userErrors.getRegNumberError());
		assertEquals(expectedUtaIdError, userErrors.getUtaIdError());
		assertEquals(userId, userDetails.getUserID());
		assertEquals(userName, userDetails.getUsername());
		assertEquals(sex, userDetails.getSex());

	}
	
	@Test
	@FileParameters("tests/test/UserDetailsDAOTest.csv")
	public void DAOtest(String type, String value, String role, boolean isRevoked, String userName, String comment, String lastName){
		UserDetailsDAO dao = new UserDetailsDAO();
		dao.changeRole(type, value, role);
		dao.getLastNames();
		dao.getUserNames();
		dao.insertUserDetails(userDetails);
		dao.ReturnUserID(userName);
		dao.revokeUser(type, value, isRevoked, comment);
		dao.searchByLastName(lastName);
		dao.searchByUsername(userName);
	}

}
