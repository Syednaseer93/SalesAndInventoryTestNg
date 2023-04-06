package script;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericutilities.BaseTest;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import page.AdminHomePage;
import page.LoginPage;
import page.ProductPage;
import page.UserPointOfSalePage;
public class TC_012 extends BaseTest{
	@Test(priority = 4, groups = "regression",retryAnalyzer = genericutilities.Retry.class)
	public void verifyProductInInventoryAndUserPage(){
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");

		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1)+RandomStringUtils.randomNumeric(3);
		String productCode=RandomStringUtils.randomNumeric(3);
		String categoryName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 6, 1);
		String supplierName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 7, 1);
		String quantity=RandomStringUtils.randomNumeric(2);
		String onHand=RandomStringUtils.randomNumeric(2);
		String productDescription=RandomStringUtils.randomAlphabetic(10);
		String price=RandomStringUtils.randomNumeric(2);
		String date=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 8, 1);

		//LOGIN TO ADMIN PAGE
		LoginPage lp = new LoginPage(driver);
		lp.enterLoginDetailsAndSubmit(adminuserName, adminPassword, driver);

		//CLICK ON PRODUCT 
		AdminHomePage ahm = new AdminHomePage(driver);
		ahm.clickOnProductModule();

		//CREATE A NEW PRODUCT
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddProductIcon();

		//ENTER  PRODUCT DETAILS AND SUBMIT
		pp.enteringProductDetails(driver, productCode, productName, productDescription, quantity, onHand, categoryName, supplierName, price, date);
		pp.clickOnSubmitProductDetails();

		//CLICK ON INVENTORY
		ahm.clickOnInventoryModule();

		//SEARCH FOR THE PRODUCT CREATED IN ALL THE PAGES
		int totalPages=26;	
		boolean flag=false;
		for(int p=1;p<=totalPages;p++) {
			pp.clickOnActivePage();

			int rows=pp.getNoOfRowsInTable();
			for(int r=1;r<=rows;r++) {
				String productCodeFromTable=pp.getProductCodeFromTable(r, driver);
				if(productCodeFromTable.equals(productCode)) {
					System.out.println(productName);
					Assert.assertEquals(productCodeFromTable, productCode);
					flag=true;
					break;				
				}
			}
			if(flag) {
				break;
			}
			else {
				String pageNo=Integer.toString(p+1);
				pp.clickOnNextPage(pageNo, driver);
			}
		}	
		//LOGOUT OF ADMIN PAGE
		ahm.logoutOfAdminPage();

		//LOGIN USER PAGE
		lp.enterLoginDetailsAndSubmit(userUsername, userPassword, driver);

		//CHECK IF THE PRODUCT ADDED IN ADMIN PAGE IS DISPLAYED HERE IN USER PAGE LIST
		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.clickOnCategory(categoryName, driver);
		String productNameFromUserPage=pos.getProductName(productName, driver);
		Assert.assertEquals(productNameFromUserPage, productName);
	}
}
