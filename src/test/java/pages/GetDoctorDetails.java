package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilites.ExcelUtilts;

public class GetDoctorDetails extends BasePage{

	public GetDoctorDetails(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[@class='info-section']") 
	List<WebElement> doctorInfoList;

	public void scrollFun() {
		JavascriptExecutor jss = (JavascriptExecutor) driver;
		jss.executeScript("window.scrollBy(0,600)");
	}
	public void doctorInfo() throws IOException {
		scrollFun();
		List<WebElement> topDoctors = new ArrayList<>();
		log.info("<--------------------- All the doctor Details --------------------->");
		for(WebElement e : doctorInfoList) {
			if(e.getText().contains("AD")) {
				System.out.println("AD occurs");
				continue;
				
			}
			else {
				topDoctors.add(e);
			}
		}
		
		
		System.out.println("\n --------------- Doctors List --------------- \n");
		if(topDoctors.size()!=0) {
			ExcelUtilts.wirteData("Doctor", topDoctors, 0);
			for(int i=0; i<5;i++) {
				System.out.println("\nDoctor  "+ (i+1) +" Info : \n") ;
				String[] sepDetails = topDoctors.get(i).getText().split("\n");
				for(String a:sepDetails) {
					System.out.println("\t"+a);					
				}
				
				System.out.println("--------------------------------------------");
			}
		}
		
	}
}
