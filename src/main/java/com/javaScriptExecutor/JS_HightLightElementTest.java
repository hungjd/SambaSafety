package com.javaScriptExecutor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

// Highlight elements using JavaScriptExecutor
// From <https://www.youtube.com/watch?v=PGPlL0zP7Ik&list=PLFGoYjJG_fqo4oVsa6l_V-_7-tzBnlulT&index=13> 
// https://www.guru99.com/execute-javascript-selenium-webdriver.html

public class JS_HightLightElementTest {

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		// launch url
		driver.get("https://ui.freecrm.com/");
		
		//enter userID and password
		driver.findElement(By.xpath("//input[@placeholder='E-mail address']")).sendKeys("jodang@mycwt.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Carlson701");
		
		// click on Login button
		WebElement loginBtn = driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);

	}

	// Highlight elements using JavaScriptExecutor
	public static void flashByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgcolor = element.getCssValue("backgroungColor");

		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element, driver); // highlight yellow 252,182,3
			changeColor(bgcolor, element, driver);
		}
	}

	// executeScript - to execute JavaScript code
	public static void changeColor(String color, WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("argument[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {

		}

	}

}
