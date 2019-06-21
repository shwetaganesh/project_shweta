package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Forward_SOD_Access_Request_ER extends TestBase{
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	UsernameGeneration userObject = new UsernameGeneration();
	String role4 = excel.getData(0, 1, 1);
	String role5 = excel.getData(0, 2, 1);
	String role8 = excel.getData(0, 4, 1);
	String role9 = excel.getData(0, 5, 1);
	String roleApproverForR4 = excel.getData(0, 8, 1);
	String roleApproverForR8 = excel.getData(0, 9, 1);
	String roleApproverForR9 = excel.getData(0, 10, 1);
	String sodApproverForR4_R5 = excel.getData(0, 13, 1);
	String sodApproverForR8_R9 = excel.getData(0, 14, 1);
	String newApprover = excel.getData(0, 15, 1);
	String  end_user, line_manager, role_approver, admin;
	String password = "password";
	public String requestNumber;
	
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	String userName,uname,user;
	String firstName = excel1.getData(0,1,1);
	String lastName = excel1.getData(0,1,2);
	String managerId = excel1.getData(0,1,3);
	String oldpassword = excel1.getData(0,1,4); // password set using admin function during end user creation
	
	// creation of new user test
	@Test(priority=1)
	public void createEndUser() throws Exception {
		logger = extent.createTest("New user creation");
		LaunchPage launch = new LaunchPage(driver);
		String admin = "TSTTEN10";
		launch.login(admin, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		userName = adminPage.clickOnUsersAndCreateUsers(firstName,lastName,managerId);
		String property16 = userName+"@saviynt.com";
		System.out.println(property16);
		adminPage.addAttributes(property16, "X|1|GMTUK|GB",userName,oldpassword);
		home.logoff();
		userObject.writeUserName(userName);
		uname=userObject.readUserName();
		System.out.println("end user is :"+uname);
		if(userName.equals(""))
			Assert.assertTrue(false, "user not created");
		else
			logger.pass("User created successfully with ID : "+userName);
		
	}
	
	/*
	 * request for roles- R4,R5,R8,R9 for the end user created. role approvers for
	 * all the roles(r4,r5,r8,r9) will approve the request training approver(same as
	 * role approver) for r4,r5 will also approve the request
	 */
	@Test(priority =2)
	public void createRequestForSodRolesandRoleApproval() throws InterruptedException, IOException {
		
		logger = extent.createTest("create request for SOD roles and obtain approval from role approvers individually");
		end_user = userObject.readUserName();
		System.out.println("end user  is: "+end_user);
		System.out.println(oldpassword);

		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(end_user, oldpassword);
		ResetPasswordPage reset = new ResetPasswordPage(driver);
		reset.changePassword(end_user, oldpassword, password);
		HomePage home = new HomePage(driver);
		home = reset.setUpSecurityQuestions();
		// open Request for enterprise role tab 
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user 
		userPage.searchEndUser(end_user);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCart(role4);
		rolePage.searchandAddtoCart(role5);
		rolePage.searchandAddtoCart(role8);
		rolePage.searchandAddtoCart(role9);
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
		System.out.println("Request Number is " + requestNumber);
		// requester log out
		home.logoff();
		
		//** login as role approver for R4 & R5
		launch.login(roleApproverForR4, password);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// role approver approves second role
		approve.acceptSecondRole();
		// ensure the presence of three sods
		String header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		boolean status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		boolean result = approve.clickOnConfirm();
		Assert.assertTrue(result,"Role Manager approval failed");		
		logger.pass("Role Manager has approved the request for role4 and role5");
		// role approver log out
		home.logoff();
		
		// ** login as role approver for R8
		launch.login(roleApproverForR8, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Role Manager approval failed");
		logger.pass("Role Manager has approved the request for role8");
		// role approver log out
		home.logoff();
		
		// ** login as role approver for R9
		launch.login(roleApproverForR9, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Role Manager approval failed");
		logger.pass("Role Manager has approved the request for role8");
		// role approver log out
		home.logoff();
		
		// ** login as role approver for R4 & R5 -- role approver and training approver same for R4 and R5
		launch.login(roleApproverForR4, password);
		home.openApprovalInbox();
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		// role approver approves first role
		approve.acceptFirstRole();
		// role approver approves second role
		approve.acceptSecondRole();
		// ensure the presence of three sods
		header = approve.verifyPresenceOfSODHeader();
		Assert.assertEquals(header, "Total 3 Segregation of Duty Violation Found");
		status = approve.verifyPresenceOfBusinessJustificationHeader();
		Assert.assertTrue(status, "Business justification header is present");
		status = approve.verifyPresenceOfAdditionalJustificationHeader();
		Assert.assertTrue(status, "Additional justification header is present");
		result = approve.clickOnConfirm();
		Assert.assertTrue(result, "Training work order approval failed");
		logger.pass("Training approver has approved the request for role4 and role5");
		// role approver log out
		home.logoff();

	}
	
	// forward sod request to another sod approver
	@Test(priority =3)
	public void forwardSODRequest() throws IOException, InterruptedException 
	{
		logger = extent.createTest("Forward sod access request");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.clickOnModifyApproversButton();
		boolean status = approve.searchAndAssignNewSODApprover(newApprover);
		if(status)
			logger.pass("New SOD approver added to the request. SOD request forwarded");
		else
			logger.fail("SOD forwarding failed");
		home.logoff();
	}
	
	/*
	 * test to check whether the forwarded request is available in the new
	 * approver's inbox
	 */
	@Test(priority=4)
	public void verifyForwardedSODRequest() throws IOException
	{
		logger = extent.createTest("Forward sod access request");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as new sod approver***
		launch.login(newApprover, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		logger.pass("request forwarded successfully " + requestNumber, MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}
}
