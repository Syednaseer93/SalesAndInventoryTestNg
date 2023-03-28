package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import genericutilities.DynamicXpathUtils;
import genericutilities.ExcelUtility;
import genericutilities.WebDriverUtility;

public class UserHomePage extends ExcelUtility{


	public UserHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@data-target='#keyboard']")
	private WebElement KeyboardCategory;


	@FindBy(xpath="//ul[@class='navbar-nav ml-auto']/li[2]/a/span")
	private WebElement profileIcon;

	@FindBy(xpath="//a[@data-target='#logoutModal']")
	private WebElement logoutLink;

	@FindBy(xpath="//div/a[text()='Logout']")
	private WebElement logoutButton;

	String categoryXpath="//a[text()='%replaceable%']";
	public void chooseProductCategory(String prodCategory,WebDriver driver) {
		String categoryFXpath=DynamicXpathUtils.getDynamicXpath(categoryXpath, prodCategory)	;
		driver.findElement(By.xpath(categoryFXpath)).click();
	}

	public void logoutOfUserPage() {
		profileIcon.click();
		logoutLink.click();
		logoutButton.click();
	}


	public String xPathProdQuantity="//h6[.='%replaceable%']/following-sibling::input[1]"; 
	public void enterProductQuantity(String quantity, String productName, WebDriver driver) {
		String PQDynamicXpath = DynamicXpathUtils.getDynamicXpath(xPathProdQuantity, productName);
		driver.findElement(By.xpath(PQDynamicXpath)).sendKeys(quantity);
	}

	public String xPathProdClick="//h6[.='%replaceable%']/following-sibling::input[4]"; 
	public void clickOnAddProduct(String productName, WebDriver driver) {
		String APDynamicXpath=DynamicXpathUtils.getDynamicXpath(xPathProdClick, productName);
		driver.findElement(By.xpath(APDynamicXpath)).click();


	}


}
