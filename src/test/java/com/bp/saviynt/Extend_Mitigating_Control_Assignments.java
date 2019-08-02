package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class Extend_Mitigating_Control_Assignments extends TestBase{
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 20, 1);
	String password = "password";
	
	@Test
	public void extendDateAndChangeMitigatingControl() throws InterruptedException, IOException
	{
		logger = extent.createTest("extend the date and change mitigating control test");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.clickOnSOD();
		SODViolationsPage sodPage = new SODViolationsPage(driver);
		sodPage.clickOnRiskAccepted();
		sodPage.searchInRiskAccepted();
		//sodPage.clickOnRiskToModifyDate();
		sodPage.clickOnRisk();
		sodPage.clickOnChangeButton();
		String newMC = sodPage.changeMitigatingControl();
		Assert.assertEquals(newMC, "MCCUTOVER");
		logger.pass("MCCUTOVER added successfully");
		sodPage.searchInRiskAccepted();
		logger.pass("change mitigating control test passed successfully", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}

}
