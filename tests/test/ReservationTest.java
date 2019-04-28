package test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Reservation;
import model.ReservationError;
import model.ReservationsHelper;

@RunWith(JUnitParamsRunner.class)
public class ReservationTest {

	private Reservation reservation;
	private ReservationError resError;

	@Before
	public void setUp() throws Exception {
		reservation = new Reservation();
		resError = new ReservationError();
	}

	@Test
	@FileParameters("tests/test/ReservationDateTest.csv")
	public void test(int startTime, int endTime, String errorMsg, String startTimeError, String endTimeError,
			String compareError) {
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeString = "";
		String endTimeString = "";
		if (startTime != 99999) {
			startCal.add(Calendar.MINUTE, startTime);
			startTimeString = dateFormat.format(startCal.getTime());
		}
		if (endTime != 99999) {
			endCal.add(Calendar.MINUTE, endTime);
			endTimeString = dateFormat.format(endCal.getTime());
		}

		reservation.validateDateTime(startTimeString, endTimeString, resError);
		assertEquals(resError.getErrorMsg(), errorMsg);
		assertEquals(resError.getStartTimeError(), startTimeError);
		assertEquals(resError.getEndTimeError(), endTimeError);
		assertEquals(resError.getCompareError(), compareError);
	}
	
	@Test
	@FileParameters("tests/test/ReservationException.csv")
	public void ExceptionTest(String startTime, String endTime, String isNormalHour) throws ParseException {
	    SimpleDateFormat formatter=new SimpleDateFormat("dd-MMM-yyyy"); 
		Date startdate = formatter.parse(startTime);
		Date enddate = formatter.parse(endTime);
		reservation.validateDateTime(startTime, endTime, resError);
	}

	@Test
	@FileParameters("tests/Excel/CheckNormalHours.csv")
	public void normalHoursTest(String startTime, String endTime, String isNormalHour) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startdate = formatter.parse(startTime);
		Date enddate = formatter.parse(endTime);
		int startDay = startdate.getDay();
		assertEquals(Boolean.parseBoolean(isNormalHour), reservation.checkNormalHours(startdate, enddate));
	}

}
