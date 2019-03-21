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

	@Test
	@FileParameters("src/updatedUserDetailsTest.csv")
	public void test(String action, String firstName, String middleName, String lastName, String dob, String address, String email, String phone, String dlNumber, String dlExpiry, String utaId, String userName, String hashedPass, String confirmPass, String role, String permitType, String regNumber, String sex, String expectedError) {
		UpdatedUserDetails updatedUserDetail = new UpdatedUserDetails(mockUsersDAO);
		updatedUserDetail.setUpdatedUserDetails(firstName, middleName, lastName, userName, sex, dob, address, email, phone, dlNumber, dlExpiry, regNumber, utaId, hashedPass, confirmPass, role, permitType);
		updatedUserDetail.setUserName(userName);
		updatedUserDetail.setUserID(1);
		
		UpdatedUserDetailsErrorMsgs errorMsgs = new UpdatedUserDetailsErrorMsgs();
		
		EasyMock.expect(mockUsersDAO.Usernameunique(userName)).andReturn(true);
		EasyMock.replay(mockUsersDAO);
		
		updatedUserDetail.validateUserDetails(action, updatedUserDetail, errorMsgs);
		assertEquals(expectedError ,errorMsgs.getErrorMsg());
	}
}
