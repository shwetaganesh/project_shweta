package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Remove_Assigned_Mitigating_Control extends TestBase{
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApproverR4_R5 = excel.getData(0, 19, 1);
	String  end_user;
	String password = "password";
	public String requestNumber;
	
	@Test
	public void removeMCP106Test() throws IOException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Removal of assigned mitigating control");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		boolean result = approve.removeAssignedMitigatingControl("MCP106");
		if(result)
			logger.pass("MCP106 removed successfully");
		else
			logger.fail("MCP106 removal unsuccessful", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
	}

}
