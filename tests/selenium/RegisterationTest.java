package selenium;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import model.UserDetails;
import model.UserDetailsErrorMsgs;
import model.Users;
import model.UsersErrorMsgs;

@RunWith(JUnitParamsRunner.class)
public class RegisterationTest extends BusinessFunctions {
	  private WebDriver driver;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private BusinessFunctions functions = new BusinessFunctions();
	  
	  private String appUrl;
	  private String sharedUIMapPath;

  @Before
  public void setUp() throws Exception {
		//Change to FireFoxDriver if using FireFox browser
//		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
//	    driver = new ChromeDriver();
		  //FireFox Driver
	    System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
	    driver = new FirefoxDriver();
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
		driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
	    appUrl = prop.getProperty("AppUrl");
	    sharedUIMapPath = prop.getProperty("SharedUIMapPath");
	    prop.load(new FileInputStream(sharedUIMapPath));
  }
  
  @Test
  @FileParameters("tests/Excel/Registration.csv")
  public void testParkingUserError(String username, String password, String confirmpassword, String role, String permitType, 
		  String firstname, String middlename, String lastname, String sex, String dob, String address, String email, String phone, String dlno, String dateofexpiry, String regno, String UTAID) throws Exception { 
	driver.get(appUrl);
	Users user = new Users(new UsersDAO());
	user.setUser(username, password, confirmpassword, role, permitType, false);
	UsersErrorMsgs  errorMsgs = new UsersErrorMsgs();
	user.validateUser("saveUser", user, errorMsgs);
	driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
	functions.Register(driver, username, password, confirmpassword, role,permitType);    
		
	if (!errorMsgs.getErrorMsg().equals(""))
	{
    try {
      assertEquals(errorMsgs.getErrorMsg(), driver.findElement(By.id("errorMsg")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
    try {
    assertEquals(errorMsgs.getusernameError(), driver.findElement(By.id("usernameError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
       
    try {
      assertEquals(errorMsgs.getpasswordError(), driver.findElement(By.id("passwordError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(errorMsgs.getconfirmpasswordError(), driver.findElement(By.id("confirmPasswordError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
	}
	else
	{
		UserDetails userdetails = new UserDetails();
		UserDetailsErrorMsgs detailserrorMsgs = new UserDetailsErrorMsgs();
		userdetails.setUsername(username);
		userdetails.setUserDetails(firstname, middlename, lastname, sex, dob, address, email, phone, dlno, dateofexpiry, regno, UTAID);
		userdetails.validateUserDetails("saveUserDetails", userdetails, detailserrorMsgs);
		functions.RegisterUserDetails(driver, firstname, middlename,lastname, sex, dob, address, email, phone, dlno, dateofexpiry, regno, UTAID);

		if (!detailserrorMsgs.getErrorMsg().equals("")) 
		{
	try {
      assertEquals(detailserrorMsgs.getErrorMsg(), driver.findElement(By.id("errorMsg")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getFirstNameError(), driver.findElement(By.id("firstnameError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getLastNameError(), driver.findElement(By.id("lastnameError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getAddressError(), driver.findElement(By.id("addressError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
	  try {
	  assertEquals(detailserrorMsgs.getBirthDateError(), driver.findElement(By.id("birthDateError")).getAttribute("value"));
	} catch (Error e) {
	  verificationErrors.append(e.toString());
	}
    try {
      assertEquals(detailserrorMsgs.getEmailError(), driver.findElement(By.id("emailError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getPhoneError(), driver.findElement(By.id("phoneError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getDrivingLicenseError(), driver.findElement(By.id("dlnoError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getDrivingLicenseExpiry(), driver.findElement(By.id("dlexpirydteError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getRegNumberError(), driver.findElement(By.id("regnoError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals(detailserrorMsgs.getUtaIdError(), driver.findElement(By.id("utaidError")).getAttribute("value"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
}
		else
		{		
		    try {
	      assertEquals("Registered Successfully.", driver.findElement(By.id("msgRegSuccess")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
		}
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
