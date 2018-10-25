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

public class ADD_Enterprise_Role_Request_Escalation_And_Rejection extends TestBase{
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String role1 = excel.getData(0, 15, 1);
	String role2 = excel.getData(0, 16, 1);
	String requestor, end_user, line_manager, role_approver_1, role_approver_2, training_work_order_id,sod_id, admin_id;
	String password = "password1";
	
	@Test
	public void TC2() throws IOException, InterruptedException 
	{
		logger = extent.createTest("New User:ADD Enterprise Role Request-Escalation and Rejection");
		requestor = excel.getData(0, 6, 6);
		end_user = excel.getData(0, 6, 7);
		line_manager = excel.getData(0, 6, 8);
		role_approver_1 = excel.getData(0, 6, 9);
		role_approver_2 = excel.getData(0, 7, 9);
		training_work_order_id = excel.getData(0, 6, 11);

		LaunchPage launch = new LaunchPage(driver);
		// *** Login to application with Requester ***
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		//	open Request for enterprise role tab
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

		// ***Login as line manager ***
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
		
		Thread.sleep(120000);
		
		// ***Login as Role manager2*** (ESCALATION)
		launch.login(role_approver_2, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		approve.acceptFirstRole();
		//reject second rle
		approve.rejectSecondRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Escalation manager operation failed");		
		logger.pass("Escalation manager accepted role1 and rejected role2");
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
		logger.pass("Training work order had approved the request");
		// training work order log out
		home.logoff();

		//***Login as requester***
		launch.login(requestor, password);
		// open request history
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(request_number);
		// create an array list containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();
		// **** Verify Access Granted or not ****
		String endpoint=excel.getData(0,6,14);
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
		// requester logout
		home.logoff();
	}
	//@Test(priority=2)
	public void JobTrigger()
	{
		logger = extent.createTest("Trigger Job");
		admin_id = excel.getData(3, 36, 1);
		LaunchPage launch = new LaunchPage(driver);
		//*** Login as Admin***
		launch.login(admin_id, password);
		HomePage home = new HomePage(driver);
		//open admin tab
		home.openAdminTab();
		AdminPage adminpage = new AdminPage(driver);
		//open job control panel
		adminpage.openJobControlPanelLink();
		// open utility link
		adminpage.openUtility();
		logger.pass("Job Trigger Scheduled Successfully");
		//log off
		home.logoff();
	}
}
