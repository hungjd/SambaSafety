package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginPage; // define at class level
	HomePage homePage;

	// step 1: create constructor LoginPageTest class
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (58:00)
	public LoginPageTest() {
		super(); /* super keyword - call super/TestBase class constructor */
	}

	// step 2:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (1:00:00)
	@BeforeMethod
	public void setUp() {
		initialization(); /*
							 * call initialization() method from supper/TestBase class; launch browser, etc
							 */
		loginPage = new LoginPage(); /* create object of LoginPage class */
	}

	// https://www.youtube.com/watch?v=LxJzeiTQGoE (1:02:00)

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Free CRM software can boost your sales by 30%");
	}

	@Test(priority = 2) 
	public void verifyLogoImageTest() { 
		boolean flag = loginPage.validateCRMImage(); 
		Assert.assertTrue(flag);
	}

	@Test(priority = 3)
	public void verifyLoginLinkTest() {
		loginPage.validateLoginLink();
	}

	@Test(priority = 4)
	public void verifyLoginTest() {
		// call loginPage.java, login page method
		// passing user name and password defined in config.property file
		// assigned to homePage
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	// step 3:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (1:00:50)
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
