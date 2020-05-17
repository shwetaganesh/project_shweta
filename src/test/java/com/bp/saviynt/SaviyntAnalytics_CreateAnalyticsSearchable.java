package com.bp.saviynt;

import java.awt.AWTException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.bp.lib.propertiesFileOperations;
import com.bp.testbase.TestBase;

public class SaviyntAnalytics_CreateAnalyticsSearchable extends TestBase {
	
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
		
		
		CreateNewConfigurationSearchablePage newconfigPage = new CreateNewConfigurationSearchablePage(driver);
	    // Method to Generate Analytics Name
		
		String name = newconfigPage.generateAnalyticsName("analyticsName_Searchable");
		
		propertiesFileOperations operation = new propertiesFileOperations();
		
		String query = operation.readQuery("query3");
		//add details to analytics
		newconfigPage.addDetails(name,query);
		
		//run analytics
		newconfigPage.clickOnRunTimeAnalyticsButton();
		
		//click on create button
		newconfigPage.clickOnCreateButton();
		
		
		//to set Attribute Type
		newconfigPage.setAttributeType();
		
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
		
		// Generate the report
		newconfigPage.generateResultReport();
		
		// Refresh the data
		newconfigPage.refreshData();
		
		// newconfigPage.clickOnRunTimeAnalyticsButton();
		


}
}


