package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.bp.lib.ExcelOperations;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class GEMS_NewAccount_UPNProvided_NoMailBox_Test extends TestBase {
	
	String user ,admin="TSTTEN10",password="password";
	String fname,lname;
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\GEMS_UserData.xlsx");

	@Test(priority=1)
	public void addUPNToNewUserTest() throws IOException, InterruptedException
	{
		logger = extent.createTest("Test to add UPN to the new user created.");
		fname = excel.getData(0, 1, 0);
		lname = excel.getData(0, 1, 1);
		//String upnValue = fname+"."+lname+"@saviynt.com";
		UsernameGeneration object = new UsernameGeneration();
		String userID = object.readUserName();
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.openJobControlPanelLink();
		
		JobControlPanelPage jobControlPanel = new JobControlPanelPage(driver);
		jobControlPanel.triggerJobToImportUser("BP1-Test");
		//adminPage.searchForUserAndClickOnUser("667495");
		
		
	}
}
