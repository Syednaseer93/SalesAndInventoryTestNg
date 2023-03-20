package script;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutilities.BaseTest;
import genericutilities.DatabaseUtility;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.JavaUtility;
import genericutilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.CustomerPage;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;

public class TC_002 extends BaseTest {
	@Test
	public void VerifyAddedCustomerPresentInTable() {
		//COMMON DATA
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");
		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");
		//TEST SPECIFIC DATA
		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1)+RandomStringUtils.randomAlphabetic(3);
		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1)+RandomStringUtils.randomAlphabetic(2);
		String customerPhoneNumber=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 2, 1)+RandomStringUtils.randomNumeric(2);
		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
		String quantity=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 9, 1);

		//LOGIN TO USER PAGE
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(userUsername);
		lp.setPassword(userPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);

		UserHomePage uhp = new UserHomePage(driver);
		//CHOOSE PRODUCT CATEGORY AND ENTER PRODUCT QUANTITY AND SUBMIT
		uhp.clickOnProductCategory();
		uhp.enterProductQuantity(quantity, productName, driver);
		uhp.clickOnAddProduct(productName, driver);

		//CLICK ON ADD CUSTOMER ICON
		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.clickOnAddCustomer();

		//ADD A NEW CUSTOMER DETAILS AND SUBMIT
		pos.enterCustomerDetails(customerFirstName, customerLastName, customerPhoneNumber);
		pos.submitCustomerDetails();
		WebDriverUtility.acceptjSAlert(driver);

		//LOGOUT OF USERPAGE
		uhp.clickOnProfileIcon();
		uhp.clickOnLogoutLink();
		uhp.clickOnButton();
		
		/******************************************************************************/
     	//LOGIN TO ADMIN PAGE
		lp.setUsername(adminuserName);
		lp.setPassword(adminPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);

		//CLICK ON CUSTOMER MODULE
		CustomerPage cp = new CustomerPage(driver);
		cp.clickOnCustomerIcon();

		//NAVIGATE TO EVERY PAGE AND CHECK FOR ADDED CUSTOMER'S NAME
		int totalPages=69;	
		boolean flag=false;
		String phoneNoFromTable="";
		for(int p=1;p<=totalPages;p++) {
			cp.clickOnActivePage();
			int row=cp.getNoOfRowsInTable();
			for(int r=1;r<=row;r++) {
				phoneNoFromTable=cp.getcustPhoneNoTable(r,driver);
				if(phoneNoFromTable.equals(customerPhoneNumber)) {
					flag=true;
					break;
				}
			}
			if(flag) {
				break;
			}
			else {
				String pageNo=Integer.toString(p+1);
				cp.clickOnNextPage(pageNo, driver).click();
			}
		}	
		//CHECK IF THE ADDED CUSTOMER'S NAME IS PRESENT IN CUSTOMER'S TABLE
		Assert.assertEquals(customerPhoneNumber, phoneNoFromTable);	
	}
}
