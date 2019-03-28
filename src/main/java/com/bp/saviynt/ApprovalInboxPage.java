package com.bp.saviynt;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class ApprovalInboxPage extends TestBase
{
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[1]")
	private WebElement acceptButton1;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[2]")
	private WebElement rejectButton1;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[3]")
	private WebElement acceptButton2;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[4]")
	private WebElement rejectButton2;

	@FindBy(xpath = "//a[contains(@class,'buttonreject') and contains(text(),'Reject All')]")
	private WebElement rejectAllButton;
	
	@FindBy(xpath = "//a[@onclick='confirmandsubmit()']")
	private WebElement confirmButton;
	
	@FindBy(xpath = "//a[contains(text(),'HOME')]")
	private WebElement req_home_link;

	@FindBy(xpath = "//*[@class='page-title']")
	private WebElement approvalInboxText;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTable123']")
	private WebElement searchBox;
	
	 @FindBy(xpath = "//*[contains(text(),'Approval Inbox')]")
	 private WebElement approval_inbox_side;
	 
	 @FindBy(xpath = "(//*[contains(text(),'Request Approval')])[2]")
	 private WebElement requestApprovalHeaderSidePanel;
	 
	 @FindBy(xpath = "(//*[contains(text(),'Add Mitigating Control')])[1]")
	 private WebElement addMitigatingControlButton;
	 
	 @FindBy(xpath = "(//*[contains(@id,'addmitigating')])[2]")
	 private WebElement addMitigatingControlButton2;
	 
	 @FindBy(xpath = "//td[contains(text(),'EPOMC001')]")
	 private WebElement mitigatingControl_Row1;
	 
	 @FindBy(xpath ="//td[contains(text(),'EPOMC002')]")
	 private WebElement mitigatingControl_Row;
	 
	 @FindBy(xpath = "(//*[@type='radio' and contains(@title,'EPOMC001')])[1]")
	 private WebElement firstRadioButton;
	 
	 /*@FindBy(xpath = "(//*[@type='radio' and @class='uniform' and @name='mitigatingcontrol' and @title='EPOMC002'])[1]")
	 private WebElement secondRadioButton;*/
	 
	 @FindBy(xpath = "(//*[@type='radio'])[6]")
	 private WebElement secondRadioButton;
	 @FindBy(xpath = "//a[@id='nextButtonMC']")
	 private WebElement nextButton;
	 
	 @FindBy(xpath = "//button[@id='submitdate']")
	 private WebElement submitDateButton;
	 
	@FindBy(xpath="//span[contains(text(),'(Purchase Order Entry and Goods')]")
	private WebElement purchaseOrderHeader;
	
	@FindBy(xpath = "//*[@id = 'dtsearch_myDataTablemitigatingControls']")
	private WebElement textBox;
	
	//14/1/19
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[5]")
	private WebElement acceptButton3;
	
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[6]")
	private WebElement rejectButton3;
	
	@FindBy(xpath = "//a[contains(text(),'ARS')]")
	private WebElement ars_link;           // instead of home
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTablemitigatingControls']")
	private WebElement searchBoxForMC; // search box for mitigating control
	
	@FindBy(xpath ="//button[contains(text(),'Submit')]")
	private WebElement submitMC; // submit mitigating control
	WebDriverWait wait;

	public ApprovalInboxPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//method to search for the request number which is raised by the requester for the end user
	public void searchRequestNumber(String requestID)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(requestID);
		searchBox.sendKeys(Keys.ENTER);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+requestID+"')]")));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(approval_inbox_side));
		//wait.until(ExpectedConditions.visibilityOf(requestApprovalHeaderSidePanel));
	}
	
	//method to accept the first role for the end user
	public void acceptFirstRole() 
	{
		acceptButton1.click();
		System.out.println("Role1 approved for the user");
	}
	
	//method to reject the first role for the end user
	public void rejectFirstRole() 
	{
		rejectButton1.click();
		System.out.println("Role1 rejected for the user");
	}

	//method to accept the second role for the end user
	public void acceptSecondRole() 
	{
		acceptButton2.click();
		System.out.println("Role2 approved for the user");
	}

	//method to reject the first role for the end user
	public void rejectSecondRole()
	{
		rejectButton2.click();
		System.out.println("Role2 rejected for the user");
	}
	
	public void acceptThirdRole()
	{
		acceptButton3.click();
		System.out.println("role3 accepted");
	}
	
	public void rejectAllRole()
	{
		TestBase.scrollUp(driver);
		rejectAllButton.click();
		System.out.println("All roles rejected for the user");
	}
	
	//method to click on the confirm button after accepting/rejecting the roles requested
	public boolean clickOnConfirm()
	{
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
		TestBase.javaScriptClickbyElement(driver, confirmButton);
		//confirmButton.click();
		System.out.println("confirm button clicked");
		wait.until(ExpectedConditions.elementToBeClickable(req_home_link));
		//wait.until(ExpectedConditions.visibilityOf(requestApprovalHeaderSidePanel));
		//boolean result = requestApprovalHeaderSidePanel.getText().equalsIgnoreCase("Request Approval");
		boolean result = approvalInboxText.getText().equalsIgnoreCase("Approval Inbox");
		//ars_link.click();
		req_home_link.click();
		return result;
	}
	
	public void addMitigatingControl(String controlName)
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMitigatingControlButton));
		addMitigatingControlButton.click();
		wait.until(ExpectedConditions.visibilityOf(searchBoxForMC));
		searchBoxForMC.sendKeys(controlName);
		searchBoxForMC.sendKeys(Keys.ENTER);
		//wait.until(ExpectedConditions.visibilityOf(mitigatingControl_Row1));
		wait.until(ExpectedConditions.visibilityOf(firstRadioButton));
		firstRadioButton.click();
		/*TestBase.javaScriptClickbyElement(driver, nextButton);
		wait.until(ExpectedConditions.elementToBeClickable(submitDateButton));
		submitDateButton.click();*/
		TestBase.javaScriptClickbyElement(driver, submitMC);
		
	}
	//button[contains(text(),'Submit')]
	//input[@id='dtsearch_myDataTablemitigatingControls']
	public void clickPurchaseOrderEntryHeader()
	{
		TestBase.javaScriptClickbyElement(driver, purchaseOrderHeader);
	}
	
	public void addMitigatingControl2() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMitigatingControlButton2));
		TestBase.javaScriptClickbyElement(driver, addMitigatingControlButton2);
		wait.until(ExpectedConditions.elementToBeClickable(mitigatingControl_Row));
		secondRadioButton.click();
		TestBase.javaScriptClickbyElement(driver, nextButton);
		wait.until(ExpectedConditions.elementToBeClickable(submitDateButton));
		submitDateButton.click();
	}

	
}