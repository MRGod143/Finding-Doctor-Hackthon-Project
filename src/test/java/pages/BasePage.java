package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	Logger log= LogManager.getLogger(this.getClass());
	//Setup Page Factory
	public BasePage(WebDriver driver)
	{
		log.info("<--------------------- Page object model --------------------->");
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
}
