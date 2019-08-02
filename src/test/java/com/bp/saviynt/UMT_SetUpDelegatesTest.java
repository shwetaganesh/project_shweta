package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class UMT_SetUpDelegatesTest extends TestBase{
	
	String password ="password";
	String userID = "navtest1";
	
	
	@Test
		public void setUpDelegatesList() throws Exception
		{
			logger = extent.createTest("set up delegates by UMT user");
			String businessJustification = "Justification For "+userID;
			LaunchPage launch = new LaunchPage(driver);
			//*** Login to application as end user***
			launch.login(userID, password);
			HomePage home = new HomePage(driver);
			home.openSetUpDelegates();
			DelegatesListPage delegates = new DelegatesListPage(driver);
			delegates.createDelegatesRequest();
			CreateDelegatesPage createDelegate = new CreateDelegatesPage(driver);
			createDelegate.delegatesCreateRequest(businessJustification,"description",5);
			Assert.assertTrue(delegates.delegatesListMethod());
			logger.pass("Delegates ", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			home.logoff();		
		}

}
