package selenium;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import functions.BusinessFunctions;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import model.Role;
import test.Data.TestDAO;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTC_EdgeCase extends BusinessFunctions {
	private WebDriver driver;
	// private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String appUrl;
	private String fakeUrl;
	private String sharedUIMapPath;

	@Before
	public void setUp() throws Exception {
		// Change to FireFoxDriver if using FireFox browser
		// FireFox Driver
		// System.setProperty("webdriver.firefox.marionette",
		// "C:\\GeckoSelenium\\geckodriver.exe");
		// driver = new FirefoxDriver();
		//FireFox Driver
		   System.setProperty("webdriver.firefox.marionette", "C:\\GeckoSelenium\\geckodriver.exe");
		   driver = new FirefoxDriver();
//		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
//		driver = new ChromeDriver();

		prop = new Properties();
		prop.load(new FileInputStream("./Configuration/Configuration.properties"));
		int timewait = (Integer.parseInt(prop.getProperty("wait_time")));
		driver.manage().timeouts().implicitlyWait(timewait, TimeUnit.SECONDS);
		appUrl = prop.getProperty("AppUrl");
		fakeUrl = prop.getProperty("FakeUrl");
		sharedUIMapPath = prop.getProperty("SharedUIMapPath");
		prop.load(new FileInputStream(sharedUIMapPath));

		driver.get(appUrl);
		driver.manage().window().setSize(new Dimension(1440, 850));
	}

	@Test
	@FileParameters("tests/Excel/TCedgecase.csv")
	public void testEdgeCases(String one,String two, String three, String four, String five, String six, String seven,
			String eight, String nine, String ten, String eleven, String twelve, String thirteen) {
		driver.get(appUrl);
		driver.get(fakeUrl);
		testEdgeCase(driver,one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen,fakeUrl);
	}

	
	

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}