package com.bp.saviynt;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.propertiesFileOperations;
import com.bp.testbase.TestBase;

public class SaviyntAnalytics_CreateAnalyticsRunTimeTest extends TestBase {
String admin_ID = "TSTTEN10", password="password";
	
	
	@Test
	public void createAndViewReportTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("Create analytics report : Runtime");
		
		TestBase.reduceBrowserResolution(driver);
		
		LaunchPage  launch = new LaunchPage(driver);
		//admin login
		launch.login(admin_ID, password);
		
		HomePage homePage = new HomePage(driver);
		// click on analytics tab present in the home page
		homePage.clickOnAnalyticsTab();
		
		AnalyticsHistoryPage analyticsHistory = new AnalyticsHistoryPage(driver);
		// after redirected to Analytics Tab, default landing page is History V2, we navigate to configuration list by clicking on the option in the side panel
		analyticsHistory.clickOnConfigurationList();
		
		AnalyticsConfigurationPage configPage = new AnalyticsConfigurationPage(driver);
		// click on create new analytics
		configPage.clickOnCreateNewAnalyticsButton();
		// using SQL query --> create
		configPage.createNewAnalyticsUsingSQLQuery();
		
		
		CreateNewAnalyticsConfigurationRunTimePage newconfigPage = new CreateNewAnalyticsConfigurationRunTimePage(driver);
	    // Method to Generate Analytics Name
		
		String name = newconfigPage.generateAnalyticsName("analyticsName_Runtime");
		
		propertiesFileOperations operation = new propertiesFileOperations();
		
		String query = operation.readQuery("query2");
		//add details to analytics
		newconfigPage.addDetails(name,query);
		
		newconfigPage.clickOnRunTimeAnalyticsButton();
		
		//click on create button
		newconfigPage.clickOnCreateButton();
		// check if analytics name already exists
		newconfigPage.checkForMessageDisplayedForExistingAnalyticsName(name);
		// submit column details 
		newconfigPage.clickOnsubmitForColumnDetailsPopUp();
		
		// scroll down the page to edit the created Analytics report
		newconfigPage.clickOnEditButton();
		// add owner to the report. Owner is the one, who can view the report. hence adding admin as the owner
		newconfigPage.addOwnerAndUpdate(admin_ID);
		
		newconfigPage.clickOnsubmitForColumnDetailsPopUp();
		// Run the report
		newconfigPage.clickOnRunDropDownAndRunNow();
		
		Thread.sleep(10000);
		
		AnalyticsHistoryDetailsPage detailsPage = new AnalyticsHistoryDetailsPage(driver);
        // after clicking the link in the history page, result table is generated with number of records in it.
        boolean result = detailsPage.validateReport();
        Assert.assertEquals(result, true);
       
        logger.pass("Data fetched for Run time report", MediaEntityBuilder
                .createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		
		
		
		
		
		
		
		
	}

}
