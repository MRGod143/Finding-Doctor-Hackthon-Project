package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilites.ExcelUtilts;

public class TopSurgery extends BasePage{

	public TopSurgery(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath="//div[@class='product-tab']/a[@title='surgery']") 
	WebElement surgeryBtn;
	
	@FindBy(className="SurgicalSolutions-module_ailmentItemWrapper__Krx-u") 
	List<WebElement> listItems;
	
	public void clickBtn() {
		
	
		log.info("<--------------------- Click Surgery  --------------------->");
			surgeryBtn.click();			
			scrollFun();
		
		
	}
	public void scrollFun() {
		JavascriptExecutor jss = (JavascriptExecutor) driver;
		jss.executeScript("window.scrollBy(0,600)");
	}
	
	
	public void topSurgery() throws InterruptedException {
		log.info("<--------------------- Display all Top surgeries --------------------->");
		System.out.println("\n --------------- Top Surgery --------------- \n");
		
		Thread.sleep(2000);
		for(WebElement s : listItems) {
			System.out.println("\n\t"+s.getText());
		}
		System.out.println("---------------------------------------- ");
		
		
	}
	public void storeData() throws IOException {
		ExcelUtilts.wirteData("Top Surgery",listItems,0);
	}
	
	

}
