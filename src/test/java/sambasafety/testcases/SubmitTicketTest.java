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

import com.ExtentReportListener.ExtentReportUtil;
import com.javaScriptExecutor.JavaScriptExecutorUltil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.Xls_ReaderUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

//Add ExtentReport - Taking ScreenShot ONLY for Failed Tests in Selenium using TestNG Listener

public class SubmitTicketTest {

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

	// TestCase 1: validate SubmitATicketTest
	@Test(priority = 1, enabled = true)
	public void SubmitATicketTest() {
		
		extentTest = extent.startTest("SubmitATicketTest");
	
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

		int rowCount = reader.getRowCount("CustomerSupport");
		System.out.println("Total rowCount present in CustomerSupport:" + rowCount);

		// Add pass/fail column and write data to excel sheet - part 2
		// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

		// Add column to excel sheet before for loop else override existing data
		//reader.addColumn("CustomerSupport", "Status");
		//reader.addColumn("CustomerSupport", "TimeStamp");

		// read the data from excel file
		// reader.getCellData(sheetName, colNum, rowNum)

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("======================");

			String FirstName = reader.getCellData("CustomerSupport", "FirstName", rowNum);
			System.out.println(FirstName);

			String LastName = reader.getCellData("CustomerSupport", "LastName", rowNum);
			System.out.println(LastName);

			String Email = reader.getCellData("CustomerSupport", "Email", rowNum);
			System.out.println(Email);

			String Phone = reader.getCellData("CustomerSupport", "Phone", rowNum);
			System.out.println(Phone);

			String Product = reader.getCellData("CustomerSupport", "Product", rowNum);
			System.out.println(Product);

			String Subject = reader.getCellData("CustomerSupport", "Subject", rowNum);
			System.out.println(Subject);

			String Description = reader.getCellData("CustomerSupport", "Description", rowNum);
			System.out.println(Description);

			// 3. clear text box > enter data
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='First_Name_Form__c']"))
					.clear();
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='First_Name_Form__c']"))
					.sendKeys(FirstName);

			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Last_Name_Form__c']"))
					.clear();
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Last_Name_Form__c']"))
					.sendKeys(LastName);

			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Email_Form__c']")).clear();
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Email_Form__c']"))
					.sendKeys(Email);

			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Phone_Form__c']")).clear();
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Phone_Form__c']"))
					.sendKeys(Phone);

			// Select select = new
			// Select(driver.findElement(By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Product__c']")));
			// select.selectByVisibleText(Product);

			driver.findElement(By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Subject']"))
					.clear();
			driver.findElement(By.xpath("//div[@class='forceCommunityContactSupportForm']//input[@name='Subject']"))
					.sendKeys(Subject);

			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//textarea[@name='Description']"))
					.clear();
			driver.findElement(
					By.xpath("//div[@class='forceCommunityContactSupportForm']//textarea[@name='Description']"))
					.sendKeys(Description);

			//write the data into a cell
			// Reader.setCellData() method - write particular data to the sheetName,colName, rowNum, data (3:40)
			// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

			reader.setCellData("CustomerSupport", "Status", rowNum, "Pass");

			String dateName = new SimpleDateFormat("dd-MM-yyyy hh:mm:s").format(new Date());
			reader.setCellData("CustomerSupport", "TimeStamp", rowNum, dateName);

			// add javascript flash before click on submit
			WebElement submitBtn = driver.findElement(By.xpath("//div[@id='submitButton']"));
			JavaScriptExecutorUltil.flashByJS(submitBtn, driver); //flash(loginBtn, driver); // highlight the element
		
			//driver.findElement(By.xpath("//div[@id='submitButton']")).submit();

		}

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
