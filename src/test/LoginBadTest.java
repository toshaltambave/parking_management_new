package test;

import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;

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
  public void testLoginBad() throws Exception {
    driver.get(appUrl);
//    driver.findElement(By.id("username")).clear();
//    driver.findElement(By.id("username")).sendKeys("T");
//    driver.findElement(By.id("btnLogin")).click();
    functions.Login(driver, "T", "");
    assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    functions.Login(driver, "Tester123", "Tes");
    assertEquals("username or password is incorrect.", driver.findElement(By.id("usernameError")).getAttribute("value"));
    functions.Login(driver, "Tester123", "Tester123");
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
