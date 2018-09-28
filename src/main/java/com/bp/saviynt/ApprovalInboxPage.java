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
	
	WebDriverWait wait;

	public ApprovalInboxPage(WebDriver ldriver) 
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void searchRequestNumber(String requestID)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(requestID);
		searchBox.sendKeys(Keys.ENTER);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+requestID+"')]")));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(approval_inbox_side));
		
	}
	
	public void acceptFirstRole() 
	{
		acceptButton1.click();
		System.out.println("Role1 approved for the user");
	}

	public void rejectFirstRole() 
	{
		rejectButton1.click();
		System.out.println("Role1 rejected for the user");
	}

	public void acceptSecondRole() 
	{
		acceptButton2.click();

		System.out.println("Role2 approved for the user");
	}

	public void rejectSecondRole() 
	{
		rejectButton2.click();

		System.out.println("Role2 rejected for the user");
	}

	public boolean clickOnConfirm() 
	{
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
		confirmButton.click();
		System.out.println("confirm button clicked");
		wait.until(ExpectedConditions.elementToBeClickable(req_home_link));
		boolean result = approvalInboxText.getText().equalsIgnoreCase("Approval Inbox");
		req_home_link.click();
		return result;
	}
}