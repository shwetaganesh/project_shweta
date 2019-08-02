package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Assign_Mitigating_Control_Test_ER  extends TestBase
{
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApproverForR4_R5 = excel.getData(0, 19, 1);
	String sodApproverForR8_R9 = excel.getData(0, 20, 1);
	String  end_user;
	String password = "password";
	public String requestNumber;
	
	@Test(priority=1)
	public void addMitigatingControlP102() throws InterruptedException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Add mitigating control for p102");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.acceptFirstRole();
		approve.acceptSecondRole();
		approve.addMitigatingControl("MCP106");
	}
	
	@Test(priority=2)
	public void checkRequestStatus() throws IOException
	{
		logger = extent.createTest("Check the request status");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR4_R5, password);
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
	public void addMitigatingControlF203() throws InterruptedException, IOException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Add mitigating control for p102");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR8_R9, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.acceptFirstRole();
		approve.acceptSecondRole();
		approve.clickOnPRECLNT100Header();
		approve.addMitigatingControl3("MCF203");
		String risk = approve.checkMitigatingControl("MCF203");
		if(risk.contains("MCF203"))
			logger.pass("Mitigating control f2032 added",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("Wrong status",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		TestBase.scrollUp(driver);
		Thread.sleep(2000);
		approve.clickOnHeaderToAddFirstMC();
		risk = approve.checkMitigatingControl("MCP106");
		if (risk.contains("MCP106"))
			logger.pass("Mitigating control p102 added", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("Wrong status", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		//approve.clickOnConfirm();
		home.logoff();
			
			
	}
}
