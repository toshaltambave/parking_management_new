package functions;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BusinessFunctions {
	
	public Properties prop = new Properties();

	public void Login(WebDriver driver, String userName, String password) {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
	}

	public void Register(WebDriver driver, String userName, String password, String confirmPassword, String role, String permitType) {
		driver.findElement(By.cssSelector("a.btn.btn-info > span")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("hashedPassword")).clear();
		driver.findElement(By.name("hashedPassword")).sendKeys(password);
		driver.findElement(By.name("confirmPassword")).clear();
		driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id("role"))).selectByVisibleText(role);
		new Select(driver.findElement(By.id("permitType"))).selectByVisibleText(permitType);
		driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
	}
	
	public void RegisterFail(WebDriver driver, String userName, String password, String confirmPassword, String role, String permitType) {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("hashedPassword")).clear();
		driver.findElement(By.name("hashedPassword")).sendKeys(password);
		driver.findElement(By.name("confirmPassword")).clear();
		driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id("role"))).selectByVisibleText(role);
		new Select(driver.findElement(By.id("permitType"))).selectByVisibleText(permitType);
		driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
	}
	

	public void RegisterUserDetails(WebDriver driver, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phone, String dlNum, String dayOfExpiry, String regNum, String utaId) {
		driver.findElement(By.name("firstname")).clear();
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		driver.findElement(By.name("middlename")).clear();
		driver.findElement(By.name("middlename")).sendKeys(middleName);
		driver.findElement(By.name("lastname")).clear();
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		Select sexSelect = new Select(driver.findElement(By.id("sex")));
		sexSelect.selectByVisibleText(sex);
		WebElement datePicker = driver.findElement(By.name("dob"));
		datePicker.click();
		By calendarXpath = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+ dayOfBirth +"]");
		driver.findElement(calendarXpath).click();
		driver.findElement(By.name("address")).clear();
		driver.findElement(By.name("address")).sendKeys(address);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("phone")).clear();
		driver.findElement(By.name("phone")).sendKeys(phone);
		driver.findElement(By.name("dlno")).clear();
		driver.findElement(By.name("dlno")).sendKeys(dlNum);
		WebElement datePicker2 = driver.findElement(By.name("dlexpirydte"));
		datePicker2.click();
		By calendarXpath2 = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+ dayOfExpiry +"]");
		driver.findElement(calendarXpath2).click();
		driver.findElement(By.name("regno")).clear();
		driver.findElement(By.name("regno")).sendKeys(regNum);
		driver.findElement(By.name("utaid")).clear();
		driver.findElement(By.name("utaid")).sendKeys(utaId);
		driver.findElement(By.cssSelector("input.btn.btn-secondary")).click();
	}
	
	public void searchUserbyUserName(WebDriver driver, String userName){
	    driver.findElement(By.linkText("Search for user")).click();
	    new Select(driver.findElement(By.id("type"))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id("two"))).selectByVisibleText(userName);
	    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	}
	
	public void revokeUser(WebDriver driver, String userName){
	    driver.findElement(By.linkText("Revoke user")).click();
	    assertTrue(!isElementPresent(driver, "msgRevSuccess"));
	    new Select(driver.findElement(By.id("one"))).selectByVisibleText("UserName");
	    driver.findElement(By.cssSelector("option[value=\"UserName\"]")).click();
	    new Select(driver.findElement(By.id("two"))).selectByVisibleText(userName);
	    driver.findElement(By.id("btnRevoke")).click();
	    assertTrue(driver.findElement(By.id("msgRevSuccess")).getText().equals("User has Been Revoked."));
	    driver.manage().window().setSize(new Dimension(1936,1056));
	    driver.findElement(By.id("homebutton")).click();
	}
	
	public boolean isElementPresent(WebDriver driver, String id) {
	    try {
	        driver.findElement(By.id(id));
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}

}