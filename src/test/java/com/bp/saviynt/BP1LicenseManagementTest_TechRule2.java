package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class BP1LicenseManagementTest_TechRule2 extends TestBase{
		
	/*
	 * check the user's employee status, make it empty if it is EMPLOYEE or INTERNAL PARTNER,
	 * add the custom properties, check if tasks are created
	 * expected output : tasks should not be created.
	 */
	@Test(priority=1)
	public void changeEmployeeStatusAndUpdateUserTest() throws InterruptedException, IOException
	{
		logger =extent.createTest("Bp1 license mgmt test for : AAD|Win10|Remote|ExceptChinaTurkeyIndonesia... clear employee class value");
		String admin = "TSTTEN10";
		String password = "password";
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser("zzzfv1");
		
		boolean status = adminPage.verifyEmployeeClassAndStatus();
		if(status)
		{
			adminPage.clearEmployeeClassValue();
			TestBase.scrollUp(driver);
			adminPage.clickOnOtherAttributesTab();
			
			adminPage.addCustomProperties("SLP", "IN", "Win10","True", "RemoteUserMailbox");
			adminPage.clickOnUpdate();
			
			//wait for update to complete and task to be created for the user
			Thread.sleep(60000);
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.advancedSearch("AAD License Mgmt Logical","zzzfv1");
			boolean result = pendingTask.checkErrorMessage();
			Assert.assertEquals(true, result);
			logger.pass("Tasks not created because user is inactive or no data in employee class" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
		}
		
	}
		
		
	
	
	
	
	
	/*
	 * assign employee class as either EMPLOYEE or INTERNAL PARTNER,
	 * check if custom properties are as specified in the test case, check if tasks are created
	 * expected output : tasks should be created, entitlements should be assigned to the user
	 */
		@Test(priority=2)
		public void BP1LicenseManagementTest() throws InterruptedException
		{
			logger =extent.createTest("Bp1 license mgmt test for : AAD|Win10|Remote|ExceptChinaTurkeyIndonesia");
			String admin = "TSTTEN10";
			String password = "password";
			
			LaunchPage launch = new LaunchPage(driver);
			launch.login(admin, password);
			
			HomePage homePage = new HomePage(driver);
			homePage.openAdminTab();
			
			AdminPage adminPage = new AdminPage(driver);
			adminPage.searchForUserAndClickOnUser("zzzfv1");
			
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
				pendingTask.advancedSearch("AAD License Mgmt Logical","zzzfv1");
				pendingTask.getPendingTaskDetails("zzzfv1");
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
				accountPage.advancedSearch("zzzfv1");
				accountPage.clickOnUser("zzzfv1");
				accountPage.clickOnEntitlementHierarchy();
				ArrayList<String> ListOfEntitlement = new ArrayList<String>();
				ListOfEntitlement.add("G GBL WTP0365LICENSING");
				ListOfEntitlement.add("G SLP O365 Users");
				ListOfEntitlement.add("G O365 Exo licensing");
				boolean result = accountPage.expandMemberOfTreeAndGetEntitlements(ListOfEntitlement);
				Assert.assertTrue(result);
		}
			else
			{
				logger.pass("User inactive, not updating any data");
				homePage.logoff();
			}
			
		}
	}

//}
