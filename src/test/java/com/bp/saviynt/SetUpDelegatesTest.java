package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class SetUpDelegatesTest extends TestBase {
	
	UsernameGeneration userObject = new UsernameGeneration();
	String requestNumber, password = "password";
	
	@Test
	public void setUpDelegates() throws Exception {
		logger = extent.createTest("Set up delegates");
		LaunchPage launch = new LaunchPage(driver);
		String endUser = userObject.readUserName();
		String businessJustification = "Justification For "+endUser;
		launch.login(endUser, password);
		HomePage home = new HomePage(driver);
		home.openSetUpDelegates();
		DelegatesListPage delegates = new DelegatesListPage(driver);
		delegates.createDelegatesRequest();
		// CreateDelegatesPage delegates = new CreateDelegatesPage();
		CreateDelegatesPage createdelegates = new CreateDelegatesPage(driver);
		createdelegates.delegatesCreateRequest(businessJustification, "description", 5);
		Assert.assertTrue(delegates.delegatesListMethod());
		logger.pass("Delegates ", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}
}
