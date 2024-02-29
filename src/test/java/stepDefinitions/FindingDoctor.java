package stepDefinitions;


import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.FindingDoctors;
import pages.SetFilters;

public class FindingDoctor {	
	
	WebDriver driver = BaseClass.getDriver();	
	FindingDoctors fd = new FindingDoctors(driver);
	SetFilters sf = new SetFilters(driver);
	
    
	
	
    @When("user enters location as {string}")    
	public void user_enters_location_as(String location) throws InterruptedException {  
    	BaseClass.getLogger().info("User Enters location ");
     	fd.locationFun(location);     	
    }
	
    @When("user enters Doctor spl as {string}")  
    public void user_enters_Doctor_spl_as(String spl) throws InterruptedException {
    	BaseClass.getLogger().info("User Enters Doctor Spl");
    	fd.findDoc(spl);
    	fd.selectDoc(spl);
    }
    
    
    @When ("verify location if location was change enters as {string}")
    public void verify_location_if_location_was_change_enters_as(String location) throws InterruptedException{
    	BaseClass.getLogger().info("Verify the Location ");
    	fd.verifyLocation(location);
    }   
    
    @Then("user should see the search result page")
    public void user_should_see_the_search_result_page() {
    	BaseClass.getLogger().info("Doctor List Show \n");
    	try{
    		Thread.sleep(5000);
    	}
    	catch(Exception e) {
    		
    	}
    }


}
