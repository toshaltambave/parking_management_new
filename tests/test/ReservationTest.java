package test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void test(String startTime, String endTime, String errorMsg, String startTimeError, String endTimeError, String compareError) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		if(!startTime.equals("")){
			startTime = dateFormat.format(date) +" "+startTime;
		}
		if(!endTime.equals("")){
			endTime = dateFormat.format(date) +" "+endTime;
		}
		
		reservation.validateDateTime(startTime, endTime, resError);
		
		assertEquals(resError.getErrorMsg(),errorMsg);
		assertEquals(resError.getStartTimeError(),startTimeError);
		assertEquals(resError.getEndTimeError(),endTimeError);
		assertEquals(resError.getCompareError(),compareError);

	}
	
	@Test
	@FileParameters("tests/Excel/CheckNormalHours.csv")
	public void normalHoursTest(String startTime, String endTime, String isNormalHour) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startdate = formatter.parse(startTime);
		Date enddate = formatter.parse(endTime);
		Boolean bool = reservation.checkNormalHours(startdate, enddate);
		assertEquals(Boolean.parseBoolean(isNormalHour),reservation.checkNormalHours(startdate, enddate));
	}

}
