package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class RequestApplicationSpecificRoleTest extends TestBase {
	
	String password = "password";
	String searchItem ="Saviynt";
	public String requestNumber,endUser;
	public String userRole;
	
	UsernameGeneration userObject = new UsernameGeneration();

	@Test(priority=1)
	public void RequestApplicatonSpecificRoles() throws Exception
	{
		logger = extent.createTest("Request for application specific role by the end user");
		LaunchPage launch = new LaunchPage(driver);
		endUser = userObject.readUserName();
		launch.login(endUser, password);
		HomePage home = new HomePage(driver);
		home.openRequestApplicationSpecificRoles();
		FindUserPage userPage = new FindUserPage(driver);
		userPage.searchEndUser(endUser);
		
		FindRolePage rolePage = new FindRolePage(driver);
		rolePage.searchandAddtoCartForUser(searchItem);
		logger.pass("Adding to cart: " +searchItem , MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		rolePage.clickOnCheckout1();
		RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
		requestAcessPage.addUserGroup();
		requestAcessPage.clickOnNext();
		// provide business justification and submit
		JustificationPage justifyPage = new JustificationPage(driver);
		//justifyPage.enterBusinessJustificationAndComment();
		justifyPage.clickOnSubmit();
		// retrieve the request number
		SubmitPage submit = new SubmitPage(driver);
		requestNumber = submit.returnRequestNumber();
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
		logger = extent.createTest("View the request history of end user");
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
