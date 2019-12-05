package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_RequestEnterpriseRole  extends TestBase{
	
	String user1,user2, role, L2Manager = "L2TEST13", admin="TSTTEN10";
	String password = "password";
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx");
	
	@Test
	public void requestEnterpriseRoleForNormalUserTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to request enterprise role for normal user");
		
		user1 = excel.getData(1, 1, 0);
		user2 = excel.getData(1, 2, 0);
		role = excel.getData(1, 6, 0);
		ArrayList<String> ListOfUsers = new ArrayList<String>();
		ListOfUsers.add(user1);
		ListOfUsers.add(user2);
		ListOfUsers.add(L2Manager);
		//L2 manager login
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// check if normal user, L1 manager and L2 manager are displayed in list of users when logged in as L2 manager
		boolean result = userPage.checkUsersDisplayed(ListOfUsers);
		Assert.assertEquals(true, result);
		logger.pass("All 3 users displayed", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		// search for end user 
		userPage.searchEndUser(user1);
			FindRolePage rolePage = new FindRolePage(driver);
			// search for required role....add to cart.
			rolePage.searchandAddtoCart(role);
			rolePage.clickOnCheckout();
			SubmitPage submitObj = new SubmitPage(driver);
			submitObj.selectBusinessCategory();
			// submit the request
			String request_number = submitObj.submitAccessRequest();
			if (request_number.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + request_number, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for is " + request_number);
			
			homePage.logoff();
			
			Thread.sleep(2000);
			
			launch.clickOnLoginAgain();
			
			launch.login(admin, password);
			
			homePage.openApprovalInbox();
			
			ApprovalInboxPage approve = new ApprovalInboxPage(driver);
			// search with the requestID and open
			approve.searchRequestNumber(request_number);
			// accept role1
			approve.acceptFirstRole();
			result = approve.clickOnConfirm();
			Assert.assertTrue(result, "Request not approved");
			logger.pass("User request approved");
			
			homePage.clickOnHome();
			
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.searchForTaskCreatedForUser(user1);
			pendingTask.completePendingTasks(user1);
			
			System.out.println("all jobs completed.");
			homePage.logoff();
			
			
}
	
	@Test(priority=2)
	public void requestEnterpriseRoleForL1ManagerTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to request enterprise role for L1 manager");
		
		user2 = excel.getData(1, 2, 0);
		role = excel.getData(1, 6, 0);
		
		//L2 manager login
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user 
		userPage.searchEndUser(user2);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		submitObj.selectParametersFromOrganisationNode();
		// submit the request
		String request_number = submitObj.submitAccessRequest();
		if (request_number.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + request_number, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for is " + request_number);

		homePage.logoff();

		Thread.sleep(2000);

		launch.clickOnLoginAgain();

		launch.login(admin, password);

		homePage.openApprovalInbox();

		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID and open
		approve.searchRequestNumber(request_number);
		// accept role1
		approve.acceptFirstRole();
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Request not approved");
		logger.pass("User request approved");

		homePage.clickOnHome();
		
		homePage.clickOnTasksandGotoPendingTasks();
		PendingTasksPage pendingTask = new PendingTasksPage(driver);
		pendingTask.searchForTaskCreatedForUser(user1);
		pendingTask.completePendingTasks(user1);
		
		System.out.println("all jobs completed.");
		homePage.logoff();

		
}
}
