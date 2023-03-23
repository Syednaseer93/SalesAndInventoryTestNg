package script;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericutilities.BaseTest;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.WebDriverUtility;
import page.AdminHomePage;
import page.LoginPage;
import page.TransactionPage;
import page.UserHomePage;
import page.UserPointOfSalePage;
public class TC_003 extends BaseTest {
	@Test(priority = 2)
	public void verifyCustomerNameShowsInInvoice() {
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");

		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1);
		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1);
		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
		String productQuantity= ExcelUtility.getCellData("customer", "./data/getData.xlsx", 4, 1);

		//LOGIN TO USERPAGE
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(userUsername);
		lp.setPassword(userPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);

		//CHOOSE PRODUCT CATEGORY AND ENTER PRODUCT QUANTITY AND SUBMIT
		UserHomePage uhp = new UserHomePage(driver);
		uhp.clickOnProductCategory();
		uhp.enterProductQuantity(productQuantity, productName, driver);
		uhp.clickOnAddProduct(productName, driver);

		//SELECT ANY CUSTOMER FROM LIST AND SUBMIT
		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.selectCustomer(customerFirstName+" "+customerLastName);		
		pos.submitCustomerDetails();

		//ENTER THE AMOUNT TO BE PAID AND SUBMIT
		pos.chooseProductCategory();
		pos.getTextOfProductAmount();
		pos.enterProductAmountP1();
		pos.ProceedToPaymentClick();
		WebDriverUtility.acceptjSAlert(driver);

		//LOGOUT OF USER PAGE
		uhp.clickOnProfileIcon();
		uhp.clickOnLogoutLink();
		uhp.clickOnButton();
		/*****************************************************************************************/
		//LOGIN TO ADMIN PAGE
		lp.setUsername(adminuserName);
		lp.setPassword(adminPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);

		//CLICK ON TRANSACTION MODULE
		AdminHomePage ahm = new AdminHomePage(driver);
		ahm.clickOnTransactionModule();

		//NAVIGATE EVERY PAGE TO CHECK FOR TRANSACTION NUMBER(PHONE NUMBER)
		TransactionPage tp = new TransactionPage(driver);
		int totalPages=20;	
		boolean flag=false;
		String customerNameFromTable="";
		for(int p=1;p<=totalPages;p++) {
			tp.clickOnActivePage();
			int rows=tp.getNoOfRowsInTable();
			for(int r=1;r<=rows;r++) {
				customerNameFromTable=tp.getCustNameTable(r, driver);
				if(customerNameFromTable.equals(customerFirstName+" "+customerLastName)) {
					flag=true;
					tp.clickToViewInvoice(r, driver);
					break;
				}
			}
			if(flag) {
				break;
			}
			else {
				String pageNo=Integer.toString(p+1);
				tp.clickOnNextPage(pageNo, driver).click();
			}
		}	
		//CHECK IF THE NAME IS PRESENT IN INVOICE
		Assert.assertEquals(customerNameFromTable,customerFirstName+" "+customerLastName);			
	}
}
