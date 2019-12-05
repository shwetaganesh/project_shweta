package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_ViewExistingAccess  extends TestBase{
	
	
	String user1,user2, role, L2Manager = "L2TEST13", admin="TSTTEN10",trainingWorkOrder;
	String password = "password";
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx");
	
	@Test
	public void viewExistingAccessTestForNormalUser() throws IOException
	{
		logger = extent.createTest("Test to view existing access for normal user");
		user1 = excel.getData(1, 1, 0);
		role = excel.getData(1, 6, 0);
		
		//L2 manager login
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openViewExistingAccess();
		
		ExistingAccessPage accessPage = new ExistingAccessPage(driver);
		
		accessPage.changeUser(user1);
		
		boolean result = accessPage.checkEnterpriseRoles(role);
		
		if(result)
			logger.pass("Role : "+role +" assigned to the user : "+user1 , MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
		else
			logger.fail("Role : "+role+" not assigned to the user: "+user1, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
		homePage.logoff();
		
	}
	
	@Test(priority=2)
	public void viewExistingAccessTestForL1Manager() throws IOException
	{
		logger = extent.createTest("Test to view existing access for normal user");
		user2 = excel.getData(1, 2, 0);
		role = excel.getData(1, 6, 0);
		
		//L2 manager login
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		// open request enterprise role
		homePage.openViewExistingAccess();
		
		ExistingAccessPage accessPage = new ExistingAccessPage(driver);
		
		accessPage.changeUser(user2);
		
		boolean result = accessPage.checkEnterpriseRoles(role);
		
		if(result)
			logger.pass("Role : "+role +" assigned to the user : "+user2, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
		else
			logger.fail("Role : "+role+" not assigned to the user: "+user2, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			
		homePage.logoff();
		
	}
	

}
