package genericutilities;


import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConst {

	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest extentTest;
	public String configPath="";
	
	@BeforeSuite()
	public void createReport() {

		ExtentSparkReporter spark=new ExtentSparkReporter(REPORT_PATH);
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Sales and Inventory");
		spark.config().setDocumentTitle("Automation reports");
		EXTENTREPORTS.attachReporter(spark); 
		EXTENTREPORTS.setSystemInfo("Tester", "Syed Naseer");
		EXTENTREPORTS.setSystemInfo("Report to", "Vijayalaxmi");
		
	}

	@AfterSuite
	public void publishReport() {
		EXTENTREPORTS.flush();
	}

	@Parameters({"property","browser"})
	@BeforeMethod
	public void openApp(Method testMethod,@Optional("config.properties") String property,@Optional("chrome") String browserName ) {
		configPath=ENV_FOLDER+property;

		String testName=testMethod.getName();
		extentTest = EXTENTREPORTS.createTest(testName);

		String browser = FileUtility.getProperty(configPath,"BROWSER");
		extentTest.log(Status.INFO, "Browser is: "+browser);

//Remember to change browserName to browser if not cross browser
		if(browserName.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			String path=System.getProperty(CHROME_KEY);
			extentTest.log(Status.INFO, "Set the path of driver exe:"+path);
			extentTest.log(Status.INFO, "Open the "+browser+" browser");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver(options);
			driver.manage().window().maximize();
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			String path=System.getProperty(GECKO_KEY);
			extentTest.log(Status.INFO, "Set the path of driver executable (exe): "+path);
			extentTest.log(Status.INFO, "Open the browser");
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}

		String strITO = FileUtility.getProperty(configPath,"ITO");
		int iITO=Integer.parseInt(strITO);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(iITO));
		extentTest.log(Status.INFO, "Set the ITO :"+iITO+" seconds");

		String strETO = FileUtility.getProperty(configPath,"ETO");
		int iETO=Integer.parseInt(strETO);
		wait=new WebDriverWait(driver, Duration.ofSeconds(iETO));
		extentTest.log(Status.INFO, "Set the ETO :"+iETO+" seconds");

		String appURL = FileUtility.getProperty(configPath,"APPURL");
		driver.get(appURL);

		extentTest.log(Status.INFO, "Enter the URL: "+appURL);
	}

	@AfterMethod
	public void closeApp(ITestResult result)  {
		String testName=result.getName();
		int testStatus= result.getStatus();

		if(testStatus==1)
		{
			extentTest.log(Status.PASS, testName+" is pass");
		}
		else
		{
			TakesScreenshot t=(TakesScreenshot)driver;
			File srcFile = t.getScreenshotAs(OutputType.FILE);
			File dstFile = new File(SCREENSHOT_FOLDER+testName+IMAGE_FORMAT);
			try
			{
				FileUtils.copyFile(srcFile, dstFile);
			}
			catch (Exception e) {

				extentTest.log(Status.FAIL, e.getMessage());
			}
			extentTest.addScreenCaptureFromPath(SCREENSHOT_FOLDER_FOR_REPORT+testName+IMAGE_FORMAT);
			String msg=result.getThrowable().toString();
			extentTest.log(Status.FAIL, msg);
		}

		extentTest.log(Status.INFO, "Close the browser");
		driver.quit();
	}
}
