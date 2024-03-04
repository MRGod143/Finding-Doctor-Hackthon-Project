package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.testng.Assert;

public class FindingDoctors extends BasePage {
	
	
	Logger log= LogManager.getLogger(this.getClass());
	
	public FindingDoctors(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search location']") 
	WebElement location;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']") 
	WebElement searchbar;	

	@FindBy(className="c-omni-suggestion-item__content__title") 
	List<WebElement> sugeestList;
	
	
	public void locationFun(String loc) throws InterruptedException {
		log.info("<--------------------- Enter a Location --------------------->");
		Thread.sleep(1000);
		location.clear();
		Thread.sleep(1000);
		location.sendKeys(loc);			
	}
	
	
	public void verifyLocation(String loc) throws InterruptedException {
		log.info("<--------------------- Verify the location --------------------->");
		Thread.sleep(1000);
		if(location.getAttribute("value").equalsIgnoreCase(loc)) {
			System.out.println("\n Location "+location.getAttribute("value")+" verified");
			System.out.println("--------------------------------------------");
		}		
		else {
			locationFun(loc);
			Thread.sleep(1000);
			for(WebElement e : sugeestList) {
				Thread.sleep(2000);
				if(e.getText().equalsIgnoreCase(loc)||e.getText().contains(loc)) {			
					e.click();
					break;
				}			
				else {
					break;
				}
			}			
		}
	}
	
	
	public void findDoc(String spl) {
		log.info("<--------------------- Finding the Doctor Specialist --------------------->");
		searchbar.clear();
		searchbar.sendKeys(spl);		
	}
	
	
	public void selectDoc(String spl) throws InterruptedException {
		Thread.sleep(1000);
		for(WebElement e : sugeestList) {
			Thread.sleep(2000);
			if(e.getText().contains(spl)||e.getText().equalsIgnoreCase(spl)) {			
				e.click();
				break;
			}
		}
			
		
	}
	
	

}
