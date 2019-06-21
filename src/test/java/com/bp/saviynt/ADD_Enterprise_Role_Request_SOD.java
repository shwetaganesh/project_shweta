package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class ADD_Enterprise_Role_Request_SOD extends TestBase
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration userObject = new UsernameGeneration();
	String role4 = excel.getData(0, 24, 1);
	String role5 = excel.getData(0, 25, 1);
	String userName,requestor, end_user, role_approver_1, training_work_order_id,sod_id, admin_id;
	String password = "password";
	String requestNumber;
	
	@Test
	public void TC4() throws IOException, InterruptedException
	{	
		logger = extent.createTest("Existing User:ADD Enterprise Role Request-SOD");
		requestor = excel.getData(0, 10, 6);
		//end_user = excel.getData(0, 10, 7);
		userName = userObject.readUserName();
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
		userPage.searchEndUser(userName);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		//rolePage.searchandAddtoCart(role4);
		rolePage.addRolesToCartAfterReordering(role4);
		// search for required role....add to cart.
		//rolePage.searchandAddtoCart(role5);
		rolePage.addRolesToCartAfterReordering(role5);
		// click on check out button
		rolePage.clickOnCheckout1();
		SubmitPage submitObj = new SubmitPage(driver);		
		submitObj.selectParametersFromOrganisationNode();
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for TC4 is " + requestNumber);
		// requester log out
		home.logoff();
		
		// ***Login as Role manager1***
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
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
		approve.searchRequestNumber(requestNumber);
		// approve first role
		approve.acceptFirstRole();
		// approve second role
		approve.acceptSecondRole();
		//TestBase.scrollDownToElement(driver, element);
		//click on confirm without adding mitigating control to check whether pop-up message comes stating "Please add mitigating controls to violations"
		approve.clickOnConfirmWithoutAddingMC();
		Thread.sleep(2000);
		//check for pop-up message display
		String message = approve.checkForPopUpMessage();
		if(message.equals("")) 
			Assert.assertTrue(false, "message not displayed");
		else
			logger.pass("" + message);
		
		// add mitigating control for first role
		approve.addMitigatingControl("EPOMC001");
		// click on purchase order entry header
		approve.clickOnHeaderToAddSecondMC();
		// add mitigating control for second role
		approve.addMitigatingControl2("EPOMC002");
		Thread.sleep(2000);
		
		// click on confirm
		 result = approve.clickOnConfirm();
		Assert.assertTrue(result, "SOD approval failed");
		logger.pass("SOD approved the request");
		home.logoff();
		
	}
	
	@Test(priority=2)
	public void completePendingTasks() throws InterruptedException
	{
		logger = extent.createTest("Discontinue All Tasks");
		//**login as admin
		LaunchPage launch = new LaunchPage(driver);
		admin_id = "TSTTEN10";
		launch.login(admin_id, password);
		HomePage home = new HomePage(driver);
		home.openPendingTasks();
		PendingTasksPage taskpage = new PendingTasksPage(driver);
		taskpage.discontinueAllTasks(userName);
		Thread.sleep(2000);
		home.logoff();
	}
}
