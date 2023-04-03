package script;
import static org.testng.Assert.fail;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import DataProvider.IteratorOfAnObjectArray;
import DataProvider.fetchFromExcel2DObjectArray;
import genericutilities.BaseTest;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import page.AdminHomePage;
import page.CustomerPage;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;

//@Listeners(genericutilities.ListenersImplementation.class)
public class TC_001 extends BaseTest  {

	@Test(priority = 0, groups = "smoke",retryAnalyzer = genericutilities.Retry.class, dataProviderClass = IteratorOfAnObjectArray.class, dataProvider = "getMultipleData")
	public void verifyAddedCustomerShowsInUSPDropDown(String customerFirstName, String customerLastName, String customerPhoneNumber, String productName,String productQuantity, String category) throws FileNotFoundException, IOException, InterruptedException {
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");
	
//		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1)+RandomStringUtils.randomAlphabetic(2);
//		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1)+RandomStringUtils.randomAlphabetic(2);
//		String customerPhoneNumber=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 2, 1)+RandomStringUtils.randomNumeric(3);
//		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
//		String productQuantity=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 4, 1);
//		String category=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 6, 1);

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
		
     	//CHOOSE ANY PRODUCT CATEGORY AND CHOOSE PRODUCT QUANTITY AND SUBMIT
		UserHomePage up = new UserHomePage(driver);
		up.chooseProductCategory(category,driver);
		up.enterProductQuantity(productQuantity, productName, driver);
		up.clickOnAddProduct(productName, driver);
	
		//CHECK IF THE ADDED CUSTOMER NAME IS SELECTED IN DROPDOWN
     	UserPointOfSalePage upos = new UserPointOfSalePage(driver);
		Select s=upos.selectCustomerByText(customerFirstName+" "+customerLastName);

		//VERIFY DROPDOWN CUSTOMER AND ADDED CUSTOMER ARE SAME
		String actualCustomerName=s.getFirstSelectedOption().getText();
		String expectedCustomerName=customerFirstName+" "+customerLastName;
		Assert.assertEquals(actualCustomerName, expectedCustomerName);
	}
}


