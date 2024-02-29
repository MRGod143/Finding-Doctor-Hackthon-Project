package stepDefinitions;


import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.*;





public class SmokeTest {
	WebDriver driver = BaseClass.getDriver();	
	
	
	@When("enters location as")
	public void enters_location_as() throws InterruptedException {
		FindingDoctors fd = new FindingDoctors(driver);
		fd.locationFun("Chennai");		
		
	}
	
	
	@When("enters Doctor spl as")
	public void enters_doctor_spl_as() throws InterruptedException {
		FindingDoctors fd = new FindingDoctors(driver);
		fd.findDoc("Dentist");
		fd.selectDoc("Dentist");
	}
	
	
	@When("Click on Top surgeries button and display the list")
		public void click_on_top_surgeries_button_and_display_the_list() throws InterruptedException {
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();		
		tc.topSurgery();
	}
	
	@When("Click Wellness form and Page verify")
	public void click_wellness_form_and_page_verify() {
		SetFormValue form = new SetFormValue(driver);
		form.clickForCorporate();
	}

	

	
	


}
