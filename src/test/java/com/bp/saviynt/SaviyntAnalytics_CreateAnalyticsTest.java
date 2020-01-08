package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.bp.testbase.TestBase;

public class SaviyntAnalytics_CreateAnalyticsTest extends TestBase {
	
	String admin_ID = "TSTTEN10", password="password";
	
	
	@Test
	public void createAndViewReportTest() throws InterruptedException
	{
		logger = extent.createTest("Analytics test to create the report, update report owner, run and view the report");
		
		LaunchPage  launch = new LaunchPage(driver);
		//admin login
		launch.login(admin_ID, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnAnalyticsTab();
		
		AnalyticsHistoryPage analyticsHistory = new AnalyticsHistoryPage(driver);
		analyticsHistory.clickOnConfigurationList();
		
		AnalyticsConfigurationPage configPage = new AnalyticsConfigurationPage(driver);
		configPage.clickOnCreateNewAnalyticsButton();
		configPage.createNewAnalyticsUsingSQLQuery();
		
		CreateNewAnalyticsConfigurationPage newconfigPage = new CreateNewAnalyticsConfigurationPage(driver);
		newconfigPage.addDetails();
	}
	
	
	//@Test
	public void test1() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys("BSICAT1@bp365.onmicrosoft.com");
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys("!AJqENjf#22");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@aria-label,'Action Tracker')]")).click();
	}
	

}
