package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class REMOVE_and_ADD_Enterprise_Role_Request  extends TestBase
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String role1 = excel.getData(0, 15, 1);
	String role3 = excel.getData(0, 17, 1);
	String requestor, end_user, line_manager, role_approver_1, role_approver_2, training_work_order_id,sod_id, admin_id;
	String password = "password1";
	
	
	@Test
	public void TC3() throws InterruptedException, IOException 
	{
		logger = extent.createTest("Existing User:REMOVE and ADD Enterprise Role Request");
		requestor = excel.getData(0, 8, 6);
		end_user = excel.getData(0, 8, 7);
		line_manager = excel.getData(0, 9, 8);
		role_approver_1 = excel.getData(0, 9, 9);
		training_work_order_id = excel.getData(0, 9, 11);

		//Thread.sleep(15000);// waiting for TC2 job to complete in back-end

		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester ***
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// open Request for enterprise role tab
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

		logger.pass("Request raised", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number entire " + request_number_all);
		int mid = request_number_all.length() / 2;
		home.openRequestHistory();
		RequestHistoryPage history = new RequestHistoryPage(driver);
		// Temporary store
		String request_number = request_number_all.substring(0, mid);
		String request_number_revoke_role = request_number_all.substring(mid);
		if (history.isRoleRevokeRequest(request_number)) 
		{
			String temp = request_number_revoke_role;
			request_number_revoke_role = request_number;
			request_number = temp;
		}
		System.out.println("Request Number for TC3 is " + request_number);

		System.out.println("Request Number for TC3 remove role is " + request_number_revoke_role);
		// requester log out
		home.logoff();
		
		// ***Login with Line Manager ***
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
		
		//***Login as Role manager1***
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// role manager1 approves first role
		approve.acceptFirstRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Role Manager approval failed");		
		logger.pass("Role Manager had approved the request");
		// role manager1 log out
		home.logoff();
		
		// ***Login as Training work order***
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// accept first role
		approve.acceptFirstRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Training work order approval failed");	
		//check_TC2=true;
		logger.pass("Training work order had approved the request");
		// training work order log out
		home.logoff();

		// ***Login as requester***
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array list containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();
		// **** Verify Access Granted or not ****
		String endpoint=excel.getData(0,9,14);
		ArrayList<String> validate_list=new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		int i=0;
		for(String s:validate_list)
		{
			validate_list.set(i,s.replace(" ","").replace("Tasksgetcreated-", ""));
			i++;
		}
		result = historyPage.validateEndPoints(arlist, validate_list);
		if(result)
			logger.pass("All endpoints were found for role 3", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("All endpoints were not found for role 3", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
		// goto home page
		home.clickOnHome();
		// open request history
		home.openRequestHistory();
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number_revoke_role);
		// create an array list containing all the end points in the TASK Tab.
		arlist = historyPage.clickOnTaskAndFetchEndPoints();
		// **** Verify Access Revoked or not ****
		endpoint = excel.getData(0, 8, 14);
		ArrayList<String> validate_list_revoke = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		i = 0;
		for (String s : validate_list_revoke) 
		{
			validate_list_revoke.set(i, s.replace(" ", "").replace("Tasksgetcreated-", ""));
			i++;
		}
		result = historyPage.validateEndPoints(arlist, validate_list_revoke);
		Assert.assertTrue(result, "All end points were not present for remove role R1");
		logger.pass("All endpoints were found", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		// requester logout	
		home.logoff();

	}
	
}
