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

public class Add_Enterprise_Role_R7 extends TestBase {
	
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration userObject = new UsernameGeneration();
	String role7 = excel.getData(0, 29, 1);
	String  end_user,role_approver, admin;
	String password = "password";
	public String requestNumber;
	
		@Test(priority=1)
		public void createRequest() throws Exception 
		{
			logger = extent.createTest("Existing User:ADD Salesforce Enterprise Role - R7");
			end_user = userObject.readUserName();
			System.out.println("end user in tc6 is:"+end_user);
			role_approver = excel.getData(0, 13, 9);
			
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
			// search for required role....add to cart.
			rolePage.searchandAddtoCart(role7);
			rolePage.clickOnCheckout();
			SubmitPage submitObj = new SubmitPage(driver);
			// submit the request
			requestNumber = submitObj.submitAccessRequest();
			if (requestNumber.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for TC6 is " + requestNumber);
			// requester log out
			home.logoff();
			
			launch.clickOnLoginAgain();
			//*** Login as role approver ***
			launch.login(role_approver,password);
			// goto approval inbox
			home.openApprovalInbox();
			ApprovalInboxPage approve = new ApprovalInboxPage(driver);
			// search with the requestID and open
			approve.searchRequestNumber(requestNumber);
			// accept role
			approve.acceptFirstRole();
			// click on confirm
			boolean result = approve.clickOnConfirm();
			Assert.assertTrue(result, "Role Approver approval failed");
			logger.pass("Role Approver has approved the request");
			// line manager log out
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
			JobControlPanelPage jobControlPanel = new JobControlPanelPage(driver);
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
			String endpoint = excel.getData(0, 17, 14);
			ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
			int i = 0;
			for (String s : validate_list) {
				validate_list.set(i,s.replace("Profile overwriten (Read Only)","Read Only").replace("Tasksgetcreated-", "").replace(" assigned",""));
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
