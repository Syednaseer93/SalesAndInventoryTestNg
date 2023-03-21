package script;


import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import genericutilities.BaseTest;
import genericutilities.DatabaseUtility;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.JavaUtility;
import genericutilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.LoginPage;
import page.UserHomePage;
import page.UserPointOfSalePage;

public class TC_010 extends BaseTest{
	@Test
	public void checkgeneratedInvoice() {

		String userUsername=FileUtility.getProperty(configPath,"USERUSERNAME");
		String userPassword=FileUtility.getProperty(configPath,"USERPASSWORD");

		String customerFirstName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 0, 1);
		String customerLastName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 1, 1);
		String customerPhoneNumber=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 2, 1);
		String productName=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 3, 1);
		String productQuantity= ExcelUtility.getCellData("customer", "./data/getData.xlsx", 4, 1);
		String expectedAlertMessage=ExcelUtility.getCellData("customer", "./data/getData.xlsx", 5, 1);
	
		//LOGIN TO USER PAGE
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

		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.clickOnAddCustomer();

		//ADD A NEW CUSTOMER DETAILS AND SUBMIT
		pos.enterCustomerDetails(customerFirstName, customerLastName, customerPhoneNumber);
		pos.clickSubmitAfterAddingCustDetails();
		WebDriverUtility.acceptjSAlert(driver);
		
		//CHECK IF THE ADDED CUSTOMER IS SHOWING IN CUSTOMER LIST DROPDOWN AND SELECT THAT AND SUBMIT
		pos.selectCustomer(customerFirstName+" "+customerLastName);
		pos.submitCustomerDetails();
		
		//ENTER THE AMOUNT AND SUBMIT
		pos.getTextOfProductAmount();
		pos.enterProductAmountP1();
		pos.ProceedToPaymentClick();

	}

}
