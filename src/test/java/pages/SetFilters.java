package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.testng.Assert;

public class SetFilters extends BasePage{

	public SetFilters(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@data-qa-id='all_filters']") 
	WebElement allFilterBtn;

	@FindBy(xpath="//span[@data-qa-id='doctor_review_count_selected']/parent::div") 
	WebElement storiesBtn;
	
	@FindBy(xpath="//span[@data-qa-id='years_of_experience_selected']") 
	WebElement expBtn;
	
	@FindBy(xpath="//div[@data-qa-id='sort_by_section']") 
	WebElement sortBtnClick;
	
	public void clickAllFilter() throws InterruptedException {
		log.info("<--------------------- Click all Filter button --------------------->");
		allFilterBtn.click();
		
	}
	
	
	public void storyFilter(String story) throws InterruptedException {		
		log.info("<--------------------- Apply filter Button --------------------->");
		Thread.sleep(1000);
		storiesBtn.click();
		Thread.sleep(1000);
		WebElement d = driver.findElement(By.xpath("//li[@aria-label='"+ story +"']"));
		d.click();
		
		System.out.println("\n Story Review Count Value " + d.getText() );
		System.out.println("--------------------------------------------");
		
	}
	
	public void experienceFilter(String exp) throws InterruptedException {
		log.info("<--------------------- Apply Experience Filter --------------------->");
		Thread.sleep(1000);
		expBtn.click();
		WebElement d = driver.findElement(By.xpath("//li[@aria-label='"+ exp +"']"));
		d.click();
		
		System.out.println("\n Experience Value " + d.getText() );
		System.out.println("--------------------------------------------");
		
	}
	
	public void feeFilter(String fee) throws InterruptedException {	
		log.info("<--------------------- Apply Fee Filter --------------------->");
		Thread.sleep(1000);
		clickAllFilter();
		Thread.sleep(1000);
		WebElement t =driver.findElement(By.xpath("//span[contains(@data-qa-id,'"+fee+"')]"));
		t.click();
		System.out.println("\n Fees Filter " + t.getText() + " Clicked " );
		System.out.println("--------------------------------------------");
		
	}
	
	public void availablityFilter(String availablity) throws InterruptedException {
		
		log.info("<--------------------- Apply the availablity --------------------->");
			Thread.sleep(1000);
			clickAllFilter();
			Thread.sleep(500);
			WebElement t = driver.findElement(By.xpath("//span[contains(@data-qa-id,'"+availablity+"')]"));
			t.click();
			
			System.out.println("\n availability Filter " + t.getText() + " Clicked " );
			System.out.println("--------------------------------------------");
		
		
	}
	
	public void sortBtnClick(String sort) throws InterruptedException {
		log.info("<--------------------- Apply Story filter --------------------->");
		Thread.sleep(2000);
		sortBtnClick.click();	
		WebElement t =driver.findElement(By.xpath("//li[contains(@aria-label,'"+sort+"')]"));
		t.click();	
		
		System.out.println("\n Sort By " + sort + " Clicked " );
		System.out.println("--------------------------------------------");
	}	
	
}
