package com.javaScriptExecutor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.javaScriptExecutor.*;

public class JS_SelectCalendarTest {

	public static void main(String[] args) {

		System.setProperty ("webdriver.chrome.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); // launch chrome

		driver.manage().window().maximize(); // maximize window
		driver.manage().deleteAllCookies(); // delete all the cookies

		//dynamic wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("http://spicejet.com/"); // enter URL
		
		// WebElement date = driver.findElement(By.id("ctl00_mainContent_txt_Fromdate"));
		WebElement date = driver.findElement(By.xpath("//input[@id='ctl00_mainContent_view_date1']"));
		String dateVal = "30-08-2020";
		
		// invoke selectDateByJS () from com.javaScriptExecutor 
		//selectDateByJS(driver, date, dateVal);
		JavaScriptExecutorUltil.selectDateByJS(driver, date, dateVal);
		
	}
	
	
//	public static void selectDateByJS(WebDriver driver, WebElement element, String dateVal){
//    	JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
//		
//	}
	
	
	
	

}
