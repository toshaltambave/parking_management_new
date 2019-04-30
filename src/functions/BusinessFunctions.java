package functions;

import static org.junit.Assert.assertEquals;
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
	}

	public void Register(WebDriver driver, String userName, String password, String confirmPassword, String role,
			String permitType) {
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Username"))).sendKeys(userName);
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_Password"))).sendKeys(password);
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).clear();
		driver.findElement(By.name(prop.getProperty("Txt_Register_ConfirmPassword"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_Role")))).selectByVisibleText(role);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Register_PermitType"))))
				.selectByVisibleText(permitType);
		driver.findElement(By.id(prop.getProperty("Btn_Register_Register"))).click();

	}

	public void RegisterUserDetails(WebDriver driver, String firstName, String middleName, String lastName, String sex,
			String dob, String address, String email, String phone, String dlNum, String expiryDate, String regNum,
			String utaId) {
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).sendKeys(middleName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).sendKeys(lastName);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Sex")))).selectByVisibleText(sex);
		;
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


	}

public void editreservationTimeAndDate(WebDriver driver, String start, String end, String area) throws Exception{
		
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
	

	public void reservationTimeAndDate(WebDriver driver, String start, String end, String area) throws Exception {

		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Reserve"))).click();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).sendKeys(start);
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).sendKeys(end);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Reservation_AreaDropDown"))))
				.selectByVisibleText(area);
		Thread.sleep(1000);
		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	public void reservationModifyTimeAndDate(WebDriver driver, String start, String end, String area) throws Exception {

		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_StartTime"))).sendKeys(start);
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_EndTime"))).sendKeys(end);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_Reservation_AreaDropDown"))))
				.selectByVisibleText(area);
		Thread.sleep(1000);
		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	public void deletereservation(WebDriver driver, Integer spot){

		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_DelRes"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.xpath("(//input[@id='btnDelete'])["+spot+"]")).click();
	    
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
	
	public void modifyreservation(WebDriver driver, Integer spot){
		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_EditRes"))).click();
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
	    driver.findElement(By.xpath("(//input[@id='btnEditReservation'])["+spot+"]")).click();
	}
	
	public void reservationFloorAndSpot(WebDriver driver, String permitType, Integer floor, Integer spot){
		driver.findElement(By.id("btnReserveFloor"+floor+""+permitType)).click();
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
	    driver.findElement(By.xpath("(//input[@id='btnReserveSpotID'])["+spot+"]")).click();
	}

	public void makeReservation(WebDriver driver, String card, String month, String year, String cvv, Boolean cartBool,
			Boolean cameraBool, Boolean historyBool, String cardType) {

		if (cartBool) {
			driver.findElement(By.id(prop.getProperty("Btn_Reservation_Cart"))).click();
		}
		if (cameraBool) {
			driver.findElement(By.id(prop.getProperty("Btn_Reservation_Camera"))).click();
		}
		if (historyBool) {
			driver.findElement(By.id(prop.getProperty("Btn_Reservation_History"))).click();
		}
		driver.findElement(By.id(prop.getProperty("Btn_Reservation_Options"))).click();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_CardNumber"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_CardNumber"))).sendKeys(card);
		if (cartBool || cameraBool || historyBool) {
			new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Card_Type")))).selectByVisibleText(cardType);
		}
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExMonth"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExMonth"))).sendKeys(month);
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExYear"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_ExYear"))).sendKeys(year);
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_CVV"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_Reservation_CVV"))).sendKeys(cvv);

		driver.findElement(By.id(prop.getProperty("Btn_Reservation_PayReserve"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void searchUserbyUserName(WebDriver driver, String userName) {
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type"))))
				.selectByVisibleText("UserName");
		if (prop.getProperty("test_delay").equals("delay")) {
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
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User")))).selectByVisibleText(userName);
		
		driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void searchUserbyLastName(WebDriver driver, String lastName) {
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type"))))
				.selectByVisibleText("LastName");
		if (prop.getProperty("test_delay").equals("delay")) {
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
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User")))).selectByVisibleText(lastName);
		driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void searchUser(WebDriver driver) {
		driver.findElement(By.id(prop.getProperty("Btn_Search_For_User"))).click();
		driver.findElement(By.id(prop.getProperty("Btn_Search"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void revokeUser(WebDriver driver, String userName,String comment){
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke_User"))).click();
	    assertTrue(!isElementPresent(driver, "Text_Revoke_Success"));
	    driver.findElement(By.id(prop.getProperty("txtComment"))).clear();
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("commentError"))).getAttribute("value").equals("Comment is mandatory."));
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_Revoke")))).selectByVisibleText("UserName");
	    new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_Revoke")))).selectByVisibleText(userName);
	    driver.findElement(By.id(prop.getProperty("txtComment"))).clear();
	    driver.findElement(By.id(prop.getProperty("txtComment"))).sendKeys(comment);
	    driver.findElement(By.id(prop.getProperty("Btn_Revoke"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void unrevokeUser(WebDriver driver, String userName) {
		driver.findElement(By.id(prop.getProperty("Btn_UnRevoke_User"))).click();
		assertTrue(!isElementPresent(driver, "Text_UnRevoke_Success"));
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_UnRevoke"))))
				.selectByVisibleText("UserName");
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_UnRevoke"))))
				.selectByVisibleText(userName);
		driver.findElement(By.id(prop.getProperty("Btn_UnRevoke"))).click();
		assertTrue(driver.findElement(By.id(prop.getProperty("Text_UnRevoke_Success"))).getText()
				.equals("User has Been UnRevoked."));
		if (prop.getProperty("test_delay").equals("delay")) {
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


	public void setNoShowById(WebDriver driver, String userName, String resId) throws InterruptedException {
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_No_Show")+resId)).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_NS_Success"))).getText()
				.equals("Marked No Show Successfully."));
	}

	public void viewUserViolations(WebDriver driver, String userName) {
		driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_UV"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void UpdateUserProfile(WebDriver driver, String userNameToUpdate, String firstName, String middleName,
			String lastName, String userName, String sex, String dob, String address, String email, String phone,
			String dlNum, String dlExpiry, String regNum, String utaId, String password, String confirmPassword, String permitType) {
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_Edit_Profile"))).click();
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_UnRevoke"))))
				.selectByVisibleText("UserName");
		if (prop.getProperty("test_delay").equals("delay")) {
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
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_UnRevoke"))))
				.selectByVisibleText(userNameToUpdate);
		driver.findElement(By.id(prop.getProperty("Btn_Update_User"))).click();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).sendKeys(middleName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).sendKeys(lastName);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_UserName"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_UserName"))).sendKeys(userName);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Sex")))).selectByVisibleText(sex);
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
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).sendKeys(dlExpiry);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).sendKeys(regNum);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).sendKeys(utaId);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Password"))).sendKeys(password);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Confirm_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Confirm_Password"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_PermitType")))).selectByVisibleText(permitType);
		driver.findElement(By.id(prop.getProperty("Btn_Update_Profile"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	
	public void UpdateUserProfileUserManager(WebDriver driver, String userNameToUpdate, String firstName, String middleName,
			String lastName, String userName, String sex, String dob, String address, String email, String phone,
			String dlNum, String dlExpiry, String regNum, String utaId, String password, String confirmPassword, String permitType) {
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Firstname"))).sendKeys(firstName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Middlename"))).sendKeys(middleName);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Lastname"))).sendKeys(lastName);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_UserName"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_UserName"))).sendKeys(userName);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UserDetails_Sex")))).selectByVisibleText(sex);
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
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_DLExpiryDte"))).sendKeys(dlExpiry);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_REGNO"))).sendKeys(regNum);
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UserDetails_UTAID"))).sendKeys(utaId);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Password"))).sendKeys(password);
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Confirm_Password"))).clear();
		driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_Confirm_Password"))).sendKeys(confirmPassword);
		new Select(driver.findElement(By.id(prop.getProperty("Txt_UpdateUserDetails_PermitType")))).selectByVisibleText(permitType);
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_Update_Profile"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void setOverdue(WebDriver driver, String userName) {
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_OD"))).click();
		assertTrue(!isElementPresent(driver, "Txt_OD_Successful"));
		driver.findElement(By.id(prop.getProperty("Btn_OD"))).click();
		assertTrue(driver.findElement(By.id(prop.getProperty("Txt_OD_Successful"))).getText()
				.equals("Marked Overdue Successfully."));
		if (prop.getProperty("test_delay").equals("delay")) {
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

	public void setRole(WebDriver driver, String userName, String role) {
		driver.findElement(By.id(prop.getProperty("Btn_ChRole_User"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
		assertTrue(!isElementPresent(driver, "Text_ChRole_Success"));
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Search_Type_ChRole"))))
				.selectByVisibleText("UserName");
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_User_ChRole"))))
				.selectByVisibleText(userName);
		new Select(driver.findElement(By.id(prop.getProperty("Drp_Dwn_Select_Role_ChRole")))).selectByVisibleText(role);
		if (prop.getProperty("test_delay").equals("delay")) {
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
		driver.findElement(By.id(prop.getProperty("Btn_ChRole"))).click();
		assertTrue(driver.findElement(By.id(prop.getProperty("Text_ChRole_Success"))).getText()
				.equals("Role has changed for User."));
		if (prop.getProperty("test_delay").equals("delay")) {
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
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_AddParkingArea"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Error"))).getAttribute("value").equals("Please correct the following errors"));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name_Error"))).getAttribute("value").equals("This field is mandatory."));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_Number_Error"))).getAttribute("value").equals("This field is mandatory."));
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Spot_Number_Error"))).getAttribute("value").equals("This field is mandatory."));
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_Save"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Exception"))).getText().equals("Add Area(s) to the list."));
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    new Select(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_PermitType")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
//	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).clear();
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).sendKeys(SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    new Select(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_PermitType")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).clear();
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).sendKeys(SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    //Added again to make duplicate value case
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    new Select(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_PermitType")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).clear();
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).sendKeys(SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_Save"))).click();
//	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Add_Success"))).getText().equals("Area(s) added successfully."));
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
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	}
	
	
	public void addParkingAreaList(WebDriver driver,String AreaName,String PermitType,String FloorNo, String SpotsNo)
	{
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_AddParkingArea"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_Name"))).sendKeys(AreaName);
	    new Select(driver.findElement(By.id(prop.getProperty("Txt_Parking_Area_PermitType")))).selectByVisibleText(PermitType);
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_Parking_Floor_No"))).sendKeys(FloorNo);
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).clear();
	    driver.findElement(By.name(prop.getProperty("Txt_Parking_Spot_No"))).sendKeys(SpotsNo);
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_AddList"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Parking_Area_Save"))).click();
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
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Parking_Add_Success"))).getText().equals("Area(s) added successfully."));
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
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	}
	
	
	public void viewParkingSpots(WebDriver driver,String AreaName,String PermitType,String FloorNo)
	{
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
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_ViewSpots"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("Drop_Down_Area")))).selectByVisibleText(AreaName);
	    driver.findElement(By.id(prop.getProperty("Btn_Spots_AreaFloors"))).click();	    
	    driver.findElement(By.id(prop.getProperty("Btn_Search_Spot_Floor")+FloorNo+PermitType)).click();
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
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	}
	
	
	public void viewUserParkingSpots(WebDriver driver,String AreaName,String PermitType,String FloorNo)
	{
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingUser_ViewSpots"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("Drop_Down_Area")))).selectByVisibleText(AreaName);
	    driver.findElement(By.id(prop.getProperty("Btn_Spots_AreaFloors"))).click();	    
	    driver.findElement(By.id(prop.getProperty("Btn_Search_Spot_Floor")+FloorNo+PermitType)).click();
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
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	}

	public void blockunblock(WebDriver driver, String AreaName, String PermitType,String FloorNo,String spotId) throws Exception{
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_Block"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("DD_SB_Area")))).selectByVisibleText(AreaName);
	    driver.findElement(By.id(prop.getProperty("btn_Submit"))).click();	    
	    driver.findElement(By.id("btnSelectParkingAreaFloors"+FloorNo+PermitType)).click();
	    driver.findElement(By.xpath("(//input[@id='btnBlockUnblock'])["+spotId+"]")).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_msgblocksuccess"))).getText().equals("spot (un)blocked successfully."));
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
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	}
	
	
	public void editParkingArea(WebDriver driver,String AreaName, String respermitType, String floorNumber, String spotId,
			String newName) throws Exception {
		driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_EditParkingArea"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    driver.findElement(By.id(prop.getProperty("Btn_Save_New_AreaName"))).click();
	    assertTrue(driver.findElement(By.id(prop.getProperty("Txt_Error_Edit"))).getAttribute("value").equals("Please select area first."));
	    new Select(driver.findElement(By.id(prop.getProperty("DropDwn_Parking_Area")))).selectByVisibleText(AreaName);
	    driver.findElement(By.id(prop.getProperty("Btn_Selected_Editname_Area"))).click();
	    driver.findElement(By.id(prop.getProperty("Txt_New_AreaName"))).clear();
	    driver.findElement(By.id(prop.getProperty("Txt_New_AreaName"))).sendKeys(newName);
	    driver.findElement(By.id(prop.getProperty("Btn_Save_New_AreaName"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertEquals("Parking Area name updated.", driver.findElement(By.id(prop.getProperty("Txt_NameUpdate_Success"))).getText());
	    driver.findElement(By.id(prop.getProperty("Btn_User_Home_Page"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_ParkingManagement_EditParkingArea"))).click();
	    new Select(driver.findElement(By.id(prop.getProperty("DropDwn_Parking_Area")))).selectByVisibleText(newName);
	    driver.findElement(By.id(prop.getProperty("Btn_Selected_Editname_Area"))).click();
	    driver.findElement(By.id(prop.getProperty("Btn_Search_Spot_Floor")+floorNumber+respermitType)).click();
	    driver.findElement(By.id(prop.getProperty("Btn_AddNew_Spot"))).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertEquals("Spot added successfully.", driver.findElement(By.id(prop.getProperty("Txt_Spot_Added_Success"))).getText());
	    driver.findElement(By.xpath("(//input[@id='btnBlockUnblock'])["+spotId+"]")).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertEquals("spot (un)blocked successfully.", driver.findElement(By.id(prop.getProperty("Txt_Spot_Blocked_Success"))).getText());
	    Integer spotAssert = Integer.parseInt(spotId)+1;
	    assertEquals("true", driver.findElement(By.xpath("//div[@id='body']/div/div/table/tbody/tr["+spotAssert+"]/td[2]")).getText());
	    driver.findElement(By.xpath("(//input[@id='btnBlockUnblock'])["+spotId+"]")).click();
		if (prop.getProperty("test_delay").equals("delay")) {
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
	    assertEquals("spot (un)blocked successfully.", driver.findElement(By.id(prop.getProperty("Txt_Spot_Blocked_Success"))).getText());
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
	
	public void testEdgeCase(WebDriver driver,String one, String two, String three, String four, String five, String six, String seven,
			String eight, String nine, String ten, String eleven, String twelve, String thirteen,String fakeUrl) {
		driver.findElement(By.id(prop.getProperty(one))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(two))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(three))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(four))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(five))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(six))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(seven))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(eight))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(nine))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(ten))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(eleven))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(twelve))).click();
		driver.get(fakeUrl);
		driver.findElement(By.id(prop.getProperty(thirteen))).click();
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
	
