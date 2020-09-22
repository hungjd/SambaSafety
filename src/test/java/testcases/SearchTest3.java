package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.javaScriptExecutor.JavaScriptExecutorUltil;

import io.github.bonigarcia.wdm.WebDriverManager;

// http://www.automationfraternity.com/selenium/automation-test-script-to-validate-search-functionality-for-ecommerce-websiteingrammicro/

public class SearchTest3 {

	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		// Browser Set Up and navigate
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		// driver.get("https://sambasafety.com");
		driver.get("https://ca.ingrammicro.com");// difference btw and naviogate/get

		// flash accept cookie button
		// WebElement AcceptCookiesBtn =
		// driver.findElement(By.xpath("//button[@class='accept']"));
		// JavaScriptExecutorUltil.flashByJS(AcceptCookiesBtn, driver); // highlight the
		// element
		// driver.findElement(By.xpath("//button[@class='accept']")).click(); // click
		// on accept cookies

		// To scroll down the web page at the bottom of the page.
		// JavaScriptExecutorUltil.scrollPageDownByJS(driver);

	}

	@Test
	public void ValidateSearch() {

		try {
			String PRODUCT = "HP";

			// Checkpoint
			/*
			 * String expected_title =
			 * "Computer and Technology Products - Services for Business to Business Needs - Ingram Micro"
			 * ; String actual_title = driver.getTitle(); Assert.assertEquals(actual_title,
			 * expected_title, "Title is correct");
			 * 
			 */
			// Search for item
			WebElement txtbx_search = driver.findElement(By.id("searchBox_Global_v2"));
			// txtbx_search.sendKeys(PRODUCT);
			txtbx_search.sendKeys(PRODUCT, Keys.ENTER);

			// driver.findElement(By.id("search-submit-anchor")).click();
			// driver.findElement(By.id("ap_email")).sendKeys("Username@gmail.com",
			// Keys.ENTER);

			// Check point
			/*
			 * expected_title = "Product Search"; actual_title = driver.getTitle();
			 * Assert.assertEquals(actual_title, expected_title,
			 * "Product search Title is correct");
			 * 
			 */
			// a[contains(@rel,'#product-title-' )]

			// Fetch all the links Title
			List<WebElement> collection_product_links = driver
					.findElements(By.xpath("//a[contains(@rel,'#product-title-')]"));

			// Validate if Search result is displayed corresponding
			// to the string which was searched
			for (int i = 0; i < collection_product_links.size(); i++) {
				String temp = collection_product_links.get(i).getText();

				if ((temp.toLowerCase().contains(PRODUCT.toLowerCase()))) {
					Assert.assertTrue(true, PRODUCT + " is displayed on product title Product Title: " + temp);
				} else {
					Assert.assertTrue(false, PRODUCT + " is not displayed on product title Product Title: " + temp);

				}

			}
			//// div[@class = 'prod-number-container vpn_breakword']/span[2]/
		} catch (Exception e) {
			Assert.assertFalse(false, "Exception thrown. Exception: " + e.toString());
			;
		}
	}
}
