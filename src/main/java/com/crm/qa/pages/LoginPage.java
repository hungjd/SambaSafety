package com.crm.qa.pages;

import java.awt.desktop.SystemSleepEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	// Page Factory - OR / object repository:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (38:45)
	// define page library

	// https://freecrm.com/ > Log In link
	@FindBy(xpath = "//a[contains(text(),'Log In')]")
	WebElement logInLink;

	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpLink;

	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;

	// https://ui.freecrm.com
	
	//@FindBy(name = "email") // locator
	@FindBy(xpath = "//input[@placeholder='E-mail address']")
	WebElement username; // define variable name

	//@FindBy(name = "password")
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;

	// @FindBy(className = "ui fluid large blue submit button")
	@FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
	WebElement loginBtn;

	// Initializing the Page Objects:
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (46:00)
	// create PageFactory constructor of this class
	// this driver come from TestBase class
	// all variable (@FindBy ...) will be initialize with this driver
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions - different features of the page :
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (47:00)

	// validate validateLoginPageTitle
	public String validateLoginPageTitle() {
		return driver.getTitle(); /* this method return title of the page */
	}

	// validate crm image
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed(); /* return true or false */
	}

	// validate log in link
	public boolean validateLoginLink() {
		return logInLink.isDisplayed();

		// add condition if sign in link display then click on url
	}

	public HomePage login(String un, String pwd) {

		// re-direct to https://ui.freecrm.com
		//logInLink.click();
		
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].click();", logInLink);
		 
		// enter user name/email and pw
		username.sendKeys(un); 
		password.sendKeys(pwd);  
		
		// loginBtn.click();
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("arguments[0].click();", loginBtn);

		return new HomePage(); /*return HomePage object */
	}

}
