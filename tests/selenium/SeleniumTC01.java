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
import model.CreditCard;
import model.CreditCardError;
import model.CreditCardTypes;
import model.Reservation;
import model.ReservationError;
import model.Users;
import test.Data.TestDAO;
import util.PasswordUtility;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC01 extends BusinessFunctions {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private BusinessFunctions functions = new BusinessFunctions();

	private String appUrl;
	private String sharedUIMapPath;
	private UsersDAO UsersDAO;
	ReservationsController rc;
	CreditCard cc;
	CreditCardError cardError;
	Reservation reservation;
	ReservationError resError;
	
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
		rc = new ReservationsController();
		cc = new CreditCard();
		cardError = new CreditCardError();
		reservation = new Reservation();
		resError  = new ReservationError();
	}


	/**
	 * This Test all combinations of validation errors a user can see in the
	 * login and registration screens.
	 * 
	 * @throws Exception
	 */
	@Test
	@FileParameters("tests/Excel/ParkingUserRegisterFailures.csv")
	public void aParkingUserRegistration(String userName, String password, String confirmPassword, String role,
			String permitType, String exceptedErrorMsg, String expectedUsernameError, String expectedPasswordError,
			String expectedConfirmPaswordError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if ("Username is already in database".equals(expectedUsernameError) && !TestDAO.userExists("PUUser1")) {
				registerUser("PUUser1","Admin12");
		}
		
		if ("None".equals(userName)) {
			// Nothing entered - all errors present
			driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
		} else {
			// UserName all ready in DataBase
			functions.Register(driver, userName, password, confirmPassword, role, permitType);
			if ("Username is already in database".equals(expectedUsernameError)) {
				TestDAO.deleteUser("PUUser1");
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
	@FileParameters("tests/Excel/ParkingUserRegisterUserDetailsFailures.csv")
	public void bParkingUserDetails(String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phoneNum, String dlNum, String expiryDate, String regNum,
			String utaId, String expectedErrorMsg, String expectedFirstNameError, String expectedMiddleNameError,
			String expectedLastNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneNumError, String expectedDlNumError,
			String expectedDlExpiryError, String RegNumError, String utaIdError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if (TestDAO.userExists("PUUser1")) {
			TestDAO.deleteUser("PUUser1");
		}
		
		functions.Register(driver, "PUUser1", "Admin12", "Admin12", "ParkingUser", "Basic");


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
	@FileParameters("tests/Excel/ParkingUserRegisterLoginFailures.csv")
	public void cParkingUserLogin(String userName, String password, String expectedErrorMsg, String expectedUserNameError, String expectedPasswordError){
		
		if (TestDAO.userExists("PUUser1")) {
			TestDAO.deleteUser("PUUser1");
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		functions.Register(driver, "PUUser1", "Admin12", "Admin12", "ParkingUser", "Basic");
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
	  @FileParameters("tests/Excel/ParkingUserReservation.csv")
	  public void dParkingUserReservation(String userName, String password, String confirmPassword, String role,
				String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
				String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
				String utaId, String startdate, String enddate, String area, String reservationPermitType, Integer floorNum,
				Integer spotNum, String ccNum, String expMon, String expYear, String cvv, String cardType, Boolean cart, Boolean camera, Boolean history,
				String startTimeError, String endTimeError, String compareError, 
				String cardNumError, String cardYearError, String cardMonthError, String cardCvvError) throws Exception {
		driver.get(appUrl);
		cc.setCardNumber(ccNum);
		cc.setCardType(cardType);
		cc.setCvv(cvv);
		cc.setMonth(expMon);
		cc.setYear(expYear);
		
	  	assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		startdate = dateFormat.format(date) +" "+startdate;
		enddate = dateFormat.format(date) +" "+enddate;
		
		functions.reservationTimeAndDate(driver, startdate, enddate, area);
	    reservation.validateDateTime(startdate, enddate, resError);
	    
	    if(resError.getErrorMsg().equals("There are time errors.")){
	    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Start_Time"))).getAttribute("value").equals(startTimeError));
	    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_End_Time"))).getAttribute("value").equals(endTimeError));
	    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Compare"))).getAttribute("value").equals(compareError));
	    }
	    else{
	    	functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
			functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
			
			if(cart || camera || history){
				cc.validatecreditcarddetails(cc, cardError);
		    }
		    if(cardError.getErrorMsg().equals("Please correct the following errors.")){
		    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Card_Num"))).getAttribute("value").equals(cardNumError));
		    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Card_Month"))).getAttribute("value").equals(cardMonthError));
		    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Card_Year"))).getAttribute("value").equals(cardYearError));
		    	assertTrue(driver.findElement(By.id(prop.getProperty("Err_Card_Cvv"))).getAttribute("value").equals(cardCvvError));
		    }
		    else{
		    	TestDAO.deleteReservation(userName);
			    TestDAO.deleteUser(userName);
			    driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		    }
	    }
	  }
	
	  @Test
	  @FileParameters("tests/Excel/ParkingUserMaxReservations.csv")
	  public void eParkingUserHappy(String userName, String password, String confirmPassword, String role,
				String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
				String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
				String utaId, String startdate, String enddate, String area, String reservationPermitType, Integer floorNum,
				Integer spotNum, String ccNum, String expMon, String expYear, String cvv, String cardType, Boolean cart, Boolean camera, Boolean history,
				String startTimeError, String endTimeError, String compareError, 
				String cardNumError, String cardYearError, String cardMonthError, String cardCvvError) throws Exception {
			driver.get(appUrl);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			startdate = dateFormat.format(date) +" "+startdate;
			enddate = dateFormat.format(date) +" "+enddate;
			//Register
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
			driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		    //Reservation 2 previous Spot + 1
		    functions.Login(driver, userName, password);
		    functions.reservationTimeAndDate(driver, startdate, enddate, area);
			functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum+1);
			functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
			assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
							.equals("Reservation has been made successfully."));
			driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	  }
	
		private void registerUser(String userName,String password) {
			driver.get(appUrl);
			functions.Register(driver, userName, password, password, "ParkingUser", "Basic");
			functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514",
					"14412552", "30", "12332147", "1000212003");
			functions.Login(driver, "PUUser1", "Admin12");
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