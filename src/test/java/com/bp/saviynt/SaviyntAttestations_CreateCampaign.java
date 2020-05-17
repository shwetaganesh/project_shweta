package com.bp.saviynt;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.bp.testbase.TestBase;

public class SaviyntAttestations_CreateCampaign extends TestBase {
String admin_ID = "TSTTEN10", password="password";
	
	
	@Test
	public void createAndViewReportTest() throws InterruptedException, AWTException
	{
		logger = extent.createTest("To create Campaign and approve it");
		
		TestBase.reduceBrowserResolution(driver);
		
		LaunchPage  launch = new LaunchPage(driver);
		//admin login
		launch.login(admin_ID, password);
		
		HomePage homePage = new HomePage(driver);
		// click on analytics tab present in the home page
		homePage.clickOnAttestationsTab();
		 
		
		
		CreateAttestationsPage attestPage = new CreateAttestationsPage(driver);
		
		// click on create new campaign
		attestPage.clickOnCreateCampaign();
		attestPage.addDetails();
		
	}
	

}
