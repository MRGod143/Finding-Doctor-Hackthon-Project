package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.TopSurgery;
import testbase.TestBaseClass;

public class TS_002 extends TestBaseClass {
	@Test
	void Top_Surgery() throws IOException, InterruptedException {
		TopSurgery tc = new TopSurgery(driver);
		tc.clickBtn();
		tc.storeData();
		tc.topSurgery();
	}

}
