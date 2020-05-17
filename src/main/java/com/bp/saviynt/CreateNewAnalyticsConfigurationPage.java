package com.bp.saviynt;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.lib.propertiesFileOperations;
import com.bp.testbase.TestBase;

public class CreateNewAnalyticsConfigurationPage extends TestBase{
	
	Random rand = new Random();
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Create New Analytics Configuration')]")
	private WebElement createNewAnalyticsConfigPageHeader;
	
	@FindBy(xpath = "//label[contains(text(),'Analytics Name')]")
	private WebElement analyticsNameTextBoxLabel;
	
	@FindBy(xpath = "//input[@id='analyticsName']")
	private WebElement analyticsNameTextBox;
	
	@FindBy(xpath = "//textarea[@id='analyticsQry']")
	private WebElement analyticsQueryTextBox;
	
	@FindBy(xpath = "//textarea[@id='description']")
	private WebElement descriptionTextBox;
	
	@FindBy(xpath = "(//label[contains(text(),'Category')])[1]/following::a[1]")
	private WebElement categoryDropDownButton;
	
	@FindBy(xpath = "(//label[contains(text(),'Risk')])[1]/following::a[1]")
	private WebElement riskDropDownButton;
	
	@FindBy(xpath = "//a[contains(text(),'Create')]")
	private WebElement createButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Saviynt Security Manager')]")
	private WebElement errorMessagePopUpHeader;
	
	@FindBy(xpath = "//div[contains(text(),'Analytics Name Already Exists')]")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//button[@class='btn default closebutton']")
	private WebElement popUpCloseButton;
	
