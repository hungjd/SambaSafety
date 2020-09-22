package com.datadriven.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.util.Xls_ReaderUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelOperations {

	public WebDriver driver;
	
	public static void main(String[] args) {

		// 1. launch browser

		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe"
		 * ); WebDriver driver = new ChromeDriver();
		 */
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/test/newtours/register.php");

		// Data Driven Approach (parameterization)

		// 2. get data from excel sheet - https://www.youtube.com/watch?v=sSNqjNzaP6s
		// -part 1
		// 2a. create object class
		// 2b import 'Xls_Reader class) 'import naveen.excel.utility.Xls_Reader;'
		// 2c. give the path of excel sheet

		// 2a. get connection to excel file
		//String TESTDATA_SHEET_PATH = "C:/Users/u029jxd/OneDrive - myCWT/Documents/Selenium/selenium_tutorials/src/test/java/naveen/testdata/testdata1.xlsx";
		String TESTDATA_SHEET_PATH = "C:\\Users\\u029jxd\\Documents\\Selenium\\testData\\testdata1.xlsx";
		
		Xls_ReaderUltil reader = new Xls_ReaderUltil(TESTDATA_SHEET_PATH);

		// isSheetExist() method check if sheet "HomePage" is not exist then addSheet
		// (13:00)
		// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

		if (!reader.isSheetExist("HomePage")) {
			reader.addSheet("HomePage");
		}

		// getRowCount() in the sheet
		int rowCount = reader.getRowCount("RegTestData");
		System.out.println("Total rowCount present in RegTestData:" + rowCount);

		// getColumCount() in the sheet
		int colCount = reader.getColumnCount("RegTestData");
		System.out.println("Total colCount present in RegTestData:" + colCount);

		// getCellRowNum() - get the exact rowNum value (17:00)

		int cellRowNum = reader.getCellRowNum("RegTestData", "FirstName", "A");
		System.out.println("FirstName A - cellRowNum in RegTestData:" + cellRowNum);

	}

}
