package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.util.TestUtil;
import com.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop; /* global variable can be use in child classes, test classes */
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// create TestBase constructor (25:20)
	// https://www.youtube.com/watch?v=LxJzeiTQGoE
	public TestBase() {
		try {
			prop = new Properties();

			// How to read properties file https://www.youtube.com/watch?v=0XowYwfvbo8
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\config\\config.properties");	
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end TestBase

	// read browser property
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (27:49)
	public static void initialization() {

		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			// System.setProperty
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", "\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe");	
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			//WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// add WebdDriver Fire Event - to generate selenium actions logs 
		//https://www.youtube.com/watch?v=H2-3w-GQZ3g (46:01)
		
		e_driver = new EventFiringWebDriver(driver);//create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		// maximize wnidow and deleteAllCookies
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		// TestUtil. come from TestUtil.java class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		// launch url from config.properties > update get url from testng.xml or excel file
		driver.get(prop.getProperty("url"));
		
		// accept cookies
		driver.findElement(By.xpath("//button[@class='accept']")).click();
		

	}
}


