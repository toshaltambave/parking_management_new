package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.UpdatedUserDetailsDAO;
import data.UsersDAO;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Role;
import model.Sex;
import model.UpdatedUserDetails;
import model.UpdatedUserDetailsErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class UpdatedUserDetailsTest {

	private UpdatedUserDetails updatedUserDetails;
	private UsersDAO mockUsersDAO;

	@Before
	public void setUp() throws Exception {
		updatedUserDetails = new UpdatedUserDetails(mockUsersDAO);
		mockUsersDAO = EasyMock.createMock(UsersDAO.class);
	}

	public String checkForNull(String attribute) {
		if ("null".equals(attribute)) {
			return null;
		}
		return attribute;
	}
	
	public String checkSexEnum(String enumName){
		String[] enumArray = Arrays.stream(Sex.values()).map(Enum::name).toArray(String[]::new);
		if(Arrays.asList(enumArray).contains(enumName)){
			return Sex.valueOf(enumName).toString();
		}
		return enumName;
	}
	
	public String checkRoleEnum(String enumName){
		String[] enumArray = Arrays.stream(Role.values()).map(Enum::name).toArray(String[]::new);
		if(Arrays.asList(enumArray).contains(enumName)){
			return Role.valueOf(enumName).toString();
		}
		return enumName;
	}

	@Test
	@FileParameters("tests/test/UpdatedUserDetailsTest.csv")
	public void test(String action, String firstName, String middleName, String lastName, String dob, String address,
			String email, String phone, String dlNumber, String dlExpiry, String utaId, String userName, String oldUserName,
			String hashedPass, String confirmPass, String role, String permitType, String regNumber, String sex,
			String expectedError, boolean mockBoolean, String expectedRoleError, String expectedPermitError,
			String expectedFirstNameError, String expectedMiddleNameError, String expectedLastNameError,
			String expectedEmailError, String expectedAddressError, String expectedUtaIdError,
			String expectedPhoneError, String expectedRegNumError, String expectedUserNameError,
			String expectedHashPassError, String expectedConfirmPassError, String expectedDlError, String expectedDlExpiryError, String expectedDobError) {
		int userId = 1;
	
		UpdatedUserDetails updatedUserDetail = new UpdatedUserDetails(mockUsersDAO);
		updatedUserDetail.setOldusername(oldUserName);
		updatedUserDetail.setUpdatedUserDetails(checkForNull(firstName), checkForNull(middleName),
				checkForNull(lastName), checkForNull(userName), checkForNull(checkSexEnum(sex)), checkForNull(dob),
				checkForNull(address), checkForNull(email), checkForNull(phone), checkForNull(dlNumber),
				checkForNull(dlExpiry), checkForNull(regNumber), checkForNull(utaId), checkForNull(hashedPass),
				checkForNull(confirmPass), checkForNull(checkRoleEnum(role)), checkForNull(permitType));

		updatedUserDetail.setUserID(userId);

		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();

		EasyMock.expect(mockUsersDAO.Usernameunique(userName)).andReturn(mockBoolean);
		EasyMock.replay(mockUsersDAO);

		updatedUserDetail.validateUserDetails(action, updatedUserDetail, errorMsgs);

		assertEquals(checkForNull(sex), updatedUserDetail.getSex());
		assertEquals((Integer) userId, updatedUserDetail.getUserID());

		assertEquals(expectedError, errorMsgs.getErrorMsg());
		assertEquals(expectedRoleError, errorMsgs.getRoleError());
		assertEquals(expectedPermitError, errorMsgs.getPermitTypeError());
		assertEquals(expectedFirstNameError, errorMsgs.getFirstNameError());
		assertEquals(expectedMiddleNameError, errorMsgs.getMiddleNameError());
		assertEquals(expectedLastNameError, errorMsgs.getLastNameError());
		assertEquals(expectedAddressError, errorMsgs.getAddressError());
		assertEquals(expectedEmailError, errorMsgs.getEmailError());
		assertEquals(expectedPhoneError, errorMsgs.getPhoneError());
		assertEquals(expectedRegNumError, errorMsgs.getRegNumberError());
		assertEquals(expectedUtaIdError, errorMsgs.getUtaIdError());
		assertEquals(expectedUserNameError, errorMsgs.getUsernameError());
		assertEquals(expectedHashPassError, errorMsgs.getHashedPasswordError());
		assertEquals(expectedConfirmPassError, errorMsgs.getConfirmPasswordError());
		assertEquals(expectedDlError, errorMsgs.getDrivingLicenseError());
		assertEquals(expectedDlExpiryError, errorMsgs.getDrivingLicenseExpiry());
		assertEquals(expectedDobError, errorMsgs.getBirthDateError());
	}
	
	@Test
	@FileParameters("tests/test/UpdatedUserDetailsDAOTest.csv")
	public void daoTest(String userName, int userId){
		UpdatedUserDetailsDAO dao = new UpdatedUserDetailsDAO();
		dao.searchByUsername(userName);

		updatedUserDetails.setUserID(userId);
		dao.updateUser(updatedUserDetails);
	}
}
