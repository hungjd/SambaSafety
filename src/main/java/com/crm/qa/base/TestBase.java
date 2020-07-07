package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop; /* global variable can be use in child classes, test classes */
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	// https://www.youtube.com/watch?v=LxJzeiTQGoE 
	//create TestBase constructor (25:20)
	public TestBase(){
		try {
			prop = new Properties();
			
			//  How to read properties file https://www.youtube.com/watch?v=0XowYwfvbo8
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/crm"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (27:49)
	// read browser property
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			// System.setProperty("webdriver.chrome.driver", "/Users/naveenkhunteta/Downloads/chromedriver");
			System.setProperty ("webdriver.chrome.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			//System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
			System.setProperty ("webdriver.gecko.driver","C:\\Users\\u029jxd\\Documents\\Selenium\\softwares\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		// https://www.youtube.com/watch?v=LxJzeiTQGoE (32:00)
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		//TestUtil. come from TestUtil.java class
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		// https://www.youtube.com/watch?v=LxJzeiTQGoE 
		// launch url from config.properties
		driver.get(prop.getProperty("url"));
		
	}
	
	
	
	
	
	
	
	

}
