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

public class Existing_User_Remove_Salesforce_Application_Access extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration userObject = new UsernameGeneration();
	String password = "password";
	String searchItem = "TEST_SalesforceTest";
	String requestor, endPointApprover, end_user, admin,permission,groupName;
	public String requestNumber;

	@Test(priority=1)
	public void createRequestAndEndpointApprove() throws IOException, InterruptedException
		{
			logger = extent.createTest("Existing User: remove account");
			requestor = excel.getData(0, 16, 6);
			endPointApprover = excel.getData(3, 28,1);
			//endUser = excel.getData(0, 16,7);
			end_user = userObject.readUserName();
			System.out.println("end user for tc10 :"+end_user);
			permission = excel.getData(0, 32, 1);
			groupName = excel.getData(0,33, 1);
			
			LaunchPage launch = new LaunchPage(driver);
			//Login to application with Requester 
			launch.login(requestor, password);
			HomePage home = new HomePage(driver);
			//open Request Application Specific Role tab 
			home.openRequestApplicationSpecificRoles();
			FindUserPage userPage = new FindUserPage(driver);
			//search for end user
			userPage.searchEndUser(end_user);
			FindRolePage rolePage = new FindRolePage(driver);
			rolePage.searchandAddtoCartNew(searchItem);
			//rolePage.clickOnModifyExistingAccount();
			rolePage.clickOnCheckout1();
			// remove group and permission
			RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
			requestAcessPage.removePermissionSet(permission);
			requestAcessPage.removeGroup(groupName);
			requestAcessPage.clickOnNext();
			// provide business justification and submit
			JustificationPage justifyPage = new JustificationPage(driver);
			justifyPage.enterBusinessJustificationAndComment();
			justifyPage.clickOnSubmit();
			// retrieve the request number
			SubmitPage submit = new SubmitPage(driver);
			requestNumber = submit.returnRequestNumber();
			if (requestNumber.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for TC10 is " + requestNumber);
			// requester log out
			home.logoff();
	
}
		@Test(priority=2)
		public void scheduleJob() {
			logger = extent.createTest("Schedule job");
			//**login as admin
			LaunchPage launch = new LaunchPage(driver);
			admin = "TSTTEN10";
			launch.login(admin, password);
			HomePage home = new HomePage(driver);
			home.openAdminTab();
			AdminPage adminPage = new AdminPage(driver);
			adminPage.openJobControlPanelLink();
			String systemName = excel.getData(0, 34, 1);
			JobControlPanelPage jobControlPanel = new JobControlPanelPage(driver);
			jobControlPanel.openUtilityandProvisioningJob(systemName);
			// endpoint approver log out
			home.logoff();
		}
		@Test(priority=3)
		public void validateEntitlements() throws IOException {
			logger = extent.createTest("Validate Entitlements");
			//*** Login as requester ***
			requestor = excel.getData(0, 16, 6);
			LaunchPage launch = new LaunchPage(driver);
			launch.login(requestor, password);
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
