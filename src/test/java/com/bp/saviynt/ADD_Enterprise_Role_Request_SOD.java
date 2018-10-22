package com.bp.saviynt;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class ADD_Enterprise_Role_Request_SOD extends TestBase
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String role4 = excel.getData(0, 18, 1);
	String role5 = excel.getData(0, 19, 1);
	String requestor, end_user, line_manager, role_approver_1, role_approver_2, training_work_order_id,sod_id, admin_id;
	String password = "password1";
	
	@Test
	public void TC4() throws IOException, InterruptedException
	{	
		logger = extent.createTest("Existing User:ADD Enterprise Role Request-SOD");
		requestor = excel.getData(0, 10, 6);
		end_user = excel.getData(0, 10, 7);
		role_approver_1 = excel.getData(0,10, 9);
		training_work_order_id = excel.getData(0, 10, 11);
		sod_id= excel.getData(0, 10, 12);
		
		LaunchPage launch = new LaunchPage(driver);
		//  Login to application with Requester 
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		//  open Request for enterprise role tab 
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
		
		// ***Login as Role manager1***
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
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Role Manager approval failed");		
		logger.pass("Role Manager had approved the request");
		// role manager1 log out
		home.logoff();	
		
		// ***Login as SOD ***
		launch.login(sod_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(request_number);
		// approve first role
		approve.acceptFirstRole();
		// approve second role
		approve.acceptSecondRole();
		// add mitigating control for first role
		approve.addMitigatingControl();
		// click on purchase order entry header
		approve.clickPurchaseOrderEntryHeader();
		// add mitigating control for second role
		approve.addMitigatingControl2();
		Thread.sleep(2000);
		approve.rejectAllRole();
		// click on confirm
		 result = approve.clickOnConfirm();
		Assert.assertTrue(result, "SOD approval failed");
		logger.pass("SOD approved the request");
		home.logoff();
		
	}
}
