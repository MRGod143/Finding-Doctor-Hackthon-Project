package testCases;

import java.io.IOException;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.SetFormValue;
import testbase.TestBaseClass;
import utilites.ExcelUtilts;

public class TC_003 extends TestBaseClass{
	
	public static int rowNo = 1,testCaseCount =10, i;
	public String name,orgname,phone,email,orgsize, interest,typeofData,expResult, actResult;
	public String[] tempData;
	
	public SetFormValue form ;
	public SoftAssert a = new SoftAssert() ;
	public void dataSetup() throws IOException {
		tempData  = ExcelUtilts.readExcelData("Form Input Data",rowNo);
		name = tempData[0];
		orgname= tempData[1];
		phone = tempData[2];
		email = tempData[3];
		orgsize = tempData[4];
		interest = tempData[5];
		typeofData = tempData[6];
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	@Test
	public void Wellness_Form() throws IOException {
		form = new SetFormValue(driver);
		form.clickForCorporate();
		for(i=0; i<testCaseCount; i++) {
			try {
				System.out.println("\n--------------------Start form Filling--------------------- \n");
				dataSetup();				
				form.setName(name);
				form.setOrgName(orgname);
				form.setPhoneNo(phone);
				form.setEmailId(email);
				form.setOrgSize(orgsize);
				form.setInterest(interest);				
				form.clickSubmitBtn();
				refreshPage();
				
				System.out.println("\n------------------------------Finish-------------------------\n");
				if(typeofData.contains("Invalid")) {
					expResult = "Fail";
				}else {
					expResult = "Pass";
				}
				actResult = "Pass";
			}
			catch(Exception e) {				
				System.out.println(e);
				actResult="Fail";
				a.assertAll();
			}
			
			
			ExcelUtilts.writeResult("TestNG Results.xlsx","Form Filling result (TestNG)", "TC_003_Form Filling(Test case - "+(i+1)+" )", tempData, expResult, actResult, i+1);
			rowNo++;
		}
		
	}

}
