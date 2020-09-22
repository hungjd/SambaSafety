package sambasafety.testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

// Find Broken / Invalid Images on a Page
// https://www.seleniumeasy.com/selenium-tutorials/find-broken-images-in-a-webpage-using-webdriver-java

public class FindBrokenImagesTest {

	private WebDriver driver;
	private int invalidImageCount;

	@BeforeMethod
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get("https:sambasafety.com");
		driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on accept cookies
	}

	// TC1: InvalidImagesTest
	@Test (priority = 1)
	public void BrokenImagesTest() {
		
		try {
			invalidImageCount = 0;
			
			//find all images on the page by using Webdriver
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of images are " + imagesList.size());
			
			// Now iterate through each image and verify response code with HttpStatus and it 
			//should be 200 if not, increment invalid images count.
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyImageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	
	// verifyImageActive
	public void verifyImageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}