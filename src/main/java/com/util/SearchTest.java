package com.util;

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

public class SearchTest {

	public WebDriver driver;

	@BeforeMethod
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

	// TestCase 1: validate ValidateSearch
	@Test(priority = 1, enabled = true)
	public void SearchTest() {

		try {
			String searchfield = "insurance";

			// Search for item
			WebElement txtbx_search = driver.findElement(By.xpath("//div[@id='header']//input[@id='s']"));
			// txtbx_search.sendKeys(PRODUCT);
			txtbx_search.sendKeys(searchfield, Keys.ENTER);

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

			// List<WebElement> collection_product_links =
			// driver.findElements(By.xpath("//a[contains(@rel,'#product-title-')]"));

			List<WebElement> collection_product_links = driver
					.findElements(By.xpath("//h2[contains(text(),'Search Results for: insurance')]"));

			// Validate if Search result is displayed corresponding
			// to the string which was searched
			for (int i = 0; i < collection_product_links.size(); i++) {
				String temp = collection_product_links.get(i).getText();

				if ((temp.toLowerCase().contains(searchfield.toLowerCase()))) {
					Assert.assertTrue(true, searchfield + " is displayed on product title Product Title: " + temp);
				} else {
					Assert.assertTrue(false, searchfield + " is not displayed on product title Product Title: " + temp);

				}

			}

		} catch (Exception e) {
			Assert.assertFalse(false, "Exception thrown. Exception: " + e.toString());
			;
		}
	}
}
