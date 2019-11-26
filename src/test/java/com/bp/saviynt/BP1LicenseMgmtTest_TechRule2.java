package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class BP1LicenseMgmtTest_TechRule2 extends TestBase{
	String admin = "TSTTEN10";
	String password = "password";
	

	@Test(priority=1)
	
	public void checkCreationOfAddAccessTasks() throws InterruptedException, IOException
	{
		ArrayList<String> ListOfEntitlement = new ArrayList<String>();
		
		ListOfEntitlement.add("G SLP O365 Users");
		
		ListOfEntitlement.add("G GBL WTP0365LICENSING");
		ListOfEntitlement.add("G O365 Exo licensing");
		
		logger =extent.createTest("Bp1 license mgmt test for :AAD|Win10|Remote|ExceptChinaTurkeyIndonesia");
		
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzuac");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(status)
		{
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperties("SLP","IN","Win10","True","RemoteUserMailbox");
			
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzuac");
			pendingTask.getPendingTaskDetails("zzzuac",ListOfEntitlement);
			homePage.clickOnHome();
			homePage.openAdminTab();
			
			adminPage.openJobControlPanelLink();
			adminPage.openUtilityandProvisioningJob("BP1 ActiveDirectory_TEST");
			//wait for job to complete.
			Thread.sleep(120000);
			
			TestBase.scrollUp(driver);
		
			homePage.openAdminTab();
			adminPage.clickOnIdentityRepository();
			adminPage.clickOnAccountsLeftHandPanel();
			AccountsPage accountPage = new AccountsPage(driver);
			accountPage.advancedSearch("zzzuac");
			accountPage.clickOnUser("zzzuac");
			accountPage.clickOnEntitlementHierarchy();
			
			boolean result = accountPage.expandMemberOfTreeAndGetEntitlements(ListOfEntitlement);
			Assert.assertEquals(true, result);
			logger.pass("Add access tasks  createdand entitlements added successfully" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
			
	}
		
	}
	
@Test(priority=2)
public void checkCreationOfRemoveAccessTasks() throws InterruptedException, IOException
{
	ArrayList<String> ListOfEntitlement = new ArrayList<String>();
	ListOfEntitlement.add("G GBL WTP0365LICENSING");
	ListOfEntitlement.add("G SLP O365 Users");
	ListOfEntitlement.add("G O365 Exo licensing");
	
	logger =extent.createTest("Bp1 license mgmt test for :AAD|Win10|Remote|ExceptChinaTurkeyIndonesia... removal test");
	
	
	LaunchPage launch = new LaunchPage(driver);
	launch.login(admin, password);
	
	HomePage homePage = new HomePage(driver);
	homePage.openAdminTab();
	
	AdminPage adminPage = new AdminPage(driver);
	adminPage.searchForUserAndClickOnUser("zzzuac");
	
	boolean status = adminPage.verifyEmployeeClassAndStatus();
	if(status)
	{
		TestBase.scrollUp(driver);
		adminPage.clickOnOtherAttributesTab();
		adminPage.removeCustomProperties("IN","Win10","True","RemoteUserMailbox");
		adminPage.clickOnUpdate();
		
		//wait for update to complete and task to be created for the user
		Thread.sleep(60000);
		
		homePage.clickOnHome();
	
		homePage.clickOnTasksandGotoPendingTasks();
		PendingTasksPage pendingTask = new PendingTasksPage(driver);
		pendingTask.advancedSearch("AAD License Mgmt Logical","zzzuac");
		pendingTask.getPendingTaskDetails("zzzuac",ListOfEntitlement);
		homePage.clickOnHome();
		homePage.openAdminTab();
		
		adminPage.openJobControlPanelLink();
		adminPage.openUtilityandProvisioningJob("BP1 ActiveDirectory_TEST");
		//wait for job to complete.
		Thread.sleep(120000);
		
		TestBase.scrollUp(driver);
	
		homePage.openAdminTab();
		adminPage.clickOnIdentityRepository();
		adminPage.clickOnAccountsLeftHandPanel();
		AccountsPage accountPage = new AccountsPage(driver);
		accountPage.advancedSearch("zzzuac");
		accountPage.clickOnUser("zzzuac");
		accountPage.clickOnEntitlementHierarchy();
		
		boolean result = accountPage.checkPresenceOfEntitlements();
		Assert.assertEquals(true, result);
		logger.pass("Remove tasks created successfully and entitlements removed");
		
		homePage.logoff();
}
}
}