package com.javaScriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.javaScriptExecutor.*;

//https://www.guru99.com/execute-javascript-selenium-webdriver.html
//Example: Click a button to login and generate Alert window using JavaScriptExecutor.


public class JS_GenerateAlertTest {

	@Test
	public void Login() {
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		// Launching the Site.
		driver.get("http://demo.guru99.com/V4/");
		
		// Login to Guru99
		driver.findElement(By.name("uid")).sendKeys("mngr34926");
		driver.findElement(By.name("password")).sendKeys("amUpenu");

		
		//clickElementByJS(loginBtn, driver);
		WebElement button = driver.findElement(By.name("btnLogin"));
		JavaScriptExecutorUltil.clickElementByJS(button, driver);
		
		// Creating the JavascriptExecutor interface object by Type casting
		//JavascriptExecutor js = (JavascriptExecutor) driver;

		// Perform Click on LOGIN button using JavascriptExecutor
		//js.executeScript("arguments[0].click();", button);

		// To generate Alert window using JavascriptExecutor. Display the alert message
		//js.executeScript("alert('Welcome to Guru99');");

	}
}