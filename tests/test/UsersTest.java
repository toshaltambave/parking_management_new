package test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.UsersDAO;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Users;
import model.UsersErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class UsersTest {

	private Users users;
	private UsersErrorMsgs usersErrorMsgs;
	private UsersErrorMsgs usersLoginErrorMsgs;
	private UsersDAO mockUsersDAO;

	@Before
	public void setUp() throws Exception {
		usersErrorMsgs = new UsersErrorMsgs();
		usersLoginErrorMsgs = new UsersErrorMsgs();
		mockUsersDAO = EasyMock.createMock(UsersDAO.class);
		users = new Users(mockUsersDAO);
	}
	
	public Integer getisRevokedValue(Boolean isRevoked) {
		if (isRevoked)
			return 1;
		else
			return 0;

	}

	@Test
	@FileParameters("tests/test/UsersTest.csv")
	public void test(Integer userId, String action, String userName, String hashedPassword, String confirmPassword,
			String role, String permitType, boolean isRevoked, boolean mockBoolean, String expectedErrorMsg,
			String expectedUserNameError, String expectedHashPasswordError, String expectedConfirmPasswordError,
			String expectedRoleError, String expectedPermitError, String expectedLoginErrorMsg,
			String expectedLoginUserNameError, String expectedLoginPasswordError) {

		EasyMock.expect(mockUsersDAO.Usernameunique(userName)).andReturn(mockBoolean);
		EasyMock.replay(mockUsersDAO);

		users.setUserID(userId);
		users.setUser(userName, hashedPassword, confirmPassword, role, permitType, isRevoked,"");
		users.validateLogin(action, users, usersErrorMsgs);
		users.validateUser(action, users, usersErrorMsgs);

		assertEquals(expectedErrorMsg, usersErrorMsgs.getErrorMsg());
		assertEquals(expectedUserNameError, usersErrorMsgs.getusernameError());
		assertEquals(expectedHashPasswordError, usersErrorMsgs.getpasswordError());
		assertEquals(expectedConfirmPasswordError, usersErrorMsgs.getconfirmpasswordError());
		assertEquals(expectedRoleError, usersErrorMsgs.getroleError());
		assertEquals(expectedPermitError, usersErrorMsgs.getpermitTypeError());
		assertEquals(getisRevokedValue(isRevoked), users.getisRevoked());
		assertEquals(userId, users.getUserID());

		users.validateLogin(action, null, usersLoginErrorMsgs);

		assertEquals(expectedLoginErrorMsg, usersLoginErrorMsgs.getErrorMsg());
		assertEquals(expectedLoginUserNameError, usersLoginErrorMsgs.getusernameError());
		assertEquals(expectedLoginPasswordError, usersLoginErrorMsgs.getpasswordError());
	}

}
