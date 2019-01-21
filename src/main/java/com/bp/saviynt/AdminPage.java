package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class AdminPage extends TestBase
{
	
	@FindBy(xpath = "(//*[contains(text(),'Job Control Panel')])[1]")
	private WebElement jobControlPanelLink;
	
	@FindBy(xpath = "//div[@class='page-container']//tbody[@role='alert']//tr[1]//td[1]")
	private WebElement firstRowInTable;
	
	@FindBy(xpath = "//div[@id='jobstatus']//*[@class='block']")
	private WebElement jobStatusTitle;
	
	@FindBy(xpath = "//a[contains(text(),'UTILITY')]")
	private WebElement utilityLink;
	
	@FindBy(xpath = "//*[contains(text(),'Trigger Chain Job')]")
	private WebElement triggerJobLink;
	
	@FindBy(xpath = "//a[contains(text(),'PROV')]")
	private WebElement provLink;
	
	@FindBy(xpath = "(//div[@id='collapse_0_22']//div[@class='col-md-1' ]//*[contains(text(),'Action')])[1]")
	private WebElement actionButton;	
	
	@FindBy(xpath ="//div[@class='btn-group pull-right open']//*[contains(text(),'Edit Trigger')]")
	private WebElement editTrigger;
	
	@FindBy(xpath = "//button[@class='btn green' and contains(text(),'Run Now')]")
	private WebElement runButton;
	
	//14/01/19
	@FindBy(xpath ="//*[contains(text(),'Provisioning Job')]")
	private WebElement provisioningJobLink;
	
	@FindBy(xpath ="(//a[contains(text(),'PROVISIONING')])[1]")
	private WebElement provisioningText;
	
	@FindBy(xpath ="(//*[@id='gritter-without-image2' and @class='WSRetryJob'])[1]")
	private WebElement startButton;
	
	@FindBy(xpath ="//li[@class='select2-search-field']//input[@type='text']")
	private WebElement selectSystemTextBox;
	
	@FindBy(xpath ="(//button[@class='btn green' and contains(text(),'Submit')])[2]")
	private WebElement submitButton;
	
	WebDriverWait wait;
	
	public AdminPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void openJobControlPanelLink()
	{
		wait.until(ExpectedConditions.visibilityOf(firstRowInTable));
		TestBase.javaScriptClickbyElement(driver, jobControlPanelLink);
	}
	
	public void openUtility()
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, triggerJobLink);
		triggerJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provLink));
		actionButton.click();
		editTrigger.click();
		wait.until(ExpectedConditions.visibilityOf(runButton));
		runButton.click();
	}
	
	public void openUtilityNew()
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, provisioningJobLink);
		provisioningJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provisioningText));
		actionButton.click();
		wait.until(ExpectedConditions.visibilityOf(startButton));
		startButton.click();
		wait.until(ExpectedConditions.visibilityOf(selectSystemTextBox));
		selectSystemTextBox.sendKeys("TEST_SalesforceTest");
		selectSystemTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();		
		
	}
}
