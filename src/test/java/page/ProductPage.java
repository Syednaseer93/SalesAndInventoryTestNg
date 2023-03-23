package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.DynamicXpathUtils;
import genericutilities.WebDriverUtility;

public class ProductPage {

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//ul/li[@class='paginate_button page-item active']/a")
	private WebElement activePage;

	@FindBy(xpath="//tbody/tr")
	private List<WebElement> noOfRowsTable;

	@FindBy(xpath="//h4[@class='m-2 font-weight-bold text-primary']/a/i")
	private WebElement addProductIcon;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/input[@name='prodcode']")
	private WebElement productCode;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/input[@name='name']")
	private WebElement productName;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/textarea")
	private WebElement productDescription;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/input[@name='quantity']")
	private WebElement productQuantity;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/input[@name='onhand']")
	private WebElement onHand;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/div/input[@name='price']")
	private WebElement price;

	@FindBy(xpath="//select[@name='category']")
	private WebElement category;

	@FindBy(xpath="//select[@name='supplier']")
	private WebElement supplier;

	@FindBy(xpath="//div[@class='form-group']/input[@name='datestock']")
	private WebElement dateStockIn;

	@FindBy(xpath="//form[@action='pro_transac.php?action=add']/button[@class='btn btn-success']")
	private WebElement submitProductDetails;

	
	String xpath="//tr[%replaceable%]/td[1]";
	public String getProductCodeFromTable(int  row, WebDriver driver) {
		String finalXpathProductCode=DynamicXpathUtils.getDynamicXpath(xpath, row);
		String prodCodeTable=driver.findElement(By.xpath(finalXpathProductCode)).getText();
		return prodCodeTable;
	}

	String xPathNextPage="//ul/li[@class='paginate_button page-item ']/a[.='%replaceable%']";
	public void clickOnNextPage(String pageNo,WebDriver driver) {
		String finalDynamicXpath2= DynamicXpathUtils.getDynamicXpath(xPathNextPage, pageNo);
		driver.findElement(By.xpath(finalDynamicXpath2)).click();
	}


	public void enteringProductDetails(WebDriver driver, String pCode,String pName,String pDesc,String pQuan,String onhand,String categoryText, String supplierText,String prodPrice, String dateStock) {
		WebDriverUtility.waitUntilElementClickable(driver, productCode);
		productCode.sendKeys(pCode);
		
		WebDriverUtility.waitUntilElementClickable(driver, productName);
		productName.sendKeys(pName);
		
		WebDriverUtility.waitUntilElementClickable(driver, productDescription);
		productDescription.sendKeys(pDesc);
	
		WebDriverUtility.waitUntilElementClickable(driver, productQuantity);
		productQuantity.sendKeys(pQuan);
		
		WebDriverUtility.waitUntilElementClickable(driver, onHand);
		onHand.sendKeys(onhand);
		
		WebDriverUtility.waitUntilElementClickable(driver, price);
		price.sendKeys(prodPrice);
		
		WebDriverUtility.selectByVisibleText(category, categoryText);
		WebDriverUtility.selectByVisibleText(supplier, supplierText);
		dateStockIn.click();
		dateStockIn.sendKeys(dateStock);
	}

	public void clickOnSubmitProductDetails() {
		submitProductDetails.click();
	}
	public void clickOnActivePage() {
		activePage.click();
	}


	public void clickOnAddProductIcon() {
		addProductIcon.click();
	}
	public int getNoOfRowsInTable() {
		int size=noOfRowsTable.size();
		return size;
	}

}
