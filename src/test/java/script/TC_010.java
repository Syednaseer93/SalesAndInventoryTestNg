package script;
import org.testng.annotations.Test;
import genericutilities.BaseTest;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.WebDriverUtility;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;

public class TC_010 extends BaseTest{
	@Test(priority = 3,retryAnalyzer = genericutilities.Retry.class)
	public void generateInvoice() {
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");
		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1);
		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1);
		String customerPhoneNumber=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 2, 1);
		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
		String productQuantity= ExcelUtility.getCellData("customer", "./data/getData.xlsx", 4, 1);
	
		//LOGIN TO USER PAGE
		LoginPage lp = new LoginPage(driver);
		lp.enterLoginDetailsAndSubmit(userUsername, userPassword, driver);

		//CHOOSE PRODUCT CATEGORY AND ENTER PRODUCT QUANTITY AND SUBMIT
		UserHomePage uhp = new UserHomePage(driver);
		uhp.clickOnProductCategory();
		uhp.enterProductQuantity(productQuantity, productName, driver);
		uhp.clickOnAddProduct(productName, driver);

		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.clickOnAddCustomer();

		//ADD A NEW CUSTOMER DETAILS AND SUBMIT
		pos.enterCustomerDetails(driver, customerFirstName, customerLastName, customerPhoneNumber);

		
		//CHECK IF THE ADDED CUSTOMER IS SHOWING IN CUSTOMER LIST DROPDOWN AND SELECT THAT AND SUBMIT
		pos.selectCustomer(customerFirstName+" "+customerLastName);
		pos.submitCustomerDetails();
		
		//ENTER THE AMOUNT AND SUBMIT
		pos.getTextOfProductAmount();
		pos.enterProductAmountP1();
		pos.ProceedToPaymentClick();
	}
}
