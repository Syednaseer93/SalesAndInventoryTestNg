package DataProvider;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataPrac {

	
	//@Test(dataProvider = "getMultipleData", dataProviderClass = IteratorOfAnObjectArray.class)
	public void useData(String un,String pw, String key) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://demo.actitime.com/login.do");
		driver.findElement(By.id("username")).sendKeys(un);
		driver.findElement(By.name("pwd")).sendKeys(pw);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("spent_138_5")).sendKeys(key);

	}


}
