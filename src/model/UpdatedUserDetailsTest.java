package model;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.UsersDAO;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class UpdatedUserDetailsTest {
	
	private UpdatedUserDetails updatedUserDetails;
	UsersDAO mockUsersDAO;
	
	@Before
	public void setUp() throws Exception {
		updatedUserDetails = new UpdatedUserDetails(mockUsersDAO);
		mockUsersDAO = EasyMock.createMock(UsersDAO.class);
	}
	
	public String checkForNull(String attribute){
		if("null".equals(attribute)){
			return null;
		}
		return attribute;
	}

	@Test
	@FileParameters("src/updatedUserDetailsTest.csv")
	public void test(String action, String firstName, String middleName, String lastName, String dob, String address, String email, String phone, String dlNumber, String dlExpiry, String utaId, String userName, String hashedPass, String confirmPass, String role, String permitType, String regNumber, String sex, String expectedError, boolean mockBoolean) {
		int userId = 1;
		
		UpdatedUserDetails updatedUserDetail = new UpdatedUserDetails(mockUsersDAO);
		updatedUserDetail.setUpdatedUserDetails(checkForNull(firstName), checkForNull(middleName), checkForNull(lastName), checkForNull(userName), checkForNull(sex), checkForNull(dob), checkForNull(address), checkForNull(email), checkForNull(phone), checkForNull(dlNumber), checkForNull(dlExpiry), checkForNull(regNumber), checkForNull(utaId), checkForNull(hashedPass), checkForNull(confirmPass), checkForNull(role), checkForNull(permitType));
		updatedUserDetail.setUserName(userName);
		updatedUserDetail.setUserID(userId);
		
		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();
		
		EasyMock.expect(mockUsersDAO.Usernameunique(userName)).andReturn(mockBoolean);
		EasyMock.replay(mockUsersDAO);
		
		updatedUserDetail.validateUserDetails(action, updatedUserDetail, errorMsgs);
		assertEquals(expectedError ,errorMsgs.getErrorMsg());
		assertEquals(checkForNull(sex), updatedUserDetail.getSex());
		assertEquals((Integer) userId, updatedUserDetail.getUserID());
	}
}
