package test;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import data.UsersDAO;
import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Users;
import util.PasswordUtility;
@RunWith(JUnitParamsRunner.class)
public class LoginBadTest extends BusinessFunctions{
	private WebDriver driver;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private BusinessFunctions functions = new BusinessFunctions();
	  
	  private String appUrl;
	  private String sharedUIMapPath;

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
	    driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    prop = new Properties();
	    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
	    appUrl = prop.getProperty("AppUrl");
	    sharedUIMapPath = prop.getProperty("SharedUIMapPath");
	    prop.load(new FileInputStream(sharedUIMapPath));
  }
  
  @Test
  @FileParameters("src/Excel/Login.csv")
  public void testLoginBad(String username, String password) throws Exception {
    driver.get(appUrl);
    functions.Login(driver, username, password);
    Users checkUser = new Users(new UsersDAO());
    String mySecurePassword = PasswordUtility.generatePassword(password);
    UsersDAO.userExists(username, mySecurePassword, checkUser);
    if(checkUser.getUserID() == null){
    	assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    }
    else{
    	try {
          assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    	
    }
 
//    assertEquals("username or password is incorrect.", driver.findElement(By.id("passwordError")).getAttribute("value"));
//    try {
//        assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
//      } catch (Error e) {
//        verificationErrors.append(e.toString());
//      }
    //    try {
//      assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
//    driver.findElement(By.id("username")).clear();
//    driver.findElement(By.id("username")).sendKeys("Tester1234");
//    driver.findElement(By.id("password")).clear();
//    driver.findElement(By.id("password")).sendKeys("Tes");
//    driver.findElement(By.id("btnLogin")).click();
//    try {
//      assertEquals("username or password is incorrect.", driver.findElement(By.id("passwordError")).getAttribute("value"));
//    } catch (Error e) {
//      verificationErrors.append(e.toString());
//    }
//    driver.findElement(By.id("username")).clear();
//    driver.findElement(By.id("username")).sendKeys("Tester1234");
//    driver.findElement(By.id("password")).clear();
//    driver.findElement(By.id("password")).sendKeys("Tester1234");
//    driver.findElement(By.id("btnLogin")).click();
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
