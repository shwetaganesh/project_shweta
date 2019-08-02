package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Validate_AddMitigatingControlButton_TaskHistory_ForERoles extends TestBase {

	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and
																						// other information for new
																						// saviynt SOD scenarios
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
	String end_user,password = "password";
	public String requestNumber;

	@Test(priority = 1)
	public void createRequestForSODApprovalTest() throws IOException, InterruptedException {
		logger = extent.createTest("create request for SOD approval");
		end_user = "rol5mk";
		System.out.println("end user  is: " + end_user);

		LaunchPage launch = new LaunchPage(driver);
		// *** Login to application with Requester***
		launch.login(end_user, password);
		HomePage home = new HomePage(driver);
		// open Request for enterprise role tab
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role4);
		rolePage.searchandAddtoCart(role5);
		rolePage.searchandAddtoCart(role8);
		rolePage.searchandAddtoCart(role9);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		submitObj.selectParametersFromOrganisationNode();
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number is " + requestNumber);
		userObject.writeRequestNumber(requestNumber);
		// requester log out
		home.logoff();
	}

	@Test(priority = 2)
	public void requestApprovalByRoleApproverAndTrainingApprover()
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Request approval by role approvers");
		LaunchPage launch = new LaunchPage(driver);
		// ** login as role approver for R4 & R5
		launch.login(roleApproverForR4, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// role approver approves second role
		approve.acceptSecondRole();
		// ensure the presence of three sods
		String header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		boolean status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Role Manager approval failed");
		logger.pass("Role Manager has approved the request for role4 and role5");
		// role approver log out
		home.logoff();

		// ** login as role approver for R8
		launch.login(roleApproverForR8, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Role Manager approval failed");
		logger.pass("Role Manager has approved the request for role8");
		// role approver log out
		home.logoff();

		// ** login as role approver for R9
		launch.login(roleApproverForR9, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Role Manager approval failed");
		logger.pass("Role Manager has approved the request for role8");
		// role approver log out
		home.logoff();
		
		// ** login as role approver for R4 & R5 -- role approver and training approver
		// same for R4 and R5
		launch.login(roleApproverForR4, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// role approver approves second role
		approve.acceptSecondRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Training work order approval failed");
		logger.pass("Training approver has approved the request for role4 and role5");
		// role approver log out
		home.logoff();
	}
	
	@Test(priority=3)
	public void requestApprovalBySODApprover() throws InterruptedException, IOException
	{
		requestNumber = userObject.readRequestNumber();
		logger = extent.createTest("Request approval by SOD approver");
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
		approve.verifyPresenceOfFirstAddMitigatingControlButton();
		Thread.sleep(2000);
		home.clickOnHome();
		home.logoff();
		
		launch.login(sodApproverForR8_R9, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.acceptFirstRole();
		approve.acceptSecondRole();
		approve.clickOnPRECLNT100Header();
		approve.verifyPresenceOfThirdAddMitigatingControlButton();
		Thread.sleep(2000);
		home.clickOnHome();
		home.logoff();
		
		launch.login(sodApproverForR4_R5, password);
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(requestNumber);
		historyPage.getTaskHistorySnapShot();
		home.logoff();
		
	}

}
