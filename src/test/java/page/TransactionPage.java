package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.DynamicXpathUtils;

public class TransactionPage {
 
	public TransactionPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//ul/li[@class='paginate_button page-item active']/a")
	private WebElement activePage;
	
	@FindBy(xpath="//tbody/tr")
	private List<WebElement> noOfRowsTable;
	
	public void clickOnActivePage() {
		activePage.click();
	}

	public int getNoOfRowsInTable() {
		int size=noOfRowsTable.size();
		return size;
	}
	
	String xPathCustomerFirstName="//tr[%replaceable%]/td[1]";
	public String getCustFirstNameTable(int  row, WebDriver driver) {
		String finalDynamicXpath1= DynamicXpathUtils.getDynamicXpath(xPathCustomerFirstName, row);
		String customerFNameTable=driver.findElement(By.xpath(finalDynamicXpath1)).getText();
		return customerFNameTable;
	}
	
	String xPathCustomerLastName="//tr[%replaceable%]/td[2]";
	public String getCustLastNameTable(int  row, WebDriver driver) {
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathCustomerLastName, row);
		String customerLNameTable=driver.findElement(By.xpath(finalDynamicXpath2)).getText();
		return customerLNameTable;
	}

	String xPathNextPage="//ul/li[@class='paginate_button page-item ']/a[.='%replaceable%']";
	public WebElement clickOnNextPage(String pageNo,WebDriver driver) {
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathNextPage, pageNo);
		WebElement nextPage=driver.findElement(By.xpath(finalDynamicXpath2));
		return nextPage;
	}
	public void clickToViewInvoice(int row, WebDriver driver) {
//		String finalDynamicXpath= DynamicXpathUtils.getDynamicXpath(xPathPhoneNo, row);
//		String customerNameTable=driver.findElement(By.xpath(finalDynamicXpath)).getText();
		String finalDynamicXpath1= DynamicXpathUtils.getDynamicXpath(xPathCustomerLastName, row);
		String customerLNameTable=driver.findElement(By.xpath(finalDynamicXpath1)).getText();
		
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathCustomerFirstName, row);
		String customerFNameTable=driver.findElement(By.xpath(finalDynamicXpath2)).getText();
		String customerNameFromTable=customerFNameTable+" "+customerLNameTable;
		
		driver.findElement(By.xpath("//td[.='"+customerNameFromTable+"']/../td[4]/a")).click();
	}
	
	public String getActualcustomerNameFromTable(WebDriver driver, int row) {
		String finalDynamicXpath1= DynamicXpathUtils.getDynamicXpath(xPathCustomerLastName, row);
		String customerLNameTable=driver.findElement(By.xpath(finalDynamicXpath1)).getText();
		
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathCustomerFirstName, row);
		String customerFNameTable=driver.findElement(By.xpath(finalDynamicXpath2)).getText();
		String customerNameFromTable=customerFNameTable+" "+customerLNameTable;
       return customerNameFromTable;
	}
	
}
