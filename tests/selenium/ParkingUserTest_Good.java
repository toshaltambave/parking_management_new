package selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import data.UsersDAO;
import functions.BusinessFunctions;
import model.Users;
import util.PasswordUtility;

public class ParkingUserTest_Good extends BusinessFunctions {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private BusinessFunctions functions = new BusinessFunctions();
  private UsersDAO UsersDAO;
  
  private String appUrl;
  private String sharedUIMapPath;
  private String username,password;

  @Before
  public void setUp() throws Exception {
//	Change to FireFoxDriver if using FireFox browser
	// System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
 //    driver = new ChromeDriver();
	//FireFox Driver
   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
   driver = new FirefoxDriver();
//    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
   prop = new Properties();	
   prop.load(new FileInputStream("./Configuration/login.properties"));
	username = prop.getProperty("puusername2");
	password = prop.getProperty("pupassword2");
    
    
    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
	int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
	driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
    appUrl = prop.getProperty("AppUrl");
    sharedUIMapPath = prop.getProperty("SharedUIMapPath");
    prop.load(new FileInputStream(sharedUIMapPath));
    UsersDAO = new UsersDAO();
  }

  
  @Test
  public void testReservation() throws Exception {
	driver.get(appUrl);
  	if(!UsersDAO.Usernameunique(username)){
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
  		functions.Register(driver, username, password, password, "ParkingUser","Basic");
  		assertEquals("Username is already in database", driver.findElement(By.id("usernameError")).getAttribute("value"));
  	}
  	else
  	{
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
  		functions.Register(driver, username, password, password, "ParkingUser","Basic");
  		functions.RegisterUserDetails(driver, "Toshal","R" ,"Tam", "Male", "2019-04-13", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
  	}
    driver.get(appUrl);
    
    //UnHappy
    functions.Login(driver, "T", "");
    Users checkUser = new Users(new UsersDAO());
    String mySecurePassword = PasswordUtility.generatePassword("");
    UsersDAO.userExists("T", mySecurePassword, checkUser);
    if(checkUser.getUserID() == null){
    	assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    	assertEquals("Login Failed.", driver.findElement(By.id("errorMsg")).getAttribute("value"));
    }
    else{
    	try {
          assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    	
    }
    //UnHappy2
    functions.Login(driver, "Tester1234", "1");
    checkUser = new Users(new UsersDAO());
    mySecurePassword = PasswordUtility.generatePassword("1");
    UsersDAO.userExists("Tester1234", mySecurePassword, checkUser);
    if(checkUser.getUserID() == null){
    	assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    	assertEquals("Login Failed.", driver.findElement(By.id("errorMsg")).getAttribute("value"));
    }
    else{
    	try {
          assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    	
    }
    //UnHappy3
    functions.Login(driver, "Tester1234", "Tester1234");
    checkUser = new Users(new UsersDAO());
    mySecurePassword = PasswordUtility.generatePassword("Tester1234");
    UsersDAO.userExists("Tester1234", mySecurePassword, checkUser);
    if(checkUser.getUserID() == null){
    	assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    	assertEquals("Login Failed.", driver.findElement(By.id("errorMsg")).getAttribute("value"));
    }
    else{
    	try {
          assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    	
    }
    
    //Happy
    functions.Login(driver, username, password);
    checkUser = new Users(new UsersDAO());
    mySecurePassword = PasswordUtility.generatePassword(password);
    UsersDAO.userExists(username, mySecurePassword, checkUser);
    if(checkUser.getUserID() == null){
    	assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    	assertEquals("Login Failed.", driver.findElement(By.id("errorMsg")).getAttribute("value"));
    }
    else{
    	try {
          assertEquals("PARKING USER HOMEPAGE", driver.findElement(By.cssSelector("h2")).getText());
        } catch (Error e) {
          verificationErrors.append(e.toString());
        }
    	
    }
    //functions.Login(driver, "toshaltest", "Toshal123.");
    functions.makeReservation(driver, "2019-04-14 22:00:00", "2019-04-14 22:15:00", "Nedderman", "Basic", 1 , 2, "4238000023456780", "12", "2020", "213");
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