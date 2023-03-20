package script;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import genericutilities.BaseTest;
import genericutilities.DatabaseUtility;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.JavaUtility;
import genericutilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.AdminHomePage;
import page.CustomerPage;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;

public class TC_001 extends BaseTest  {
	@Test
	public void testmethod() throws FileNotFoundException, IOException, InterruptedException {

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
		lp.setUsername(adminuserName);
		lp.setPassword(adminPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);
		
		//ENTER CUSTOMER PAGE AND ADD A NEW CUSTOMER
		CustomerPage cp = new CustomerPage(driver);
		cp.clickOnCustomerIcon();
		cp.clickOnAddCustomer();
		cp.enterCustomerDetails(customerFirstName, customerLastName, customerPhoneNumber);
		cp.submitCustomerDetails();
		
		//LOGOUT FROM ADMIN PAGE
		AdminHomePage ahm = new AdminHomePage(driver);
		ahm.clickOnProfileIcon();
		ahm.clickOnLogoutLink();
		ahm.clickOnButton();
		
   		//LOGIN USERPAGE
		lp.setUsername(userUsername);
		lp.setPassword(userPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);
		
		//CHOOSE PRODUCT CATEGORY AND CHOOSE PRODUCT QUANTITY AND SUBMIT
		UserHomePage up = new UserHomePage(driver);
		up.clickOnProductCategory();
		up.enterProductQuantity(productQuantity, productName, driver);
		up.clickOnAddProduct(productName, driver);

		//CHECK IF THE ADDED CUSTOMER NAME IS SELECTED IN DROPDOWN
		UserPointOfSalePage upos = new UserPointOfSalePage(driver);
		Select s=upos.selectCustomer(customerFirstName+" "+customerLastName);
	
		String actualCustomerName=s.getFirstSelectedOption().getText();
		String expectedCustomerName=customerFirstName+" "+customerLastName;
		Assert.assertEquals(actualCustomerName, expectedCustomerName);
	}
}
	

