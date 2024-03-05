package stepDefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.*;





public class SmokeTest {
	WebDriver driver = BaseClass.getDriver();	
	Logger log= LogManager.getLogger(this.getClass());
	
	@When("enters location as")
	public void enters_location_as() throws InterruptedException {
		log.info("<--------------------- Smoke Test : enters location --------------------->");
		FindingDoctors fd = new FindingDoctors(driver);
		fd.locationFun("Chennai");		
		
	}
	
	
	@When("enters Doctor spl as")
	public void enters_doctor_spl_as() throws InterruptedException {
		log.info("<--------------------- Smoke Test : enters Doctor spl --------------------->");
		FindingDoctors fd = new FindingDoctors(driver);
		fd.findDoc("Dentist");
		fd.selectDoc("Dentist");
	}
	
	
	@When("Click on Top surgeries button and display the list")
		public void click_on_top_surgeries_button_and_display_the_list() throws InterruptedException {
		log.info("<--------------------- Smoke Test : Click on Top surgeries button and display the list --------------------->");
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();		
		tc.topSurgery();
	}
	
	@When("Click Wellness form and Page verify")
	public void click_wellness_form_and_page_verify() {
		log.info("<--------------------- Smoke Test : Click Wellness form and Page verify --------------------->");
		SetFormValue form = new SetFormValue(driver);
		form.clickForCorporate();
	}

	

	
	


}
