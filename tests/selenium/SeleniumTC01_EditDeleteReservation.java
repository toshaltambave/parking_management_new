package selenium;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import model.Role;
import model.Users;
import test.Data.TestDAO;
import util.PasswordUtility;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC01_EditDeleteReservation extends BusinessFunctions {
	private WebDriver driver;
//	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String sharedUIMapPath;
	private UsersDAO UsersDAO;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		//FireFox Driver
//		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
//		   driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		prop = new Properties();
//		prop.load(new FileInputStream("./Configuration/login.properties"));
		
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
	  @FileParameters("tests/Excel/ParkingUserMaxReservations.csv")
	  public void dParkingUserEditCancelReservation(String userName, String password, String confirmPassword, String role,
				String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
				String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
				String utaId, String startdate, String enddate, String area, String reservationPermitType, Integer floorNum,
				Integer spotNum, String ccNum, String expMon, String expYear, String cvv, String cardType, Boolean cart, Boolean camera, Boolean history,
				String startTimeError, String endTimeError, String compareError, 
				String cardNumError, String cardYearError, String cardMonthError, String cardCvvError) throws Exception 
	  {
		driver.get(appUrl);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		startdate = dateFormat.format(date) +" "+startdate;
		enddate = dateFormat.format(date) +" "+enddate;
		String pmUserName = userName +1;
		//Register
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(pmUserName);
			TestDAO.deleteReservation(userName);
			TestDAO.deleteUser(userName);
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		//Login
		functions.Login(driver, userName, password);
		//Reservation 1
		functions.reservationTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		//Modify Reservation
		functions.modifyreservation(driver,1);
		functions.editreservationTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		//Delete Reservation
		functions.deletereservation(driver, 1);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Del_msg"))).getText().equals("Deleted Successfully."));
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		
	    //Again Reserve for PM
	    functions.reservationTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		
		
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, pmUserName, password, confirmPassword, Role.ParkingManager.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
	    
		functions.Login(driver, pmUserName, password);
	    
		//Modify Reservation
		functions.modifyreservation(driver,1);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		//Delete Reservation
		functions.deletereservation(driver, 2);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Del_msg"))).getText().equals("Deleted Successfully."));
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