package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ExtentReportListener.ExtentReportUtil;
import com.javaScriptExecutor.JavaScriptExecutorUltil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.CommonUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest2 {

	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Extent.html", true);
		// extent = new ExtentReports(System.getProperty("user.dir") +
		// "/test-output/ExtentReport.html", true);
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

		driver.get("https://sambasafety.com");

		// flash accept cookie button
		WebElement AcceptCookiesBtn = driver.findElement(By.xpath("//button[@class='accept']"));
		JavaScriptExecutorUltil.flashByJS(AcceptCookiesBtn, driver); // highlight the element
		driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on accept cookies

		// To scroll down the web page at the bottom of the page.
		JavaScriptExecutorUltil.scrollPageDownByJS(driver);

		// flash Submit A Ticket link
		WebElement submitTicketLink = driver.findElement(By.xpath("//a[contains(text(),'Submit A Ticket')]"));
		JavaScriptExecutorUltil.flashByJS(submitTicketLink, driver); // highlight the element
		driver.findElement(By.xpath("//a[contains(text(),'Submit A Ticket')]")).click();

	}

	// TestCase 1: validate SearchTextTest
	@Test(priority = 1, enabled = false)
	public void SearchTextTest() {

		//driver.findElement(By.xpath("//div[@id='header']//input[@id='s']")).click();
		//CommonUtil.MediumWait();
		driver.findElement(By.id("ap_email")).sendKeys("Username@gmail.com", Keys.ENTER);
		driver.findElement(By.id("ap_email")).sendKeys("Username@gmail.com", Keys.ENTER);
		
		CommonUtil.MediumWait();
		driver.findElement(By.id("ap_password")).sendKeys("Password", Keys.ENTER);
		CommonUtil.MediumWait();
		driver.close();
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