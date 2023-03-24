package DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class FetchFromExcel {

	@DataProvider
	public Object[][] getDataFromExcel(String path, String sheet) throws EncryptedDocumentException, FileNotFoundException, IOException {
		 Workbook wb = WorkbookFactory.create(new FileInputStream(path));
		Sheet sh= wb.getSheet(path);
		int totalRows=sh.getLastRowNum()+1;
		int totalColumns=sh.getRow(0).getLastCellNum();
		Object[][] data= new Object[totalRows][totalColumns];
		for(int i=0;i<totalRows;i++) {
			for(int j=0;j<totalColumns;j++) {
				data[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
}
