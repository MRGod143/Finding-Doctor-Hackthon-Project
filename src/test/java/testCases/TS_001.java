package testCases;


import java.io.IOException;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pages.FindingDoctors;
import pages.GetDoctorDetails;
import pages.SetFilters;
import testbase.TestBaseClass;

import utilites.ExcelUtilts;

public class TS_001 extends TestBaseClass{
	
    
	
	public static int rowNo = 1,testCaseCount =10, i;
	public String location,spl,story,exp,fees,availablity,sort,typeOfData,expResult, actResult;
	public String[] tempData;
	
	public FindingDoctors fd ;
	public SetFilters sf;
	public GetDoctorDetails doctorDetails;
	public SoftAssert a = new SoftAssert() ;
	
	public void dataSetup() throws IOException {
		log.info("<-------------------Test Scenario 001---------------->");
		tempData  = ExcelUtilts.readExcelData("Doctor Search Input Data",rowNo);
		location = tempData[0];
		spl = tempData[1];
		story = tempData[2];
		exp = tempData[3];
		fees = tempData[4];
		availablity = tempData[5];
		sort = tempData[6];
		typeOfData = tempData[7];

		if(typeOfData.contains("Invalid")) {
			expResult = "Fail";
		}else {
			expResult = "Pass";
		}
	}
	
	@Test
	public void Finding_Doctor() throws InterruptedException, IOException {
		log.info("<-------------------Test Scenario 001 : Finding Doctors---------------->");
			fd = new FindingDoctors(driver);
			sf = new SetFilters(driver);
			doctorDetails = new GetDoctorDetails(driver);
			
			for(i=0;i<testCaseCount; i++) {
				
				try {
					dataSetup();
					clickFindDoctorBtn();
					findingDoctor();
					storyFilter();
					experience();
					feeFilter();
					availabilityFilter();
					sortBtnClick();					
					
					actResult = "Pass";
					if(actResult=="Pass") {
			    		doctorInfo();
			    	}
			    	else {
			    		System.out.println("\n--------------------------Filter option Something Not Correct........!---------------------------");
			    		System.out.println("\n-------------------------Verify the Filter options----------------------");
			    	}
			    	
					
				}catch(Exception e) {
					driver.navigate().refresh();
					actResult = "Fail";
					a.assertAll();					
				}
				
				
				ExcelUtilts.writeResult("TestNG Results.xlsx","Finding Doctor result (TestNG)", "TC_001_Finding Doctor(Test case - "+(i+1)+" )", tempData, expResult, actResult, i+1);
				rowNo++;
			}
		
	}
	
	
	public void clickFindDoctorBtn() {
		fd.clickFIndDoctor();
	}
	
	public void findingDoctor() throws InterruptedException{	
		log.info("<-------------------finding Doctor---------------->");
		fd.locationFun(location);
		fd.findDoc(spl);	
		fd.selectDoc(spl);	
		
		fd.verifyLocation(location);
	}
	
	public void storyFilter() throws InterruptedException {
		log.info("<-------------------story Filter---------------->");
		sf.storyFilter(story);	
	}	
	public void experience() throws InterruptedException {
		log.info("<-------------------experience Filter---------------->");
		sf.experienceFilter(exp);	
	}	
	public void feeFilter() throws InterruptedException {		
		log.info("<-------------------Fee Filter---------------->");
		sf.feeFilter(fees);
	}	
	public void availabilityFilter() throws InterruptedException {
		log.info("<-------------------availablity Filter---------------->");
		sf.availablityFilter(availablity);;	
	}	
	public void sortBtnClick() throws InterruptedException {
		log.info("<-------------------Sort by Filter---------------->");
		sf.sortBtnClick(sort);	
	}	
	public void doctorInfo() throws IOException, InterruptedException {		
		log.info("<-------------------Doctor Info---------------->");
		doctorDetails.doctorInfo();		
	}
}	
	

	

