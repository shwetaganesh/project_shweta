package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_WelcomeMessageAndHomePageValidation extends TestBase {

	String userID = "navtest1";
	String password = "password";

	@Test(priority = 1)
	public void verifyWelcomeMessage() throws IOException {

		logger = extent.createTest("end user log in, verify the home page and welcome message");
		LaunchPage launch = new LaunchPage(driver);
		// *** Login to application as umt user***
		launch.login(userID, password);
		HomePage home = new HomePage(driver);
		Assert.assertTrue(home.verifyWelcomeMessage(), "Welcome Message not displayed");
		Assert.assertTrue(home.verifyUserInformation(), "User Information not displayed");
		System.out.println("Number of tiles" + home.totalNumberofTiles());
		home.getNameOfTiles();
		Assert.assertTrue(home.verifyTilesName(
				"Setup Delegates&Request History&Request Approval&Request Access For Others&Request Enterprise Roles&View Existing Access&Update User Details"));
		Assert.assertEquals(home.totalNumberofTiles(), 7, "Number of tiles are more or less");
		logger.pass("Tiles in the home page", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}

}
