package script;


import java.time.Duration;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericutilities.BaseTest;
import genericutilities.DatabaseUtility;
import genericutilities.ExcelUtility;
import genericutilities.FileUtility;
import genericutilities.JavaUtility;
import genericutilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import page.AdminHomePage;
import page.LoginPage;
import page.ProductPage;
import page.UserPointOfSalePage;

public class TC_012 extends BaseTest{
    @Test
	public void test5(){
	
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
		lp.setUsername(adminuserName);
		lp.setPassword(adminPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);
		
		//CLICK ON PRODUCT 
		AdminHomePage ahm = new AdminHomePage(driver);
		ahm.clickOnProductModule();
		
		//CREATE A NEW PRODUCT
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddProductIcon();
		
		//ENTER  PRODUCT DETAILS AND SUBMIT
		pp.enteringProductDetails(productCode, productName, productDescription, quantity, onHand, categoryName, supplierName, price, date);
		pp.clickOnSubmitProductDetails();
		
		//CLICK ON INVENTORY
		ahm.clickOnInventoryModule();

		//SEARCH FOR THE PRODUCT CREATED IN ALL THE PAGES
		int totalPages=12;	
		boolean flag=false;
		String productCodeFromTable="";
		for(int p=1;p<=totalPages;p++) {
			pp.clickOnActivePage();

			int rows=pp.getNoOfRowsInTable();
			for(int r=1;r<=rows;r++) {
				productCodeFromTable=pp.getProductCodeFromTable(r, driver);
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
		ahm.clickOnProfileIcon();
		ahm.clickOnLogoutLink();
		ahm.clickOnButton();
  	/*****************************************************************************************************/
		//LOGIN USER PAGE
		lp.setUsername(userUsername);
		lp.setPassword(userPassword);
		lp.clickLoginButton();
		WebDriverUtility.acceptjSAlert(driver);

		UserPointOfSalePage pos = new UserPointOfSalePage(driver);
		pos.clickOnCategory(categoryName, driver);
		String productNameFromUserPage=pos.getProductName(productName, driver);
		Assert.assertEquals(productNameFromUserPage, productName);
	}
}
