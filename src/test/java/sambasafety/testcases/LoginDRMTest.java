package sambasafety.testcases;

import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.Select;
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

import io.github.bonigarcia.wdm.WebDriverManager;

//Add ExtentReport - Taking ScreenShot ONLY for Failed Tests in Selenium using TestNG Listener

public class LoginDRMTest {

	// public WebDriver driver;
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
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

		// click on Login link
		WebElement login = driver.findElement(By.xpath("//div[@id='header-bar']//a[contains(text(),'Login')]"));
		JavaScriptExecutorUltil.flashByJS(login, driver); // highlight the element
		driver.findElement(By.xpath("//div[@id='header-bar']//a[contains(text(),'Login')]")).click(); // click on Login link

		// Find element and store in variable "Element" > click on DRM to log in
		WebElement DRM = driver.findElement(By.xpath("//h3[contains(text(),'DRM')]"));
		 
		JavaScriptExecutorUltil.ScrollByPixel(driver); // Scroll vertically down by 500 pixels		
		JavaScriptExecutorUltil.flashByJS(DRM, driver); // highlight the element

		driver.findElement(By.xpath("//h3[contains(text(),'DRM')]")).click();
		CommonUtil.LognWait(); // Thread.sleep(10000);
	
	}

	@Test(priority = 1, enabled = true)
	public void LoginDriverMonitorTest() {
		
		extentTest = extent.startTest("LoginDriverMonitorTest");

		// Data Driven Approach (parameterization):
		// get data from excel sheet - https://www.youtube.com/watch?v=sSNqjNzaP6s -
		// 2a. create object class
		// 2b import 'Xls_Reader class) 'import naveen.excel.utility.Xls_Reader;'
		// 2c. give the path of excel sheet

		// 2a. get connection to excel file

		String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\testdata\\SambaSafety_testdata.xlsx";
		System.out.println("TESTDATA_SHEET_PATH: " + TESTDATA_SHEET_PATH);

		Xls_ReaderUltil reader = new Xls_ReaderUltil(TESTDATA_SHEET_PATH);

		int rowCount = reader.getRowCount("LoginDRM");
		System.out.println("Total rowCount present in LoginDRM:" + rowCount);

		// Add pass/fail column and write data to excel sheet - part 2
		// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

		// Add column to excel sheet before for loop

		reader.addColumn("LoginDRM", "Test Status"); 
		reader.addColumn("LoginDRM", "TimeStamp");

		// read the data from excel file
		// reader.getCellData(sheetName, colNum, rowNum)

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("======================");

			String UserID = reader.getCellData("LoginDRM", "UserID", rowNum);
			System.out.println(UserID);

			String Password = reader.getCellData("LoginDRM", "Password", rowNum);
			System.out.println(Password);

			// 3. clear text box > enter data
			/*
			 * driver.findElement(By.xpath("//div[@id='loginArea']//input[@id='username']"))
			 * .clear();
			 * driver.findElement(By.xpath("//div[@id='loginArea']//input[@id='username']"))
			 * .sendKeys(UserID);
			 * 
			 * driver.findElement(By.xpath(
			 * "//div[@id='loginArea']//div[@class='well']//input[@id='password']")).clear()
			 * ; driver.findElement(By.xpath(
			 * "//div[@id='loginArea']//div[@class='well']//input[@id='password']")).
			 * sendKeys(Password);
			 */
			// write the data into a cell
			// Reader.setCellData() method - write particular data to the sheetName,colName,
			// rowNum, data (3:40)
			// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

			reader.setCellData("LoginDRM", "Test Status", rowNum, "Pass");

			String dateName = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
			reader.setCellData("LoginDRM", "TimeStamp", rowNum, dateName);

			// add javascript flash before click on submit
			WebElement submitBtn = driver.findElement(By.xpath("//input[@id='submit']"));

			JavaScriptExecutorUltil.flashByJS(submitBtn, driver);
			// driver.findElement(By.xpath("//input[@id='goBtn']")).submit();

		}

		// add tear down method and capture failed test
		// driver.close();

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

			String screenshotPath = HomePageTest.getScreenshot(driver, result.getName());
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

	// getScreenshot
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		return destination;
	}

}
