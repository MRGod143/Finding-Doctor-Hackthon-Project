package utilites;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DataProvide {
	
	public static HashMap<String, String> storeValues = new HashMap<String, String>();
	
	public static List<HashMap<String,String>> readData(String sheetName) throws IOException{ 
		List<HashMap<String,String>> myData = new ArrayList<>();
		try {
			
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\testData\\TestInputData.xlsx");        
	        XSSFWorkbook wb = new XSSFWorkbook(file);
	        XSSFSheet sheet = wb.getSheet(sheetName);        
	        XSSFRow HeaderRow = sheet.getRow(0);
	        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) 
			{
			Row currentRow = sheet.getRow(i);
			HashMap<String, String> currentHash = new HashMap<String, String>();
			for (int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++) 
				{
				Cell currentCell = currentRow.getCell(j);
				switch (currentCell.getCellType()) 
					{
						case STRING:
							currentHash.put(
									HeaderRow.getCell(j).getStringCellValue(), 
									currentCell.getStringCellValue());
							break;
						default:
							break;
					}
				}
			myData.add(currentHash);
			}
	        wb.close();
		file.close();        
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
        return myData;
    }
	
}
