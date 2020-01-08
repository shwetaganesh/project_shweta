package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_SetUpDelegates extends TestBase{
	
	String user1,user2, role, L2Manager = "L2TEST13", admin="TSTTEN10";
	String password = "password";
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx");

	@Test
	public void setUpDelegateTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to setup delegate for normal user and first line manager");
		
		user1 = excel.getData(1, 1, 0);
		user2 = excel.getData(1, 2, 0);
		String businessJustification = "Justification For "+user1;
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as end user***
		launch.login(L2Manager, password);
		HomePage home = new HomePage(driver);
		home.openSetUpDelegates();
		DelegatesListPage delegates = new DelegatesListPage(driver);
		delegates.createDelegatesRequest();
		CreateDelegatesPage createDelegate = new CreateDelegatesPage(driver);
		createDelegate.delegatesCreateRequest(businessJustification,"description",5);
		Assert.assertTrue(delegates.delegatesListMethod());
		logger.pass("Delegates ", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}
}
