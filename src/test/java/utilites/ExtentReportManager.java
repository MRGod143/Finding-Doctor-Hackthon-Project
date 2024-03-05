package utilites;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testbase.TestBaseClass;

public class ExtentReportManager implements ITestListener {
	WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;
	//onStart
	public void onStart(ITestContext testContext) {
		
				
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\TestNGReports\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("Finding Doctor Test Report"); // Title of report
		sparkReporter.config().setReportName("Finding Doctor Automation Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	//Success Method
	public void onTestSuccess(ITestResult result) {
	
		test = extent.createTest(result.getMethod().getRealClass().getSimpleName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report 
		//+ " -- "+result.getMethod().getMethodName().toUpperCase()
		test.log(Status.PASS,result.getName().toUpperCase() +" got successfully executed");
		try {
			TestBaseClass bc = new TestBaseClass();
			String imgPath = bc.captureScreen(result.getName()+" Test","Success");
			test.addScreenCaptureFromPath(imgPath);			
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	//Failure Method
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getMethod().getRealClass().getSimpleName()+ " -- "+result.getMethod().getMethodName().toUpperCase());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			TestBaseClass bc = new TestBaseClass();
			String imgPath = bc.captureScreen(result.getName()+" Test","Failure");
			test.addScreenCaptureFromPath(imgPath);			
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	//Skipped Method
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getRealClass().getSimpleName()+ " -- "+result.getMethod().getMethodName().toUpperCase());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());	
		try {
			TestBaseClass bc = new TestBaseClass();
			String imgPath = bc.captureScreen(result.getName()+" Test","Skipped");
			test.addScreenCaptureFromPath(imgPath);			
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	//Finish Method
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\TestNGReports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
