package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.ParkingAreaController;
import data.FetchParkingSpotsDAO;
import data.ParkingAreaDAO;
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
	private ParkingAreaHelper parkingAreaHelper1;
	private ParkingAreaHelper parkingAreaHelper2;
	private ParkingAreaHelper area1;
	private ParkingAreaHelper area2;
	private ParkingAreaHelperError errorMsg;
	private ParkingAreaController controller;
	private HttpServletRequest request;
	private HttpSession session;
	private ParkingArea parkingArea;
	private ParkingAreaFloors parkingAreaFloors;
	private ParkingSpots parkingSpots;
	private ArrayList<ParkingAreaHelper> copy;

	@Before
	public void setUp() throws Exception {
		copy = new ArrayList<ParkingAreaHelper>();
		parkingAreaHelper = new ParkingAreaHelper();
		parkingAreaHelper1 = new ParkingAreaHelper();
		parkingAreaHelper2 = new ParkingAreaHelper();
		errorMsg = new ParkingAreaHelperError();
		controller = new ParkingAreaController();
		parkingSpots = new ParkingSpots();
		parkingArea = new ParkingArea();
		parkingAreaFloors = new ParkingAreaFloors();
		area1 = EasyMock.createMock(ParkingAreaHelper.class);
		area2 = EasyMock.createMock(ParkingAreaHelper.class);
		request = EasyMock.createMock(HttpServletRequest.class);
		session = EasyMock.createMock(HttpSession.class);
	}

	private Integer getNumber(String number) {
		if (number.isEmpty()) {
			return null;
		}
		return Integer.valueOf(number);
	}

	@Test
	@FileParameters("tests/test/ParkingAreaHelperTest.csv")
	public void test(String action, String areaName, String permitType, String numberofSpots, String floorNumber,
			String expectedErrorMsg, String expectedAreaNameError, String expectedPermitTypeError,
			String expectedFloorNumberError, String expectedNumOfSpotsError) {

		EasyMock.expect(request.getParameter("parkingareaname")).andReturn(areaName);
		EasyMock.expect(request.getParameter("numberofSpots")).andReturn(numberofSpots);
		EasyMock.expect(request.getParameter("floornumber")).andReturn(floorNumber);
		request.setAttribute("isAreaListEmpty", false);
		EasyMock.expectLastCall();
		EasyMock.replay(request);

		controller.getError(request, session, errorMsg, action);
		parkingAreaHelper.setDetails(areaName, PermitType.valueOf(permitType).toString(), getNumber(numberofSpots),
				getNumber(floorNumber));

		assertEquals(expectedErrorMsg, errorMsg.getErrorMsg());
		assertEquals(expectedAreaNameError, errorMsg.getAreaNameError());
		assertEquals(expectedFloorNumberError, errorMsg.getFloorNumberError());
		assertEquals(expectedNumOfSpotsError, errorMsg.getNumberofSpotsError());
		assertEquals(areaName, parkingAreaHelper.getAreaname());
		assertEquals(permitType, parkingAreaHelper.getPermittype());
		assertEquals(getNumber(numberofSpots), parkingAreaHelper.getNumberofspots());
		assertEquals(getNumber(floorNumber), parkingAreaHelper.getFloornumber());

		// Getter Setter Tests - NOT REQUIRED
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

		assertEquals((Integer) 1, parkingArea.getArea_Id());
		assertEquals("testName", parkingArea.getArea_Name());

		assertEquals((Integer) 1, parkingAreaFloors.getArea_Id());
		assertEquals((Integer) 1, parkingAreaFloors.getNo_Spots());
		assertEquals((Integer) 1, parkingAreaFloors.getFloor_Number());
		assertEquals("Basic", parkingAreaFloors.getPermitType());

		assertEquals((Integer) 1, parkingSpots.getArea_Id());
		assertEquals((Integer) 1, parkingSpots.getSpot_Id());
		assertEquals((Integer) 1, parkingSpots.getFloor_Number());
		assertEquals((Integer) 1, parkingSpots.getSpot_UID());
		assertEquals("Basic", parkingSpots.getPermitType());
		assertEquals(true, parkingSpots.getIsBlocked());
	}

	@Test(expected = NullPointerException.class)
	@FileParameters("tests/test/ParkingDAOTest.csv")
	public void DAOTest(int spotUuid, int isBlocked, int areaId, String permitType, int floorNum, String areaName)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet resultSet = null;

		FetchParkingSpotsDAO.blockSpot(spotUuid, isBlocked);
		FetchParkingSpotsDAO.getAllParkingAreas();
		FetchParkingSpotsDAO.getFilteredFloorsbyParkingAreaId(areaId, permitType);
		FetchParkingSpotsDAO.getFilteredParkingAreaFloors();
		FetchParkingSpotsDAO.getFloorsbyParkingAreaId(areaId, permitType);
		FetchParkingSpotsDAO.getspecificParkingArea(1);
		FetchParkingSpotsDAO.getSpotsByAreaFloorPermitFromDb(areaId, floorNum, permitType);
		FetchParkingSpotsDAO.updateParkingAreaName(areaId, areaName);

		parkingAreaHelper.setAreaname(areaName);
		parkingAreaHelper.setFloornumber(floorNum);
		parkingAreaHelper.setNumberofspots(spotUuid);
		parkingAreaHelper.setPermittype(permitType);
		ParkingAreaDAO.addParkingSpot(areaId, floorNum, permitType);
		ParkingAreaDAO.saveArea(parkingAreaHelper);
		ParkingAreaDAO.insertparkingareas(parkingAreaHelper, conn, pst);
		ParkingAreaDAO.insertparkingspots(parkingAreaHelper, conn, resultSet);

	}

	@Test
	@FileParameters("tests/test/ParkingAreaCompare.csv")
	public void testCompare(String areaName, Integer floorNumber, String permitType, String areaName1,
			Integer floorNumber1, String permitType1) {

		EasyMock.expect(area1.getAreaname()).andReturn(areaName);
		EasyMock.expect(area1.getFloornumber()).andReturn(floorNumber);
		EasyMock.expect(area1.getPermittype()).andReturn(permitType);
		EasyMock.expect(area2.getAreaname()).andReturn(areaName1);
		EasyMock.expect(area2.getFloornumber()).andReturn(floorNumber1);
		EasyMock.expect(area2.getPermittype()).andReturn(permitType1);

		copy.add(area1);
		// copy.set(0, area1);
		// EasyMock.expectLastCall();

		EasyMock.replay(area1, area2);

		controller.CompareArea(area1, area2, copy, 0);

	}
}
