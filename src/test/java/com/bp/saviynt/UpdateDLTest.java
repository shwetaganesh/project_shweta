package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UpdateDLTest extends TestBase {
	
	 public String adminId, password = "password";
	 
	 
	@Test(priority=1)
	 public void adminLoginAndImportUserTest() throws IOException
	 {
		 
		logger = extent.createTest("test to import user");
		
		//admin login
		LaunchPage launch = new LaunchPage(driver);
		launch.login("test_02", "temp1234");
		HomePage home = new HomePage(driver);
		//goto admin tab
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		// click on connections, search for ISIM and click on it
		adminPage.clickOnConnectionsandSearchForConnection("ISIM-PreProd");
		boolean status = adminPage.filterUser("ISIM-PreProd");
		if(status)
			logger.pass("connection test success",MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		else
			logger.fail("User import failed", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
		home.logoff();
		
	 }
	 
	@Test(priority=2)
	 public void runJobToImportUser() throws InterruptedException
	 {
		 logger = extent.createTest("run user import job");
			
			//admin login
			LaunchPage launch = new LaunchPage(driver);
			launch.login("test_02", "temp1234");
			HomePage home = new HomePage(driver);
			//goto admin tab
			home.openAdminTab();
			AdminPage adminPage = new AdminPage(driver);
			adminPage.openJobControlPanelLink();
			adminPage.triggerJobToImportUser();
			
			home.logoff();		
	 }
	 
	 @Test(priority=3)
	 public void checkCompanyNameandJobStatus() throws InterruptedException
	 {
		 	Thread.sleep(60000);
		 	
		 	logger = extent.createTest("check company name update and run provisioning job");
		 	
		 	LaunchPage launch = new LaunchPage(driver);
			launch.login("test_02", "temp1234");
			HomePage home = new HomePage(driver);
			//goto admin tab
			home.openAdminTab();
			AdminPage adminPage = new AdminPage(driver);
			String companyName = adminPage.searchForUser("ishugb");	
			
			home.openAdminTab();
			adminPage.openJobControlPanelLink();
			adminPage.openUtilityandProvisioningJob("BP1 ActiveDirectory_TEST");
			
			Thread.sleep(180000);
			
			home.clickOnHome();
			home.clickOnTasksandGotoCompletedTasks();
			
			CompletedTasksPage cPage = new CompletedTasksPage(driver);
			boolean result = cPage.verifyStatusOfTask("ishugb");
			if(result)
				logger.pass("Status of task validated successfully");
			else
				logger.fail("Error in task validation");
			
	 }
	 
	 
	 

	 
	 
}
