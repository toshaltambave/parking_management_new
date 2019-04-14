package test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import test.Data.TestDAO;

@RunWith(JUnitParamsRunner.class)
public class AdminTest_Fail extends BusinessFunctions {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String sharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
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
	@FileParameters("src/test/AdminRegisterFailures.csv")
	public void testAdminTestFail(String userName, String password, String confirmPassword, String role,
			String permitType, String exceptedErrorMsg, String expectedUsernameError, String expectedPasswordError,
			String expectedConfirmPaswordError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if ("Username is already in database".equals(expectedUsernameError) && !TestDAO.userExists("User7")) {
				registerUser("User7");
		}
		
		if ("None".equals(userName)) {
			// Nothing entered - all errors present
			driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
		} else {
			// UserName all ready in DataBase
			functions.Register(driver, userName, password, confirmPassword, role, permitType);
			if ("Username is already in database".equals(expectedUsernameError)) {
				TestDAO.deleteUser("User7");
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
	@FileParameters("src/test/AdminRegisterUserDetailsFailures.csv")
	public void testAdminTestUserDetailFails(String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phoneNum, String dlNum, String expiryDate, String regNum,
			String utaId, String expectedErrorMsg, String expectedFirstNameError, String expectedMiddleNameError,
			String expectedLastNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneNumError, String expectedDlNumError,
			String expectedDlExpiryError, String RegNumError, String utaIdError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if (TestDAO.userExists("User7")) {
			TestDAO.deleteUser("User7");
		}
		
		functions.Register(driver, "User7", "User7", "User7", "Admin", "Basic");

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
	@FileParameters("src/test/AdminRegisterLoginFailures.csv")
	public void AdminLoginFail(String userName, String password, String expectedErrorMsg, String expectedUserNameError, String expectedPasswordError){
		
		if (TestDAO.userExists("User7")) {
			TestDAO.deleteUser("User7");
		}
		
		functions.Register(driver, "User7", "User7", "User7", "Admin", "Basic");
		functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
		
		if("None".equals(userName)){
			functions.Login(driver, "", "");
		}else{
			functions.Login(driver, userName, password);
		}
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_CommonError"))).getAttribute("value").equals(expectedErrorMsg));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_UsernameError"))).getAttribute("value").equals(expectedUserNameError));
		 assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Login_PasswordError"))).getAttribute("value").equals(expectedPasswordError));
		
	}
	

	private void registerUser(String userName) {
		driver.get(appUrl);
		functions.Register(driver, userName, userName, userName, "Admin", "Basic");
		functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514",
				"14412552", "30", "12332147", "1000212003");
		functions.Login(driver, "User7", "User7");
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		TestDAO.deleteUser("User7");
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}