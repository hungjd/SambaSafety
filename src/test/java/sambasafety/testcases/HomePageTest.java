package sambasafety.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.javaScriptExecutor.JavaScriptExecutorUltil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.CommonUtil;
import com.util.Xls_ReaderUltil;
import com.ExtentReportListener.*;

import io.github.bonigarcia.wdm.WebDriverManager;

//ExtentReport - Taking ScreenShot ONLY for Failed Tests in Selenium using TestNG Listener

public class HomePageTest {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Extent.html", true);
		//extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Company", "SambaSafety");
		extent.addSystemInfo("Test Environment", "Demo");
		extent.addSystemInfo("User Name", "JD");

	}
	
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// open url
		driver.get("https:sambasafety.com");

		// flash accept cookie button
		WebElement AcceptCookiesBtn = driver.findElement(By.xpath("//button[@class='accept']"));
		JavaScriptExecutorUltil.flashByJS(AcceptCookiesBtn, driver); // highlight the element
		driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on accept cookies

	}

	// TestCase 1: validate CompanyLogoImage
	@Test(priority = 1, enabled = true)
	public void CompanyLogoTest() {
		extentTest = extent.startTest("CompanyLogoTest");

		// flash LogoImage
		WebElement LogoImage = driver.findElement(By.xpath("//img[@class='attachment-medium size-medium']"));
		JavaScriptExecutorUltil.flashByJS(LogoImage, driver); // highlight the element
		
		boolean flag = driver.findElement(By.xpath("//img[@class='attachment-medium size-medium']")).isDisplayed();

		// Assert.assertTrue(condition, message);
		Assert.assertTrue(flag, "Logo is not display - Test Case Failed.");
	}

	// TestCase 2: validate PageTitleTest
	@Test(priority = 2, enabled = true)
	public void PageTitleTest() {
		extentTest = extent.startTest("PageTitleTest");
		String title = driver.getTitle();

		System.out.println(title);
		Assert.assertEquals(title, "Continuous MVR Monitoring & Driver Risk Management Software - TESTING",
				"Page Title not match - Test Case Failed.");
	}

	// TestCase 3: validate CopyRight
	@Test(priority = 3, enabled = true)
	public void CopyRightTest() {
		extentTest = extent.startTest("SambaSafetyCopyRightTest");

		// flash LogoImage
		WebElement SambaSafetyCopyRight = driver.findElement(By.xpath("//div[@class='copyright']"));
		
		// To scroll down the web page at the bottom of the page.
		JavaScriptExecutorUltil.scrollPageDownByJS(driver);
		CommonUtil.LongWait(); // Thread.sleep(6000)	
		JavaScriptExecutorUltil.flashByJS(SambaSafetyCopyRight, driver); // highlight the element
	
		WebElement element = driver.findElement(By.xpath("//div[@class='copyright']")); 
		String strng = element.getText();
		System.out.println(strng);
		
		Assert.assertEquals("©SambaSafety 2020", strng, "SambaSafety 2020 is not match - Test Case Failed.");
	}


	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, result.getName() + "  - FAILED"); // to add name in extent report
			extentTest.log(LogStatus.FAIL, result.getThrowable() + "  - FAILED"); // to add error/exception in
																					// extent report

			//String screenshotPath = HomePageTest.takeScreenshot(driver, result.getName());
			String screenshotPath = ExtentReportUtil.takeScreenshot(driver, result.getName());
			
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to add screenshot in extent
																							// report
			// extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath));
			// //to add screencast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, result.getName() + "  - SKIPPED");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, result.getName() + "  - PASSED");

		}

		extent.endTest(extentTest); // ending test and ends the current test and prepare to create html report
		driver.quit();
	}

}
