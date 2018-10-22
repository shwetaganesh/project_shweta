package com.bp.saviynt;

import org.testng.annotations.Test;

import com.bp.lib.ExcelOperations;
import com.bp.testbase.TestBase;

public class JobTrigger extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String password = "password1";
	String admin_id;
	
	@Test
	public void TriggerJobTest()
	{
		logger = extent.createTest("Trigger Job");
		admin_id = excel.getData(3, 36, 1);
		LaunchPage launch = new LaunchPage(driver);
		
		//*** Login as Admin***
		launch.login(admin_id, password);
		HomePage home = new HomePage(driver);
		//open admin tab
		home.openAdminTab();
		AdminPage adminpage = new AdminPage(driver);
		//open job control panel
		adminpage.openJobControlPanelLink();
		// open utility link
		adminpage.openUtility();
		logger.pass("Job Trigger Scheduled Successfully");
		//log off
		home.logoff();
	}
	
	
	
}
