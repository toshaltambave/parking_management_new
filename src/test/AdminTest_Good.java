package test;


import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import test.Data.TestDAO;

public class AdminTest_Good extends BusinessFunctions {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private BusinessFunctions functions = new BusinessFunctions();
  
  private String appUrl;
  private String sharedUIMapPath;

  @Before
  public void setUp() throws Exception {
	//Change to FireFoxDriver if using FireFox browser
	System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    prop = new Properties();
    prop.load(new FileInputStream("./Configuration/Configuration.properties"));
    appUrl = prop.getProperty("AppUrl");
    sharedUIMapPath = prop.getProperty("SharedUIMapPath");
    prop.load(new FileInputStream(sharedUIMapPath));
    
    if(TestDAO.userExists("User7")){
    	TestDAO.deleteUser("User7");
    }
  }

  @Test
  public void testAdminTestGood() throws Exception {
    driver.get(appUrl);
    assertTrue(!isElementPresent(driver, "msgRegSuccess"));
    functions.Register(driver, "User7", "User7", "User7", "Admin", "Basic");    
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
    assertTrue(driver.findElement(By.id("msgRegSuccess")).getText().equals("Registered Successfully."));
    functions.Login(driver, "User7", "User7");
    functions.searchUserbyUserName(driver, "User6");
    driver.findElement(By.name("back")).click();
    driver.findElement(By.name("back")).click();
    functions.revokeUser(driver, "User6");
    driver.findElement(By.name("logout")).click();
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