package com.bp.saviynt;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bp.lib.propertiesFileOperations;
import com.bp.testbase.TestBase;

public class SaviyntAnalytics_CreateAnalyticsTest extends TestBase {
	
	String admin_ID = "TSTTEN10", password="password";
	
	
	@Test
	public void createAndViewReportTest() throws InterruptedException, AWTException
	{
		logger = extent.createTest("Analytics test to create the report, update report owner, run and view the report");
		
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
		
		
		CreateNewAnalyticsConfigurationPage newconfigPage = new CreateNewAnalyticsConfigurationPage(driver);
	    // Method to Generate Analytics Name
		
		String name = newconfigPage.generateAnalyticsName("analyticsName");
		
		propertiesFileOperations operation = new propertiesFileOperations();
		
		String query = operation.readQuery("query1");
		//add details to analytics
		newconfigPage.addDetails(name,query);
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
		
		// wait for the data to be fetched
		Thread.sleep(10000);
		// check for the success message
		newconfigPage.checkRecordFetchSuccessMessage();
		// run --> run history
		newconfigPage.clickOnRunDropDownAndRunHistory();
		
		AnalyticsHistoryPage historyPage = new AnalyticsHistoryPage(driver);
		// table is displayed containing a link for report that is created. 
		historyPage.fetchReportDetails();
		
		AnalyticsHistoryDetailsPage detailsPage = new AnalyticsHistoryDetailsPage(driver);
		// after clicking the link in the history page, result table is generated with number of records in it.
		boolean result = detailsPage.validateReport();
		Assert.assertEquals(result, true);
		
	}
	
	
	
}
