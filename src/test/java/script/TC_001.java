package script;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import genericutilities.BaseTest;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.WebDriverUtility;
import page.AdminHomePage;
import page.CustomerPage;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;
public class TC_001 extends BaseTest  {
	
	@Test(priority = 0,retryAnalyzer = genericutilities.Retry.class)
	public void verifyAddedCustomerShowsInUSPDropDown() throws FileNotFoundException, IOException, InterruptedException {
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");
		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1);
		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1);
		String customerPhoneNumber=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 2, 1);
		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
		String productQuantity=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 4, 1);

		//LOGIN TO ADMIN PAGE
		LoginPage lp = new LoginPage(driver);
		lp.enterLoginDetailsAndSubmit(adminuserName, adminPassword, driver);

		//ENTER CUSTOMER PAGE AND ADD A NEW CUSTOMER
		CustomerPage cp = new CustomerPage(driver);
		cp.clickOnCustomerIcon();
		cp.clickOnAddCustomer();
		cp.enterCustomerDetailsAndSubmit(driver,customerFirstName, customerLastName, customerPhoneNumber);
	

		//LOGOUT FROM ADMIN PAGE
		AdminHomePage ahm = new AdminHomePage(driver);
		ahm.logoutOfAdminPage();

		//LOGIN USERPAGE
		lp.enterLoginDetailsAndSubmit(userUsername, userPassword, driver);
		

		//CHOOSE PRODUCT CATEGORY AND CHOOSE PRODUCT QUANTITY AND SUBMIT
		UserHomePage up = new UserHomePage(driver);
		up.clickOnProductCategory();
		up.enterProductQuantity(productQuantity, productName, driver);
		up.clickOnAddProduct(productName, driver);

		//CHECK IF THE ADDED CUSTOMER NAME IS SELECTED IN DROPDOWN
		UserPointOfSalePage upos = new UserPointOfSalePage(driver);
		Select s=upos.selectCustomer(customerFirstName+" "+customerLastName);

		//VERIFY DROPDOWN CUSTOMER AND ADDED CUSTOMER ARE SAME
		String actualCustomerName=s.getFirstSelectedOption().getText();
		String expectedCustomerName=customerFirstName+" "+customerLastName;
		Assert.assertEquals(actualCustomerName, expectedCustomerName);
	}
}


