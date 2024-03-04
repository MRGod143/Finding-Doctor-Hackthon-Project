package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

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
	 public boolean previousScenarioPassed = true;
     
	@Before
    public void setup() throws IOException, InterruptedException
    {
    	driver=BaseClass.initilizeBrowser();
    	    	
    	p=BaseClass.getProperties();
    	Thread.sleep(3000);
    	driver.get(p.getProperty("appURL"));    	
    	driver.manage().window().maximize();
    
    			
	}
	
	@After 
	public void afterScenario(Scenario s) {
		if(s.isFailed()) {
			previousScenarioPassed = false;
		}
		
	}
		
    
    @After
    public void tearDown(Scenario scenario) {
        		
       driver.quit();
       
    }
    
    //@AfterStep
    public void captureScreen(String name, String folderName) 
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd.hh.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ folderName+ "\\"+ name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
//		return targetFilePath;
	}
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	//captureScreen(scenario.getName(),"failure");
        	            
        }
        else {
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        	//captureScreen(scenario.getName(),"success");
        	
        }
      
    }
    
    
}
