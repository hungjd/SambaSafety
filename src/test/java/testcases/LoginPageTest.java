package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginPage; // define at class level
	HomePage homePage;

	// step 1: create constructor LoginPageTest class
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (58:00)
	public LoginPageTest() {
		super(); //super keyword - call super/TestBase class constructor 
	}

	// step 2:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (1:00:00)
	@BeforeMethod
	public void setUp() {
		
		//call initialization() method from supper/TestBase class
		initialization();  
		
		//create object of LoginPage class
		loginPage = new LoginPage(); 
	}

	@Test(priority = 1) 	
	public void LoginPageTitleTest() {
		
		SoftAssert softAssert1 = new SoftAssert(); // create SoftAssert object
		
		String ActualTitle = loginPage.LoginPageTitle();
		System.out.println("ExpectedTitle: - "+ActualTitle);
		
		String ExpectedTitle = "Free CRM software can boost your sales by 30%";
		System.out.println("ExpectedTitle: - "+ExpectedTitle);
		
		// validate LoginPageTitle
		//Assert.assertEquals(actual, expected, message);
		Assert.assertEquals(ActualTitle, ExpectedTitle, "LoginPageTitle is not match - Test Failed");
		
		softAssert1.assertAll();
	
	}

	@Test(priority = 2)  	
	public void CRMLogoImageTest() { 
		
		SoftAssert softAssert2 = new SoftAssert(); // create SoftAssert object
		
		boolean flag = loginPage.CRMLogoImage();
		System.out.println("ActualFlag: - " +flag);
		
		// validate CRMLogoImage 
		//Assert.assertTrue(condition, message);
		Assert.assertTrue(flag, "CRMLogoImage is not display - Test Failed");
		
		softAssert2.assertAll();
	}

	@Test(priority = 3)
	public void LoginLinkTest() {
		
		loginPage.LoginLinkDisplay();
		
		// add assertion
	}

	@Test(priority = 4)
	public void LoginTest() {
		// call loginPage.java, login page method
		// passing user name and password defined in config.property file
		// assigned to homePage
		homePage = loginPage.LoginUser(prop.getProperty("username"), prop.getProperty("password"));
	}

	// step 3:
	@AfterMethod
	public void closeBrowser() {
		System.out.println("Closing browser");
		driver.quit();
	}

}
