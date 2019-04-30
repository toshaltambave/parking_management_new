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
	private String fakeUrl;
	private String appUrl;
	private String sharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		// FireFox Driver
		// System.setProperty("webdriver.firefox.marionette",
		// "C:\\GeckoSelenium\\geckodriver.exe");
		// driver = new FirefoxDriver();
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
		fakeUrl = prop.getProperty("FakeUrl");
		driver.manage().window().setSize(new Dimension(1440, 850));
	}

	@Test
	@FileParameters("tests/Excel/TC04.csv")
	public void aUpdateProfileAllValidations(String userNameToUpdate, String userName, String updateUserName, String password,
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
	@FileParameters("tests/Excel/TCedgecase.csv")
	public void bTestEdgeCases(String one,String two, String three, String four, String five, String six, String seven,
			String eight, String nine, String ten, String eleven, String twelve, String thirteen) {
		driver.get(appUrl);
		driver.get(fakeUrl);
		testEdgeCase(driver,one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen,fakeUrl);
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