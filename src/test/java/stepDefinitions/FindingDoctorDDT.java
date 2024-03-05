package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;

import io.cucumber.java.en.*;
import pages.FindingDoctors;
import pages.GetDoctorDetails;
import pages.SetFilters;
import utilites.DataProvide;
import utilites.ExcelUtilts;

public class FindingDoctorDDT {
	
	WebDriver driver = BaseClass.getDriver();	
	Logger log= LogManager.getLogger(this.getClass());
	FindingDoctors fd = new FindingDoctors(driver);
	SetFilters sf = new SetFilters(driver);
	GetDoctorDetails doc = new GetDoctorDetails(driver);
	String location, spl,story, experience, fee, availability,sort,typeOfData,expResult, actResult;
	
    List<HashMap<String, String>> datamap; //Data drive
    
    

       
    @When ("enter a location and Doctor Speacialist {string}")
    public void enter_a_location_and_Doctor_Speacialist(String indexVal) throws IOException, InterruptedException {
    	log.info("<--------------------- enter a location and Doctor Speacialist --------------------->");
    	datamap = DataProvide.readData("Doctor Search Input Data");
    	int index=Integer.parseInt(indexVal)-1;  	
    	
    	location = datamap.get(index).get("Location");
    	spl = datamap.get(index).get("Specialist");  	
    	typeOfData= datamap.get(index).get("Type"); 
    	if(typeOfData.contains("Invalid")) {
			expResult = "Fail";
		}else {
			expResult = "Pass";
		}
    	try {
    		fd.locationFun(location);
    		fd.findDoc(spl);
    		fd.selectDoc(spl);
    		fd.verifyLocation(location);
    		
			actResult = "Pass";
    		
    	}
    	catch(Exception e) {
    		actResult = "Fail";
    	}    	
    }
    
    
    @When("Enters all fliter will show result page {string}")
    public void Enters_all_fliter_will_show_result_page(String indexVal) throws IOException, InterruptedException {
    	log.info("<--------------------- Enters all fliter will show result page --------------------->");
    	datamap = DataProvide.readData("Doctor Search Input Data");    	
    	int index=Integer.parseInt(indexVal)-1;
    	story = datamap.get(index).get("Patient Stories");
    	experience = datamap.get(index).get("Experience");
    	fee = datamap.get(index).get("Fees");
    	availability= datamap.get(index).get("Availability");
    	sort = datamap.get(index).get("Sort By");
    	typeOfData = datamap.get(index).get("Type");
    	if(typeOfData.contains("Invalid")) {
			expResult = "Fail";
		}else {
			expResult = "Pass";
		}
    	try {
	    	sf.storyFilter(story);
	    	sf.experienceFilter(experience);
	    	sf.feeFilter(fee);
	    	sf.availablityFilter(availability);
	    	sf.sortBtnClick(sort);
	    	
			actResult = "Pass";
    	}
    	catch(Exception e) {
    		actResult = "Fail";
    	}
	    	
    }
    
    
    @Then("print top doctor List in console window")
    public void print_top_doctor_List_in_console_window() throws IOException {
    	log.info("<--------------------- print top doctor List in console window --------------------->");
    	if(actResult=="Pass") {
    		doc.doctorInfo();
    	}
    	else {
    		System.out.println("\n-----------------------Filter option Something Not Correct........!--------------------------------");
    		System.out.println("\n------------------------Verify the Filter options------------------------");
    	}
    	
    }

    @Then("store the Result Status in Excel {string}")
    public void store_the_Result_Status_in_Excel(String no) throws IOException {
    	log.info("<--------------------- store the Result Status in Excel --------------------->");
    	int rowNo = Integer.parseInt(no);		
		String[] tempData = {location, spl,story, experience, fee, availability,sort,expResult};
		ExcelUtilts.writeResult("Cumber Results.xlsx","Finding Doctor (Cucumber)", ("Finding Doctor Test Case :" +  rowNo), tempData, expResult, actResult, rowNo);
    }
}
