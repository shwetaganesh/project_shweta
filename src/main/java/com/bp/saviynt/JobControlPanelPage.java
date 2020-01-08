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

public class JobControlPanelPage extends TestBase{
	
	@FindBy(xpath = "//a[contains(text(),'UTILITY')]")
	private WebElement utilityLink;
	
	@FindBy(xpath = "//*[contains(text(),'Trigger Chain Job')]")
	private WebElement triggerJobLink;
	
	@FindBy(xpath = "//a[contains(text(),'PROV')]")
	private WebElement provLink;
	
	/*@FindBy(xpath = "(//div[@id='collapse_0_22']//div[@class='col-md-1' ]//*[contains(text(),'Action')])[1]")
	private WebElement actionButton;*/
	
	@FindBy(xpath = "(//div[@id='collapse_0_23']//*[contains(text(),'Action')])[1]")
	private WebElement actionButtonInProvisioningJob;
	
	@FindBy(xpath = "(//div[@id='collapse_0_21']//*[contains(text(),'Action')])[1]")
	private WebElement actionButtonInTriggerChain;
	
	@FindBy(xpath ="//div[@class='btn-group pull-right open']//*[contains(text(),'Edit Trigger')]")
	private WebElement editTrigger;
	
	@FindBy(xpath = "//button[@class='btn green' and contains(text(),'Run Now')]")
	private WebElement runButton;
	
	//14/01/19
	@FindBy(xpath ="//*[contains(text(),'Provisioning Job')]")
	private WebElement provisioningJobLink;
	
	/*@FindBy(xpath ="(//*[contains(text(),'Provisioning Job')]/following::a[contains(text(),'PROV')])[1]")  // job name changing everytime.
	private WebElement provisioningText;*/
	@FindBy(xpath ="(//*[contains(text(),'Provisioning Job')]/following::a[1])[1]")
	private WebElement provisioningText;
	
	@FindBy(xpath ="(//*[@id='gritter-without-image2' and @class='WSRetryJob'])[1]")
	private WebElement startButton;
	
	@FindBy(xpath ="//li[@class='select2-search-field']//input[@type='text']")
	private WebElement selectSystemTextBox;
	
	@FindBy(xpath ="(//button[@class='btn green' and contains(text(),'Submit')])[2]")
	private WebElement submitButton;
	WebDriverWait wait;
	
	@FindBy(xpath = "(//li//a[contains(text(),'DATA')])[2]")
	private WebElement data;
	
	@FindBy(xpath = "//div//h4[contains(text(),'UserImportJob')]")
	private WebElement userImportJob;
	
	@FindBy(xpath = "(//div[@id='collapse_3_4']//*[contains(text(),'Action')])[1]")
	private WebElement actionButtonInUserImportJob;
	
	@FindBy(xpath = "(//*[@id='gritter-without-image2' and @class='UserImportJob'])[1]")
	private WebElement startButtonInUserImportJob;
	
	@FindBy(xpath = "(//h4[contains(text(),'Enter Details')])[2]")
	private WebElement enterDetailsPopUpHeader;
	
	@FindBy(xpath = "//div[contains(@id,'importsavconnectkey')]")
	private WebElement saviyntConnectDropDown;
	
	@FindBy(xpath = "(//div//div[contains(text(),'No')])[2]")
	private WebElement noOptionInSaviyntConnectDropDown;
	
	@FindBy(xpath = "//div[contains(@id,'s2id_externalconnkey')]")
	private WebElement externalConnectionDropDown;
	
	@FindBy(xpath = "//label[contains(text(),'Job Type')]//following::a[1]")
	private WebElement jobTypeDropDown;
	
	@FindBy(xpath = "//div[contains(text(),'Full Import')]")
	private WebElement fullImport;
	
	@FindBy(xpath = "(//div[@class='modal-footer']//button[contains(text(),'Submit')])[2]")
	private WebElement submitButtonForUserImport;
	
	public JobControlPanelPage(WebDriver ldriver)
	{
		driver = ldriver;
		wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
	}
	public void openUtilityandTriggerChain()
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, triggerJobLink);
		triggerJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provLink));
		wait.until(ExpectedConditions.visibilityOf(actionButtonInTriggerChain));
		actionButtonInTriggerChain.click();
		editTrigger.click();
		wait.until(ExpectedConditions.visibilityOf(runButton));
		runButton.click();
	}
	
	public void openUtilityandProvisioningJob(String systemName)
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, provisioningJobLink);
		provisioningJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provisioningText));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Provisioning Job')]/following::a[1]")));
		actionButtonInProvisioningJob.click();
		wait.until(ExpectedConditions.visibilityOf(startButton));
		startButton.click();
		wait.until(ExpectedConditions.visibilityOf(selectSystemTextBox));
		selectSystemTextBox.sendKeys(systemName);
		selectSystemTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();		
		
	}
	
	// run the job to import user
		public void triggerJobToImportUser(String connectionName) throws InterruptedException
		{
			wait.until(ExpectedConditions.visibilityOf(data));
			data.click();
			wait.until(ExpectedConditions.elementToBeClickable(userImportJob));
			userImportJob.click();
			wait.until(ExpectedConditions.visibilityOf(actionButtonInUserImportJob));
			actionButtonInUserImportJob.click();
			wait.until(ExpectedConditions.visibilityOf(startButtonInUserImportJob));
			startButtonInUserImportJob.click();
			wait.until(ExpectedConditions.visibilityOf(enterDetailsPopUpHeader));
			saviyntConnectDropDown.click();
			noOptionInSaviyntConnectDropDown.click();
			wait.until(ExpectedConditions.visibilityOf(externalConnectionDropDown));
			externalConnectionDropDown.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(connectionName);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOf(jobTypeDropDown));
			jobTypeDropDown.click();
			wait.until(ExpectedConditions.visibilityOf(fullImport));
			fullImport.click();
			TestBase.scrollDownToElement(driver, submitButtonForUserImport);
			submitButtonForUserImport.click();
			
			
		}

}
