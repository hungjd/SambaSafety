package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.pages.CalendarPage;
import com.pages.CompanyPage;
import com.pages.ContactsPage;
import com.pages.DealsPage;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.TasksPage;
import com.util.TestUtil;

//https://www.youtube.com/watch?v=ea0P7MBQmiM (16:00)
// ctrl + shift all to import all extends class

public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	TasksPage tasksPage;
	CalendarPage calendarPage;
	CompanyPage companyPage;

	// create HomePageTest constructor
	public HomePageTest() {
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

		// initialize testUtil object
		testUtil = new TestUtil(); // https://www.youtube.com/watch?v=ea0P7MBQmiM (39:30)

		// initialize ContactsPage object
		contactsPage = new ContactsPage(); // https://www.youtube.com/watch?v=ea0P7MBQmiM (43:30)

		// initialize LoginPage object
		loginPage = new LoginPage();

		homePage = loginPage.LoginUser(prop.getProperty("username"), prop.getProperty("password"));
	}

	// TestCase 1: validate HomePageTitle
	@Test(priority = 1)
	public void HomePageTitleTest() {
		SoftAssert softAssert1 = new SoftAssert();

		String ActualHomePageTitle = homePage.HomePageTitle();
		String ExpectedHomePageTitle = "Cogmento CRM";

		// validate HomePageTitle
		// Assert.assertEquals(actual, expected, message);
		Assert.assertEquals(ActualHomePageTitle, ExpectedHomePageTitle, "HomePageTitle is not match - Test Failed");

		softAssert1.assertAll();
	}

	// TestCase 2: validate UserNameLabel
	@Test(priority = 2)
	public void UserNameTest() {

		SoftAssert softAssert2 = new SoftAssert();

		boolean flag = homePage.UserNameLabel();
		System.out.println("ActualFlag: " + flag);

		// validate UserName
		// Assert.assertTrue(condition, message);
		Assert.assertTrue(flag, "UserName is not match - Test Failed");

		softAssert2.assertAll();
	}

	// TestCase 3: click on Calendar link  
	@Test(priority = 3, enabled = false) // enabled = false, to disable test 
	public void CalendarLinkTest() {
		calendarPage = homePage.clickOnCalendarLink();
	}
	
	// TestCase 4: click on Contacts link
	// https://www.youtube.com/watch?v=ea0P7MBQmiM (43:30)
	@Test(priority = 4)
	public void ContactsLinkTest() {

		// switch to frame
		// testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

//  TestCase 5: click on Company link
	@Test(priority = 5, enabled = false)
	public void clickOnCompanyLinkTest() {
		companyPage = homePage.clickOnCompanyLink();
	}
	
//  TestCase 6: click on Deals link 
	@Test(priority = 6, enabled = false)
	public void clickOnDealsLinkTest() {
		dealsPage = homePage.clickOnDealsLink();
	}

	//  TestCase 7: click on  Tasks link
	@Test(priority = 7, enabled = false)
	public void clickOnTasksLinkTest() {
		tasksPage = homePage.clickOnTasksLink();
	}
 
	// add if test case failed take screenshot
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
