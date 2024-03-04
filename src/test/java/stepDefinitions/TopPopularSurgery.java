package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.TopSurgery;

public class TopPopularSurgery {
	WebDriver driver = BaseClass.getDriver();
	TopSurgery ts = new TopSurgery(driver);
	
	@When("Click Top Surgery button")
	public void clickBtn() {
		ts.clickBtn();
	}
	
	@Then("Get all Top Popular after result page")
	public void allPopularSurgery() throws IOException, InterruptedException {
		ts.topSurgery();
	}
	@And("Store the Data into Excel")
	public void store_The_Data_Into_Excel() throws IOException {
		ts.storeData();
	}

}
