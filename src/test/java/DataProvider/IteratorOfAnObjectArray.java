package DataProvider;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IteratorOfAnObjectArray {
	
@DataProvider
	public Iterator<Object[]> getMultipleData(String path, String sheet) {
		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));

			int rowCount=wb.getSheet(sheet).getLastRowNum();
			int colCount=wb.getSheet(sheet).getRow(1).getLastCellNum();

			for(int i=0;i<=rowCount;i++) 
			{
				String[] dataRow= new String[colCount];

				for(int j=0;j<colCount;j++) {
					try {
						String v=wb.getSheet(sheet).getRow(i).getCell(j).getStringCellValue();
						dataRow[j]=v;
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				dataList.add(dataRow);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			Iterator<Object[]> data = dataList.iterator();
			return data;
	}
	@Test(dataProvider = "getMultipleData")
	public void useData(String a1,String b1, String b3) {
		System.out.println(a1+b1+b3);
		
	}
}
