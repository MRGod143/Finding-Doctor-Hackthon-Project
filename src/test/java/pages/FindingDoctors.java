package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FindingDoctors extends BasePage {
	
	
	
	public FindingDoctors(WebDriver driver) {
		super(driver);
	}
	

	@FindBy(xpath="//div[@class='product-tab__title' and contains(text(),'Find Doctors')]") 
	WebElement findDoctorBtn;
	
	@FindBy(xpath="//input[@placeholder='Search location']") 
	WebElement location;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']") 
	WebElement searchbar;	

	@FindBy(className="c-omni-suggestion-item__content__title") 
	List<WebElement> sugeestList;
	
	
	public void clickFIndDoctor() {
		findDoctorBtn.click();
	}
	
	public void locationFun(String loc) {
		location.clear();
		location.sendKeys(loc);	
		
	}
	
	
	public void verifyLocation(String loc) throws InterruptedException {
		Thread.sleep(1000);
			
		if(location.getAttribute("value").contains(loc)) {
			System.out.println("\n ---------------------Verification------------------ \n");
			System.out.println(" Location "+location.getAttribute("value")+" verified");
			System.out.println("--------------------------------------------");
		}		
		else {
			locationFun(loc);
			Thread.sleep(1000);
			for(WebElement e : sugeestList) {
				Thread.sleep(1000);
				if(e.getText().equals(loc)) {			
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
		searchbar.clear();
		searchbar.sendKeys(spl);		
	}
	
	
	public void selectDoc(String spl) throws InterruptedException {
		Thread.sleep(1000);
		for(WebElement e : sugeestList) {
			Thread.sleep(2000);
			if(e.getText().contains(spl)) {			
				e.click();
				break;
			}
		}		
	}
}
