package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class HomePage extends TestBase
{
	@FindBy(xpath = "(//*[@class='icon-angle-down'])[1]")
	private WebElement logoff_expand;
	
	@FindBy(xpath = "//a[@href='/ECM/j_spring_security_logout']")
	private WebElement logoff_link;
	
	@FindBy(xpath = "//*[contains(text(),'Request Home')]")
	private WebElement request_home_link;
	
	@FindBy(xpath = "//div[contains(text(),'Request Enterprise Roles ')]/following::a[1]")
	private WebElement req_link;
	
	@FindBy(xpath = "//div[@id='arsRequestApproval']//a[@class='more']")
	private WebElement approvalInboxView;
	
	@FindBy(xpath = "//a[@href='/ECM/jbpmworkflowmanagement/showmyhistoryrequests']")
	private WebElement req_history_link;
	
	@FindBy(xpath = "//a[contains(text(),'HOME')]")
	private WebElement home_link;
	
	/*@FindBy(xpath = "//a[contains(text(),'ARS')]")
	private WebElement ars_link;  */         // instead of home
	
	@FindBy(xpath = "//a[contains(text(),'ADMIN')]")
	private WebElement adminLink;
	
	// 11-01-2019
	@FindBy(xpath = "//div[@id='arsRequestAccessForOthers'] //a[@class='more']")
	private WebElement requestApplicationSpecificRoles;
	
	WebDriverWait wait;
	
	public HomePage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	//method to open Request Enterprise Role tab to find the end user.
	public void openRequestEnterpriseRole()
	{
		wait.until(ExpectedConditions.elementToBeClickable(req_link));
		req_link.click();
	}
	
	//method to navigate to approval inbox
	public void openApprovalInbox()
	{
		wait.until(ExpectedConditions.elementToBeClickable(approvalInboxView));
		approvalInboxView.click();
	}
	
	//method to navigate to Request History
	public void openRequestHistory()
	{
		wait.until(ExpectedConditions.elementToBeClickable(req_history_link));
		req_history_link.click();
	}
	
	public void clickOnRequestHome()
	{
		wait.until(ExpectedConditions.elementToBeClickable(home_link));
		home_link.click();
		
	}
	public void clickOnHome()
	{
		/*wait.until(ExpectedConditions.elementToBeClickable(home_link));
		home_link.click();*/
		wait.until(ExpectedConditions.elementToBeClickable(home_link));
		home_link.click();
	}
	
	public void openAdminTab(){
		wait.until(ExpectedConditions.elementToBeClickable(adminLink));
		adminLink.click();
	}
	//method to logoff the User.
	public void logoff()
	{
		wait.until(ExpectedConditions.visibilityOf(logoff_expand));
		
		TestBase.javaScriptClickbyElement(driver, logoff_expand);
		//logoff_expand.click();
		wait.until(ExpectedConditions.visibilityOf(logoff_link));
		logoff_link.click();
		System.out.println("Logoff Successful");
	}
	
	//open request application specific roles link
	public void openRequestApplicationSpecificRoles()
	{
		wait.until(ExpectedConditions.visibilityOf(requestApplicationSpecificRoles));
		requestApplicationSpecificRoles.click();
	}
	
	
}