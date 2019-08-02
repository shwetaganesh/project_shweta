package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Assign_Mitigating_Control_Test_ASP extends TestBase {
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 22, 1);
	String  end_user;
	String password = "password";
	public String requestNumber = userObject.readRequestNumber();
	
	@Test(priority=1)
	public void addMitigatingControlC101() throws InterruptedException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Add mitigating control for C101");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.clickOnAcceptAll();
		approve.addMitigatingControl("MCC101");
		logger.pass("Mitigating control added");
	}
	
	@Test(priority=2)
	public void checkRequestStatus() throws IOException
	{
		logger = extent.createTest("Check the request status");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openRequestHistory();
		RequestHistoryPage historypage = new RequestHistoryPage(driver);
		String status = historypage.searchRequestNumberAndCheckStatus(requestNumber);
		System.out.println(status);
		if(status.contains("Open"))
			logger.pass("Request still open",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("Wrong status",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}
	
	@Test(priority=3)
	public void addMitigatingControlF103() throws InterruptedException, IOException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Add mitigating control for f103");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver ***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		//approve.clickOnAcceptAll();
		approve.clickOnPRKCLNT100Header();
		approve.addMitigatingControl4("MCF103");
		String risk = approve.checkMitigatingControl("MCF103");
		System.out.println(risk);
		if(risk.contains("MCF103"))
			logger.pass("Mitigating control mcf103 added",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("Wrong status",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
			
			
	}
	
	@Test(priority=4)
	public void checkMitigatingControl() throws IOException
	{
	
		logger = extent.createTest("check if the added mitigating control is MCC101");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver ***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		String risk = approve.checkMitigatingControl("MCC101");
		System.out.println(risk);
		if (risk.contains("MCC101"))
			logger.pass("Mitigating control c101 added", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("Wrong status", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		//approve.clickOnConfirm();
		home.logoff();
	}

}
