package com.bp.saviynt;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class ADD_Enterprise_Role_Request_Basic_Test extends TestBase 
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String role1 = excel.getData(0, 15, 1);
	String role2 = excel.getData(0, 16, 1);
	String requestor, end_user, line_manager, role_approver_1, role_approver_2, training_work_order_id,sod_id, admin_id;
	String password = "password1";
	boolean check_TC1 = false;
	
	@Test
	public void TC1() throws Exception 
	{
		logger = extent.createTest("New User:ADD Enterprise Role Request-Basic");
		requestor = excel.getData(0, 5, 6);
		end_user = excel.getData(0, 5, 7);
		line_manager = excel.getData(0, 5, 8);
		role_approver_1 = excel.getData(0, 6, 9);
		role_approver_2 = excel.getData(0, 7, 9);
		training_work_order_id = excel.getData(0, 5, 11);
		
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// open Request for enterprise role tab 
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
		// requester log out
		home.logoff();

		//*** Login as line manager ***
		launch.login(line_manager,password);
		// goto approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID and open
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

		// ***Login as Role manager1***
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"First Role Manager approval failed");
		logger.pass("First Role Manager had approved the request");
		// role manager1 log out
		home.logoff();

		// ***Login as Role manager2 ***
		launch.login(role_approver_2, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// First role of role manager 2 is actually 2nd role on behalf of our test case
		approve.acceptFirstRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Second Role Manager approval failed");		
		logger.pass("Second Role Manager had approved the request");
		// role manager2 logout
		home.logoff();

		// ***Login as Training work order***
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept first role
		approve.acceptFirstRole();
		// accept second role
		approve.acceptSecondRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Training work order approval failed");
		check_TC1 = true;
		logger.pass("Training work order had approved the request");	
		// training work order log out
		home.logoff();

		//*** Login as requester ***
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array lsit containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();
		// **** Verify Access Granted or not ****		
		String endpoint=excel.getData(0,5,14);
		ArrayList<String> validate_list=new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		int i=0;
		for(String s:validate_list)
		{
			validate_list.set(i,s.replace(" ","").replace("Tasksgetcreated-", ""));
			i++;
		}
		result = historyPage.validateEndPoints(arlist, validate_list);
		Assert.assertTrue(result, "All end points were not present");
		logger.pass("All endpoints were found", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		//requester logout
		home.logoff();
	}

}
