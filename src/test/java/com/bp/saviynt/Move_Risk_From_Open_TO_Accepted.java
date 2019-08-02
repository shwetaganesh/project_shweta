package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class Move_Risk_From_Open_TO_Accepted extends TestBase{
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 20, 1);
	String password = "password";
	
	@Test
	public void moveRiskFromOpenToAccepted() throws InterruptedException, IOException
	{
		logger = extent.createTest("sod rejection and recalculation");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.clickOnSOD();
		SODViolationsPage sodPage = new SODViolationsPage(driver);
		sodPage.clickOnOpenRisks();
		sodPage.filterByEndPoints();
		ArrayList<String> firstRowData = sodPage.selectFirstCheckBoxAndRetrieveFirstRowData();
		sodPage.moveRiskToAccepted();
		sodPage.selectMitigatingControl();
		sodPage.searchInRiskAccepted();
		logger.pass("mitigating control moved from open to accepted", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
		
	}
}
