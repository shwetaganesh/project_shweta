package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Forward_SOD_Request_ER extends TestBase{
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApproverForR4_R5 = excel.getData(0, 19, 1);
	String sodApproverForR8_R9 = excel.getData(0, 20, 1);
	String newApprover = excel.getData(0, 21, 1);
	String password = "password";
	public String requestNumber;
	
	
	// forward sod request to another sod approver
	@Test(priority =1)
	public void forwardSODRequest() throws IOException, InterruptedException 
	{
		logger = extent.createTest("Forward sod access request");
		requestNumber = userObject.readRequestNumber();
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.clickOnModifyApproversButton();
		boolean status = approve.searchAndAssignNewSODApprover(newApprover);
		if(status)
			logger.pass("New SOD approver added to the request. SOD request forwarded");
		else
			logger.fail("SOD forwarding failed");
		home.logoff();
	}
	
	/*
	 * test to check whether the forwarded request is available in the new
	 * approver's inbox
	 */
	@Test(priority=2)
	public void verifyForwardedSODRequest() throws IOException
	{
		logger = extent.createTest("Verify the presence of forwarded request in the new SOD approver's inbox");
		requestNumber = userObject.readRequestNumber();
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as new sod approver***
		launch.login(newApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		logger.pass("forwarded request present in new SOD approver: sisor7 - inbox", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
		
		//*** login as previous SOD approver to check the request is present in his approval inbox**
		launch.login(sodApproverForR4_R5, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		logger.pass("Request present in previous SOD approver : rahms8 " + requestNumber, MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
		
		
	}
}
