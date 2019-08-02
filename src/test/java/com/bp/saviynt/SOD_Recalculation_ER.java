package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class SOD_Recalculation_ER extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	UsernameGeneration userObject = new UsernameGeneration();
	String role4 = excel.getData(0, 1, 1);
	String role5 = excel.getData(0, 2, 1);
	String role8 = excel.getData(0, 4, 1);
	String role9 = excel.getData(0, 5, 1);
	String roleApproverForR4 = excel.getData(0, 13, 1);
	String roleApproverForR8 = excel.getData(0, 14, 1);
	String roleApproverForR9 = excel.getData(0, 15, 1);
	String sodApproverForR4_R5 = excel.getData(0, 19, 1);
	String sodApproverForR8_R9 = excel.getData(0, 20, 1);
	String password = "password";
	String requestNumber = userObject.readRequestNumber();
	
	
	// test to reject r8 and check the recalculation of sod 
	@Test(priority =1)
	public void sodRejectionAndRecalculation() throws IOException, InterruptedException 
	{
		logger = extent.createTest("sod rejection and recalculation");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApproverForR8_R9, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.rejectFirstRole();
		Thread.sleep(2000);
		approve.addCommentsAndSubmit();
		approve.clickOnConfirmWithoutAddingMC();
		//Thread.sleep(10000);
		approve.clickOnStayInCurrentRequest();
		String header = approve.verifyPresenceOfSODHeader();
		System.out.println(header);
		if(header.contains("Total 3 Segregation of Duty Violation Found"))
			Assert.assertTrue(false,"SOD rejection failed, roles not recalculated");
		else
			logger.pass("SOD Roles recalculated: " + header, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		approve.clickOnSODApprovalTask();
		logger.pass("SOD rejected role 8", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}
	
	
	// test to add mitigating controls for r4 and r5
	@Test(priority=2)
	public void addMitigatingControls() throws InterruptedException
	{
		logger = extent.createTest("add mc to r4,r5");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApproverForR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.acceptFirstRole();
		approve.acceptSecondRole();
		approve.scrollDownToFirstMCHeader();
		// add mitigating control for first role
		approve.addMitigatingControl("MCP106");
		// click on purchase order entry header
		approve.clickOnHeaderToAddSecondMC();
		// add mitigating control for second role
		approve.modifyMitigatingControl("MCP206");
		Thread.sleep(2000);
		home.clickOnHome();
		home.logoff();
		
		// sod approver logs in to check the MC status
		launch.login(sodApproverForR8_R9, password);
		home.openApprovalInbox();
		approve.searchRequestNumber(requestNumber);
		String status = approve.verifyMitigatingControlStatus();
		System.out.println(status);
		if(status.equalsIgnoreCase("Mitigating Control Added"))
			logger.pass("MC status : Mitigating control added");
		else
			logger.fail("Issue with MC status");
		home.logoff();
	}
}
