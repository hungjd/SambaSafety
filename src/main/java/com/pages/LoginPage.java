package com.pages;

import java.awt.desktop.SystemSleepEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;

public class LoginPage extends TestBase {

	// Initializing the Page Objects Factory / object repository:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (38:45) - (46:00)
	// this driver come from TestBase class
	// all variable (@FindBy ...) will be initialize with this driver

	// https://freecrm.com/ > Log In link
	@FindBy(xpath = "//a[contains(text(),'Log In')]")
	WebElement logInLink;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpLink;

	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

	// https://ui.freecrm.com
	@FindBy(xpath = "//input[@placeholder='E-mail address']")
	WebElement username;

	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;

	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement loginBtn;

	// create PageFactory constructor of this class
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions - different features of the page :
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (47:00)

	// validate LoginPageTitle
	public String LoginPageTitle() {
		return driver.getTitle(); /* this method return title of the page */
	}

	// validate crm image
	public boolean CRMLogoImage() {
		return crmLogo.isDisplayed(); /* return true or false */
	}

	// validate LoginLinkDisplay
	public boolean LoginLinkDisplay() {
		return logInLink.isDisplayed(); /* return true or false */
	}

	// validate LoginUser
	public HomePage LoginUser(String un, String pwd) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", logInLink);

			// enter user name/email and pw
			username.sendKeys(un);
			password.sendKeys(pwd);

			// loginBtn.click();
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].click();", loginBtn);

		} catch (Exception e) {
			System.out.println("Something went wrong.");

		}
		
		return new HomePage(); /* return HomePage object */
		
	} // end Exception handling
	
}// end LoginUser

