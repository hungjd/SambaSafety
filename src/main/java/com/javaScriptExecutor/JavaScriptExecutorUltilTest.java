package com.javaScriptExecutor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.javaScriptExecutor.*;

//JavaScriptExecutorConcept Test
//https://www.youtube.com/watch?v=Dpx1Q62QpFU&list=PLFGoYjJG_fqo4oVsa6l_V-_7-tzBnlulT&index=14
//https://drive.google.com/file/d/0B2i0eXr3_uQDSGY1OURlSDJLUzg/view

public class JavaScriptExecutorUltilTest {

	public static void main(String[] args) throws IOException {

		// declaration and instantiation of objects/variables
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		// dynamic wait
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("https://ui.freecrm.com"); // enter URL

		driver.findElement(By.xpath("//input[@placeholder='E-mail address']")).sendKeys("jodang@mycwt.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Carlson701");
		// driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();

		// executeScript -- to execute JavaScript code

		// WebElement loginBtn =
		// driver.findElement(By.xpath("//input[contains(@type,'submit')]")); //login
		// button
		WebElement loginBtn = driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']"));

		//flash(loginBtn, driver); // highlight the element
		JavaScriptExecutorUltil.flashByJS(loginBtn, driver);

		//drawBorder(loginBtn, driver); // draw a border
		JavaScriptExecutorUltil.drawBorderByJS(loginBtn, driver); 
		
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File(
				"Users\\u029jxd\\OneDrive - myCWT\\Documents\\Selenium\\selenium_tutorials\\src\\test\\java\\com\\javaScriptExecutor\\element.png"));

		// generate Alert
		// generateAlert(driver, "There is an issue with Login button on Login Page");
		// add code to click on alert pop up accept() or dismiss()

		// click on any element by using JS executor
		//clickElementByJS(loginBtn, driver);
		JavaScriptExecutorUltil.clickElementByJS(loginBtn, driver);

		// refresh the page:
		// 1. by using selenium:
		driver.navigate().refresh();

		// 2. by using JS executor:
		//refreshBrowserByJS(driver);
		JavaScriptExecutorUltil.refreshBrowserByJS(driver);

		// get the tile of the page by JS:
		//	System.out.println(getTitleByJS(driver));
		String Title =JavaScriptExecutorUltil.getTitleByJS(driver);
		System.out.println(Title);

		// get the page text:
		//System.out.println(getPageInnerText(driver));
		String PageInnerText =JavaScriptExecutorUltil.getPageInnerTextByJS(driver);
		System.out.println(PageInnerText);

		// scroll page down:
		// scrollPageDown(driver);

		WebElement forgotPwdLink = driver.findElement(By.xpath("//a[contains(text(),'Forgot Password?')]"));
		//scrollIntoView(forgotPwdLink, driver);
		JavaScriptExecutorUltil.scrollIntoViewByJS(forgotPwdLink, driver);
	}

	
}