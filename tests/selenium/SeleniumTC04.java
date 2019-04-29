package selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Role;
import test.Data.TestDAO;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC04 extends BusinessFunctions {
	private WebDriver driver;
	// private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String fakeUrl;
	private String sharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		// FireFox Driver
		// System.setProperty("webdriver.firefox.marionette",
		// "C:\\GeckoSelenium\\geckodriver.exe");
		// driver = new FirefoxDriver();
		//FireFox Driver
//		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
//		   driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
		driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
		appUrl = prop.getProperty("AppUrl");
		fakeUrl = prop.getProperty("FakeUrl");
		sharedUIMapPath = prop.getProperty("SharedUIMapPath");
		prop.load(new FileInputStream(sharedUIMapPath));

		driver.get(appUrl);
		driver.manage().window().setSize(new Dimension(1440, 850));
	}

	@Test
	@FileParameters("tests/Excel/TC04.csv")
	public void tc04(String userNameToUpdate, String userName, String updateUserName, String password,
			String updatePassword, String confirmPassword, String updateConfirmPassword, String role, String permitType,
			String updatePermitType, String firstName, String updateFirstName, String middleName,
			String updateMiddleName, String lastName, String updateLastName, String sex, String updateSex, String dob,
			String updateDob, String address, String updateAddress, String email, String updateEmail, String phoneNum,
			String updatePhoneNum, String dlNum, String updateDlNum, String dlExpiry, String updateDlExpiry,
			String regNum, String updateRegNum, String utaId, String updateUtaId, String expectedErrorMsg,
			String expectedFirstNameError, String expectedMiddleNameError, String expectedLastNameError,
			String expectedUserNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneNumError, String expectedDlNumError,
			String expectedDlExpiryError, String expectedRegNumError, String expectedUtaIdError,
			String expectConfirmPasswordError, String expectedPasswordError) throws Exception {
		driver.get(appUrl);
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
//		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, Role.Admin.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum,
				dlNum, dlExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		functions.UpdateUserProfile(driver, userNameToUpdate, updateFirstName, updateMiddleName, updateLastName,
				updateUserName, updateSex, updateDob, updateAddress, updateEmail, updatePhoneNum, updateDlNum,
				updateDlExpiry, updateRegNum, updateUtaId, updatePassword, updateConfirmPassword, updatePermitType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_CommonError"))).getAttribute("value")
				.equals(expectedErrorMsg));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_FirstnameError"))).getAttribute("value")
				.equals(expectedFirstNameError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_MiddlenameError"))).getAttribute("value")
				.equals(expectedMiddleNameError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_LastnameError"))).getAttribute("value")
				.equals(expectedLastNameError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_UserNameError")))
				.getAttribute("value").equals(expectedUserNameError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOBError"))).getAttribute("value")
				.equals(expectedDobError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_AddressError"))).getAttribute("value")
				.equals(expectedAddressError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_EmailError"))).getAttribute("value")
				.equals(expectedEmailError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_PhoneError"))).getAttribute("value")
				.equals(expectedPhoneNumError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNOError"))).getAttribute("value")
				.equals(expectedDlNumError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDteError"))).getAttribute("value")
				.equals(expectedDlExpiryError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNOError"))).getAttribute("value")
				.equals(expectedRegNumError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAIDError"))).getAttribute("value")
				.equals(expectedUtaIdError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_PasswordError")))
				.getAttribute("value").equals(expectedPasswordError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Confirm_PasswordError")))
				.getAttribute("value").equals(expectConfirmPasswordError));
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}

	@Test
	@FileParameters("tests/Excel/TC04Good.csv")
	public void tc04GoodAdmin(String userNameToUpdate, String userName, String updateUserName, String password,
			String updatePassword, String confirmPassword, String updateConfirmPassword, String role, String permitType,
			String updatePermitType, String firstName, String updateFirstName, String middleName,
			String updateMiddleName, String lastName, String updateLastName, String sex, String updateSex, String dob,
			String updateDob, String address, String updateAddress, String email, String updateEmail, String phoneNum,
			String updatePhoneNum, String dlNum, String updateDlNum, String dlExpiry, String updateDlExpiry,
			String regNum, String updateRegNum, String utaId, String updateUtaId) {
		driver.get(appUrl);
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, Role.Admin.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum,
				dlNum, dlExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		functions.UpdateUserProfile(driver, userNameToUpdate, updateFirstName, updateMiddleName, updateLastName,
				updateUserName, updateSex, updateDob, updateAddress, updateEmail, updatePhoneNum, updateDlNum,
				updateDlExpiry, updateRegNum, updateUtaId, updatePassword, updateConfirmPassword, updatePermitType);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();

		driver.get(fakeUrl);
		driver.findElement(By.id("faketest")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketestupdate")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketestclick")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest2")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest3")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest4")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest5")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest6")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest7")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest8")).click();
		driver.get(fakeUrl);
		driver.findElement(By.id("faketest9")).click();
	}
	
	@Test
	@FileParameters("tests/Excel/TC04Good.csv")
	public void tc04GoodParkingManager(String userNameToUpdate, String userName, String updateUserName, String password,
			String updatePassword, String confirmPassword, String updateConfirmPassword, String role, String permitType,
			String updatePermitType, String firstName, String updateFirstName, String middleName,
			String updateMiddleName, String lastName, String updateLastName, String sex, String updateSex, String dob,
			String updateDob, String address, String updateAddress, String email, String updateEmail, String phoneNum,
			String updatePhoneNum, String dlNum, String updateDlNum, String dlExpiry, String updateDlExpiry,
			String regNum, String updateRegNum, String utaId, String updateUtaId) {
		driver.get(appUrl);
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, Role.ParkingManager.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum,
				dlNum, dlExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		driver.findElement(By.id(prop.getProperty("Btn_PM_EditProfile"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_Update_User"))).click();
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_UnRevoke"))))
				.selectByVisibleText("UserName");
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_UnRevoke"))))
				.selectByVisibleText(userNameToUpdate);
		driver.findElement(By.id(prop.getProperty("Btn_Update_User"))).click();
		functions.UpdateUserProfileUserManager(driver, userNameToUpdate, updateFirstName, updateMiddleName, updateLastName,
				updateUserName, updateSex, updateDob, updateAddress, updateEmail, updatePhoneNum, updateDlNum,
				updateDlExpiry, updateRegNum, updateUtaId, updatePassword, updateConfirmPassword, updatePermitType);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();

	}
	
	@Test
	@FileParameters("tests/Excel/TC04Good.csv")
	public void tc04GoodParkingUser(String userNameToUpdate, String userName, String updateUserName, String password,
			String updatePassword, String confirmPassword, String updateConfirmPassword, String role, String permitType,
			String updatePermitType, String firstName, String updateFirstName, String middleName,
			String updateMiddleName, String lastName, String updateLastName, String sex, String updateSex, String dob,
			String updateDob, String address, String updateAddress, String email, String updateEmail, String phoneNum,
			String updatePhoneNum, String dlNum, String updateDlNum, String dlExpiry, String updateDlExpiry,
			String regNum, String updateRegNum, String utaId, String updateUtaId) {
		driver.get(appUrl);
		userName = userName+1;
		updateUserName = updateUserName+2;
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		TestDAO.deleteUser(updateUserName);
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, Role.ParkingUser.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum,
				dlNum, dlExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_upd"))).click();
		functions.UpdateUserProfileUserManager(driver, userName, updateFirstName, updateMiddleName, updateLastName,
				updateUserName, updateSex, updateDob, updateAddress, updateEmail, updatePhoneNum, updateDlNum,
				updateDlExpiry, updateRegNum, updateUtaId, updatePassword, updateConfirmPassword, updatePermitType);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}
	
	@Test
	@FileParameters("tests/Excel/TC04Good.csv")
	public void tc04GoodParkingUserUnique(String userNameToUpdate, String userName, String updateUserName, String password,
			String updatePassword, String confirmPassword, String updateConfirmPassword, String role, String permitType,
			String updatePermitType, String firstName, String updateFirstName, String middleName,
			String updateMiddleName, String lastName, String updateLastName, String sex, String updateSex, String dob,
			String updateDob, String address, String updateAddress, String email, String updateEmail, String phoneNum,
			String updatePhoneNum, String dlNum, String updateDlNum, String dlExpiry, String updateDlExpiry,
			String regNum, String updateRegNum, String utaId, String updateUtaId) {
		driver.get(appUrl);
		userName = userName+1;
		updateUserName = updateUserName+2;
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, Role.ParkingUser.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum,
				dlNum, dlExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_upd"))).click();
		functions.UpdateUserProfileUserManager(driver, userName, updateFirstName, updateMiddleName, updateLastName,
				updateUserName, updateSex, updateDob, updateAddress, updateEmail, updatePhoneNum, updateDlNum,
				updateDlExpiry, updateRegNum, updateUtaId, updatePassword, updateConfirmPassword, updatePermitType);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}