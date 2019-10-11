package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UpdateDLTest extends TestBase {
	
	 public String adminId, password = "password";
	 
	 
	//@Test(priority=1)
	 public void adminLoginAndImportUserTest() throws IOException
	 {
		 
		logger = extent.createTest("test to import user");
		
		//admin login
		LaunchPage launch = new LaunchPage(driver);
		launch.login("kegqo8", "Test02");
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
			launch.login("kegqo8", "Test02");
			HomePage home = new HomePage(driver);
			//goto admin tab
			home.openAdminTab();
			AdminPage adminPage = new AdminPage(driver);
			adminPage.openJobControlPanelLink();
			adminPage.triggerJobToImportUser();
			
			
	 }

}
