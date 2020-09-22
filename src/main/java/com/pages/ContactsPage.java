package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.base.TestBase;

// https://www.youtube.com/watch?v=ea0P7MBQmiM (48:00)

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//div[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath = "//button[contains(text(),'New')]")
	WebElement newContactLink;

	// https://www.youtube.com/watch?v=H2-3w-GQZ3g (13:00)
	//@FindBy(id = "first_name")
	@FindBy(xpath = "//input[@name='first_name' and @type='text']")
	WebElement firstName;

	//@FindBy(id = "surname")
	@FindBy(xpath = "//input[@name='last_name' and @type='text']")
	WebElement lastName;

	//@FindBy(name = "client_lookup")
	@FindBy(xpath = "//div[@name='company']//input[@class='search']")
	WebElement company;

	//@FindBy(xpath = "//input[@type='submit' and @value='Save']")
	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveBtn;

	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions - different features of the page:
	
	// TestCase 1:verifyContactsLabel
	public boolean verifyContactsLabel() {
		return contactsLabel.isDisplayed();
	}

	// TestCase 2:create new contact - data driven framework
	// https://www.youtube.com/watch?v=H2-3w-GQZ3g (4:00)
	public void createNewContact(String fName, String lName, String comp) {

		// https://www.youtube.com/watch?v=H2-3w-GQZ3g (10:45)
		// drop down title > select class 
		/*
		 * Select select = new Select(driver.findElement(By.name("title")));
		 * select.selectByVisibleText(title);
		 */ 

		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		company.sendKeys(comp);
		saveBtn.click();

	}
	
	//  Contacts > click on New Contact links
	// https://www.youtube.com/watch?v=H2-3w-GQZ3g (4:00)
	/*
	 * public void clickOnNewContactLink(){ Actions action = new Actions(driver);
	 * action.moveToElement(contactsLink).build().perform(); newContactLink.click();
	 * }
	 */
	
	
	
	
	// TestCase 3:verifyContactsByName
	// https://www.youtube.com/watch?v=ea0P7MBQmiM (54:40)
	// review xpath tutorial https://www.youtube.com/watch?v=3uktjWgKrtI
	public void verifyContactsByName(String name) {
		
		  driver.findElement(By.xpath("//a[text()='"+name+
		  "']//parent::td[@class='datalistrow']" +
		  "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).
		  click();
		 
		// driver.findElement(By.xpath("//td[contains(text(),'Steven
		// VerlMun')]")).click();

		driver.findElement(By.xpath("//td[contains(text(),'" + name + "')]")).click();

	}

}
