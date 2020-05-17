package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class AnalyticsConfigurationPage extends TestBase {
	
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Analytics Configurations')]")
	private WebElement  analyticsConfigurationPageHeader;
	
	@FindBy(xpath = "//a[@onclick='showCreateOptions()']")
	private WebElement  createNewAnalyticsButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Create New Analytics (v2)')]")
	private WebElement  createNewAnalyticsHeader; // header appearing in pop-up 
	
	@FindBy(xpath = "(//label[@class='btn blue'])[1]")
	private WebElement  firstOpotionInPopUp;
	
	@FindBy(xpath = "(//button[@type='button' and contains(text(),'Create')])[1]")
	private WebElement firstCreateButtonInPopUp;
	
	public AnalyticsConfigurationPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
	}
	
	public void clickOnCreateNewAnalyticsButton()
	{
		wait.until(ExpectedConditions.visibilityOf(createNewAnalyticsButton));
		createNewAnalyticsButton.click();
		wait.until(ExpectedConditions.visibilityOf(createNewAnalyticsHeader));
	}
	
	public void createNewAnalyticsUsingSQLQuery()
	{
		wait.until(ExpectedConditions.visibilityOf(firstOpotionInPopUp));
		//firstRadioButtonInPopUp.click();
		TestBase.javaScriptClickbyElement(driver, firstOpotionInPopUp);
		wait.until(ExpectedConditions.elementToBeClickable(firstCreateButtonInPopUp));
		firstCreateButtonInPopUp.click();
	}
}
