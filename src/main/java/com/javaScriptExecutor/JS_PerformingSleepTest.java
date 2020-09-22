package com.javaScriptExecutor;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

// https://www.guru99.com/execute-javascript-selenium-webdriver.html
// Example 1: Performing a sleep in the browser under test.


public class JS_PerformingSleepTest {

	@Test
	public void Login() {

		System.setProperty("webdriver.gecko.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launching the Site.
		driver.get("http://demo.guru99.com/V4/");

		// Maximize window
		driver.manage().window().maximize();

		// Set the Script Timeout to 20 seconds
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

		// Declare and set the start time
		long start_time = System.currentTimeMillis();

		// Call executeAsyncScript() method to wait for 5 seconds
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 5000);");

		// Get the difference (currentTime - startTime) of times.
		System.out.println("Passed time: " + (System.currentTimeMillis() - start_time));

	}
}