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

public class Add_Enterprise_Role_New extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration uobject = new UsernameGeneration();
	String role1 = excel.getData(0, 30, 1);
	String role2 = excel.getData(0, 31, 1);
	String request_number,requestor, end_user, line_manager, admin_id;
	String password = "password";
	
	@Test(priority=1)
		public void createRequestAndRoleApproval() throws Exception 
		{
			logger = extent.createTest("New User:ADD Enterprise Role Request-NEW");
			requestor = excel.getData(0, 5, 6);
			end_user = uobject.readUserName();
			System.out.println(end_user);
			line_manager = excel.getData(0, 5, 8);
			
			LaunchPage launch = new LaunchPage(driver);
			//*** Login to application with Requester***
			launch.login(requestor, password);
			HomePage home = new HomePage(driver);
			// open Request for enterprise role tab 
			home.openRequestEnterpriseRole();
			FindUserPage userPage = new FindUserPage(driver);
			// search for end user 
			userPage.searchEndUser(end_user);
			FindRolePage rolePage = new FindRolePage(driver);
			// search for required role....add to cart.
			rolePage.searchandAddtoCart(role1);
			rolePage.searchandAddtoCart(role2);
			// click on check out button
			rolePage.clickOnCheckout();
			SubmitPage submitObj = new SubmitPage(driver);
			//submitObj.selectParametersFromOrganisationNode();
			// submit the request
			request_number = submitObj.submitAccessRequest();
			if (request_number.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + request_number, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for TCNew is " + request_number);
			// requester log out
			home.logoff();

			//*** Login as line manager ***
			launch.login(line_manager,password);
			// goto approval inbox
			home.openApprovalInbox();
			ApprovalInboxPage approve = new ApprovalInboxPage(driver);
			// search with the requestID and open
			approve.searchRequestNumber(request_number);
			// accept role1
			approve.acceptFirstRole();
			// accept role2
			approve.acceptSecondRole();
			// click on confirm
			boolean result = approve.clickOnConfirm();
			Assert.assertTrue(result, "Line Manager approval failed");
			logger.pass("Line Manager had approved the request");
			// line manager log out
			home.logoff();

}
		@Test(priority=2)
		public void JobTrigger()
		{
			logger = extent.createTest("Trigger Job");
			admin_id = "TSTTEN10";
			LaunchPage launch = new LaunchPage(driver);
			//*** Login as Admin***
			launch.login(admin_id, "password");
			HomePage home = new HomePage(driver);
			//open admin tab
			home.openAdminTab();
			AdminPage adminpage = new AdminPage(driver);
			//open job control panel
			adminpage.openJobControlPanelLink();
			// open utility link
			adminpage.openUtilityandTriggerChain();
			logger.pass("Job Trigger Scheduled Successfully");
			//log off
			home.logoff();
		}
		
		@Test(priority=3)
		public void validateEndpoints() throws IOException {
			LaunchPage launch = new LaunchPage(driver);
			// *** Login as requester ***
			launch.login(requestor, password);
			HomePage home = new HomePage(driver);
			// open request history
			home.openRequestHistory();
			RequestHistoryPage historyPage = new RequestHistoryPage(driver);
			// search and open the request number
			historyPage.searchRequestAndOpen(request_number);
			// create an array list containing all the end points in the TASK Tab.
			ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();
			// **** Verify Access Granted or not ****
			String endpoint = excel.getData(0, 19, 14);
			ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
			int i = 0;
			for (String s : validate_list) {
				validate_list.set(i, s.replace("Tasks get created - ", ""));
				i++;
			}
			boolean result = historyPage.validateEndPoints(arlist, validate_list);
			Assert.assertTrue(result, "All end points were not present");
			logger.pass("All endpoints were found", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			// requester logout
			home.logoff();
}
}
