package genericutilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersImplementation extends BaseTest implements ITestListener  {


	public void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot)driver;
		try {
			FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File("./screenshot/"+methodName+".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
