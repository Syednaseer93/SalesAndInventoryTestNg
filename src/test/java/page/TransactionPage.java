package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.DynamicXpathUtils;
import genericutilities.WebDriverUtility;

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
	
	String xPathCustomerName="//tr[%replaceable%]/td[2]";
	public String getCustNameTable(int  row, WebDriver driver) {
		String finalDynamicXpath1= DynamicXpathUtils.getDynamicXpath(xPathCustomerName, row);
		String customerNameTable=driver.findElement(By.xpath(finalDynamicXpath1)).getText();
		return customerNameTable;
	}
	

	String xPathNextPage="//ul/li[@class='paginate_button page-item ']/a[.='%replaceable%']";
	public WebElement clickOnNextPage(String pageNo,WebDriver driver) {
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathNextPage, pageNo);
		WebElement nextPage=driver.findElement(By.xpath(finalDynamicXpath2));
		return nextPage;
	}
	
	public void clickToViewInvoice(int row, WebDriver driver) {
		String finalDynamicXpath1= DynamicXpathUtils.getDynamicXpath(xPathCustomerName, row);
		String customerNameTable=driver.findElement(By.xpath(finalDynamicXpath1)).getText();
		driver.findElement(By.xpath("//td[text()='"+customerNameTable+"']/../td[4]/a")).click();
	}
	

	
}
