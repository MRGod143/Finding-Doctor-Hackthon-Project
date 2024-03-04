package testng_testsuite;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.TopSurgery;
import testbase.TestBaseClass;

public class RegressionTesting extends TestBaseClass {

	@Test
	void Top_Surgery() throws IOException, InterruptedException {
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();		
		tc.topSurgery();
	}

}
