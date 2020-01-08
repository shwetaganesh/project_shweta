package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Remove_Enterprise_Role_R6andR7 extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration userObject = new UsernameGeneration();
	String  end_user, line_manager, role_approver, admin,role6,role7;
	String password = "password";
	public String requestNumber;
	
	@Test(priority=1)
	public void RemoveRoleR6andR7() throws InterruptedException, IOException {
		
		logger = extent.createTest("Existing User:Remove Salesforce Enterprise Role - R6 and R7");
		end_user = userObject.readUserName();
		System.out.println("end user in tc7:"+end_user);
		role6 = excel.getData(0, 28, 1);
		role7 = excel.getData(0, 29, 1);
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(end_user, password);
		HomePage home = new HomePage(driver);
		// open Request for enterprise role tab 
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user 
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		rolePage.searchAndRemoveFromCart(role7);
		rolePage.searchAndRemoveFromCart(role6);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for TC7 is " + requestNumber);
		// requester log out
		home.logoff();
		
	}

	@Test(priority=2)
	public void scheduleJob() {
		String systemName = "TEST_SalesforceTest";
		logger = extent.createTest("Schedule job");
		//**login as admin
		LaunchPage launch = new LaunchPage(driver);
		admin = "TSTTEN10";
		launch.login(admin, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		adminPage.openJobControlPanelLink();
		JobControlPanelPage jobControlPanel= new JobControlPanelPage(driver);
		jobControlPanel.openUtilityandProvisioningJob(systemName);
		// endpoint approver log out
		home.logoff();
	}
	@Test(priority=3)
	public void validateEntitlements() throws IOException {
		logger = extent.createTest("Validate Entitlements");
		//*** Login as requester ***
		end_user = userObject.readUserName();
		LaunchPage launch = new LaunchPage(driver);
		launch.login(end_user, password);
		// open request history
		HomePage home = new HomePage(driver);
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(requestNumber);
		// create an array list containing all the entitlement in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEntitlements();
		// **** Verify Presence of all required entitlements ****
		String endpoint = excel.getData(0, 18, 14);
		ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		int i = 0;
		for (String s : validate_list) {
			validate_list.set(i,s.replace("Tasks get created to REMOVE -", ""));
			i++;
		}
		boolean result = historyPage.validateEndPoints(arlist, validate_list);
		Assert.assertTrue(result, "All Entitlement values were not present");
		logger.pass("All Entitlement values were found", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		// requester logout
		home.logoff();
}
}
