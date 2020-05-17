package com.bp.saviynt;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import com.bp.testbase.TestBase;

public class CreateAttestationsPage extends TestBase {
	
//Random rand = new Random();
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Welcome Admin Manager')]")
	private WebElement welcomeAdminManagerHeader;
	
	@FindBy(xpath = "//a[contains(text(),'Attestations')]")
	private WebElement attestationsHeading;
	
	@FindBy(xpath = "//h3[contains(text(),'Campaign')]")
	private WebElement campaignHeading;
	
	@FindBy(xpath = "//a[@id='createCampaign']")
	private WebElement createCampaignButton;
	
	@FindBy(xpath = "//h3[contains(text(),'Create Campaign')]")
	private WebElement createCampaignHeading;
	
	@FindBy(xpath = "//input[@id='campaign_name']")
	private WebElement campaign_nameInputBox;
	
	@FindBy(xpath = "//span[contains(text(),'Select User')]")
	private WebElement campaign_ownerSelectBox;
	
	@FindBy(xpath = "//div[@id='select2-drop']//input")
	private WebElement campaign_ownerInputBox;
	
	@FindBy(xpath = "//div[@id='s2id_campaignType']//a")
	private WebElement campaign_typeSelectBox;
	
	
	@FindBy(xpath = "//div[@id='select2-drop']//input[@class='select2-input']")
	private WebElement campaign_typeInputBox;
	
	@FindBy(xpath = "//input[@id='daystoexpire']")
	private WebElement no_daysToExpireCampaign;
	
	@FindBy(xpath="//div[@id='s2id_type']//span[contains(text(),'Select')]")
	private WebElement certifierSelectBox;
	
	
	//@FindBy(xpath = "//div[@id='select2-drop']//input[@class='select2-input']")"
	@FindBy(xpath="//div[contains(text(),'Select All Certifier')]")
	private WebElement certifierInputBox;
	
	
	
	@FindBy(xpath = "//button[@id='campaignCreateNow']")
	private WebElement  createNowButton; // to create the campaign
	
	
	public CreateAttestationsPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
	}
	
	// propertiesFileOperations operation = new propertiesFileOperations();
	
	public void clickOnAttestation()
	{
		wait.until(ExpectedConditions.visibilityOf(welcomeAdminManagerHeader));
		attestationsHeading.click();
	}
	
	public void clickOnCreateCampaign()
	{
		wait.until(ExpectedConditions.visibilityOf(campaignHeading));
		createCampaignButton.click();
	}
	
	
	
	public void addDetails() throws InterruptedException
    {
       
        wait.until(ExpectedConditions.visibilityOf(createCampaignHeading));
        campaign_nameInputBox.sendKeys("automation testing10122020");
        campaign_ownerSelectBox.click();
        campaign_ownerInputBox.sendKeys("admin");
       /* driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        campaign_ownerInputBox.sendKeys(Keys.ENTER);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOf(campaign_typeSelectBox));*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li//div[contains(text(),'Saviynt')])[1]")));
        driver.findElement(By.xpath("(//li//div[contains(text(),'Saviynt')])[1]")).click();
        campaign_typeSelectBox.click();
      // wait.until(ExpectedConditions.visibilityOf(campaign_typeInputBox));
       // campaign_typeInputBox.sendKeys("Role Owner");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       // campaign_typeInputBox.sendKeys(Keys.ENTER);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Role Owner')]"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        no_daysToExpireCampaign.sendKeys("2");
       //.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        TestBase.scrollDownToElement(driver, certifierSelectBox);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        certifierSelectBox.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Select All Certifier')]"))).click();
        // certifierInputBox.sendKeys("Select All Certifier");
        // certifierInputBox.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // certifierInputBox.sendKeys(Keys.ENTER);
        // Thread.sleep(5000);
        TestBase.scrollDownToElement(driver, createNowButton);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        createNowButton.click();     
    }
	
	

}
