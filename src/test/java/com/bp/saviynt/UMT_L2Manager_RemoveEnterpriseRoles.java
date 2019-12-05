package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_RemoveEnterpriseRoles extends TestBase {

	String user1,user2, role, L2Manager = "L2TEST13", admin="TSTTEN10";
	String password = "password";
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx");
	
	
	
	@Test(priority=1)
	public void removeEnterpriseRoleAssignedForNormalUser() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to request enterprise role for normal user");
		
		user1 = excel.getData(1, 1, 0);
		user2 = excel.getData(1, 2, 0);
		role = excel.getData(1, 6, 0);
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		
		userPage.searchEndUser(user1);
		FindRolePage rolePage = new FindRolePage(driver);
		rolePage.searchAndRemoveFromCart(role);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		
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
		
		
		homePage.clickOnTasksandGotoPendingTasks();
		PendingTasksPage pendingTask = new PendingTasksPage(driver);
		pendingTask.searchForTaskCreatedForUser(user1);
		pendingTask.completePendingTasks(user1);
		
		System.out.println("all jobs completed.");
		homePage.logoff();
	}
	
	
	@Test(priority=1)
	public void removeEnterpriseRoleAssignedForL1Manager() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to request enterprise role for normal user");
		
		user2 = excel.getData(1, 2, 0);
		role = excel.getData(1, 6, 0);
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		
		userPage.searchEndUser(user2);
		FindRolePage rolePage = new FindRolePage(driver);
		rolePage.searchAndRemoveFromCart(role);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		
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
		
		
		homePage.clickOnTasksandGotoPendingTasks();
		PendingTasksPage pendingTask = new PendingTasksPage(driver);
		pendingTask.searchForTaskCreatedForUser(user2);
		pendingTask.completePendingTasks(user2);
		
		System.out.println("all jobs completed.");
		homePage.logoff();
	}
}
