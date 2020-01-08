package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class GEMS_NewAccount_NoUPN_NoMailBox_Test extends TestBase {
	
	String user ,admin="TSTTEN10",password="password";
	String fname,lname,city,street,phone,manager,customProperty1,customProperty2,customProperty3,customProperty4,customProperty11,customProperty50;
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\GEMS_UserData.xlsx");
	
	@Test(priority=1)
	public void createNewUserTest() throws IOException
	{
		logger = extent.createTest("Test to create new user without UPN and Mailbox");
		fname = excel.getData(0, 1, 0);
		lname = excel.getData(0, 1, 1);
		city =excel.getData(0, 1, 3);
		street =excel.getData(0, 1, 2);
		phone = "9876543210";
		manager ="598446";

		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.clickOnUserandCreateUserButton();
		user = adminPage.createUser(fname, lname, city, street, phone, manager);
		System.out.println(user);
		UsernameGeneration object = new UsernameGeneration();
		object.writeUserName(user);
		if(user.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("user created successfully: " +user);
	}
	
	@Test(priority=2)
	public void newAccountCreationWithoutUPNandMailBox() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to validate new BP1 account creation[no UPN & Mailbox provided]");
		customProperty1 = excel.getData(0, 1, 4);
		customProperty2 = excel.getData(0, 1, 5);
		customProperty3 = excel.getData(0, 1, 6);
		customProperty4 = excel.getData(0, 1, 7);
		customProperty11 = excel.getData(0, 1, 8);
		customProperty50 = excel.getData(0, 1, 9);
		
		ArrayList<String> ListOfEntitlement = new ArrayList<String>();
		ListOfEntitlement.add("CN=G GBL DW GSD TST GL EXT Business Unit");
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser(user);
		adminPage.addEmployeeClassAndUserDetailsUpdate();
		
		adminPage.clickOnOtherAttributesTab();
		
		adminPage.addCustomProperties(customProperty1,customProperty2,customProperty3,customProperty4,customProperty11,customProperty50);
		adminPage.clickOnUpdate();
		
		homePage.clickOnHome();
		homePage.clickOnTasksandGotoPendingTasks();
		
		PendingTasksPage pendingTaskPage = new PendingTasksPage(driver);
		pendingTaskPage.searchForTaskCreatedForUser(user);
		pendingTaskPage.getPendingTaskDetails(user, ListOfEntitlement);
		
		homePage.openAdminTab();
		adminPage.openJobControlPanelLink();
		
		JobControlPanelPage jobControlPanel = new JobControlPanelPage(driver);
		jobControlPanel.openUtilityandProvisioningJob("BP1-Testing");
		
		homePage.openAdminTab();
		
		adminPage.searchForUserAndClickOnUser(user);
		adminPage.clickOnAccountsTab();
		boolean result = adminPage.validateCreationOfBP1Account();
		if(result) {
			logger.pass("bp1 account created for the user successfully", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		}
		
	}

}
