package com.datadriven.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.util.Xls_ReaderUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

// how to fetch data from web table rows and columns and write data into Excel sheet.
// https://www.youtube.com/watch?v=s2anoT8GHKE&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=5

public class DynamicWebTableHandleTest {

	public static void main(String[] args) {
		// launch browser

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * "C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe"
		 * ); WebDriver driver = new FirefoxDriver();
		 */

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driver.get("https://www.w3schools.com/html/html_tables.asp");

		// 1. Inspect elements > click on <td> right mouse, copy xpath
		// 2. Find the pattern

		// Company - column
		// *[@id="customers"]/tbody/tr[2]/td[1]
		// *[@id="customers"]/tbody/tr[3]/td[1]
		// *[@id="customers"]/tbody/tr[4]/td[1]
		// *[@id="customers"]/tbody/tr[5]/td[1]

		// Contact - column
		// *[@id="customers"]/tbody/tr[2]/td[2]
		// *[@id="customers"]/tbody/tr[3]/td[2]
		// *[@id="customers"]/tbody/tr[4]/td[2]
		// *[@id="customers"]/tbody/tr[5]/td[2]

		// Country
		// *[@id="customers"]/tbody/tr[2]/td[3]
		// *[@id="customers"]/tbody/tr[3]/td[3]
		// *[@id="customers"]/tbody/tr[4]/td[3]
		// *[@id="customers"]/tbody/tr[5]/td[3]

		// 3. Break beforeXpath and afterXpath

		String beforeXpath_company = "//*[@id='customers']/tbody/tr[";
		String afterXpath_company = "]/td[1]";

		String beforeXpath_contact = "//*[@id='customers']/tbody/tr[";
		String afterXpath_contact = "]/td[2]";

		String beforeXpath_country = "//*[@id='customers']/tbody/tr[";
		String afterXpath_country = "]/td[3]";

		// 4. find number of rows in the table
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));
		System.out.println("total number of row = " + (rows.size() - 1)); // -1 exclude column header
		int rowCount = rows.size();

		// 6. At run time;
		// 6a. add sheetName - if sheet name exist don't add new sheetName
		// 6b. add column
		// 6c. fetch/write data to excel sheet
		
		String TESTDATA_SHEET_PATH = "C:/Users/u029jxd/OneDrive - myCWT/Documents/Selenium/selenium_tutorials/src/test/java/naveen/testdata/testdata2.xlsx";
		Xls_ReaderUltil reader = new Xls_ReaderUltil(TESTDATA_SHEET_PATH);
		
		// if sheetName exist don't add new sheetName
		if(!reader.isSheetExist("WebTable")) {
			reader.addSheet("WebTable");
			reader.addColumn("WebTable", "CompanyName");
			reader.addColumn("WebTable", "ContactName");
			reader.addColumn("WebTable", "CountryName");
		}
		

		// 5. get actual values of rows in the table
		for (int i = 2; i <= rowCount; i++) {

			System.out.println("+++++++++++++++++++");
			String actualXpath_company = beforeXpath_company + i + afterXpath_company;
			String companyName = driver.findElement(By.xpath(actualXpath_company)).getText();
			System.out.println(companyName);
			
			//6c. fetch/write data to excel sheet
			reader.setCellData("WebTable", "CompanyName", i, companyName);
			

			String actualXpath_contact = beforeXpath_contact + i + afterXpath_contact;
			String contactName = driver.findElement(By.xpath(actualXpath_contact)).getText();
			System.out.println(contactName);

			//6c. fetch/write data to excel sheet
			reader.setCellData("WebTable", "ContactName", i, contactName);
			
			
			String actualXpath_country = beforeXpath_country + i + afterXpath_country;
			String countryName = driver.findElement(By.xpath(actualXpath_country)).getText();
			System.out.println(countryName);
			
			//6c. fetch/write data to excel sheet
			reader.setCellData("WebTable", "CountryName", i, countryName);

		}

		// close browser
		driver.quit();
	}

}
