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
import model.PermitType;
import model.Role;
import model.Users;
import test.Data.TestDAO;
import util.PasswordUtility;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingUserReservations extends BusinessFunctions {
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
//		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
//		   driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
	@FileParameters("tests/Excel/ParkingUserReservations.csv")
	public void alluserparkingcombination(String parkingmanageruserName,String basicparkinguseruserName,
			String premiumparkinguseruserName, String midrangeparkinguseruserName,String accessparkinguseruserName,
			String password, String confirmPassword, String parkingmangerrole,String parkinguserrole,
			String basicpermitType,String premiumpermitType,String midrangepermitType,String accesspermitType,
			String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
			String utaId,String AreaName,String floorNumber,String SpotsNo,String startdate, String enddate, 
			Integer floorNum,Integer spotNum, String ccNum, String expMon, String expYear,
			String cvv, String cardType, Boolean cart, Boolean camera, Boolean history) 
			throws Exception {
		driver.get(appUrl);
		TestDAO.deleteReservation(basicparkinguseruserName);
		TestDAO.deleteUser(basicparkinguseruserName);
		TestDAO.deleteReservation(premiumparkinguseruserName);
		TestDAO.deleteUser(premiumparkinguseruserName);
		TestDAO.deleteReservation(midrangeparkinguseruserName);
		TestDAO.deleteUser(midrangeparkinguseruserName);
		TestDAO.deleteReservation(accessparkinguseruserName);
		TestDAO.deleteUser(accessparkinguseruserName);
		TestDAO.deleteUser(parkingmanageruserName);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		startdate = dateFormat.format(date) +" "+startdate;
		enddate = dateFormat.format(date) +" "+enddate;
		int val = TestDAO.getAreaId();
		AreaName = AreaName+val;
//		driver.manage().window().setSize(new Dimension(1936, 1056));
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, parkingmanageruserName, password, confirmPassword, parkingmangerrole, premiumpermitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, basicparkinguseruserName, password, confirmPassword, parkinguserrole, basicpermitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, premiumparkinguseruserName, password, confirmPassword, parkinguserrole, premiumpermitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, midrangeparkinguseruserName, password, confirmPassword, parkinguserrole, midrangepermitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, accessparkinguseruserName, password, confirmPassword, parkinguserrole, accesspermitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
				
		functions.Login(driver, parkingmanageruserName, password);
		functions.addParkingAreaList(driver, AreaName, basicpermitType, floorNumber, SpotsNo);
		functions.addParkingAreaList(driver, AreaName, premiumpermitType, floorNumber, SpotsNo);
		functions.addParkingAreaList(driver, AreaName, midrangepermitType, floorNumber, SpotsNo);
		functions.addParkingAreaList(driver, AreaName, accesspermitType, floorNumber, SpotsNo);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
				
		functions.Login(driver, basicparkinguseruserName, password);
		functions.reservationTimeAndDate(driver, startdate, enddate, AreaName);
		functions.reservationFloorAndSpot(driver, basicpermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		functions.viewUserParkingSpots(driver, AreaName, basicpermitType, floorNum.toString());

	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		
		functions.Login(driver, premiumparkinguseruserName, password);
		functions.reservationTimeAndDate(driver, startdate, enddate, AreaName);
		functions.reservationFloorAndSpot(driver, premiumpermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		functions.viewUserParkingSpots(driver, AreaName, premiumpermitType, floorNum.toString());
		
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		
		functions.Login(driver, midrangeparkinguseruserName, password);
		functions.reservationTimeAndDate(driver, startdate, enddate, AreaName);
		functions.reservationFloorAndSpot(driver, midrangepermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		functions.viewUserParkingSpots(driver, AreaName, midrangepermitType, floorNum.toString());
		
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		
		functions.Login(driver, accessparkinguseruserName, password);
		functions.reservationTimeAndDate(driver, startdate, enddate, AreaName);
		functions.reservationFloorAndSpot(driver, accesspermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		functions.viewUserParkingSpots(driver, AreaName, accesspermitType, floorNum.toString());
		
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