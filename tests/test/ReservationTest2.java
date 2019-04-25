package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Reservation;
import model.ReservationsHelper;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest2 {

	private Reservation reservation;
	private ReservationsHelper helper;

	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
		helper = new ReservationsHelper();
	}

	@Test
	@FileParameters("tests/test/ReservationTest.csv")
	public void test(Integer userId, Integer spotUID, String startTime, String endTime, boolean noShow,
			boolean overStay, boolean cart, boolean camera, boolean history, Double total, Integer reservationId,
			Integer floorNumber, Integer noShowNum, Integer overDue, Integer spotId, String areaName, String lastName,
			String userName) {

		reservation.makeReservation(userId, spotUID, startTime, endTime, noShow, overStay, cart, camera, history,
				total);
		reservation.setReservationID(reservationId);

		helper.setReservationID(reservationId);
		helper.setAreaName(areaName);
		helper.setEnd_Time(endTime);
		helper.setFloor_Number(floorNumber);
		helper.setIsNoShow(noShow);
		helper.setisNoShow(noShowNum);
		helper.setisOverDue(overDue);
		helper.setIsOverStay(overStay);
		helper.setLastName(lastName);
		helper.setSpot_Id(spotId);
		helper.setStart_Time(startTime);
		helper.setUserName(userName);

		assertEquals(camera, reservation.getCamera());
		assertEquals(cart, reservation.getCart());
		assertEquals(history, reservation.getHistory());
		assertEquals(noShow, reservation.getNoShow());
		assertEquals(overStay, reservation.getOverStay());
		assertEquals(endTime, reservation.getEndTime());
		assertEquals(startTime, reservation.getStartTime());
		assertEquals(reservationId, reservation.getReservationID());
		assertEquals(spotUID, reservation.getSpotUID());
		assertEquals(userId, reservation.getUserID());
		assertEquals(total, reservation.getTotal());

		assertEquals(noShow, helper.getIsNoShow());
		assertEquals(noShow, helper.getisNoShow());
		assertEquals(overStay, helper.getIsOverStay());
		assertEquals(overStay, helper.getisOverDue());
		assertEquals(floorNumber, helper.getFloor_Number());
		assertEquals(reservationId, helper.getReservationID());
		assertEquals(spotId, helper.getSpot_Id());
		assertEquals(endTime, helper.getEnd_Time());
		assertEquals(startTime, helper.getStart_Time());
		assertEquals(areaName, helper.getAreaName());
		assertEquals(lastName, helper.getLastName());
		assertEquals(userName, helper.getUserName());

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
