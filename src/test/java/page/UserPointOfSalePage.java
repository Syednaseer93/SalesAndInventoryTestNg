package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import genericutilities.DynamicXpathUtils;
import genericutilities.WebDriverUtility;

public class UserPointOfSalePage extends WebDriverUtility {

	public UserPointOfSalePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@data-target='#poscustomerModal']/i")
	private WebElement addCustomerIcon;

	@FindBy(name="customer")
	private WebElement selectCustomerDD;
	
	@FindBy(xpath="//form[@action='cust_pos_trans.php?action=add']/div/input[@name='firstname']")
	private WebElement customerFirstName;
	
	@FindBy(xpath="//form[@action='cust_pos_trans.php?action=add']/div/input[@name='lastname']")
	private WebElement customerLastName;
		
	@FindBy(xpath="//form[@action='cust_pos_trans.php?action=add']/div/input[@name='phonenumber']")
	private WebElement customerPhoneNumber;

	
	@FindBy(xpath="//form[@action='cust_pos_trans.php?action=add']/button[@type='submit']")
	private WebElement clickSubmitAfterCustDetails;
	
	@FindBy(xpath="//button[.='SUBMIT']")
	private WebElement submitOrder;
	
	@FindBy(xpath="//input[@id='txtNumber']")
	private WebElement anyProductCategoryP1;
	
	@FindBy(xpath="//h3[@class='font-weight-bold py-3 bg-light']")
	private WebElement randomProductTextP1;

	@FindBy(xpath="//button[.='PROCEED TO PAYMENT']")
	private WebElement proceedToPaymentButton;
	
	public Select selectCustomerByText(String text)
	{
		Select s = selectByVisibleText(selectCustomerDD, text);
		return s;
	}
	public void clickOnAddCustomer() {
		addCustomerIcon.click();
	}
	public void submitOrder() {
		submitOrder.click();
	}
	public void submitAfterAddingCustDetails() {
		clickSubmitAfterCustDetails.click();
	}
	public void enterCustomerDetails(WebDriver driver,String fName, String lName, String phoneNo) {
		WebDriverUtility.waitUntilElementClickable(driver, customerFirstName);
		customerFirstName.sendKeys(fName);
		
		WebDriverUtility.waitUntilElementClickable(driver, customerLastName);
		customerLastName.sendKeys(lName);
		
		WebDriverUtility.waitUntilElementClickable(driver, customerPhoneNumber);
		customerPhoneNumber.sendKeys(phoneNo);
		
		clickSubmitAfterCustDetails.click();
		
		WebDriverUtility.acceptjSAlert(driver);
	}
	public void chooseProductCategory() {
		anyProductCategoryP1.click();
	}
	public String getTextOfProductAmount() {
		String amt=randomProductTextP1.getText();
		return amt;
	}
	public void enterProductAmountP1() {
		String amt=randomProductTextP1.getText();
		anyProductCategoryP1.sendKeys(amt);
	}
	
	public void ProceedToPaymentClick() {
		proceedToPaymentButton.click();
	}
	
	public String getActualAlertMessage(WebDriver driver) {
		String actualAlertMessage=driver.switchTo().alert().getText();
		return actualAlertMessage;
	}
	String xPath="//a[.='%replaceable%']";
	public void clickOnCategory(String catogoryName, WebDriver driver) {
		String finalDXpathCatName=DynamicXpathUtils.getDynamicXpath(xPath, catogoryName);
		driver.findElement(By.xpath(finalDXpathCatName)).click();
	}
	String xPath2="//h6[.='%replaceable%']";
	public String getProductName(String prodName, WebDriver driver) {
		String finalDXpathProdName=DynamicXpathUtils.getDynamicXpath(xPath2, prodName);
		String productName=driver.findElement(By.xpath(finalDXpathProdName)).getText();
		return productName;
	}
}
