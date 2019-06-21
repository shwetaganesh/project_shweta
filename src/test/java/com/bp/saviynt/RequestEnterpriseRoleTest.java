package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class RequestEnterpriseRoleTest extends TestBase {

	UsernameGeneration userObject = new UsernameGeneration();
	String requestNumber, password = "password";

	@Test
	public void enterpriseRoleRequestTest() throws Exception {
		
		logger = extent.createTest("Request for enterprise role by the end user");
		LaunchPage launch = new LaunchPage(driver);
		String endUser = userObject.readUserName();
		String role = "BP Remote user";
		launch.login(endUser, password);
		HomePage home = new HomePage(driver);
		home.openRequestEnterpriseRole();
		FindUserPage userPage = new FindUserPage(driver);
		userPage.searchEndUser(endUser);
		/*
		 * RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
		 * requestAcessPage.endUserRequestAccessForEnterPriseRole(userRole);
		 * requestAcessPage.enterpriseCheckoutRole();
		 */
		FindRolePage rolePage = new FindRolePage(driver);
		rolePage.searchandAddtoCart(role);
		rolePage.clickOnCheckout();
		// retrieve the request number
		SubmitPage submitObj = new SubmitPage(driver);
		// submit the request
		requestNumber = submitObj.submitAccessRequest();
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
	
	@Test(priority=2)
	public void viewRequestHistoryTest() throws IOException
	{
		logger = extent.createTest("View the request history of the end user");
		LaunchPage launch = new LaunchPage(driver);
		String endUser = userObject.readUserName();
		launch.login(endUser, password);
		HomePage home = new HomePage(driver);
		home.openRequestHistory();
		RequestHistoryPage history = new RequestHistoryPage(driver);
		history.searchRequestAndOpen(requestNumber);
		logger.pass("Request history snapshot ", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}

}
