package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class TestCase1 extends TestBase 
{

	ExcelOperations excel = new ExcelOperations(".\\Test Data\\SAVIYNT - Test Scenarios 300818.xlsx");

	String role1 = excel.getData(0, 15, 1);
	String role2 = excel.getData(0, 16, 1);
	String role3 = excel.getData(0, 17, 1);
	String role4 = excel.getData(0, 18, 1);
	String role5 = excel.getData(0, 19, 1);

	String requestor, end_user, line_manager, role_approver_1, role_approver_2, training_work_order_id,sod_id;
	String password = "password1";

	@Test(priority = 1)
	public void TC1() throws Exception 
	{
		logger = extent.createTest("TC1");
		requestor = excel.getData(0, 5, 6);
		end_user = excel.getData(0, 5, 7);
		line_manager = excel.getData(0, 5, 8);
		role_approver_1 = "RGTSR01"; // Get from Excel
		role_approver_2 = "RGTSR02"; // Get from Excel
		training_work_order_id = excel.getData(0, 5, 11);

		LaunchPage launch = new LaunchPage(driver);
		// **** Login to application with Requestor ****
		launch.login(requestor, password);

		HomePage home = new HomePage(driver);
		// *** open Request for enterprise role tab ***
		home.openRequestEnterpriseRole();

		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);

		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role1);
		rolePage.searchandAddtoCart(role2);
		// click on check out button
		rolePage.clickOnCheckout();

		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		String request_number = submitObj.submitAccessRequest();
		if (request_number.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + request_number, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

		System.out.println("Request Number for TC1 is " + request_number);

		// requestor log out
		home.logoff();

		// login as line manager
		launch.login(line_manager, password);

		// goto approval inbox
		home.openApprovalInbox();

		ApprovalInboxPage approve = new ApprovalInboxPage(driver);

		// search with the requestID
		approve.searchRequestNumber(request_number);

		// accept role1
		approve.acceptFirstRole();
		// accept role2
		approve.acceptSecondRole();
		// click on confirm
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Line Manager approval failed");

		logger.pass("Line Manager had approved the request");

		// line manager log out
		home.logoff();

		// login as Role manager1
		launch.login(role_approver_1, password);

		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		// role manager1 log out
		home.logoff();

		// login as Role manager2
		launch.login(role_approver_2, password);

		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		/*
		 * First role of role manager 2 is actually 2nd role on behalf of our
		 * test case
		 */
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		home.logoff();

		// login as Training work order
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept first role
		approve.acceptFirstRole();
		// accept second role
		approve.acceptSecondRole();
		approve.clickOnConfirm();
		// training work order log out
		home.logoff();

		// login as requestor
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();

		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array lsit containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();

		// **** Verify Access Granted or not ****
		ArrayList<String> validate_list = new ArrayList<String>();
		validate_list.add("PR5CLNT100");
		validate_list.add("PROCLNT100");
		validate_list.add("WR5CLNT100");
		validate_list.add("RROCLNT100");
		validate_list.add("WROCLNT100");

		result = historyPage.validateEndPoints(arlist, validate_list);
		Assert.assertTrue(result, "All end points were not present");

		logger.pass("All endpoints were found", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

	}

	//@Test(priority = 2)
	public void TC2() throws IOException 
	{
		logger = extent.createTest("TC2");
		requestor = excel.getData(0, 6, 6);
		end_user = excel.getData(0, 6, 7);
		line_manager = excel.getData(0, 6, 8);
		role_approver_1 = excel.getData(0, 6, 9);
		role_approver_2 = excel.getData(0, 7, 9);
		training_work_order_id = excel.getData(0, 6, 11);

		LaunchPage launch = new LaunchPage(driver);

		// **** Login to application with Requestor ****
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// *** open Request for enterprise role tab ***
		home.openRequestEnterpriseRole();

		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role1);
		rolePage.searchandAddtoCart(role2);
		// click on check out button
		rolePage.clickOnCheckout();

		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		String request_number = submitObj.submitAccessRequest();
		if (request_number.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + request_number, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

		System.out.println("Request Number for TC2 is " + request_number);
		// requester log out
		home.logoff();

		// login as line manager
		launch.login(line_manager, password);

		// goto approval inbox
		home.openApprovalInbox();

		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(request_number);

		// accept role1
		approve.acceptFirstRole();
		// accept role2
		approve.acceptSecondRole();
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Line Manager approval failed");
		logger.pass("Line Manager had approved the request");
		// line manager log out
		home.logoff();

		// login as Role manager1
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		// role manager1 log out
		home.logoff();

		// login as Role manager2
		launch.login(role_approver_2, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		/*
		 * First role of role manager 2 is actually 2nd role on behalf of our
		 * test case
		 */
		approve.rejectFirstRole();
		approve.clickOnConfirm();
		home.logoff();

		// login as Training work order
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept first role
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		// training work order log out
		home.logoff();

		// login as requester
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();

		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array list containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();

		// **** Verify Access Granted or not ****
		ArrayList<String> validate_list = new ArrayList<String>();
		validate_list.add("PR5CLNT100");
		validate_list.add("PROCLNT100");
		validate_list.add("RROCLNT100");
		result = historyPage.validateEndPoints(arlist, validate_list);
		Assert.assertTrue(result, "All end points were not present");

		logger.pass("All endpoints were found", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}

	//@Test(priority = 3)
	public void TC3() throws InterruptedException, IOException 
	{
		logger = extent.createTest("TC3");
		requestor = excel.getData(0, 8, 6);
		end_user = excel.getData(0, 8, 7);
		line_manager = excel.getData(0, 9, 8);
		role_approver_2 = excel.getData(0, 9, 9);
		training_work_order_id = excel.getData(0, 9, 11);

		Thread.sleep(15000);// waiting for TC2 job to complete in back-end

		LaunchPage launch = new LaunchPage(driver);

		// **** Login to application with Requester ****
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// *** open Request for enterprise role tab ***
		home.openRequestEnterpriseRole();

		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....remove from cart.
		rolePage.searchAndRemoveFromCart(role1);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role3);
		// click on check out button
		rolePage.clickOnCheckout();

		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		String request_number_all = submitObj.submitAccessAndRevokeRequest();

		if (request_number_all.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");

		logger.pass("Request raised ", MediaEntityBuilder.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

		System.out.println("Request Number entire " + request_number_all);

		int mid = request_number_all.length() / 2;

		home.openRequestHistory();

		RequestHistoryPage history = new RequestHistoryPage(driver);
		// Temporary store
		String request_number = request_number_all.substring(0, mid);

		String request_number_revoke_role = request_number_all.substring(mid);
		
		//Check which one is revoke request and then swap id needed
		if (history.isRoleRevokeRequest(request_number)) 
		{
			String temp = request_number_revoke_role;

			request_number_revoke_role = request_number;

			request_number = temp;
		}

		System.out.println("Request Number for TC3 is " + request_number);

		System.out.println("Request Number for TC3 remove role is " + request_number_revoke_role);

		Reporter.log("Request Number for TC3 new role is " + request_number);

		Reporter.log("Request Number for TC3 remove role is " + request_number_revoke_role);

		// requester log out
		home.logoff();
		
		// **** Login with Line Manager ****
		launch.login(line_manager, password);
		// goto approval inbox
		home.openApprovalInbox();

		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept role1
		approve.acceptFirstRole();
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Line Manager approval failed");
		logger.pass("Line Manager had approved the request");
		// line manager log out
		home.logoff();
		
		// login as Role manager2
		launch.login(role_approver_2, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		// role manager1 log out
		home.logoff();
		
		// login as Training work order
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept first role
		approve.acceptFirstRole();
		approve.clickOnConfirm();
		// training work order log out
		home.logoff();

		// login as requester
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();

		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array list containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();

		// **** Verify Access Granted or not ****
		ArrayList<String> validate_list = new ArrayList<String>();
		validate_list.add("PR5CLNT100");
		validate_list.add("PROCLNT100");
		validate_list.add("RROCLNT100");
		result = historyPage.validateEndPoints(arlist, validate_list);
		Assert.assertTrue(result, "All end points were not present");

		logger.pass("All endpoints were found", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

	}

	//@Test(priority=4)
	public void TC4() throws IOException, InterruptedException
	{		
		logger = extent.createTest("TC4");
		requestor = excel.getData(0, 10, 6);
		end_user = excel.getData(0, 10, 7);
		//line_manager = excel.getData(0, 9, 8);
		role_approver_1 = excel.getData(0,10, 9);
		training_work_order_id = excel.getData(0, 10, 11);
		sod_id= excel.getData(0, 10, 12);


		LaunchPage launch = new LaunchPage(driver);

		// **** Login to application with Requester ****
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// *** open Request for enterprise role tab ***
		home.openRequestEnterpriseRole();

		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role4);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role5);
		// click on check out button
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		
		submitObj.nonRegressionRoleRequest();
		// submit the request
		String request_number = submitObj.submitAccessRequest();
		if (request_number.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + request_number, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());

		System.out.println("Request Number for TC4 is " + request_number);
		// requester log out
		home.logoff();
		
		// login as Role manager1
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		// role manager1 approves second role
		approve.acceptSecondRole();
		approve.clickOnConfirm();
		// role manager1 log out
		home.logoff();
		
		
		// login as Role manager1
		/*launch.login(sod_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);*/
	}
	
}
