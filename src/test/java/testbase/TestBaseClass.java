package testbase;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import factory.BaseClass;


public class TestBaseClass {
	
	public static WebDriver driver;	
	public Properties p;
	public Logger log= LogManager.getLogger(this.getClass());
	@BeforeClass
//	@Parameters({"os","browser"})
	public void setup() throws IOException  {
		log = BaseClass.getLogger();
		
		FileInputStream	file = new FileInputStream(".//src/test/resources/config.properties");
		
		p = new Properties();
		p.load(file);
		log.info("<-------------------Launching The Browser---------------->");
		String br=p.getProperty("browser"),os =p.getProperty("os");
		// Chrome browser
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			//For Selecting OS:
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os...");
				return;
			}
			//For Selecting Browser:
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge": cap.setBrowserName("edge"); break;
			default:System.out.println("No Matched Browser");
			return;
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444"), cap);
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
			{
			switch(br.toLowerCase()) {
			case "chrome" :driver=new ChromeDriver(); break;
			case "edge" :driver=new EdgeDriver(); break;
			default: System.out.println("No Matching Browser");
			return;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}	
	
	
	@AfterClass
	public void close() {
		driver.quit();
	}	
	

	@BeforeMethod
	public void waitFun() throws InterruptedException {	
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		//driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(10));
		
	}
	
	
	
	public String captureScreen(String name, String folderName) 
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd.hh.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\TestNG Screenshots\\"+ folderName+ "\\"+ name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
