package functions;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BusinessFunctions {
	
	public static Properties prop;
	public static WebDriver driver;

	public void Login(WebDriver driver, String userName, String password) {
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).sendKeys(userName);
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
	}

	public void Register(WebDriver driver, String userName, String password, String confirmPassword, String role, String permitType) {
		driver.findElement(By.id(prop.getProperty("Btn_Login_Register"))).click();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).sendKeys(userName);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_Role")))).selectByVisibleText(role);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_PermitType")))).selectByVisibleText(permitType);
		driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
	}
	

	public void RegisterUserDetails(WebDriver driver, String firstName, String middleName, String lastName, String sex, String dayOfBirth,
			String address, String email, String phone, String dlNum, String dayOfExpiry, String regNum, String utaId) {
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).sendKeys(middleName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).sendKeys(lastName);
		Select sexSelect = new Select(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Sex"))));
		sexSelect.selectByVisibleText(sex);
	    driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOB"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOB"))).sendKeys(dayOfBirth);
//		WebElement datePicker = driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOB")));
//		datePicker.click();
//		By calendarXpath = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+ dayOfBirth +"]");
//		driver.findElement(calendarXpath).click();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Address"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Address"))).sendKeys(address);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Email"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Email"))).sendKeys(email);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Phone"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Phone"))).sendKeys(phone);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNO"))).sendKeys(dlNum);
	    driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).sendKeys(dayOfExpiry);
//		WebElement datePicker2 = driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte")));
//		datePicker2.click();
//		By calendarXpath2 = By.xpath("//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()="+ dayOfExpiry +"]");
//		driver.findElement(calendarXpath2).click();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).sendKeys(regNum);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).sendKeys(utaId);
		driver.findElement(By.id(prop.getProperty("Btn_UserDetails_Submit"))).click();
	}
	
	public void makeReservation(WebDriver driver, String start, String end, Integer spot, String card, String month, String year, String cvv ){
	    driver.findElement(By.id("lnkRequestReservation")).click();
	    driver.findElement(By.id("starttime")).clear();
		driver.findElement(By.id("starttime")).sendKeys(start);
		driver.findElement(By.id("endtime")).clear();
		driver.findElement(By.id("endtime")).sendKeys(end);
	    driver.findElement(By.id("btnSearch")).click();
	    driver.findElement(By.id("btnReserveFloor")).click();
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
	
	public void searchUserbyUserName(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
	}
	
	public void revokeUser(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke_User"))).click();
	    assertTrue(!isElementPresent(driver, "Text_Revoke_Success"));
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_Revoke")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_Revoke")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke"))).click();
	    assertTrue(driver.findElement(By.id("msgRevSuccess")).getText().equals("User has Been Revoked."));
	    driver.manage().window().setSize(new Dimension(1936,1056));
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
	}
	
	public boolean isElementPresent(WebDriver driver, String id) {
	    try {
	        driver.findElement(By.id(prop.getProperty(id)));
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}

}