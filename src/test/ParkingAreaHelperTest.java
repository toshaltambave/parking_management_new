package test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.ParkingAreaController;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.ParkingArea;
import model.ParkingAreaFloors;
import model.ParkingAreaHelper;
import model.ParkingAreaHelperError;
import model.ParkingSpots;
import model.PermitType;

@RunWith(JUnitParamsRunner.class)
public class ParkingAreaHelperTest {

	private ParkingAreaHelper parkingAreaHelper;
	private ParkingAreaHelperError errorMsg;
	private ParkingAreaController controller;
	private HttpServletRequest request;
	private HttpSession session;
	private ParkingArea parkingArea;
	private ParkingAreaFloors parkingAreaFloors;
	private ParkingSpots parkingSpots;

	@Before
	public void setUp() throws Exception {
		parkingAreaHelper = new ParkingAreaHelper();
		errorMsg = new ParkingAreaHelperError();
		controller = new ParkingAreaController();
		parkingSpots = new ParkingSpots();
		parkingArea = new ParkingArea();
		parkingAreaFloors = new ParkingAreaFloors();
		request = EasyMock.createMock(HttpServletRequest.class);
		session = EasyMock.createMock(HttpSession.class);
	}
	
	private Integer getNumber(String number){
			if(number.isEmpty()){
				return null;
			}
			return Integer.valueOf(number);
	}

	@Test
	@FileParameters("src/test/ParkingAreaHelperTest.csv")
	public void test(String action, String areaName, String permitType, String numberofSpots, String floorNumber,
			String expectedErrorMsg, String expectedAreaNameError, String expectedPermitTypeError,
			String expectedFloorNumberError, String expectedNumOfSpotsError) {

		EasyMock.expect(request.getParameter("parkingareaname")).andReturn(areaName);
		EasyMock.expect(request.getParameter("numberofSpots")).andReturn(numberofSpots);
		EasyMock.expect(request.getParameter("floornumber")).andReturn(floorNumber);
		EasyMock.replay(request);

		controller.getError(request, session, errorMsg, action);
		parkingAreaHelper.setDetails(areaName, PermitType.valueOf(permitType).toString(), getNumber(numberofSpots), getNumber(floorNumber));

		assertEquals(expectedErrorMsg, errorMsg.getErrorMsg());
		assertEquals(expectedAreaNameError, errorMsg.getAreaNameError());
		assertEquals(expectedFloorNumberError, errorMsg.getFloorNumberError());
		assertEquals(expectedNumOfSpotsError, errorMsg.getNumberofSpotsError());
		assertEquals(areaName, parkingAreaHelper.getAreaname());
		assertEquals(permitType, parkingAreaHelper.getPermittype());
		assertEquals(getNumber(numberofSpots), parkingAreaHelper.getNumberofspots());
		assertEquals(getNumber(floorNumber), parkingAreaHelper.getFloornumber());
		
		
		//Getter Setter Tests - NOT REQUIRED
		parkingArea.setArea_Id(1);
		parkingArea.setArea_Name("testName");
		
		parkingAreaFloors.setArea_Id(1);
		parkingAreaFloors.setFloor_Number(1);
		parkingAreaFloors.setNo_Spots(1);
		parkingAreaFloors.setPermitType("Basic");
		
		parkingSpots.setArea_Id(1);
		parkingSpots.setFloor_Number(1);
		parkingSpots.setIsBlocked(true);
		parkingSpots.setPermitType("Basic");
		parkingSpots.setSpot_Id(1);
		parkingSpots.setSpot_UID(1);
		
		assertEquals((Integer)1, parkingArea.getArea_Id());		
		assertEquals("testName", parkingArea.getArea_Name());	
		
		assertEquals((Integer)1, parkingAreaFloors.getArea_Id());	
		assertEquals((Integer)1, parkingAreaFloors.getNo_Spots());	
		assertEquals((Integer)1, parkingAreaFloors.getFloor_Number());	
		assertEquals("Basic", parkingAreaFloors.getPermitType());
		
		assertEquals((Integer)1, parkingSpots.getArea_Id());	
		assertEquals((Integer)1, parkingSpots.getSpot_Id());	
		assertEquals((Integer)1, parkingSpots.getFloor_Number());	
		assertEquals((Integer)1, parkingSpots.getSpot_UID());
		assertEquals("Basic", parkingSpots.getPermitType());
		assertEquals(true, parkingSpots.getIsBlocked());
	}

}
