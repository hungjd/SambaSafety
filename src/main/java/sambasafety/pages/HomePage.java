package sambasafety.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.TestBase;

public class HomePage extends TestBase {

	// Initializing the Page Objects Factory / object repository:
	// all variable (@FindBy ...) will be initialize with this driver

	@FindBy(xpath = "//img[@class='attachment-medium size-medium']")
	WebElement logoImage;

	@FindBy(xpath = "//div[@class='copyright']")
	WebElement copyright;

	@FindBy(xpath = "//li[@id='menu-item-1973']//a[contains(text(),'Solutions')]")
	WebElement SolutionsLink;

	@FindBy(xpath = "//li[@id='menu-item-4063']//a[contains(text(),'Products')]")
	WebElement ProductsLink;

	@FindBy(xpath = "//li[@id='menu-item-4339']//a[contains(text(),'Resources')]")
	WebElement ResourcesLink;

	@FindBy(xpath = "//li[@id='menu-item-1979']//a[contains(text(),'Blog')]")
	WebElement BlogLink;

	@FindBy(xpath = "//li[@id='menu-item-2064']//a[contains(text(),'Support')]")
	WebElement SupportLink;

	@FindBy(xpath = "//div[@id='header-bar']//a[contains(text(),'Login')]")
	WebElement LoginLink;

	@FindBy(xpath = "//div[@id='header']//input[@id='s']")
	WebElement Search;

	// Initialize HomePage constructor
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	// Actions - different features of the page :

	// TestCase 1: validate HomePageTitle
	public String HomePageTitle() {
		return driver.getTitle();
	}

	// TestCase 2: validate LogoImage
	public boolean LogoImage() {
		return logoImage.isDisplayed(); /* return true or false */
	}

	// TestCase 3: validate CopyRight
	public boolean CopyRight() {
		return copyright.isDisplayed(); /* return true or false */
	}

	// TestCase 4: validate SolutionsLink
	public boolean SolutionsLinkLabel() {
		return SolutionsLink.isDisplayed(); /* return true or false */
	}

	// TestCase 5: validate ProductsLinkLabel
	public boolean ProductsLinkLabel() {
		return ProductsLink.isDisplayed();
	}

	// TestCase 6: validate ResourcesLink
	public boolean ResourcesLinkLabel() {
		return ResourcesLink.isDisplayed();
	}

	// TestCase 7: validate BlogLink
	public boolean BlogLinkLabel() {
		return BlogLink.isDisplayed();
	}

	// TestCase 8: validate SupportLink
	public boolean SupportLinkLabel() {
		return SupportLink.isDisplayed();
	}

	// TestCase 9: validate LoginLink
	public boolean LoginLinkLabel() {
		return LoginLink.isDisplayed();
	}

	// TestCase 10: validate Search
	public boolean Search() {
		return Search.isDisplayed();
	}

	// TestCase : click on SolutionsLink should return Solution Page object()
//	public CalendarPage clickOnSolutionLink() {
//		SolutionsLink.click();
//		CommonUtil.shortWait();
//		return new SolutionsPage();
//	}

}
