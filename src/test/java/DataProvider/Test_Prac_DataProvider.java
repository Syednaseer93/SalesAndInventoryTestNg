package DataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericutilities.ExcelUtility;

public class Test_Prac_DataProvider {
    @DataProvider
	public Object[][] getData() throws EncryptedDocumentException, FileNotFoundException, IOException {
    	ExcelUtility eu = new ExcelUtility();
    	Object[][] data = eu.getMultipleDataFromExcel("./data/DP1.xlsx", "sheet1");
    	return data;
	}
	//@Test(dataProvider = "getData")
	public void useData(String src,String dst, String loc) {
		System.out.println(src+"---->"+dst+"------> "+loc);
	}
	
}
