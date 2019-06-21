package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class ViewExistingAccessTest extends TestBase {

	UsernameGeneration userObject = new UsernameGeneration();
	String password = "password";
	
	@Test
	public void viewExistingAccess() throws IOException {
		
		logger = extent.createTest("View the existing access of the end user");
		LaunchPage launch = new LaunchPage(driver);
		String endUser = userObject.readUserName();
		launch.login(endUser, password);
		HomePage home = new HomePage(driver);
		home.openViewExistingAccess();
		ExistingAccessPage existingrole = new ExistingAccessPage(driver);
		String userID = existingrole.existingAccessRequest();
		System.out.println(userID);
		Assert.assertEquals(endUser, userID);
		logger.pass("Existing access page snapshot" , MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}

}
