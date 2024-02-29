package utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtilts {

	
	public static String[] readExcelData(String sheetName,int rowNo) throws IOException{ 
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\testData\\TestInputData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet ws = wb.getSheet(sheetName);        
        XSSFRow r = ws.getRow(rowNo);
        int c_no = r.getLastCellNum();
        
        String Data[] = new String[c_no];
        for(int i=0; i<c_no; i++)
        {
            Data[i]=r.getCell(i).toString();
        }         
        wb.close();
        file.close();
        return Data;
    }
	
	
	
	public static void writeResult(String fileName,String sheetName, String testcaseName, String[] a,String expResult,String actResult ,int rowNo) throws IOException {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\Excel-Report\\"+fileName);
		XSSFWorkbook workbook=new XSSFWorkbook(file); 		
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
		}
		
		if(sheetName=="Finding Doctor result (TestNG)"||sheetName=="Finding Doctor (Cucumber)") {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNo);
			int lastColNo = sheet.getRow(0).getPhysicalNumberOfCells();
			for(int i =0; i<lastColNo; i++) {
				
				if(i==0) {
					row.createCell(i).setCellValue(testcaseName);				
				}
				else if(i>0 && i<9) {
					row.createCell(i).setCellValue(a[i-1]);
				}				
				else if (i==9) {
					if(expResult==null) {
						if(a[7].toLowerCase().contains("invalid")) {
							row.createCell(i).setCellValue("Fail");
						}
						else {
							row.createCell(i).setCellValue("Pass");
						}
						
					}
					row.createCell(i).setCellValue(expResult);
					
				}
				else if (i==10) {
					row.createCell(i).setCellValue(actResult);
				}	
				
			}
		}
		else if(sheetName=="Form Filling result (TestNG)"||sheetName == "Form Filling(Cucumber)") {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNo);
			int lastColNo = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Store data array size : " + a.length+"\t Exp & Act Result " + expResult + " " + actResult);
			for(int i=0; i<lastColNo; i++) {
				if(i==0) {
					row.createCell(i).setCellValue(testcaseName);				
				}
				else if(i>0 && i<8) {
					row.createCell(i).setCellValue(a[i-1]);
				}
				else if (i==8) {
					if(expResult==null) {
						if(a[7].contains("Invalid")) {
							row.createCell(i).setCellValue("Fail");
						}
						else {
							row.createCell(i).setCellValue("Pass");
						}
						
					}
					row.createCell(i).setCellValue(expResult);
				}
				else if (i==9) {
					row.createCell(i).setCellValue(actResult);
				}
			}
			
		}
		 
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\Excel-Report\\"+fileName);
		workbook.write(fo);		
		workbook.close();
		file.close();
		fo.close();

	}
	
	public static void wirteData(String sheetName,List<WebElement> list, int col_no) throws IOException {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\Excel-Output-Data\\Output Data.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file); 		
		
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
		}
		
		if(sheetName == "Doctor") {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row ;
			for(int i =1; i<=list.size(); i++) {			
				row = sheet.createRow(i);
				String tempDataArr[] = list.get(i-1).getText().split("\n");
				int colNo=tempDataArr.length;
				
				for(int j =0; j<colNo;j++){
					row.createCell(j).setCellValue(tempDataArr[j]);				
				}			
			}			
		}
		else if(sheetName == "Top Surgery") {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row ;
			for(int i =1; i<=list.size(); i++) {
				row = sheet.createRow(i);
				row.createCell(col_no).setCellValue("Surgery : " + list.get(i-1).getText());	
			}
			
		}
		
		FileOutputStream fo=new FileOutputStream(System.getProperty("user.dir")+"\\Excel-Output-Data\\Output Data.xlsx");
		workbook.write(fo);		
		workbook.close();
		file.close();
		fo.close();
	}    
}
