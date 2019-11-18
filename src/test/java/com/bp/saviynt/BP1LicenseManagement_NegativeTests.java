package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class BP1LicenseManagement_NegativeTests extends TestBase{
	
	public String admin = "TSTTEN10";
	public String password = "password";
	/*
	 * test to check if user status is inactive, add the custom properties, check if
	 * task is created or not. (Expected output : tasks should not be created)
	 */
	@Test(priority=1) 
	public void checkUserStatusAndUpdateTest() throws InterruptedException, IOException
	{
		logger =extent.createTest("Bp1 license mgmt test for : AAD|Win10|Remote|ChinaTurkeyIndonesia .... User Inactive");
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzuc6");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(!status)
		{
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperties("SLP","CN","Win10","True","RemoteUserMailbox");
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzuc6");
			boolean result = pendingTask.checkErrorMessage();
			Assert.assertEquals(true, result);
			logger.pass("Tasks not created because user is inactive" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
		}
	
	}
	
	/*
	 * check the user's employee class value, make it empty if it is EMPLOYEE or INTERNAL PARTNER,
	 * add the custom properties, check if tasks are created
	 * expected output : tasks should not be created.
	 */
	@Test(priority=2)
	public void changeEmployeeStatusAndUpdateUserTest() throws InterruptedException, IOException
	{
		logger =extent.createTest("Bp1 license mgmt test for : AAD|Win10|Remote|ChinaTurkeyIndonesia .... No data in employee class");
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzuc6");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(!status)
		{
			adminPage.clearEmployeeClassValue();
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			
			adminPage.addCustomProperties("SLP","CN","Win10","True","RemoteUserMailbox");
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzuc6");
			boolean result = pendingTask.checkErrorMessage();
			Assert.assertEquals(true, result);
			logger.pass("Tasks not created because no data in employee class" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
		}
		
	}
	
	
	
	
	
	/*
	 * change the user status to Active, check if custom properties are as mentioned for the test case, update user, check if tasks are created
	 * (expected output : tasks should be created, entitlements need to be assigned)
	 */
	@Test(priority=3)
	public void changeUserStatusAndCheckTaskCreationTest() throws InterruptedException, IOException
	{
		logger =extent.createTest("Bp1 license mgmt test for : AAD|Win10|Remote|ChinaTurkeyIndonesia.... making inactive user as active, Employee class is empty, check if the tasks are created");
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzuc6");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(!status)
		{
			adminPage.changeUserStatusAndUpdate();
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperties("SLP","CN","Win10","True","RemoteUserMailbox");
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzuc6");
			boolean result = pendingTask.checkErrorMessage();
			Assert.assertEquals(true, result);
			logger.pass("Tasks not created because user is active and no data in employee class" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
		}
		
		
		
		
	}
}
