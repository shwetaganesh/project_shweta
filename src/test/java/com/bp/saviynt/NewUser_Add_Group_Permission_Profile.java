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

public class NewUser_Add_Group_Permission_Profile extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	String password = "password";
	String searchItem = "TEST_SalesforceTest";
	String requestor, endPointApprover, endUser, admin,permission,groupName;
	public String requestNumber;
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	String userName,uname;
	String firstName = excel1.getData(0,1,1);
	String lastName = excel1.getData(0,1,2);
	String managerId = excel1.getData(0,1,3);
	String oldpassword = excel1.getData(0,1,4); // password set using admin function during end user creation
	
	@Test(priority=1)
	public void createEndUser() throws Exception {
		
		logger = extent.createTest("Create new user");
		LaunchPage launch = new LaunchPage(driver);
		String admin = "TSTTEN10";
		launch.login(admin, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		String userName = adminPage.clickOnUsersAndCreateUsersAndAddAttributes(lastName,managerId,"X|1|GMTUK|GB");
		home.logoff();
		userObject.writeUserName(userName);
		uname=userObject.readUserName();
		System.out.println("end user is :"+uname);
		if(userName.equals(""))
			Assert.assertTrue(false, "user not created");
		else
			logger.pass("User created successfully with ID : "+userName);
	}
	
	@Test(priority=2)
	public void createRequest() throws IOException, InterruptedException
	{
		logger = extent.createTest("New User:Request for new account");
		requestor = excel.getData(0, 16, 6);
		endPointApprover = excel.getData(3, 28,1);
		endUser = userObject.readUserName();
		permission = excel.getData(0, 32, 1);
		groupName = excel.getData(0, 33, 1);
		 
		LaunchPage launch = new LaunchPage(driver);
		//Login to application with Requester 
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		//open Request Application Specific Role tab 
		home.openRequestApplicationSpecificRoles();
		FindUserPage userPage = new FindUserPage(driver);
		//search for end user
		userPage.searchEndUser(endUser);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.searchandAddtoCartNew(searchItem);
		rolePage.clickOnCheckout1();
		// add profile,permission and group for user.
		RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
		requestAcessPage.selectProfile();
		requestAcessPage.addPermissionSet(permission);
		requestAcessPage.addGroup(groupName);
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
		System.out.println("Request Number for TC8 is " + requestNumber);
		// requester log out
		home.logoff();
		launch.clickOnLoginAgain();
		
		
		
		//** Login as endpoint approver 
		launch.login(endPointApprover, password);
		
		// open approval inbox
		home.openApprovalInbox();
		ApprovalInboxPage approvalInbox = new ApprovalInboxPage(driver);
		approvalInbox.searchRequestNumber(requestNumber);
		approvalInbox.acceptFirstRole();
		approvalInbox.acceptSecondRole();
		approvalInbox.acceptThirdRole();
		boolean result = approvalInbox.clickOnConfirm();
		Assert.assertTrue(result,"End point Approver approval failed");		
		logger.pass("End Point Approver has approved the request");
		// endpoint approver log out
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
		JobControlPanelPage jobControlPanel = new JobControlPanelPage(driver);
		jobControlPanel.openUtilityandProvisioningJob("TEST_SalesforceTest");
		// endpoint approver log out
		home.logoff();
	}
	
	@Test(priority=4)
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
