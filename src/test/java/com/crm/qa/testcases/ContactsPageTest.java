/*
 * @author Naveen Khunteta
 * 
 */

package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

// https://www.youtube.com/watch?v=ea0P7MBQmiM (57:41)

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName = "contacts";

	// call super class constructor to initialize properties
	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException {

		initialization();

		testUtil = new TestUtil();

		contactsPage = new ContactsPage();

		// entered application
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		TestUtil.runTimeInfo("error", "login successful");

		// in homePage.java > click on Contacts link
		// https://www.youtube.com/watch?v=ea0P7MBQmiM (1:03:00)
		// testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "contacts label is missing on the page");
	}

	@Test(priority = 2)
	public void verifySelectSingleContactsTest() {
		contactsPage.selectContactsByName("Steven VerlMun");
	}

	@Test(priority = 3)
	public void verifySelectMultipleContactsTest() {
		contactsPage.selectContactsByName("Steven VerlMun");
		contactsPage.selectContactsByName("John Dang");

	}

	// https://www.youtube.com/watch?v=H2-3w-GQZ3g - 4 (30:00)
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	// https://www.youtube.com/watch?v=H2-3w-GQZ3g - 4 (19:00)
	@Test(priority = 4, dataProvider = "getCRMTestData")
	public void verifyCreateNewContact(String firstName, String lastName, String company) {
		
		homePage.clickOnNewContactLink(); // click on New contact button
		
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(firstName, lastName, company);

		
		// https://www.youtube.com/watch?v=H2-3w-GQZ3g - 4 (38:40)
		// add assertion to verify CreateNewContact successful or not
		
	}
	
	

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
