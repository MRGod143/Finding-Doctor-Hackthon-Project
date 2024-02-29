package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.SetFormValue;
import utilites.DataProvide;
import utilites.ExcelUtilts;

public class FormFill {
	WebDriver driver = BaseClass.getDriver();
	SetFormValue form = new SetFormValue(driver);
	
	String userName, orgName,phoneNo, emailId, orgSize, typeOfData,interest,expResult;	
	public String actResult;

    List<HashMap<String, String>> datamap; //Data drive
	
	@When("Click the Form button")
	public void clickFormBtn() {
		form.clickForCorporate();
		
	}
	
	@When("Enter all details {string}")
	public void enter_all_data_into_form(String indexVal) throws IOException {
		datamap = DataProvide.readData("Form Input Data");
    	
    	int index=Integer.parseInt(indexVal)-1;
    	userName = datamap.get(index).get("Name");
    	orgName = datamap.get(index).get("Company Name");
    	phoneNo = datamap.get(index).get("Phone Number");
    	emailId = datamap.get(index).get("Email ID");
    	orgSize = datamap.get(index).get("Org Size");
    	interest = datamap.get(index).get("Interest ");
    	typeOfData = datamap.get(index).get("Type");
    	
    	try {
    	
    		form.setName(userName);
    		form.setOrgName(orgName);
    		form.setPhoneNo(phoneNo);
    		form.setEmailId(emailId);
    		form.setOrgSize(orgSize);
			form.setInterest(interest);
			form.clickSubmitBtn();
			if(typeOfData.contains("Invalid")) {
				expResult = "Fail";
			}else {
				expResult = "Pass";
			}
			actResult = "Pass";
    	}
    	catch(Exception e)
		{
			actResult ="Fail";
			
		}
	
	}
	
	
	@Then("update the status in Excel data {string}")
	public void status_update_excel( String no) throws IOException {
		
		 
		int rowNo = Integer.parseInt(no);		
		String[] tempData = {userName,orgName,phoneNo,emailId,orgSize,interest};
		ExcelUtilts.writeResult("Cumber Results.xlsx","Form Filling(Cucumber)", ("Form Filling Test Case :" +  rowNo), tempData, expResult, actResult, rowNo);
			
	}


}
