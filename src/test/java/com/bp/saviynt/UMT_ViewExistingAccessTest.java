package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class UMT_ViewExistingAccessTest extends TestBase{
	
	String password ="password";
	String userID = "navtest1";
	
	
	@Test(priority=4)
	public void viewExisitingTest() throws Exception
	{
		logger = extent.createTest("Set up delegates for UMT user");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as end user***
		launch.login(userID, password);
		HomePage home = new HomePage(driver);
		home.openViewExistingAccess();
		ExistingAccessPage existingrole = new ExistingAccessPage(driver);
		boolean result = existingrole.existingAccessRequest(userID);
		Assert.assertEquals(result, true);
		logger.pass("Existing access page snapshot" , MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		result = existingrole.accessDetailsSnapShot();
		if(result)
			logger.pass("Access details snapshot" , MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());	
		else
			logger.fail("Error in Access details" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}

}
