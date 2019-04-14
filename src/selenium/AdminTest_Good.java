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

import data.UsersDAO;
import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import test.Data.TestDAO;

@RunWith(JUnitParamsRunner.class)
public class AdminTest_Good extends BusinessFunctions {
	private boolean acceptNextAlert = true;
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		appUrl = prop.getProperty("AppUrl");
		sharedUIMapPath = prop.getProperty("SharedUIMapPath");
		prop.load(new FileInputStream(sharedUIMapPath));

		if (TestDAO.userExists("User7")) {
			TestDAO.deleteUser("User7");
		}
	}

	@Test
	@FileParameters("src/Excel/AdminGoodTest.csv")
	public void testAdminTestGood(String userName, String password, String confirmPassword, String role,
			String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum, String utaId, String userToRevoke) throws Exception {
		driver.get(appUrl);
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		
	  	if(!UsersDAO.Usernameunique(userName)){
			driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
	  		functions.Register(driver, userName, password, confirmPassword, role, permitType);
	  		assertEquals("Username is already in database", driver.findElement(By.id("usernameError")).getAttribute("value"));
	  		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	  		functions.Login(driver, userName, password);
			functions.searchUserbyUserName(driver, userToRevoke);
			driver.manage().window().setSize(new Dimension(1936, 1056));
			driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
			functions.revokeUser(driver, userToRevoke);
			driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	  	}
	  	else
	  	{
			driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
			functions.Register(driver, userName, password, confirmPassword, role, permitType);
			functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email, phoneNum,
					dlNum, dayOfExpiry, regNum, utaId);
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
					.equals("Registered Successfully."));		
		  	functions.Login(driver, userName, password);
			functions.searchUserbyUserName(driver, userToRevoke);
			driver.manage().window().setSize(new Dimension(1936, 1056));
			driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
			functions.revokeUser(driver, userToRevoke);
			driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	  	}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
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