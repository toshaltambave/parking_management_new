package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import controller.ReservationsController;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.CreditCard;
import model.CreditCardError;
import model.CreditCardTypes;

@RunWith(JUnitParamsRunner.class)
public class CreditCardTest {

	private CreditCard creditCard;
	private ReservationsController controller;
	private CreditCardError errorMsgs;
	private String[] creditCardTypes;

	@Before
	public void setUp() throws Exception {
		creditCard = new CreditCard();
		controller = new ReservationsController();
		errorMsgs = new CreditCardError();
	}


	@Test
	@FileParameters("tests/test/CreditCardTest.csv")
	public void test(String cardNumber, String creditCardType, String cvv, String month, String year,
			String excpetedErrorMsg, String exceptedCardNumError, String cvvError, String expectedMonthError, String expectedYearError) {
		creditCard.setCardNumber(cardNumber);
		creditCard.setCardType(CreditCardTypes.valueOf(creditCardType).toString());
		creditCard.setCvv(cvv);
		creditCard.setMonth(month);
		creditCard.setYear(year);
		creditCard.validatecreditcarddetails(creditCard, errorMsgs);
		creditCardTypes = Arrays.stream(CreditCardTypes.values()).map(Enum::name).toArray(String[]::new);

		assertEquals(excpetedErrorMsg, errorMsgs.getErrorMsg());
		assertEquals(exceptedCardNumError, errorMsgs.getCardNumberError());
		assertEquals(cvvError, errorMsgs.getCvvError());
		assertEquals(expectedMonthError, errorMsgs.getMonthError());
		assertEquals(expectedYearError, errorMsgs.getYearError());
		assertTrue(Arrays.asList(creditCardTypes).contains("VISA"));
		assertTrue(Arrays.asList(creditCardTypes).contains("AMEX"));
		assertTrue(Arrays.asList(creditCardTypes).contains("MASTERCARD"));
		assertTrue(Arrays.asList(creditCardTypes).contains("DISCOVER"));
	}

}
