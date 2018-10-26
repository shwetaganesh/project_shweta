package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class RolesRemovalTest extends TestBase 
{
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\NEWSAVIYNT - Test Scenarios 300818.xlsx");
	String requestor, end_user_tc1, end_user_tc3;
	String password = "password1";
	String role1 = excel.getData(0, 15, 1);
	String role2 = excel.getData(0, 16, 1);
	String role3 = excel.getData(0, 17, 1);

	@Test
	public void removeRole() throws Exception 
	{
		logger = extent.createTest("Roles removal test");
		requestor = excel.getData(0, 5, 6);
		end_user_tc1 = excel.getData(0, 5, 7);
		end_user_tc3 = excel.getData(0, 8, 7);
		String request_number;

		LaunchPage launch = new LaunchPage(driver);

		// ***Login to application with Requester ***
		launch.login(requestor, password);
		HomePage home = new HomePage(driver);
		// open Request for enterprise role tab
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		// search for end user
		userPage.searchEndUser(end_user_tc1);
		FindRolePage rolePage = new FindRolePage(driver);
		// search and remove role
		boolean result1 = rolePage.searchAndRemoveFromCart(role1);
		boolean result2 = rolePage.searchAndRemoveFromCart(role2);
		SubmitPage submitObj = new SubmitPage(driver);
		if(result1||result2)
		{
			// click on checkout button
			rolePage.clickOnCheckout();
			
			request_number = submitObj.submitAccessRequest();
			if (request_number.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + request_number, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for TC1 role removal is " + request_number);
	
			logger.pass("Roles successfully removed by requestor");
		}
		else
			logger.pass("No roles present to remove for TC1");
	
		home.clickOnHome();
		// open Request for enterprise role tab
		home.openRequestEnterpriseRole();
		// search for end user
		userPage.searchEndUser(end_user_tc3);
		// search and remove role
		boolean result3 = rolePage.searchAndRemoveFromCart(role3);
		System.out.println("The value of result3 = "+result3);
		if(result3)
		{
			// click on checkout button
			rolePage.clickOnCheckout();
			request_number = submitObj.submitAccessRequest();
			if (request_number.equals(""))
				Assert.assertTrue(false, "Request was not raised properly");
			else
				logger.pass("Request raised with number " + request_number, MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			System.out.println("Request Number for TC3 role removal is " + request_number);
	
			logger.pass("Roles successfully removed by requestor");
		}
		else
			logger.pass("No roles to remove for TC3");

		home.logoff();
	}

}
