package test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;

public class ParkingUserTest_Good extends BusinessFunctions {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private BusinessFunctions functions = new BusinessFunctions();
  
  private String appUrl;
  private String sharedUIMapPath;

  @Before
  public void setUp() throws Exception {
//	Change to FireFoxDriver if using FireFox browser
//	System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
//    driver = new ChromeDriver();
	//FireFox Driver
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
  public void testReservation() throws Exception {
	    driver.get(appUrl);
	    functions.makeReservation(driver, "toshal11", "Toshal123.", "2019-04-12 19:00:00", "2019-04-12 20:00:00", 2, "4238000023456780", "12", "2020", "213");
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