package testng_testsuite;

import java.io.IOException;

import org.testng.annotations.*;
import pages.*;
import testbase.TestBaseClass;




public class SmokeTest extends TestBaseClass {

	
	
	@Test(priority = 0)
	void Finding_Doctor() throws InterruptedException {
		FindingDoctors fd = new FindingDoctors(driver);
		fd.locationFun("Chennai");		
		fd.findDoc("Dentist");
		fd.selectDoc("Dentist");
	}
	@Test(priority = 1)
	void Top_Surgery() throws IOException, InterruptedException {
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();		
		tc.topSurgery();
	}

	@Test(priority = 2)
	void Wellness_Form() {
		SetFormValue form = new SetFormValue(driver);
		form.clickForCorporate();
	}

	

	
	


}
