package genericutilities;

import java.util.ArrayList;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	ArrayList<String> list = new ArrayList<String>();
	public boolean retry(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		if(list.contains(testName)) {
			return false;
		}
		else {
			list.add(testName);
			return true;
		}	
	}	
}
