package sambasafety.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.util.CommonUtil;
import com.util.TestUtil;

import sambasafety.pages.*;

// ctrl + shift all to import all extends class

public class HomePageTestPOM extends TestBase {
//	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;

	// create HomePageTest constructor
	public HomePageTestPOM() {
		super(); // call super/TestBase class constructor
	}

	// test cases should be separated -- independent with each other
	// before each test case -- launch the browser and login
	// @test -- execute test case
	// after each test case -- close the browser

	@BeforeMethod
	public void setUp() {

		// call initialization() in TestBase/parent class; initialize properties,open
		// browser,maximize window,etc
		initialization();
		CommonUtil.MediumWait();

		// initialize testUtil object
		// testUtil = new TestUtil();

		// initialize ContactsPage object
		// contactsPage = new ContactsPage();

		// initialize LoginPage object
		// loginPage = new LoginPage();

		// homePage = loginPage.LoginUser(prop.getProperty("username"),
		// prop.getProperty("password"));
		// CommonUtil.MediumWait();
	}

	// TestCase 1: validate HomePageTitle
	@Test(priority = 1)
	public void HomePageTitleTest() {
		// SoftAssert softAssert1 = new SoftAssert();

		String ActualTitle = homePage.HomePageTitle();
		String ExpectedTitle = "Continous MVR Monitoring &";

		System.out.println("ActualHomePageTitle: " + ActualTitle);
		System.out.println("ExpectedHomePageTitle: " + ExpectedTitle);

		// Assert.assertEquals(actual, expected, message);
		Assert.assertEquals(ActualTitle, ExpectedTitle, "HomePageTitle is not match - Test Failed");

		// softAssert1.assertAll();

	}

	// TestCase 2: validate CompanyLogoImage
	@Test(priority = 2)
	public void CompanyLogoImageTest() {

		SoftAssert softAssert2 = new SoftAssert(); // create SoftAssert object

		boolean flag = homePage.LogoImage();

		// Assert.assertTrue(condition, message);
		Assert.assertTrue(flag, "CompanyLogoImage is not display - Test Failed");

		softAssert2.assertAll();
	}

	// TestCase 3: validate CopyRight
	@Test(priority = 3)
	public void CopyRightTest() {

		SoftAssert softAssert3 = new SoftAssert();

		boolean flag = homePage.CopyRight();
		
		// Assert.assertTrue(condition, message);
		Assert.assertTrue(flag, "CopyRight is not display - Test Failed");

		softAssert3.assertAll();
	}

	// add if test case failed take screenshot
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
