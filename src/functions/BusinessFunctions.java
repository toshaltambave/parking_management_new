package functions;

import java.util.Properties;

import org.openqa.selenium.By;
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
		driver.findElement(By.id("btnLogin")).click();
	}

	public void Register(WebDriver driver, String userName, String password, String confirmPassword, String role) {
		//driver.findElement(By.cssSelector("a.btn.btn-info > span")).click();registeruser
		driver.findElement(By.id("registeruser")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("hashedPassword")).clear();
		driver.findElement(By.name("hashedPassword")).sendKeys(password);
		driver.findElement(By.name("confirmPassword")).clear();
		driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id("role"))).selectByVisibleText(role);
		driver.findElement(By.id("btnRegister")).click();
	}

	public void RegisterUserDetails(WebDriver driver, String firstName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phone, String dlNum, String dayOfExpiry, String regNum, String utaId) {
		driver.findElement(By.name("firstname")).clear();  
		driver.findElement(By.name("firstname")).sendKeys(firstName);
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
		driver.findElement(By.id("btnuserdetailssubmit")).click();
	}
	
	public void searchUserbyUserName(WebDriver driver, String userName){
	    driver.findElement(By.linkText("Search for user")).click();
	    new Select(driver.findElement(By.id("type"))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id("two"))).selectByVisibleText(userName);
	    driver.findElement(By.id("btnUserSearch")).click();
	}
	
	public void revokeUser(WebDriver driver, String userName){
	    driver.findElement(By.linkText("Revoke user")).click();
	    new Select(driver.findElement(By.id("one"))).selectByVisibleText("UserName");
	    driver.findElement(By.cssSelector("option[value=\"UserName\"]")).click();
	    new Select(driver.findElement(By.id("two"))).selectByVisibleText(userName);
	    driver.findElement(By.id("btnRevoke")).click();
	}
	
	public void makeReservation(WebDriver driver, String start, String end, String area, String permitType, Integer floor, Integer spot, String card, String month, String year, String cvv ){
	    driver.findElement(By.id("lnkRequestReservation")).click();
	    driver.findElement(By.id("starttime")).clear();
		driver.findElement(By.id("starttime")).sendKeys(start);
		driver.findElement(By.id("endtime")).clear();
		driver.findElement(By.id("endtime")).sendKeys(end);
		new Select(driver.findElement(By.id("areaDropDrown"))).selectByVisibleText(area);
	    driver.findElement(By.id("btnSearch")).click();
	    driver.findElement(By.id("btnReserveFloor"+floor+""+permitType)).click();
	    driver.findElement(By.xpath("(//input[@id='btnReserveSpotID'])["+spot+"]")).click();
	    driver.findElement(By.id("cart")).click();
	    driver.findElement(By.id("camera")).click();
	    driver.findElement(By.id("history")).click();
	    driver.findElement(By.id("btnOptions")).click();
	    driver.findElement(By.id("cardNumber")).clear();
	    driver.findElement(By.id("cardNumber")).sendKeys(card);
	    driver.findElement(By.id("expiryMonth")).clear();
	    driver.findElement(By.id("expiryMonth")).sendKeys(month);
	    driver.findElement(By.id("expiryYear")).clear();
	    driver.findElement(By.id("expiryYear")).sendKeys(year);
	    driver.findElement(By.id("cvvCode")).clear();
	    driver.findElement(By.id("cvvCode")).sendKeys(cvv);
	    driver.findElement(By.id("btnPayReserve")).click();
	    driver.findElement(By.id("btnLogout")).click();
	}

}
