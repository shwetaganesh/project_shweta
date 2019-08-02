package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Forward_SOD_Request_ASP extends TestBase{
	
	
	UsernameGeneration userObject = new UsernameGeneration();
	String requestNumber = userObject.readRequestNumber();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String role10 = excel.getData(0, 7, 1);
	String role11 = excel.getData(0, 8, 1);
	String role12 = excel.getData(0, 9, 1);
	String role13 = excel.getData(0, 10, 1);
	String roleApprover = excel.getData(0, 16, 1); // one role approver for all roles - r10,11,12,13
	String sodApprover = excel.getData(0, 22, 1);
	String newApprover = excel.getData(0, 23, 1);
	String endUser = excel.getData(0, 27, 1);
	String password = "password",endPoint="PRKCLNT100";
	String requestor = excel.getData(0, 26, 1);
	
	
	@Test(priority=1)
	public void forwardSODRequestTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("Forward sod request");
		LaunchPage launch = new LaunchPage(driver);
		//Login to application with Requester 
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		
		//open Request Application Specific Role tab 
		/*home.openRequestApplicationSpecificRoles();
		FindUserPage userPage = new FindUserPage(driver);
		//search for end user
		userPage.searchEndUser(endUser);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.selectEndPoint(endPoint);
		rolePage.clickOnModifyExistingAccount();
		rolePage.clickOnCheckout1();
		RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
		requestAcessPage.searchAndAddSapRole(role10);
		requestAcessPage.searchAndAddSapRole(role11);
		requestAcessPage.searchAndAddSapRole(role12);
		requestAcessPage.searchAndAddSapRole(role13);
		requestAcessPage.clickOnNext();
		JustificationPage justifyPage = new JustificationPage(driver);
		justifyPage.clickOnSubmit();
		// retrieve the request number
		SubmitPage submit = new SubmitPage(driver);
		requestNumber = submit.returnRequestNumber();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number is " + requestNumber);
		// click on home*/
		//home.clickOnHome();
		home.openApprovalInbox();
		ApprovalInboxPage approvalPage = new ApprovalInboxPage(driver);
		approvalPage.searchRequestNumber(requestNumber);
		//approvalPage.clickOnAcceptAll();
		approvalPage.clickOnModifyApproversButton();
		boolean status = approvalPage.searchAndAssignNewSODApprover(newApprover);
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
	@Test(priority = 2)
	public void verifyForwardedSODRequest() throws IOException {
		
		logger = extent.createTest("Verify the presence of forwarded request in the new SOD approver's inbox");
		//requestNumber = userObject.readRequestNumber();
		LaunchPage launch = new LaunchPage(driver);
		// *** Login to application as new sod approver***
		launch.login(newApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		logger.pass("forwarded request present in new SOD approver: dah1nu - inbox", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();

		// *** login as previous SOD approver to check the request is present in his
		// approval inbox**
		launch.login(sodApprover, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		logger.pass("Request present in previous SOD approver : basqdp " + requestNumber, MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
		
	}

}
