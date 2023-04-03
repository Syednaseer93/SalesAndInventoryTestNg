package DataProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class fetchFromExcel2DObjectArray {
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, FileNotFoundException, IOException {

		Workbook wb=WorkbookFactory.create(new FileInputStream("./data/DP1.xlsx"));
		Sheet sheet = wb.getSheet("sheet1");
		int row=sheet.getLastRowNum();
		int column=sheet.getRow(0).getLastCellNum();
		Object[][] data= new Object[row+1][column];
		for(int i=0;i<=row;i++) {
			for(int j=0;j<column;j++) {
				data[i][j]=wb.getSheet("sheet1").getRow(i).getCell(j).getStringCellValue();
			}		
		}
		
		return data;
	}
	//@Test(dataProvider = "getData")
	public void useData(String s1, String s2, String s3) {
		System.out.println(s1+s2+s3);
	}
}
