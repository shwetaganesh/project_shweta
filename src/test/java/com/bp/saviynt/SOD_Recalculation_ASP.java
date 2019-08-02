package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class SOD_Recalculation_ASP  extends TestBase

{
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String role10 = excel.getData(0, 7, 1);
	String role11 = excel.getData(0, 8, 1);
	String role12 = excel.getData(0, 9, 1);
	String role13 = excel.getData(0, 10, 1);
	String roleApprover = excel.getData(0, 16, 1); // one role approver for all roles - r10,11,12,13
													// requestor and role approver are same.
	String sodApprover = excel.getData(0, 22, 1);
	String endUser = excel.getData(0, 27, 1);
	String password = "password";
	public String requestNumber = userObject.readRequestNumber();
	String requestor = excel.getData(0, 26, 1);
	
	
	@Test(priority=1)
	public void sodRejectionAndRecalculation() throws IOException, InterruptedException 
	{
		logger = extent.createTest("sod rejection and recalculation");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.rejectSecondRole();
		Thread.sleep(2000);
		approve.addCommentsAndSubmit();
		approve.rejectFourthRole();
		Thread.sleep(2000);
		approve.addCommentsAndSubmit();
		approve.clickOnConfirmWithoutAddingMC();
		//Thread.sleep(10000);
		approve.clickOnStayInCurrentRequest();
		String header = approve.verifyPresenceOfSODHeader();
		System.out.println(header);
		if(header.contains("Total 8 Segregation of Duty Violation Found"))
			Assert.assertTrue(false,"SOD rejection failed, roles not recalculated");
		else
			logger.pass("SOD Roles recalculated: " + header, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		approve.clickOnSODApprovalTask();
		logger.pass("SOD rejected role 12 and role 13", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}

}
