package com.datadriven.test;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.util.Xls_ReaderUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

// Data Driven Framework in Selenium WebDriver(Using Apache POI) - Part 1
// https://www.youtube.com/watch?v=sSNqjNzaP6s

public class ExcelDataDrivenTest {

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

		// handle pop up or iframe ads
		// for (String winhandle: driver.getWindowHandles()) {
		// driver.switchTo().window(winhandle);
		// System.out.println("Window Switch");
		// Thread.sleep(2000);

		// driver.findElement(By.xpath("(//button[span[contains(text(),'closeBtn')]])[1]")).click();
		// driver.findElement(By.xpath("//*[@id="closeBtn"]/a/img")).click();
		// }

		// Data Driven Approach (parameterization)

		// 2. get data from excel sheet - https://www.youtube.com/watch?v=sSNqjNzaP6s -
		// part 1
		// 2a. create object class
		// 2b import 'Xls_Reader class) 'import naveen.excel.utility.Xls_Reader;'
		// 2c. give the path of excel sheet

		// 2a. get connection to excel file
		String TESTDATA_SHEET_PATH = "C:\\Users\\u029jxd\\Documents\\Selenium\\testData\\testdata1.xlsx";

		Xls_ReaderUltil reader = new Xls_ReaderUltil(TESTDATA_SHEET_PATH);

		int rowCount = reader.getRowCount("RegTestData");
		System.out.println("Total rowCount present in RegTestData:" + rowCount);

		// Add pass/fail column and write data to excel sheet - part 2
		// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

		// Add column to excel sheet before for loop
		reader.addColumn("RegTestData", "Status");

		// read the data from excel file
		// reader.getCellData(sheetName, colNum, rowNum)

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			System.out.println("======================");

			String Title = reader.getCellData("RegTestData", "Title", rowNum);
			System.out.println(Title);

			String FirstName = reader.getCellData("RegTestData", "FirstName", rowNum);
			System.out.println(FirstName);

			String LastName = reader.getCellData("RegTestData", "LastName", rowNum);
			System.out.println(LastName);

			String Phone = reader.getCellData("RegTestData", "Phone", rowNum);
			System.out.println(Phone);

			String Email = reader.getCellData("RegTestData", "Email", rowNum);
			System.out.println(Email);

			String Address = reader.getCellData("RegTestData", "Address", rowNum);
			System.out.println(Address);

			String City = reader.getCellData("RegTestData", "City", rowNum);
			System.out.println(City);

			String StateProvince = reader.getCellData("RegTestData", "StateProvince", rowNum);
			System.out.println(StateProvince);

			String PostalCode = reader.getCellData("RegTestData", "PostalCode", rowNum);
			System.out.println(PostalCode);

			String Country = reader.getCellData("RegTestData", "Country", rowNum);
			System.out.println(Country);

			String UserName = reader.getCellData("RegTestData", "UserName", rowNum);
			System.out.println(UserName);

			String Password = reader.getCellData("RegTestData", "Password", rowNum);
			System.out.println(Password);

			String ConfirmPassword = reader.getCellData("RegTestData", "ConfirmPassword", rowNum);
			System.out.println(ConfirmPassword);

			// 3. clear text box > enter data

			// Contact Information
			driver.findElement(By.xpath("//input[@name='firstName']")).clear();
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);

			driver.findElement(By.xpath("//input[@name='lastName']")).clear();
			driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);

			driver.findElement(By.xpath("//input[@name='phone']")).clear();
			driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(Phone);

			driver.findElement(By.xpath("//input[@id='userName']")).clear();
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(Email);

			// Mailing Information
			driver.findElement(By.xpath("//input[@name='address1']")).clear();
			driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(Address);

			driver.findElement(By.xpath("//input[@name='city']")).clear();
			driver.findElement(By.xpath("//input[@name='city']")).sendKeys(City);

			driver.findElement(By.xpath("//input[@name='state']")).clear();
			driver.findElement(By.xpath("//input[@name='state']")).sendKeys(StateProvince);

			driver.findElement(By.xpath("//input[@name='postalCode']")).clear();
			driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(PostalCode);

			Select select = new Select(driver.findElement(By.xpath("//select[@name='country']")));
			select.selectByVisibleText(Country);

			// User Information
			driver.findElement(By.xpath("//input[@id='email']")).clear();
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys(UserName);

			driver.findElement(By.xpath("//input[@name='password']")).clear();
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);

			driver.findElement(By.xpath("//input[@name='confirmPassword']")).clear();
			driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(ConfirmPassword);

			// Reader.setCellData() method - write particular data to the sheetName,
			// colName, rowNum, data (3:40)
			// <https://www.youtube.com/watch?v=7evjY7tqwJc&list=PLFGoYjJG_fqqlW6swKwutBOVU2O8k_JHT&index=2>

			reader.setCellData("RegTestData", "Status", rowNum, "Pass");

			// driver.findElement(By.xpath("//input[@name='submit']")).submit();

		}
		driver.close();
	}

}
