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

public class ADD_Enterprise_Role_Request_Escalation_And_Rejection extends TestBase{
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	String role1 = excel.getData(0, 21, 1);
	String role2 = excel.getData(0, 22, 1);
	String requestNumber,requestor, end_user, line_manager, role_approver_2, training_work_order_id,sod_id, admin_id;
	String password = "password";
	
	@Test
	public void TC2() throws IOException, InterruptedException 
	{
		logger = extent.createTest("New User:ADD Enterprise Role Request-Escalation and Rejection");
		requestor = excel.getData(0, 6, 6);
		end_user = excel.getData(0, 6, 7);
		line_manager = excel.getData(0, 6, 8);
		role_approver_2 = excel.getData(0, 7, 9);
		training_work_order_id = excel.getData(0, 6, 11);

		LaunchPage launch = new LaunchPage(driver);
		// *** Login to application with Requester ***
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		//	open Request for enterprise role tab
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser("RGTSU44");
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role1);
		rolePage.searchandAddtoCart(role2);
		// click on check out button
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		submitObj.selectParametersFromOrganisationNode();
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for TC2 is " + requestNumber);
		// requester log out
		home.logoff();

		// ***Login as line manager ***
		launch.login(line_manager, password);
		// goto approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// accept role1
		approve.acceptFirstRole();
		// accept role2
		approve.acceptSecondRole();
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Line Manager approval failed");
		logger.pass("Line Manager had approved the request");
		// line manager log out
		home.logoff();
		
		Thread.sleep(120000);
		
		// ***Login as Role manager2*** (ESCALATION)
		launch.login(role_approver_2, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.acceptFirstRole();
		//reject second role
		approve.rejectSecondRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Escalation manager operation failed");		
		logger.pass("Escalation manager accepted role1 and rejected role2");
		// role manager1 log out
		home.logoff();


		// ***Login as Training work order***
		launch.login(training_work_order_id, password);
		// open approval inbox
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// accept first role
		approve.acceptFirstRole();
		result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Training work order approval failed");		
		logger.pass("Training work order had approved the request");
		// training work order log out
		home.logoff();

		
	}
	@Test(priority=2)
	public void JobTrigger()
	{
		logger = extent.createTest("Trigger Job");
		admin_id = "TSTTEN10";
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
		adminpage.openUtilityandTriggerChain();
		logger.pass("Job Trigger Scheduled Successfully");
		//log off
		home.logoff();
	}
	//@Test(priority=2)
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
	public void validateEndPoints() throws IOException {
		
			//***Login as requester***
		LaunchPage launch = new LaunchPage(driver);
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// open request history
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(requestNumber);
		// create an array list containing all the end points in the TASK Tab.
		ArrayList<String> arlist = historyPage.clickOnTaskAndFetchEndPoints();
		// **** Verify Access Granted or not ****
		String endpoint = excel.getData(0, 6, 14);
		ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		int i = 0;
		for (String s : validate_list) {
			validate_list.set(i, s.replace(" ", "").replace("Tasksgetcreated-", ""));
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
