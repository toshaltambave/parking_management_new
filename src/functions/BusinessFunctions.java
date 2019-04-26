package functions;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import controller.ReservationsController;
import model.CreditCardError;

public class BusinessFunctions {
	
	public static Properties prop;
	public static WebDriver driver;

	public void Login(WebDriver driver, String userName, String password) {
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Login_Username"))).sendKeys(userName);
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Login_Password"))).sendKeys(password);
		driver.findElement(By.id(prop.getProperty("Btn_Login_Login"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void Register(WebDriver driver, String userName, String password, String confirmPassword, String role, String permitType) {
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).sendKeys(userName);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_Role")))).selectByVisibleText(role);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_PermitType")))).selectByVisibleText(permitType);
		driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void RegisterUserDetails(WebDriver driver, String firstName, String middleName, String lastName, String sex, String dob,
			String address, String email, String phone, String dlNum, String expiryDate, String regNum, String utaId) {
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).sendKeys(middleName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).sendKeys(lastName);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Sex")))).selectByVisibleText(sex);;
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOB"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DOB"))).sendKeys(dob);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Address"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Address"))).sendKeys(address);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Email"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Email"))).sendKeys(email);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Phone"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Phone"))).sendKeys(phone);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLNO"))).sendKeys(dlNum);
	    driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).sendKeys(expiryDate);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).sendKeys(regNum);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).sendKeys(utaId);
		driver.findElement(By.id(prop.getProperty("Btn_UserDetails_Submit"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void reservationTimeAndDate(WebDriver driver, String start, String end, String area) throws Exception{
		
		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Reserve"))).click();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).sendKeys(start);
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).sendKeys(end);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Reservation_AreaDropDown")))).selectByVisibleText(area);
		Thread.sleep(1000);
	    driver.findElement(By.id(prop.getProperty("Btn_Reservation_Search"))).click();
	    if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void reservationFloorAndSpot(WebDriver driver, String permitType, Integer floor, Integer spot){
		driver.findElement(By.id("btnReserveFloor"+floor+""+permitType)).click();
	    driver.findElement(By.xpath("(//input[@id='btnReserveSpotID'])["+spot+"]")).click();
	}
	
	public void makeReservation(WebDriver driver, String card, String month, String year, String cvv, Boolean cartBool, Boolean cameraBool, Boolean historyBool, String cardType){
	    
	    if(cartBool){
	    	driver.findElement(By.id(prop.getProperty("Btn_Reservation_Cart"))).click();
	    }
	    if(cameraBool){
	    	driver.findElement(By.id(prop.getProperty("Btn_Reservation_Camera"))).click();
	    }
	    if(historyBool){
	    	driver.findElement(By.id(prop.getProperty("Btn_Reservation_History"))).click();
	    }
	    driver.findElement(By.id(prop.getProperty("Btn_Reservation_Options"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_CardNumber"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_CardNumber"))).sendKeys(card);
	    if(cartBool || cameraBool || historyBool){
	    	new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Card_Type")))).selectByVisibleText(cardType);
	    }
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExMonth"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExMonth"))).sendKeys(month);
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExYear"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExYear"))).sendKeys(year);
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_CVV"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Reservation_CVV"))).sendKeys(cvv);
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_Reservation_PayReserve"))).click();
		
	}
	
	public void searchUserbyUserName(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void searchUserbyLastName(WebDriver driver, String lastName){
	    driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type")))).selectByVisibleText("LastName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User")))).selectByVisibleText(lastName);
	    driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void searchUser(WebDriver driver){
	    driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void revokeUser(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke_User"))).click();
	    assertTrue(!isElementPresent(driver, "Text_Revoke_Success"));
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_Revoke")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_Revoke")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke"))).click();
	    assertTrue(driver.findElement(By.id("msgRevSuccess")).getText().equals("User has Been Revoked."));
	    
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
	}
	
	public void unrevokeUser(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_UnRevoke_User"))).click();
	    assertTrue(!isElementPresent(driver, "Text_UnRevoke_Success"));
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_UnRevoke")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_UnRevoke")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("Btn_UnRevoke"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Text_UnRevoke_Success"))).getText().equals("User has Been UnRevoked."));
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_UnRevoke"))).click();
	}
	
	public void setNoShow(WebDriver driver, String userName){
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_NoShow"))).click();
	    assertTrue(!isElementPresent(driver, "Txt_NoShow_msg"));
	    driver.findElement(By.id("btnNoShow")).click();
	    assertTrue(driver.findElement(By.id("msgNoShow")).getText().equals("Marked No Show Successfully."));
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_Revoke"))).click();
	}
	
	public void setRole(WebDriver driver, String userName,String role){
	    driver.findElement(By.id(prop.getProperty("Btn_ChRole_User"))).click();
	    assertTrue(!isElementPresent(driver, "Text_ChRole_Success"));
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_ChRole")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_ChRole")))).selectByVisibleText(userName);
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Role_ChRole")))).selectByVisibleText(role);
	    driver.findElement(By.id(prop.getProperty("Btn_ChRole"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Text_ChRole_Success"))).getText().equals("Role has changed for User."));
		if (prop.getProperty("test_delay").equals("delay"))
		{	
			try {
				Thread.sleep((Integer.parseInt(prop.getProperty("thread_sleep"))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_ChRole"))).click();
	}
	
	public void addParkingArea(WebDriver driver,String AreaName,String PermitType,String FloorNo, String SpotsNo)
	{
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page_ChRole"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Error"))).getText().equals("Please correct the following errors"));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name_Error"))).getText().equals("This field is mandatory."));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_Number_Error"))).getText().equals("This field is mandatory."));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Spot_Number_Error"))).getText().equals("This field is mandatory."));
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_Save"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Exception"))).getText().equals("Add Area(s) to the list."));
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    new Select(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_PermitType")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).clear();
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).sendKeys(SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_Save"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Add_Success"))).getText().equals("Area(s) added successfully."));
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
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