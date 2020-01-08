package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class AnalyticsHistoryDetailsPage extends TestBase {
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Analytics History  : AD - ISIM Connection')]")
	private WebElement pageHeader;
	
	
	public AnalyticsHistoryDetailsPage(WebDriver ldriver)
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
	}
	
	public boolean validateReport()
	{
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
		if(driver.findElements(By.xpath("//table[@id='analyticsDetailsList']//tbody//tr")).size()>0)
			return true;
		else 
			return false;
	}
	

}
