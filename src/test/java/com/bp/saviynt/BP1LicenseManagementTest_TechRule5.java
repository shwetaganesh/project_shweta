package com.bp.saviynt;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bp.testbase.TestBase;

public class BP1LicenseManagementTest_TechRule5 extends TestBase {
	
	
	@Test
	public void BP1LicenseManagementTest() throws InterruptedException
	{
		logger =extent.createTest("Bp1 license mgmt test for : AAD|NonBP|Thirdparty|NoMailbox");
		String admin = "TSTTEN10";
		String password = "password";
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzug4");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(status)
		{
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperties("SLP","ID","Non-BP","True","3rdpartymailbox");
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzug4");
			//pendingTask.searchForTaskCreatedForUser("zzzug4");
			pendingTask.getPendingTaskDetails("zzzug4");
			
			homePage.clickOnHome();
			homePage.openAdminTab();
			adminPage.openJobControlPanelLink();
			adminPage.openUtilityandProvisioningJob("BP1 ActiveDirectory_TEST");
			//wait for job to complete.
			Thread.sleep(120000);
			
			
			homePage.openAdminTab();
			
			adminPage.clickOnIdentityRepository();
			adminPage.clickOnAccountsLeftHandPanel();
			AccountsPage accountPage = new AccountsPage(driver);
			accountPage.advancedSearch("zzzug4");
			accountPage.clickOnUser("zzzug4");
			accountPage.clickOnEntitlementHierarchy();
			ArrayList<String> ListOfEntitlement = new ArrayList<String>();
			ListOfEntitlement.add("G GBL O365 Non BP Device Users");
			boolean result = accountPage.expandMemberOfTreeAndGetEntitlements(ListOfEntitlement);
			Assert.assertTrue(result);
			System.out.println("Test pass");
		}
		else
		{
			logger.pass("User inactive, not updating any data");
			homePage.logoff();
		}
		
		
		
	}


}
