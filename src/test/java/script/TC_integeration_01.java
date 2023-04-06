package script;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import genericutilities.BaseTest;
import genericutilities.FileUtility;
import page.CustomerPage;
import page.LoginPage;
public class TC_integeration_01 extends BaseTest{

	@Test(priority = 5, groups = "regression",retryAnalyzer = genericutilities.Retry.class)
	public void checkCustomerNamesAreSorted() {
		extentTest.log(Status.INFO, "Enter the URL:");
		String adminuserName=FileUtility.getProperty(configPath,"ADMINUSERNAME");
		String adminPassword=FileUtility.getProperty(configPath,"ADMINPASSWORD");

		//LOGIN TO ADMIN PAGE
		LoginPage lp = new LoginPage(driver);
		lp.enterLoginDetailsAndSubmit(adminuserName, adminPassword, driver);

		//CLICK ON CUSTOMER MODULE
		CustomerPage cp = new CustomerPage(driver);
		cp.clickOnCustomerIcon();

		//CHECK IF THE NAME OF CUSTOMER IS SORTED IN EVERY PAGE 
		int totalPages=80;	
		boolean flag=false;
		for(int p=1;p<=totalPages;p++) {
			cp.clickOnActivePage();

			int rows=cp.getNoOfRowsInTable();
			ArrayList<String> orglist = new ArrayList<String>();
			ArrayList<String> sortedList = new ArrayList<String>();
			for(int r=1;r<=rows;r++) 
			{
				String customerName = cp.getCustomerNameTable(r, driver);
				orglist.add(customerName);
				sortedList.add(customerName);		
			}	
			Collections.sort(sortedList);
			//Collections.sort(sortedList, Collections.reverseOrder());
			System.out.println(sortedList+" | "+orglist);

			if(orglist.equals(sortedList)) {
				cp.clickOnNextButtonWithOutPN();
				flag=true;
			}
			else {
				flag=false;
				break;
			}		
		}
		Assert.assertTrue(!flag);
	}	
}


