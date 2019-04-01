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

public class Add_Enterprise_Role_R6 extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	UsernameGeneration userObject = new UsernameGeneration();
	String role6 = excel.getData(0, 28, 1);
	String  end_user, line_manager, role_approver, admin;
	String password = "password";
	public String requestNumber;
	
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	String username1,uname;
	String firstName = excel1.getData(0,1,1);
	String lastName = excel1.getData(0,1,2);
	String managerId = excel1.getData(0,1,3);
	String oldpassword = excel1.getData(0,1,4); // password set using admin function during end user creation
	
	@Test(priority=1)
	public void createEndUser() throws Exception {
		
		username1=userObject.generateUserName();
		System.out.println("random user generated :"+username1);
		System.out.println(managerId);
		String property16 = username1+"@saviynt.com";
		LaunchPage launch = new LaunchPage(driver);
		String admin = "TSTTEN10";
		launch.login(admin, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		adminPage.clickOnUsersAndCreateUsers(username1,firstName,lastName,managerId);
		adminPage.addAttributes(property16, "X|1|GMTUK|GB",username1,oldpassword);
		home.logoff();
		userObject.writeUserName(username1);
		uname=userObject.readUserName();
		System.out.println("end user is :"+uname);
		
	}
	@Test(priority=2)
	public void createRequestAndRoleApproval() throws Exception 
	{
		logger = extent.createTest("New User:ADD Salesforce Enterprise Role - R6");
		
		end_user = userObject.readUserName();
		System.out.println("end user in tc5 is: "+end_user);
		role_approver = excel.getData(0, 13, 9);
		
		
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(end_user, oldpassword);
		ResetPasswordPage reset = new ResetPasswordPage(driver);
		reset.changePassword(end_user, oldpassword, password);
		HomePage home;
		home = reset.setUpSecurityQuestions();
		//HomePage home = new HomePage(driver);
		// open Request for enterprise role tab 
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user 
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role6);
		rolePage.clickOnCheckout();
		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number for TC5 is " + requestNumber);
		// requester log out
		home.logoff();
		
		
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
	@Test(priority=3)
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
		adminPage.openUtilityandProvisioningJob();
		// admin log out
		home.logoff();
	}
	
	@Test(priority=4)
	public void validateEntitlements() throws IOException {
		logger = extent.createTest("Validate Entitlements");
		//*** Login as end user ***
		end_user = userObject.readUserName();
		System.out.println(requestNumber);
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
		String endpoint = excel.getData(0, 16, 14);
		ArrayList<String> validate_list = new ArrayList<String>(Arrays.asList(endpoint.split(",")));
		int i = 0;
		for (String s : validate_list) {
			validate_list.set(i, s.replace("Tasksgetcreated-", ""));
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

