package genericutilities;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataProviderMethod {

	public void getMultipleData(String path, int row, int column) {
		ArrayList<Object[]> dataList = new ArrayList<Object[]>();
		try {
			Workbook wb=WorkbookFactory.create(new FileInputStream(path));

			int rowCount=wb.getSheet("").getLastRowNum();
			int colCount=wb.getSheet(path).getRow(row).getLastCellNum();

			for(int i=0;i<=rowCount;i++) 
			{
				String[] dataRow= new String[colCount];

				for(int j=0;j<colCount;j++) {
					try {
						String v=wb.getSheet(path).getRow(row).getCell(column).getStringCellValue();
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

	}
}