	@FindBy(xpath = "//h4[@class='modal-title' and contains(text(),'Columns to show')]")
	private WebElement columnDetailsPopUpHeader;
	
	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(),'Submit')]")
	private WebElement  submitButton; // submitButton in column details pop up window.
	
	@FindBy(xpath = "//h3[contains(text(),'Analytics Configuration Detail')]")
	private WebElement configDetailHeader;
	
	@FindBy(xpath = "//button[contains(text(),'Edit')]")
	private WebElement editButton;
	
	@FindBy(xpath = "//label[contains(text(),'Owner')]")
	private WebElement ownerLabel;
	
	@FindBy(xpath = "//button[contains(text(),'Add Owner')]")
	private WebElement addOwnerButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Add Owner Type')]")
	private WebElement  addOwnerPopUpHeader;
	
	@FindBy(xpath = "//label[contains(text(),'Owner Type')]/following::a")
	private WebElement ownerTypeDropDownButton;
	
	@FindBy(xpath = "(//div[contains(text(),'User')])[1]")
	private WebElement firstOptionInDropDown; // first option in the drop down for owner type
	
	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement nextButtonInPopUp;
	
	@FindBy(xpath = "//input[@id='dtsearch_allusersofloggedmanager']")
	private WebElement  searchOwnerTextBox;
	
	@FindBy(xpath = "//button[@id='submitbtn']")
	private WebElement ownerSubmitButton;
	
	@FindBy(xpath ="//div[contains(text(),'Owner Added')]")
	private WebElement successMessage; // success message that appears when owner is added successfully.
	
	@FindBy(xpath = "//a[@id='updatebutton']")
	private WebElement updateButton;
	
	@FindBy(xpath = "(//div[@class='actions']//a)[1]")
	private WebElement runDropDownButton;
	
	@FindBy(xpath = "//ul[@role='menu']//li[2]//a")
	private WebElement runNowButton;
	
	@FindBy(xpath ="//ul[@role='menu']//li[3]//a")
	private WebElement runHistoryButton;
	
	@FindBy(xpath = "//div[Total 20000 record(s) saved")
	private WebElement recordFetchedSuccessMessage;
	
	

	public CreateNewAnalyticsConfigurationPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
	}
	
	propertiesFileOperations operation = new propertiesFileOperations();
	
	public String generateAnalyticsName(String analyticsName)
    {
        String name = operation.readAnalyticsName(analyticsName);
       
        int number = rand.nextInt(9 - 0 + 1) + 0;
        name = name + number;
        return name;
    }
	
	
	public void addDetails(String name,String query) throws InterruptedException
    {
       
        wait.until(ExpectedConditions.visibilityOf(analyticsNameTextBoxLabel));
        analyticsNameTextBox.sendKeys(name);
        analyticsQueryTextBox.sendKeys(query);
        String description = operation.readDescription();
        descriptionTextBox.sendKeys(description);
        categoryDropDownButton.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys("Test");
        driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys(Keys.ENTER);
       
        riskDropDownButton.click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys("Low");
        driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys(Keys.ENTER);
    }
	
	public void clickOnCreateButton()
	{
		TestBase.scrollDownToElement(driver, createButton);
		createButton.click();
	}
	
	public void checkForMessageDisplayedForExistingAnalyticsName(String analyticsName)
    {
        for(int i=0;i<5;i++)
        {
            if(driver.findElements(By.xpath("//div[contains(text(),'Analytics Name Already Exists')]")).size()>0) {
                wait.until(ExpectedConditions.elementToBeClickable(popUpCloseButton));
                popUpCloseButton.click();
                TestBase.scrollUp(driver);
                int number = rand.nextInt(9 - 0 + 1) + 0;
                analyticsName = analyticsName+number;
                analyticsNameTextBox.clear();
                analyticsNameTextBox.sendKeys(analyticsName);
                TestBase.scrollDownToElement(driver, createButton);
                createButton.click();
            }
            else {
                break;
            }
           
        }
           
           
    }
	
	// click on submit button, that appears in the pop up, after clicking on Create button in Create new analytics config page.
	public void clickOnsubmitForColumnDetailsPopUp()
	{
		wait.until(ExpectedConditions.visibilityOf(columnDetailsPopUpHeader));
		TestBase.scrollDownToElement(driver, submitButton);
		submitButton.click();
	}
	
	public void clickOnEditButton() {
		
		wait.until(ExpectedConditions.visibilityOf(configDetailHeader));
		TestBase.scrollDownToElement(driver, editButton);
		editButton.click();
	}
	
	public void addOwnerAndUpdate(String owner)
	{
		wait.until(ExpectedConditions.visibilityOf(ownerLabel));
		wait.until(ExpectedConditions.elementToBeClickable(addOwnerButton));
		addOwnerButton.click();
		wait.until(ExpectedConditions.visibilityOf(addOwnerPopUpHeader));
		ownerTypeDropDownButton.click();
		wait.until(ExpectedConditions.visibilityOf(firstOptionInDropDown));
		firstOptionInDropDown.click();
		wait.until(ExpectedConditions.elementToBeClickable(nextButtonInPopUp)).click();
		wait.until(ExpectedConditions.visibilityOf(searchOwnerTextBox));
		searchOwnerTextBox.sendKeys(owner);
		searchOwnerTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody//td[2][contains(text(),'"+owner+"')]")));
		driver.findElement(By.xpath("//input[@type='radio' and @label='"+owner+"']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(ownerSubmitButton));
		TestBase.scrollDownToElement(driver, ownerSubmitButton);
		ownerSubmitButton.click();
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		popUpCloseButton.click();
		
		TestBase.scrollUp(driver);
		descriptionTextBox.sendKeys(".");
		//TestBase.scrollDownToElement(driver, element);
		TestBase.scrollToEndOfPage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
		
	}
	
	public void clickOnRunDropDownAndRunNow()
	{
		wait.until(ExpectedConditions.visibilityOf(runDropDownButton));
		runDropDownButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(runNowButton));
		runNowButton.click();
	}

	public void clickOnRunDropDownAndRunHistory()
	{
		wait.until(ExpectedConditions.visibilityOf(runDropDownButton));
		runDropDownButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(runHistoryButton));
	}
	
	public void checkRecordFetchSuccessMessage()
	{
		wait.until(ExpectedConditions.visibilityOf(recordFetchedSuccessMessage));
		popUpCloseButton.click();
	}
}
