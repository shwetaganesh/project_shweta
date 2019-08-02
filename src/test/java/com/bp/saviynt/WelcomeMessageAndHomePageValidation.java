package com.bp.saviynt;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class WelcomeMessageAndHomePageValidation  extends TestBase

{
	String password = "password";
	String searchItem ="Saviynt";
	public String requestNumber;
	public String userRole;
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	String userName,uname,user;
	String firstName = excel1.getData(0,1,1);
	String lastName = excel1.getData(0,1,2);
	String managerId = excel1.getData(0,1,3);
	String oldpassword = excel1.getData(0,1,4); // password set using admin function during end user creation
	
	
	// creation of new user test
	@Test(priority = 1)
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

	// end user logs in to verify the welcome message, different tiles displayed in the user's home page.
	@Test(priority=2)
	public void verifyWelcomeMessage() throws IOException {
		
		logger = extent.createTest("end user log in, verify the home page and welcome message");
		String end_user = userObject.readUserName();
		System.out.println("end user  is: "+end_user);
		System.out.println(oldpassword);

		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as end user***
		launch.login(end_user, oldpassword);
		ResetPasswordPage reset = new ResetPasswordPage(driver);
		reset.changePassword(end_user, oldpassword, password);
		HomePage home = new HomePage(driver);
		home = reset.setUpSecurityQuestions();
		Assert.assertTrue(home.verifyWelcomeMessage(), "Welcome Message not displayed");
		Assert.assertTrue(home.verifyUserInformation(), "User Information not displayed");
		System.out.println("Number of tiles" + home.totalNumberofTiles());
		Assert.assertEquals(home.totalNumberofTiles(), 6, "Number of tiles are more or less");
		home.getNameOfTiles();
		Assert.assertTrue(home.verifyTilesName(
				"Setup Delegates&Request History&Request Approval&Request Access For Others&Request Enterprise Roles&View Existing Access"));
		logger.pass("Tiles in the home page" , MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}
	
}
