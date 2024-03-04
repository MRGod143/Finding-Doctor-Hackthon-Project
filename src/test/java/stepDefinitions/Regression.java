package stepDefinitions;


import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.*;

public class Regression {
	WebDriver driver = BaseClass.getDriver();	
	
	@When("click surgery button")
		public void click_on_top_surgeries_button_and_display_the_list(){
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();		
		
	}
	@Then("fetch all Popular after result page")
	public void fetch_all_popular_after_result_page() throws InterruptedException {
		TopSurgery tc = new TopSurgery(driver);
		tc.topSurgery();
	}
	
	
	
	


}
