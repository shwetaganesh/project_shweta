package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class UMT_RequestEnterpriseRoleTest extends TestBase{
	
	String userID = "navtest1";
	String password = "password";
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	String userName,uname,user;
	String firstName = excel1.getData(0,1,1);
	String lastName = excel1.getData(0,1,2);
	String managerId = excel1.getData(0,1,3);
	String oldpassword = excel1.getData(0,1,4); // password set using admin function during end user creation
	
	
	// creation of new user test
	//@Test(priority = 1)
	public void createEndUser() throws Exception {
		logger = extent.createTest("New user creation");
		LaunchPage launch = new LaunchPage(driver);
		String admin = "TSTTEN10";
		launch.login(admin, password);
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		userName = adminPage.clickOnUsersAndCreateUsers(firstName, lastName, managerId);
		String property16 = userName + "@saviynt.com";
		System.out.println(property16);
		adminPage.addAttributes(property16, "X|1|GMTUK|GB", userName, oldpassword);
		home.logoff();
		userObject.writeUserName(userName);
		uname = userObject.readUserName();
		System.out.println("end user is :" + uname);
		if (userName.equals(""))
			Assert.assertTrue(false, "user not created");
		else
			logger.pass("User created successfully with ID : " + userName);

	}
		@Test(priority=2)
		public void requestEnterpriseRole() throws Exception
		{
			logger = extent.createTest("Request enterprise role for an end user by the UMT user");
			String role = "BP Remote user";
			String endUser = userObject.readUserName();
			LaunchPage launch = new LaunchPage(driver);
			//*** Login to application as end user***
			launch.login(userID, password);
			HomePage home = new HomePage(driver);
			home.openRequestEnterpriseRole();
			FindUserPage userPage = new FindUserPage(driver);
			userPage.searchEndUser(endUser);
			FindRolePage rolePage = new FindRolePage(driver);
			rolePage.searchandAddtoCart(role);
			rolePage.clickOnCheckout();
			// retrieve the request number
			SubmitPage submitObj = new SubmitPage(driver);
			// submit the request
			String requestNumber = submitObj.submitAccessRequest();
			System.out.println(requestNumber);
			if (requestNumber.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number is " + requestNumber);
			// requester log out
			home.logoff();		
		}

}
