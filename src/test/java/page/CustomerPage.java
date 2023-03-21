package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.DynamicXpathUtils;

public class CustomerPage {

	public CustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//span[text()='Customer']")
	private WebElement CustomerIcon;
	
	@FindBy(xpath="//a[@data-target='#customerModal']/i")
	private WebElement addCustomerIcon;
	
	@FindBy(xpath="//ul/li[@class='paginate_button page-item active']/a")
	private WebElement activePage;
	
	
	
	@FindBy(xpath="//form[@action='cust_transac.php?action=add']/div/input[@name='firstname']")
	private WebElement customerFirstName;
	
	@FindBy(xpath="//form[@action='cust_transac.php?action=add']//input[@name='lastname']")
	private WebElement customerLastName;
		
	@FindBy(name="phonenumber")
	private WebElement customerPhoneNumber;
	
	@FindBy(xpath="//tbody/tr")
	private List<WebElement> noOfRowsTable;
	

		@FindBy(xpath="//form[@action='cust_transac.php?action=add']/button[@type='submit']")
	private WebElement submitCustomerDetails;
	
	public void clickOnCustomerIcon() {
		CustomerIcon.click();
	}
	public void clickOnAddCustomer() {
		addCustomerIcon.click();
	}
	public void enterCustomerDetails(String fName, String lName, String phoneNo) {
		customerFirstName.sendKeys(fName);
		customerLastName.sendKeys(lName);
		customerPhoneNumber.sendKeys(phoneNo);
	}
	public void clickOnActivePage() {
		activePage.click();
	}

	public void submitCustomerDetails() {
		submitCustomerDetails.click();
	}

	public int getNoOfRowsInTable() {
		int size=noOfRowsTable.size();
		return size;
	}
//	public String xPathProdQuantity="//h6[.='%replaceable%']/following-sibling::input[1]"; 
//	public String xPathProdClick="//h6[.='%replaceable%']/following-sibling::input[4]"; 
//
//	public void enterProductQuantity(String quantity, String productName, WebDriver driver) {
//		String finalDynamicXpath = DynamicXpathUtils.getDynamicXpath(xPathProdQuantity, productName);
//		driver.findElement(By.xpath(finalDynamicXpath)).sendKeys(quantity);
//	}
	String xPathPhoneNo="//tr[%replaceable%]/td[3]";
	public String getcustPhoneNoTable(int  row, WebDriver driver) {
		String finalDynamicXpath= DynamicXpathUtils.getDynamicXpath(xPathPhoneNo, row);
		String phone=driver.findElement(By.xpath(finalDynamicXpath)).getText();
		return phone;
	}
	//driver.findElement(By.xpath("//ul/li[@class='paginate_button page-item ']/a[.='"+pageNo+"']"))
	String xPathNextPage="//ul/li[@class='paginate_button page-item ']/a[.='%replaceable%']";
	public WebElement clickOnNextPage(String pageNo,WebDriver driver) {
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathNextPage, pageNo);
		WebElement nextPage=driver.findElement(By.xpath(finalDynamicXpath2));
		return nextPage;
	}
}
