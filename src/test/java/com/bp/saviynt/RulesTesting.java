package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class RulesTesting extends TestBase {
	
	String admin = "TSTTEN10";
	String password = "password";
	String user = "RGTSU51";
	
	String roleDescription = "Upstream|Birthright|Global|Data Marketplace User";
	
	@Test(priority=1)
	
	public void validateRoleAssignment() throws InterruptedException, IOException
	{
		logger =extent.createTest("Rules testing : role assignment");
		String value = "UP";
		ArrayList<String> ListOfEntitlement = new ArrayList<String>();
		ListOfEntitlement.add("SAP_LT_RFC_USER");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT OMAN JVA Display & Reporting");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT EG GFT Report Power User & CDM Reporter");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT MA Direct Table Access - Basis");
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser(user);
		
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperty1(value);
			TestBase.scrollToEndOfPage(driver);
			adminPage.clickOnUpdate();
			
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.searchForTaskCreatedForUser(user);
			pendingTask.getPendingTaskDetails(user, ListOfEntitlement);
			pendingTask.completePendingTasks(user);
			
			homePage.clickOnHome();
			homePage.openAdminTab();
			
			adminPage.searchForUserAndClickOnUser(user);
			adminPage.clickOnRolesTab();
			
			RolesPage rolePage = new RolesPage(driver);
			boolean result = rolePage.verifyAssignedRole(roleDescription);
			Assert.assertEquals(true, result);
			logger.pass("Role added to the user successfully" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
			
	}
	
	@Test(priority=2)
	public void  validateRoleRemoval() throws InterruptedException, IOException
	{
		logger =extent.createTest("Rules testing : role removal");
		String value = "CF";
		ArrayList<String> ListOfEntitlement = new ArrayList<String>();
		ListOfEntitlement.add("SAP_LT_RFC_USER");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT OMAN JVA Display & Reporting");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT EG GFT Report Power User & CDM Reporter");
		ListOfEntitlement.add("CN=G GBL ISIM Test UP GFT MA Direct Table Access - Basis");
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser(user);
		
			adminPage.clickOnOtherAttributesTab();
			adminPage.addCustomProperty1(value);
			TestBase.scrollToEndOfPage(driver);
			adminPage.clickOnUpdate();
			
			
			homePage.clickOnHome();
		
			homePage.clickOnTasksandGotoPendingTasks();
			PendingTasksPage pendingTask = new PendingTasksPage(driver);
			pendingTask.searchForTaskCreatedForUser(user);
			pendingTask.getPendingTaskDetails(user, ListOfEntitlement);
			pendingTask.completePendingTasks(user);
			
			homePage.clickOnHome();
			homePage.openAdminTab();
			
			adminPage.searchForUserAndClickOnUser(user);
			adminPage.clickOnRolesTab();
			
			RolesPage rolePage = new RolesPage(driver);
			boolean result = rolePage.verifyNoData();
			Assert.assertEquals(true, result);
			logger.pass("Role removed from the user successfully" , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
			homePage.logoff();
			
	}
	

}
