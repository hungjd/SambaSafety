package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
//import com.crm.qa.util.TestUtil;

//https://www.youtube.com/watch?v=ea0P7MBQmiM (16:00)
// ctrl + shift all to import all extends class

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	//TestUtil testUtil;
	ContactsPage contactsPage;

	// create constructor HomePageTest
	public HomePageTest() {
		super();
	}
	
	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	
	@BeforeMethod
	public void setUp() {
		
		// from TestBase class; initialize properties,open browser,maximize window,etc
		initialization(); 
		
		// https://www.youtube.com/watch?v=ea0P7MBQmiM (39:30)
		// initialize testUtil object
		//// new https://ui.freecrm.com/home given error org.openqa.selenium.NoSuchFrameException: No frame element found by name or id mainpanel
		//testUtil = new TestUtil();
	
		// log in the application first (19:00)
		loginPage = new LoginPage();
		
		// after loginPage > return homePage class object
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	
		// https://www.youtube.com/watch?v=ea0P7MBQmiM (43:30)
		contactsPage = new ContactsPage();
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest(){
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Cogmento CRM");
	}
	
	@Test(priority=2)
	public void verifyUserNameTest(){
		
		// https://www.youtube.com/watch?v=ea0P7MBQmiM (40:25)
		// this method will call switchToFrame method in testUtil class, jump to frame name "mainpanel" 
		
		// new https://ui.freecrm.com/home given error org.openqa.selenium.NoSuchFrameException: No frame element found by name or id mainpanel
		//testUtil.switchToFrame(); 
		
		// then verify username display or not
		Assert.assertTrue(homePage.verifyCorrectUserName()); //https://www.youtube.com/watch?v=ea0P7MBQmiM (32:00)
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest(){
		// new https://ui.freecrm.com/home given error org.openqa.selenium.NoSuchFrameException: No frame element found by name or id mainpanel
		//testUtil.switchToFrame();
		
		contactsPage = homePage.clickOnContactsLink(); // https://www.youtube.com/watch?v=ea0P7MBQmiM (43:30)
	}
	

	@AfterMethod
	public void closeBrowser(){
		driver.quit();
	}
	
	

}
