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
import model.Users;
import test.Data.TestDAO;
import util.PasswordUtility;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingManagerEditArea extends BusinessFunctions {
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
	  @FileParameters("tests/Excel/ParkingManagerEditArea.csv")
	  public void dParkingUserReservation(String userName, String password, String confirmPassword, String role,
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
		TestDAO.deleteSpot(AreaName);
		functions.addParkingArea(driver, AreaName, respermitType, floorNumber, SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_EditParkingArea"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("DropDwn_Parking_Area")))).selectByVisibleText(AreaName);
	    driver.findElement(By.id(prop.getProperty("Btn_Selected_Editname_Area"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_New_AreaName"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_New_AreaName"))).sendKeys("West GarageTest");
	    driver.findElement(By.id(prop.getProperty("Btn_Save_New_AreaName"))).click();
	    try {
	      assertEquals("Parking Area name updated.", driver.findElement(By.id(prop.getProperty("Txt_NameUpdate_Success"))).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_EditParkingArea"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("DropDwn_Parking_Area")))).selectByVisibleText("West GarageTest");
	    driver.findElement(By.id(prop.getProperty("Btn_Selected_Editname_Area"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Search_Spot_Floor")+floorNumber+respermitType)).click();
	    driver.findElement(By.id(prop.getProperty("Btn_AddNew_Spot"))).click();
	    try {
	      assertEquals("Spot added successfully.", driver.findElement(By.id(prop.getProperty("Txt_Spot_Added_Success"))).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.xpath("(//input[@id='btnBlockUnblock'])["+spotId+"]")).click();
	    try {
	      assertEquals("spot (un)blocked successfully.", driver.findElement(By.id(prop.getProperty("Txt_Spot_Blocked_Success"))).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    Integer spotAssert = Integer.parseInt(spotId)+1;
	    try {
	      assertEquals("true", driver.findElement(By.xpath("//div[@id='body']/div/div/table/tbody/tr["+spotAssert+"]/td[2]")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
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