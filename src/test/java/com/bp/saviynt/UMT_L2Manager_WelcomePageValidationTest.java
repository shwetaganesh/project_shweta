package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_WelcomePageValidationTest extends TestBase{
	
	String userID,password="password";

	
	@Test(priority = 1)
	public void verifyHomePageAndWelcomeMessageForNormalUser() throws IOException {

		logger = extent.createTest("normal user log in, verify the home page and welcome message");
		LaunchPage launch = new LaunchPage(driver);
		userID = "L2TEST11";
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
	
	@Test(priority =2)
	public void verifyHomePageAndWelcomeMessageForFirstManager() throws IOException {

		logger = extent.createTest("first manager log in, verify the home page and welcome message");
		LaunchPage launch = new LaunchPage(driver);
		userID = "L2TEST12";
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
	
	@Test(priority = 1)
	public void verifyHomePageAndWelcomeMessageForSecondManager() throws IOException {

		logger = extent.createTest("Second manager log in, verify the home page and welcome message");
		LaunchPage launch = new LaunchPage(driver);
		userID= "L2TEST13";
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
