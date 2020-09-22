package sambasafety.testcases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

import com.ExtentReportListener.ExtentReportUtil;
import com.javaScriptExecutor.JavaScriptExecutorUltil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.util.CommonUtil;
import com.util.Xls_ReaderUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

//http://www.automationfraternity.com/selenium/automation-test-script-to-validate-search-functionality-for-ecommerce-websiteingrammicro/

public class HomePageSearchTest {

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
	}

	// TestCase 1: validate ValidateSearch
	@Test(priority = 1, enabled = true)
	public void SearchTest() {

		// static search
		//String SearchCriteria = "insurance";

		// dynamic search
		String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\testdata\\SambaSafety_testdata.xlsx";
		System.out.println("TESTDATA_SHEET_PATH: " + TESTDATA_SHEET_PATH);

		Xls_ReaderUltil reader = new Xls_ReaderUltil(TESTDATA_SHEET_PATH);

		int rowCount = reader.getRowCount("Search");
		System.out.println("Total rowCount present in Search test data:" + rowCount);

		// Add column to excel sheet before for loop else override existing data
		//reader.addColumn("Search", "Status");
		//reader.addColumn("Search", "TimeStamp");

		// read the data from excel file
		// reader.getCellData(sheetName, colNum, rowNum)

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("======================");

			String SearchCriteria = reader.getCellData("Search", "SearchCriteria", rowNum);
			System.out.println("Search:" + SearchCriteria);

			// 3. clear text box > enter data
			WebElement element = driver.findElement(By.xpath("//div[@id='header']//input[@id='s']"));
			//JavaScriptExecutorUltil.flashByJS(element, driver); // highlight the element

			// Search for item
			driver.findElement(By.xpath("//div[@id='header']//input[@id='s']")).clear();
			WebElement txtbx_search = driver.findElement(By.xpath("//div[@id='header']//input[@id='s']"));
			txtbx_search.sendKeys(SearchCriteria, Keys.ENTER);
			
			CommonUtil.ShortWait(); // Thread.sleep(6000)
			
			//write the data into a cell
			// Reader.setCellData() method - write particular data to the sheetName,colName, rowNum, data (3:40)
			// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

			reader.setCellData("Search", "Status", rowNum, "Pass");

			String dateName = new SimpleDateFormat("dd-MM-yyyy hh:mm:s").format(new Date());
			reader.setCellData("Search", "TimeStamp", rowNum, dateName);
			
			// validate search result: compare expected vs actual result
			// Fetch all the links Title

			// List<WebElement> collection_product_links =
			// driver.findElements(By.xpath("//a[contains(@rel,'#product-title-')]"));

			// List<WebElement> collection_product_links =
			// driver.findElements(By.xpath("//h2[contains(text(),'Search Results for:
			// insurance')]"));

			// Validate if Search result is displayed corresponding
			// to the string which was searched
			// for (int i = 0; i < collection_product_links.size(); i++) {
			// String temp = collection_product_links.get(i).getText();

			// if ((temp.toLowerCase().contains(SearchCriteria.toLowerCase()))) {
			// Assert.assertTrue(true, SearchCriteria + " is displayed on product title
			// Product Title: " + temp);
			// } else {
			// Assert.assertTrue(false, SearchCriteria + " is not displayed on product title
			// Product Title: " + temp);

			// }

			// }

//		} catch (Exception e) {
//			Assert.assertFalse(false, "Exception thrown. Exception: " + e.toString());
//			;
//		}
			// }

		}

	}
	
	@AfterTest
//	public void endReport() {
//		extent.flush();
//		extent.close();
//	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
