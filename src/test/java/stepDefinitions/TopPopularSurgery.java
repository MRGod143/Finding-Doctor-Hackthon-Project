package stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pages.TopSurgery;

public class TopPopularSurgery {
	WebDriver driver = BaseClass.getDriver();
	Logger log= LogManager.getLogger(this.getClass());
	TopSurgery ts = new TopSurgery(driver);
	
	@When("Click Top Surgery button")
	public void clickBtn() {
		log.info("<--------------------- Click Top Surgery button --------------------->");
		ts.clickBtn();
	}
	
	@Then("Get all Top Popular after result page")
	public void allPopularSurgery() throws IOException, InterruptedException {
		log.info("<--------------------- Get all Top Popular after result page --------------------->");
		ts.topSurgery();
	}
	@And("Store the Data into Excel")
	public void store_The_Data_Into_Excel() throws IOException {
		log.info("<--------------------- Store the Data into Excel --------------------->");
		ts.storeData();
	}

}
