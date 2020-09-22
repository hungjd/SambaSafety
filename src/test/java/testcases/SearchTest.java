package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.javaScriptExecutor.JavaScriptExecutorUltil;
import com.util.CommonUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {
	
	public static WebDriver driver;
	
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("https://sambasafety.com");

		// flash accept cookie button
		WebElement AcceptCookiesBtn = driver.findElement(By.xpath("//button[@class='accept']"));
		JavaScriptExecutorUltil.flashByJS(AcceptCookiesBtn, driver); // highlight the element
		driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on accept cookies


	}
	
	
	public static void search ( ) {
	    // First, let's declare our search term
	    String searchTerm = "Selenium";

	    // Next, we'll execute the search
	    WebElement searchField = driver.findElement(By.name("q"));
		//driver.findElement(By.xpath("//div[@id='header']//input[@id='s']")).click();
	 
		
		 WebElement searchField = driver.findElement(By.xpath("//div[@id='header']//input[@id='s']")).sendKeys(searchTerm, Keys.ENTER);
		CommonUtil.MediumWait();
		
	    searchField.sendKeys(searchTerm);
	    WebElement searchButton = driver.findElement(By.name("btnK"));
	    searchButton.click();

	    // Now, let's gather our search results
	    List<WebElement> results = driver.findElements(By.cssSelector(".r"));

	    // Finally, we'll loop over the list to verify each result link contains our term
	    for (int i = 0; i < results.size(); i++) {
	        Assert.assertTrue(results.get(i).getText().contains(searchTerm), "Search result validation failed at instance [ + i + ].");
	    }
	}

}
