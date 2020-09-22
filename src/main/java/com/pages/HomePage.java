package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;
import com.util.*;

// https://www.youtube.com/watch?v=ea0P7MBQmiM
//https://www.youtube.com/watch?v=LxJzeiTQGoE

public class HomePage extends TestBase {
	
	 WebDriver driver;

	// Initializing the Page Objects Factory / object repository:
	// all variable (@FindBy ...) will be initialize with this driver
	
	@FindBy(xpath = "//span[@class='user-display']")
	@CacheLookup
	WebElement userNameLabel;
	
	@FindBy(xpath = "//span[contains(text(),'Calendar')]")
	WebElement calendarLink;
	
	@FindBy(xpath = "//span[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//span[contains(text(),'Companies')]")
	WebElement companyLink;
	
	@FindBy(xpath = "//span[contains(text(),'Deals')]")
	WebElement dealsLink;

	@FindBy(xpath = "//span[contains(text(),'Tasks')]")
	WebElement tasksLink;


	 // Initialize HomePage constructor 
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions - different features of the page :
	// https://www.youtube.com/watch?v=LxJzeiTQGoE (47:00)
	
	// TestCase 1: validate HomePageTitle 
	public String HomePageTitle(){
		return driver.getTitle();
	}
	
	//  TestCase 2: validate UserNameLabel
	public boolean UserNameLabel(){
		return userNameLabel.isDisplayed();
	}
	
	// TestCase 3: click on Calendar link should return Calendar Page object() 
	public CalendarPage clickOnCalendarLink(){
		calendarLink.click();
		CommonUtil.shortWait();
		return new CalendarPage();  
	}
	
	//  TestCase 4: click on Contacts link should return Contact Page object() 
	//https://www.youtube.com/watch?v=ea0P7MBQmiM (15:00)
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		CommonUtil.shortWait();
		return new ContactsPage();  
	}
	
	//  TestCase 5: click on Company link should return CompanyPageobject ()
	public CompanyPage clickOnCompanyLink(){
		companyLink.click();
		CommonUtil.shortWait();
		return new CompanyPage();  
	}

	//  TestCase 6: click on Deals link should return Deals Page object ()
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		CommonUtil.shortWait();
		return new DealsPage();  
	}
	
	//  TestCase 7: click on  Tasks link should return  Tasks Page object ()
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		CommonUtil.shortWait();
		return new TasksPage();  
	}
	
}
