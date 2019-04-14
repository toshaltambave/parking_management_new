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

public class AdminTest_Fail extends BusinessFunctions {
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
  
    if(!TestDAO.userExists("User7")){
    	registerUser("User7");
    }
  }

  /**
   * This Test all combinations of validation errors a user can see in the login and registration screens.
   * 
   * @throws Exception
   */
  @Test
  public void testAdminTestFail() throws Exception {
    driver.get(appUrl);
    driver.findElement(By.id("registeruser")).click();
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals(""));
    
    //Nothing entered - all errors present
    driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals("Your username must between 4 and 10 characters"));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Your password must between 4 and 10 characters."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals(""));
    
    //UserName all ready in DataBase
    functions.Register(driver, "User7", "", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals("Username is already in database"));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Your password must between 4 and 10 characters."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals(""));
    TestDAO.deleteUser("User7");
    
    //Good username entered - All other errors still present
    functions.Register(driver, "User7", "", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Your password must between 4 and 10 characters."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals(""));
    Thread.sleep(1000);
    
    //All number password
    functions.Register(driver, "User7", "1234", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Password must contain at least one uppercase."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals("Passwords do not match."));
    Thread.sleep(1000);
    
    //All letter password
    functions.Register(driver, "User7", "Abcd", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Password must contain at least one number."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals("Passwords do not match."));
    Thread.sleep(1000);
    
    //eleven character password
    functions.Register(driver, "User7", "12345678901", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("Your password must between 4 and 10 characters."));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals("Passwords do not match."));
    Thread.sleep(1000);
    
    //Good password but doesn't match the confirmation password
    functions.Register(driver, "User7", "User7", "", "Admin", "Basic"); 
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("confirmPasswordError")).getAttribute("value").equals("Passwords do not match."));
    Thread.sleep(1000);
    
    //All Good
    functions.Register(driver, "User7", "User7", "User7", "Admin", "Basic");
    
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals(""));
    
    //Nothing Entered all errors present
    driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));    
    Thread.sleep(1000); 
    
    //Numbers enter for first name
    functions.RegisterUserDetails(driver, "123", "", "", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals("Your name must only contain alphabets."));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));  
    Thread.sleep(1000); 
    
    //Good first name 
    functions.RegisterUserDetails(driver, "Lex", "", "", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //numbers entered for middle name 
    functions.RegisterUserDetails(driver, "Lex", "123", "", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals("Your name must only contain alphabets."));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //numbers entered for last name 
    functions.RegisterUserDetails(driver, "Lex", "", "123", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals("Your name must only contain alphabets."));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //Correct Lastname 
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //Bad dob entered
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "30", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals("Date of birth can't be after the current date."));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //Good dob entered
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000); 
    
    //Address entered
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Bad email no @, domain, and short
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("Email address needs to contain @"));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Bad email short
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("Email address must be between 7 and 45 characters long"));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //bad domain
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lexxxx@", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals("Invalid domain name"));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Good email
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
     //Short phone
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "469", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("Your phone number should be 10 digits. eg: 7283334567"));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Letters entered for phone number
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "abcdefghij", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals("Phone number can only contain digits."));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Good Phone number
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Short DL number
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "144", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("This should be of length 8 digits."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //DL has numbers 
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "1441255a", "30", "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals("It can only contain digits."));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("The field is mandatory."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Good DL number and short regNum
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "123", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals("Registration number should be between 6 and 10 characters."));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);
    
    //Good RegNum
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("The field is mandatory."));
    Thread.sleep(1000);

    //Short UtaId
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "123");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("UTA ID must be 10 digits long."));
    Thread.sleep(1000);
    
    //UtaId doesn't start with 100
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1011212003");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("UTA ID must start with 100."));
    Thread.sleep(1000);
    
    //UtaId contains letters
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "100021200a");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Please correct the following errors"));
    assertTrue(driver.findElement(By.id("firstnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("middlenameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("lastnameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("birthDateError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("addressError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("emailError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("phoneError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("dlexpirydteError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("regnoError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("utaidError")).getAttribute("value").equals("UTA ID must contain digits only."));
    Thread.sleep(1000);
    
    //Good Everything
    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
    
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals(""));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals(""));
    
    //Bad Login - Nothing Entered
    functions.Login(driver, "", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Login Failed."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals("username or password is incorrect."));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("username or password is incorrect."));
    
    //No Password Given
    functions.Login(driver, "User7", "");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Login Failed."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals("username or password is incorrect."));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("username or password is incorrect."));
    
    //Wrong Password
    functions.Login(driver, "User7", "Random");
    assertTrue(driver.findElement(By.id("errorMsg")).getAttribute("value").equals("Login Failed."));
    assertTrue(driver.findElement(By.id("usernameError")).getAttribute("value").equals("username or password is incorrect."));
    assertTrue(driver.findElement(By.id("passwordError")).getAttribute("value").equals("username or password is incorrect."));
    
    //Good Login
    functions.Login(driver, "User7", "User7");
    
    driver.findElement(By.name("logout")).click();
  }
  
  private void registerUser(String userName){
	  	driver.get(appUrl);
	    functions.Register(driver, userName, userName, userName, "Admin", "Basic");    
	    functions.RegisterUserDetails(driver, "Lex", "", "Luthor", "Male", "1", "LexCorp", "Lex@aol.com", "4693332514", "14412552", "30", "12332147", "1000212003");
	    functions.Login(driver, "User7", "User7");
	    driver.findElement(By.name("logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    TestDAO.deleteUser("User7");
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