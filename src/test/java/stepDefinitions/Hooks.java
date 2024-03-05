package stepDefinitions;


import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;



public class Hooks {
	
	
	 WebDriver driver;
	 Properties p;
	 Logger log= LogManager.getLogger(this.getClass());
	 public boolean previousScenarioPassed = true;
     
	@Before
    public void setup() throws IOException, InterruptedException
    {
    	driver=BaseClass.initilizeBrowser();
    	log.info("<--------------------- setup --------------------->");
    	    	
    	p=BaseClass.getProperties();
    	Thread.sleep(3000);
    	driver.get(p.getProperty("appURL"));    	
    	driver.manage().window().maximize();
    
    			
	}
	
	@After 
	public void afterScenario(Scenario s) {
		log.info("<--------------------- After Every Scenario --------------------->");
		if(s.isFailed()) {
			previousScenarioPassed = false;
		}
		
	}
		
    
    @After
    public void tearDown(Scenario scenario) {
    	log.info("<--------------------- Driver Close --------------------->");
        		
       driver.quit();
       
    }    
   
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {									// failure scenario 
        	log.info("<--------------------- Create Failure report & Takes Screenshot  --------------------->");
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	
        	            
        }
        else {														// Success scenario 
        	log.info("<--------------------- Create Success report & Takes Screenshot  --------------------->");
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());       
        	
        }
      
    }
    
    
}
