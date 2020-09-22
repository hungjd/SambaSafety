package sambasafety.testcases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

// find-broken-links-selenium-webdriver.html
//For checking the broken links, you will need to do the following steps.
//Collect all the links in the web page based on <a> tag.
//Send HTTP request for the link and read HTTP response code.
//Find out whether the link is valid or broken based on HTTP response code.
//Repeat this for all the links captured.


//Add ExtentReport - Taking ScreenShot ONLY for Failed Tests in Selenium using TestNG Listener


public class FindBrokenLinksTest {

	private static WebDriver driver = null;
	// private WebDriver driver;

	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	String homePage = "https://sambasafety.com";

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
	@Test(priority = 1)
	public void BrokenLinkTest() {

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if (!url.startsWith(homePage)) {
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

}