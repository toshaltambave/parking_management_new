package selenium;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import controller.ReservationsController;
import data.UsersDAO;
import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.CreditCardError;
import model.ReservationsHelper;
import model.Role;
import model.Users;
import test.Data.TestDAO;
import util.PasswordUtility;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingUserViolations extends BusinessFunctions {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String sharedUIMapPath;
	private UsersDAO UsersDAO;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		//FireFox Driver
		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		   driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop = new Properties();
		
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		appUrl = prop.getProperty("AppUrl");
		int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
		driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
		sharedUIMapPath = prop.getProperty("SharedUIMapPath");
		prop.load(new FileInputStream(sharedUIMapPath));
	    UsersDAO = new UsersDAO();
		driver.get(appUrl);
		driver.manage().window().setSize(new Dimension(1440,850));
	}
	
	@Test
	@FileParameters("tests/Excel/Violations.csv")
	public void violations(String userName, String password, String confirmPassword, String role,
			String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
			String utaId, String parkinguser,String parkinguserpassword) throws Exception {
		driver.get(appUrl);
//		driver.manage().window().setSize(new Dimension(1936, 1056));
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteReservation(userName);
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_NoShow"))).click();
		ArrayList<ReservationsHelper> res = new ArrayList<ReservationsHelper> ();
		res = TestDAO.GetReservationsByUsername(parkinguser);
	    functions.setNoShowById(driver, parkinguser, res.get(0).getReservationID().toString());
	    functions.setNoShowById(driver, parkinguser, res.get(0).getReservationID().toString());
	    functions.setNoShowById(driver, parkinguser, res.get(0).getReservationID().toString());
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
		functions.setOverdue(driver, parkinguser);
		functions.setOverdue(driver, parkinguser);
		functions.setOverdue(driver, parkinguser);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		functions.Login(driver, parkinguser, parkinguserpassword);
		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_viewres"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
		functions.viewUserViolations(driver, parkinguser);
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