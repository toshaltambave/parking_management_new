package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest {
	
	private Reservation reservation;
	private Reservations reservations;
	private ReservationsHelper helper;
	
	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
		reservations = new Reservations();
		helper = new ReservationsHelper();
	}

	@Test
	public void test() {

		reservations.makeReservations(1, 1, "startTime", "endTime", true, true, true, true, true);
		reservations.setReservationID(1);
		
		reservation.makeReservation(1, 1, "startTime", "endTime", true, true, true, true, true, 1.0);
		reservation.setReservationID(1);
		
		
		helper.setReservationID(1);
		helper.setAreaName("testName");
		helper.setEnd_Time("endTime");
		helper.setFloor_Number(1);
		helper.setIsNoShow(true);
		helper.setisNoShow(1);
		helper.setisOverDue(1);
		helper.setIsOverStay(true);
		helper.setLastName("lastName");
		helper.setSpot_Id(1);
		helper.setStart_Time("startTime");
		helper.setUserName("userName");
		
		assertEquals(true, reservation.getCamera());
		assertEquals(true, reservation.getCart());
		assertEquals(true, reservation.getHistory());
		assertEquals(true, reservation.getNoShow());
		assertEquals(true, reservation.getOverStay());
		assertEquals("endTime", reservation.getEndTime());
		assertEquals("startTime", reservation.getStartTime());
		assertEquals((Integer) 1, reservation.getReservationID());
		assertEquals((Integer) 1, reservation.getSpotUID());
		assertEquals((Integer) 1, reservation.getUserID());
		assertEquals((Double) 1.0, reservation.getTotal());
		
		assertEquals(true, reservations.getCamera());
		assertEquals(true, reservations.getCart());
		assertEquals(true, reservations.getHistory());
		assertEquals(true, reservations.getNoShow());
		assertEquals(true, reservations.getOverStay());
		assertEquals("endTime", reservations.getEndTime());
		assertEquals("startTime", reservations.getStartTime());
		assertEquals((Integer) 1, reservations.getReservationID());
		assertEquals((Integer) 1, reservations.getSpotUID());
		assertEquals((Integer) 1, reservations.getUserID());
		
		assertEquals(true, helper.getisNoShow());
		assertEquals(true, helper.getIsOverStay());
		assertEquals(true, helper.getIsNoShow());
		assertEquals(true, helper.getisOverDue());
		assertEquals((Integer) 1, helper.getFloor_Number());
		assertEquals((Integer) 1, helper.getReservationID());
		assertEquals((Integer) 1, helper.getSpot_Id());
		assertEquals("endTime", helper.getEnd_Time());
		assertEquals("startTime", helper.getStart_Time());
		assertEquals("testName", helper.getAreaName());
		assertEquals("lastName", helper.getLastName());
		assertEquals("userName", helper.getUserName());
		
		helper.setisNoShow(0);
		helper.setisOverDue(0);
		
		assertEquals(false, helper.getisNoShow());
		assertEquals(false, helper.getisOverDue());
		assertEquals(false, helper.getIsNoShow());
		assertEquals(false, helper.getIsOverStay());
		
		helper.setIsNoShow(true);
		helper.setIsOverStay(true);
		
		assertEquals(true, helper.getIsNoShow());
		assertEquals(true, helper.getisNoShow());
		assertEquals(true, helper.getisOverDue());
		assertEquals(true, helper.getIsOverStay());
		
		helper.setIsNoShow(true);
		helper.setisOverDue(1);
		
		assertEquals(true, helper.getIsNoShow());
		assertEquals(true, helper.getisNoShow());
		assertEquals(true, helper.getisOverDue());
		assertEquals(true, helper.getIsOverStay());
			
	}

}
