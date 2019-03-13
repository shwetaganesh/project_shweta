package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class ADD_Enterprise_Role_Request_SOD extends TestBase
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	String role4 = excel.getData(0, 24, 1);
	String role5 = excel.getData(0, 25, 1);
	String requestor, end_user, role_approver_1, training_work_order_id,sod_id, admin_id;
	String password = "password";
	String requestNumber;
	
	@Test
	public void TC4() throws IOException, InterruptedException
	{	
		logger = extent.createTest("Existing User:ADD Enterprise Role Request-SOD");
		requestor = excel.getData(0, 10, 6);
		end_user = excel.getData(0, 10, 7);
		role_approver_1 = excel.getData(0,10, 9);
		training_work_order_id = excel.getData(0, 10, 11);
		sod_id= excel.getData(0, 10, 12);
		
		LaunchPage launch = new LaunchPage(driver);
		//  Login to application with Requester 
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		//  open Request for enterprise role tab 
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role4);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role5);
		// click on check out button
		rolePage.clickOnCheckout1();
		SubmitPage submitObj = new SubmitPage(driver);		
		submitObj.nonRegressionRoleRequest();
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for TC4 is " + requestNumber);
		// requester log out
		home.logoff();
		
		// ***Login as Role manager1***
		launch.login(role_approver_1, password);
		// open approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role manager1 approves first role
		approve.acceptFirstRole();
		// role manager1 approves second role
		approve.acceptSecondRole();
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Role Manager approval failed");		
		logger.pass("Role Manager had approved the request");
		// role manager1 log out
		home.logoff();	
		
		// ***Login as SOD ***
		launch.login(sod_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// approve first role
		approve.acceptFirstRole();
		// approve second role
		approve.acceptSecondRole();
		// add mitigating control for first role
		approve.addMitigatingControl();
		// click on purchase order entry header
		approve.clickPurchaseOrderEntryHeader();
		// add mitigating control for second role
		approve.addMitigatingControl2();
		Thread.sleep(2000);
		approve.rejectAllRole();
		// click on confirm
		 result = approve.clickOnConfirm();
		Assert.assertTrue(result, "SOD approval failed");
		logger.pass("SOD approved the request");
		home.logoff();
		
	}
	@Test(priority=2)
	public void scheduleJob() {
		logger = extent.createTest("Schedule job");
		//**login as admin
		LaunchPage launch = new LaunchPage(driver);
		admin_id = "TSTTEN10";
		launch.login(admin_id, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		adminPage.openJobControlPanelLink();
		adminPage.openUtilityandProvisioningJob();
		// endpoint approver log out
		home.logoff();
	}
	@Test(priority=3)
	public void validateEntitlements() throws IOException {
		LaunchPage launch = new LaunchPage(driver);
	// ***Login as requester***
			launch.login(requestor, password);
			HomePage home = new HomePage(driver);
			// open request history
			home.openRequestHistory();
			RequestHistoryPage historyPage = new RequestHistoryPage(driver);
			// search and open the request number
			historyPage.searchRequestAndOpen(requestNumber);
			// create an array list containing all the entitlement in the TASK Tab.
			ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEntitlements();
			// **** Verify Presence of all required entitlements ****
			String endpoint = excel.getData(0, 10, 14);
			ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
			int i = 0;
			for (String s : validate_list) {
				validate_list.set(i,s.replace(" ","").replace("Tasksgetcreated-", ""));
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
