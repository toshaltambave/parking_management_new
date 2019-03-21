package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserDetailsTest {
	
	private UserDetails userDetails;

	@Before
	public void setUp() throws Exception {
		userDetails = new UserDetails();
	}

	@Test
	public void test() {
		assertEquals("",userDetails.validateMandatory("test"));
	}

}
