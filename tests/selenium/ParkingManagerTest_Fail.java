package selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import test.Data.TestDAO;

@RunWith(JUnitParamsRunner.class)
public class ParkingManagerTest_Fail extends BusinessFunctions {
	private WebDriver driver;
//	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String sharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		//FireFox Driver
		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		   driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
//		driver = new ChromeDriver();
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
		driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
		appUrl = prop.getProperty("AppUrl");
		sharedUIMapPath = prop.getProperty("SharedUIMapPath");
		prop.load(new FileInputStream(sharedUIMapPath));

		driver.get(appUrl);
	}

	/**
	 * This Test all combinations of validation errors a user can see in the
	 * login and registration screens.
	 * 
	 * @throws Exception
	 */
	@Test
	@FileParameters("tests/Excel/ParkingManagerRegisterFailures.csv")
	public void testParkingManagerTestFail(String userName, String password, String confirmPassword, String role,
			String permitType, String exceptedErrorMsg, String expectedUsernameError, String expectedPasswordError,
			String expectedConfirmPaswordError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if ("Username is already in database".equals(expectedUsernameError) && !TestDAO.userExists("PMUser1")) {
				registerUser("PMUser1","Admin12");
		}
		
		if ("None".equals(userName)) {
			// Nothing entered - all errors present
			driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
		} else {
			// UserName all ready in DataBase
			functions.Register(driver, userName, password, confirmPassword, role, permitType);
			if ("Username is already in database".equals(expectedUsernameError)) {
				TestDAO.deleteUser("PMUser1");
			}
		}
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_CommonError"))).getAttribute("value")
				.equals(exceptedErrorMsg));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_UsernameError"))).getAttribute("value")
				.equals(expectedUsernameError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_PasswordError"))).getAttribute("value")
				.equals(expectedPasswordError));
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_ConfirmPasswordError")))
				.getAttribute("value").equals(expectedConfirmPaswordError));

	}

	@Test
	@FileParameters("tests/Excel/ParkingManagerRegisterUserDetailsFailures.csv")
	public void testParkingManagerTestUserDetailFails(String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phoneNum, String dlNum, String expiryDate, String regNum,
			String utaId, String expectedErrorMsg, String expectedFirstNameError, String expectedMiddleNameError,
			String expectedLastNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneNumError, String expectedDlNumError,
			String expectedDlExpiryError, String RegNumError, String utaIdError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if (TestDAO.userExists("PMUser1")) {
			TestDAO.deleteUser("PMUser1");
		}
		
		functions.Register(driver, "PMUser1", "Admin12", "Admin12", "ParkingManager", "Basic");


		if("None".equals(firstName)){
			 //Nothing Entered all errors present
			 driver.findElement(By.id(prop.getProperty("Btn_UserDetails_Submit"))).click();
		}else{
			 functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum, dlNum, expiryDate, regNum, utaId);
		}
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_CommonError"))).getAttribute("value").equals(expectedErrorMsg));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_FirstnameError"))).getAttribute("value").equals(expectedFirstNameError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_MiddlenameError"))).getAttribute("value").equals(expectedMiddleNameError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_LastnameError"))).getAttribute("value").equals(expectedLastNameError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOBError"))).getAttribute("value").equals(expectedDobError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_AddressError"))).getAttribute("value").equals(expectedAddressError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_EmailError"))).getAttribute("value").equals(expectedEmailError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_PhoneError"))).getAttribute("value").equals(expectedPhoneNumError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNOError"))).getAttribute("value").equals(expectedDlNumError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDteError"))).getAttribute("value").equals(expectedDlExpiryError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNOError"))).getAttribute("value").equals(RegNumError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAIDError"))).getAttribute("value").equals(utaIdError));

	}
	
	@Test
	@FileParameters("tests/Excel/ParkingManagerRegisterLoginFailures.csv")
	public void ParkingManagerLoginFail(String userName, String password, String expectedErrorMsg, String expectedUserNameError, String expectedPasswordError){
		
		if (TestDAO.userExists("PMUser1")) {
			TestDAO.deleteUser("PMUser1");
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		functions.Register(driver, "PMUser1", "Admin12", "Admin12", "ParkingManager", "Basic");
		functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
		
		if("None".equals(userName)){
			functions.Login(driver, "", "");
		}else{
			functions.Login(driver, userName, password);
		}
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_CommonError"))).getAttribute("value").equals(expectedErrorMsg));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_UsernameError"))).getAttribute("value").equals(expectedUserNameError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_UsernameError"))).getAttribute("value").equals(expectedPasswordError));
		
	}
	
	@Test
	@FileParameters("tests/Excel/ParkingManagerGoodTest.csv")
	public void testParkingManagerLoginHappy(String userName, String password, String confirmPassword, String role,
			String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
			String utaId, String userToSearch) throws Exception {
		driver.get(appUrl);
//		driver.manage().window().setSize(new Dimension(1936, 1056));
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		functions.searchUserbyUserName(driver, userToSearch);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		functions.setNoShow(driver, userName);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}
	
	

	private void registerUser(String userName,String password) {
		driver.get(appUrl);
		functions.Register(driver, userName, password, password, "ParkingManager", "Basic");
		functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514",
				"14412552", "30", "12332147", "1000212003");
		functions.Login(driver, "PMUser1", "Admin12");
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		TestDAO.deleteUser("PMUser1");
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

//	private boolean isElementPresent(By by) {
//		try {
//			driver.findElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			return false;
//		}
//	}

//	private boolean isAlertPresent() {
//		try {
//			driver.switchTo().alert();
//			return true;
//		} catch (NoAlertPresentException e) {
//			return false;
//		}
//	}
//
//	private String closeAlertAndGetItsText() {
//		try {
//			Alert alert = driver.switchTo().alert();
//			String alertText = alert.getText();
//			if (acceptNextAlert) {
//				alert.accept();
//			} else {
//				alert.dismiss();
//			}
//			return alertText;
//		} finally {
//			acceptNextAlert = true;
//		}
//	}
}