package genericutilities;

import com.aventstack.extentreports.ExtentReports;

//Note: all variable in interface are by default public static final
public interface IAutoConst {
	ExtentReports EXTENTREPORTS = new ExtentReports();
	String REPORT_PATH="./result/Report.html";
	String ENV_FOLDER="./env/";
	String CHROME_KEY="webdriver.chrome.driver";
	String GECKO_KEY="webdriver.gecko.driver";
	String SCREENSHOT_FOLDER="./screenshot/";
	String IMAGE_FORMAT =".png";
	String SCREENSHOT_FOLDER_FOR_REPORT="./../screenshot/";
	String INPUTXL_PATH="./data/input.xlsx";
	
	String DBURL="jdbc:mysql://rmgtestingserver:3333/projects";
	String DBUSERNAME="root@%";
	String DBPASSWORD="root";
	String ConfigPath="./data/config.properties";
	String EXCELFILEPATH="./data/getData.xlsx";
	
}
