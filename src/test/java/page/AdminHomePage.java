package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {

	public AdminHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[text()='Customer']")
	private WebElement customerModule;
	
	@FindBy(xpath="//span[text()='Transaction']")
	private WebElement transactionModule;
	
	
	@FindBy(xpath="//span[text()='Product']")
	private WebElement productModule;
	
	@FindBy(xpath="//span[text()='Inventory']")
	private WebElement inventoryModule;

	@FindBy(xpath="//ul[@class='navbar-nav ml-auto']/li[2]/a/span")
	private WebElement profileIcon;
	
	@FindBy(xpath="//a[@data-target='#logoutModal']")
	private WebElement logoutLink;
	
	@FindBy(xpath="//div/a[text()='Logout']")
	private WebElement logoutButton;
	
	
	
	public void clickOnCustomerModule() {
		customerModule.click();
	}
	
	public void clickOnProductModule() {
		productModule.click();
	}
	public void clickOnInventoryModule() {
		inventoryModule.click();
	}
	
	public void clickOnProfileIcon() {
		profileIcon.click();
	}
	
	public void clickOnLogoutLink() {
		logoutLink.click();
	}
	public void clickOnButton() {
		logoutButton.click();
	}
	public void clickOnTransactionModule() {
		transactionModule.click();
	}
}
