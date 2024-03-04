package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
					features= {".//Feature File"},//,".//Feature File/TopSurgery.feature",".//Feature File/FormFilling.feature"},
					glue="stepDefinitions",
					plugin= { 
							"pretty","html:reports/cucumberReport/myreport.html", 					//Cucumber Report 
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" 	//Extent Report
							
					},
					
					dryRun = false,	 	// checks mapping between scenario steps and step definition methods
					monochrome=true,	// to avoid junk characters in output
					publish = true,	// to publish report in cucumber server
					tags = "@sanity or @regression"
		)

public class TestRunner {

}
