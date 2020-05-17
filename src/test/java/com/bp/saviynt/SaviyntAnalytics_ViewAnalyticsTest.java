package com.bp.saviynt;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bp.testbase.TestBase;

public class SaviyntAnalytics_ViewAnalyticsTest extends TestBase {

	String adminID ="TSTTEN10", password = "password";
	
	@Test
	public void viewAnalyticsTest()
	{
		logger = extent.createTest("View analytics Test");
		LaunchPage launch = new LaunchPage(driver);
		launch.login(adminID, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnAnalyticsTab();
		
		AnalyticsHistoryPage analyticsPage = new AnalyticsHistoryPage(driver);
		//analyticsPage.clickOnConfigurationList();
		analyticsPage.selectCategoryFromTheDropDown();
		boolean result = analyticsPage.clickOnISIMConnectionControlName();
		
		Assert.assertEquals(result, true);
		logger.pass("Records obtained after selecting TEST category");
		
		AnalyticsHistoryDetailsPage detailsPage = new AnalyticsHistoryDetailsPage(driver);
		result = detailsPage.validateReport();
		
		Assert.assertEquals(result, true);
		logger.pass("AD-ISIM connection reports fetched successfully");
		
	}
	
}
