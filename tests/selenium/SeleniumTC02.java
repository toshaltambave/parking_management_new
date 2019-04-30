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

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import test.Data.TestDAO;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.ReservationsHelper;
import model.Role;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC02 extends BusinessFunctions {
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
		driver.manage().window().setSize(new Dimension(1440,850));
	}

	/**
	 * This Test all combinations of validation errors a user can see in the
	 * login and registration screens.
	 * 
	 * @throws Exception
	 */
	@Test
	@FileParameters("tests/Excel/RegisterFailures.csv")
	public void aParkingManagerRegistration(String userName, String password, String confirmPassword, String role,
			String permitType, String exceptedErrorMsg, String expectedUsernameError, String expectedPasswordError,
			String expectedConfirmPaswordError) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		TestDAO.deleteUser(userName);
//		if ("Username is already in database".equals(expectedUsernameError) && !TestDAO.userExists(userName)) {
//			registerUser(userName, correctPassword,role,permitType,firstName,middleName,lastName,
//					sex,dob,address,email,phoneNum,dlno,dlexpiry,regNum,utaId);
//		}
		
		if ("None".equals(userName)) {
			// Nothing entered - all errors present
			driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
		} else {
			// UserName all ready in DataBase
			functions.Register(driver, userName, password, confirmPassword, Role.ParkingManager.toString(), permitType);
			if ("Username is already in database".equals(expectedUsernameError)) {
				TestDAO.deleteUser(userName);
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
	@FileParameters("tests/Excel/UserDetailsFailures.csv")
	public void bParkingManagerUserDetails(String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phoneNum, String dlNum, String expiryDate, String regNum,
			String utaId, String expectedErrorMsg, String expectedFirstNameError, String expectedMiddleNameError,
			String expectedLastNameError, String expectedDobError, String expectedAddressError,
			String expectedEmailError, String expectedPhoneNumError, String expectedDlNumError,
			String expectedDlExpiryError, String RegNumError, String utaIdError, String userName, String password, String confirmPassword, String role,
			String permitType) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteUser(userName);
		}
		
		functions.Register(driver, userName, password, confirmPassword, Role.ParkingManager.toString(), permitType);


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
	@FileParameters("tests/Excel/RegisterLoginFailures.csv")
	public void cParkingManagerLogin(String userName, String password, String expectedErrorMsg, String expectedUserNameError, String expectedPasswordError,String reguserName, String regpassword, String confirmPassword, String role,
			String permitType,String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phoneNum, String dlNum, String expiryDate, String regNum,
			String utaId){
		
		if (TestDAO.userExists(reguserName)) {
			TestDAO.deleteUser(reguserName);
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		
		functions.Register(driver, reguserName, regpassword, confirmPassword, Role.ParkingManager.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dob, address, email, phoneNum, dlNum, expiryDate, regNum, utaId);
		
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
	@FileParameters("tests/Excel/TC04Good.csv")
	public void dParkingManagerUpdateProfile(String userNameToUpdate, String userName, String updateUserName, String password,
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
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Btn_PM_EditProfile"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Btn_Update_User"))).click();
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_UnRevoke"))))
				.selectByVisibleText("UserName");
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	@FileParameters("tests/Excel/GoodTest.csv")
	public void eParkingManagerHappy(String userName, String password, String confirmPassword, String role,
			String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
			String utaId, String userToSearch) throws Exception {
		driver.get(appUrl);
//		driver.manage().window().setSize(new Dimension(1936, 1056));
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		if (TestDAO.userExists(userName)) {
			TestDAO.deleteReservation(userName);
			TestDAO.deleteUser(userName);
		}
		functions.Register(driver, userName, password, confirmPassword, Role.ParkingManager.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		functions.Login(driver, userName, password);
		functions.searchUserbyUserName(driver, userToSearch);
		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
//		functions.setNoShow(driver, userName);
//		functions.setNoShow(driver, userName);
//		functions.setOverdue(driver, userName);
//		functions.setOverdue(driver, userName);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}
	
	@Test
	@FileParameters("tests/Excel/Violations.csv")
	public void fParkingManagerViolations(String userName, String password, String confirmPassword, String role,
			String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
			String utaId, String parkinguser,String parkinguserpassword) throws Exception {
		driver.get(appUrl);
//		driver.manage().window().setSize(new Dimension(1936, 1056));
		assertTrue(!isElementPresent(driver, "Txt_Register_Success"));
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		TestDAO.deleteReservation(userName);
		TestDAO.deleteUser(userName);
	
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
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
		functions.viewUserViolations(driver, parkinguser);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	}
	
	@Test
	  @FileParameters("tests/Excel/ParkingManagerEditArea.csv")
	  public void gParkingManagerAddEditArea(String userName, String password, String confirmPassword, String role,
				String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
				String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
				String utaId, String AreaName, String respermitType, String floorNumber, String SpotsNo, String spotId, String newName) throws Exception 
	  {
		driver.get(appUrl);
		//Register
		TestDAO.deleteUser(userName);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		//Login
		functions.Login(driver, userName, password);
		//Add Here
		//TestDAO.deleteSpot(AreaName);
		int val = TestDAO.getAreaId();
		AreaName = AreaName+val;
		newName = newName + val;
		functions.addParkingArea(driver, AreaName, respermitType, floorNumber, SpotsNo);
	    functions.editParkingArea(driver,AreaName, respermitType, floorNumber, spotId, newName);
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();		
	  }
	
	  @Test
	  @FileParameters("tests/Excel/ParkingManagerBlockUnblock.csv")
	  public void hParkingManagerBlockUnblock(String userName, String password, String confirmPassword, String role,
				String permitType, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
				String address, String email, String phoneNum, String dlNum, String dayOfExpiry, String regNum,
				String utaId, String AreaName, String respermitType, String floorNumber, String SpotsNo, String spotId, String newName) throws Exception 
	  {
		driver.get(appUrl);
		//Register
		TestDAO.deleteUser(userName);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		//Login
		functions.Login(driver, userName, password);
		//Add Here
		//TestDAO.deleteSpot(AreaName);
		int val = TestDAO.getAreaId();
		AreaName = AreaName+val;
		newName = newName + val;
		functions.addParkingArea(driver, AreaName, respermitType, floorNumber, SpotsNo);	    
		functions.viewParkingSpots(driver, AreaName, respermitType, floorNumber);
	    functions.blockunblock(driver, AreaName, respermitType,floorNumber,spotId);
	    functions.blockunblock(driver, AreaName, respermitType,floorNumber,spotId);
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		
	  }
	  
	  @Test
	  @FileParameters("tests/Excel/ParkingUserMaxReservations.csv")
	  public void iParkingManagerEditCancelReservation(String userName, String password, String confirmPassword, String role,
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
		String pmUserName = userName + firstName;
		
		//Register
		TestDAO.deleteReservation(pmUserName);
		TestDAO.deleteUser(pmUserName);
		TestDAO.deleteReservation(userName);
		TestDAO.deleteUser(userName);
		
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName, password, confirmPassword, role, permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		//Login
		functions.Login(driver, userName, password);
		
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
	    functions.reservationModifyTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		
		functions.modifyreservation(driver,1);
	    functions.reservationModifyTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, false, false, false, cardType);
//		driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		//Delete Reservation
		functions.deletereservation(driver, 2);
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Del_msg"))).getText().equals("Deleted Successfully."));
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();		
				
	  }
	  
	  @Test
	  @FileParameters("tests/Excel/ParkingUserMaxReservations.csv")
	  public void jParkingManager3NoShow(String userName, String password, String confirmPassword, String role,
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
		//Register
		if (TestDAO.userExists(userName)) {
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
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
	    //Reservation 2 previous Spot + 1
	    functions.Login(driver, userName, password);
	    functions.reservationTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum+1);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		//Reservation 3 previous Spot + 2
	    functions.Login(driver, userName, password);
	    functions.reservationTimeAndDate(driver, startdate, enddate, area);
		functions.reservationFloorAndSpot(driver, reservationPermitType, floorNum, spotNum+2);
		functions.makeReservation(driver, ccNum, expMon, expYear, cvv, cart, camera, history, cardType);
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Reserve_Sucess"))).getText()
						.equals("Reservation has been made successfully."));
		driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		//Register Parking Manager
		if (TestDAO.userExists(userName+"man")) {
			TestDAO.deleteReservation(userName+"man");
			TestDAO.deleteUser(userName+"man");
		}
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		functions.Register(driver, userName+"man", password, confirmPassword, Role.ParkingManager.toString(), permitType);
		functions.RegisterUserDetails(driver, firstName, middleName, lastName, sex, dayOfBirth, address, email,
				phoneNum, dlNum, dayOfExpiry, regNum, utaId);
		//Assert user registered
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Register_Success"))).getText()
				.equals("Registered Successfully."));
		//Login
		functions.Login(driver, userName+"man", password);
		driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_NoShow"))).click();
		ArrayList<ReservationsHelper> res = new ArrayList<ReservationsHelper> ();
		res = TestDAO.GetReservationsByUsername(userName);
	    for (int i = 0; i < res.size(); i++) {
	        functions.setNoShowById(driver, userName, res.get(i).getReservationID().toString());
	      }
		//Check Revoked
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_User_Logout"))).click();
		functions.Login(driver, userName, password);
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Reserve"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Revoked"))).getText()
				.contains("Your Account has been revoked please contact manager, reason:"));
		
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