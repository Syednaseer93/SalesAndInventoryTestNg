package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	private WebElement submitCustomerDetails;
	
	@FindBy(xpath="//button[.='SUBMIT']")
	private WebElement submitOrder;
	
	@FindBy(xpath="//input[@id='txtNumber']")
	private WebElement anyProductCategoryP1;
	
	@FindBy(xpath="//h3[@class='font-weight-bold py-3 bg-light']")
	private WebElement randomProductTextP1;

	@FindBy(xpath="//button[.='PROCEED TO PAYMENT']")
	private WebElement proceedToPaymentButton;
	
	public Select selectCustomer(String text){
		Select s = selectByVisibleText(selectCustomerDD, text);
		return s;
	}
	public void clickOnAddCustomer() {
		addCustomerIcon.click();
	}
	public void submitCustomerDetails() {
		submitCustomerDetails.click();
	}
	public void enterCustomerDetails(String fName, String lName, String phoneNo) {
		customerFirstName.sendKeys(fName);
		customerLastName.sendKeys(lName);
		customerPhoneNumber.sendKeys(phoneNo);
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
	
	
}
