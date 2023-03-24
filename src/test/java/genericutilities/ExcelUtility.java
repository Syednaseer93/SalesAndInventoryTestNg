package genericutilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {


	public static int getLastRowNumber(String sheet, String path) {
		int row = 0;
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			row = wb.getSheet(sheet).getLastRowNum();
		} catch (Exception e) {
		}
		return row;
	}

	public static int getLastColumnNumber(String sheet, String path, int row) {
		int column = 0;
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			column = wb.getSheet(sheet).getRow(row).getLastCellNum();
		} catch (Exception e) {
		}
		return column;
	}

	public static String getCellData(String sheet, String path, int row, int column) {
		String data = "";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			data = wb.getSheet(sheet).getRow(row).getCell(column).toString();
		} catch (Exception e) {
		}
		return data;
	}
	public void setCellValue(String sheet, String path, int r, int cc, String value) {
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			wb.getSheet(sheet).getRow(r).createCell(cc).setCellValue(value);
			wb.write(new FileOutputStream("./data/logindata.xlsx"));
		} catch (Exception e) {

		}
	}
	public HashMap<String,String> readMultipleData(String path, String sheet, int keyColumn, int valueColumn) throws EncryptedDocumentException, FileNotFoundException, IOException 
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(path));
		int rowNum=wb.getSheet(sheet).getLastRowNum();
		HashMap<String,String>map = new HashMap<String,String>();
		for (int i = 0; i <= rowNum; i++) {
			String key=wb.getSheet(sheet).getRow(i).getCell(keyColumn).getStringCellValue();
			String value=wb.getSheet(sheet).getRow(i).getCell(valueColumn).getStringCellValue();
			map.put(key, value);
		}	
		return map;
	}
	public Object[][] getMultipleDataFromExcel(String path, String sheet) throws EncryptedDocumentException, FileNotFoundException, IOException {
		 Workbook wb = WorkbookFactory.create(new FileInputStream(path));
		 wb.getSheet(sheet).getLastRowNum();
		int totalRows=wb.getSheet(sheet).getLastRowNum()+1;
		int totalColumns=wb.getSheet(sheet).getRow(0).getLastCellNum();
		Object[][] data= new Object[totalRows][totalColumns];
		for(int i=0;i<totalRows;i++) {
			for(int j=0;j<totalColumns;j++) {
				data[i][j]=wb.getSheet(sheet).getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
	public Iterator<Object[]> getMultipleDataFromIterator(String path, String sheet) {
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
}


