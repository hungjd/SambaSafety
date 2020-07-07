package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	//https://www.youtube.com/watch?v=ea0P7MBQmiM

	//@FindBy(xpath = "//td[contains(text(),'User: Naveen K')]")
	@FindBy(xpath = "//span[@class='user-display']")
	@CacheLookup
	WebElement userNameLabel;


	//@FindBy(xpath = "//a[contains(text(),'Contacts')]")
	@FindBy(xpath = "//span[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	
	//@FindBy(xpath = "//a[contains(text(),'New Contact')]")
	@FindBy(xpath = "//button[contains(text(),'New')]")
	WebElement newContactLink;
	
	
	//@FindBy(xpath = "//a[contains(text(),'Deals')]")
	@FindBy(xpath = "//span[contains(text(),'Deals')]")
	WebElement dealsLink;

	//@FindBy(xpath = "//a[contains(text(),'Tasks')]")
	@FindBy(xpath = "//span[contains(text(),'Tasks')]")
	WebElement tasksLink;


	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this); // Initialize constructor HomePage
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	//https://www.youtube.com/watch?v=ea0P7MBQmiM (30:30)
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	
	// click on Contacts link should return Contact Page/object 
	//https://www.youtube.com/watch?v=ea0P7MBQmiM (15:00)
	public ContactsPage clickOnContactsLink(){
		contactsLink.click();
		return new ContactsPage();  
	}
	
	// click on Deals link should return Deals Page/object 
	public DealsPage clickOnDealsLink(){
		dealsLink.click();
		return new DealsPage();  
	}
	
	// click on  Tasks link should return  Tasks Page/object 
	public TasksPage clickOnTasksLink(){
		tasksLink.click();
		return new TasksPage();  
	}
	
	// Contacts > click on New Contact links
	// https://www.youtube.com/watch?v=H2-3w-GQZ3g (4:00)
	public void clickOnNewContactLink(){
		
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		
		newContactLink.click();
		
	}
	
	
	
	
	
	
	

}
