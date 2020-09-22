package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertPopUpHandle {

	public WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get("https:sambasafety.com");
		driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on accept cookies
		
		//driver.findElement(By.xpath("//div[@class='cookies-notice']"));
		
		//driver.findElement(By.xpath("//p[contains(text(),'Hi there! We want to let you know that our site us')]"));

		//Thread.sleep(5000);

		//Alert alert = driver.switchTo().alert();

		//System.out.println(alert.getText());

		//String text = alert.getText();

		//if (text.equals("Hi there! We want to let you know that our site us")) {

			//driver.findElement(By.xpath("//button[@class='accept']")).click(); // click on Go btn

			//System.out.println("correct alert messg");

		//} else {
			//System.out.println("in-correct alert messg");
		//}

		// alert.accept(); //click on OK btn

		// alert.dismiss(); //click on cancel btn

		// @Test(priority = 1)
		// public void HomePageTitleTest() {
		// SoftAssert softAssert1 = new SoftAssert();

		// driver.getTitle();

		//SoftAssert softAssert1 = new SoftAssert();
		// String ActualTitle = homePage.HomePageTitle();

		String ActualTitle = driver.getTitle();
		String ExpectedTitle = "Continuous MVR Monitoring & Driver Risk Management Software";

		System.out.println("ActualHomePageTitle: " + ActualTitle);
		System.out.println("ExpectedHomePageTitle: " + ExpectedTitle);

		//Assert.assertEquals(actual, expected, message);
		Assert.assertEquals(ActualTitle, ExpectedTitle, "HomePageTitle is not match - Test Failed");

		//softAssert1.assertAll();

		driver.quit();

	}

}
