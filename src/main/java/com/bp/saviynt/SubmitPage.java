package com.bp.saviynt;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class SubmitPage extends TestBase
{
	@FindBy(xpath = "//a[contains(text(),'Submit') and @onclick='opennextpage()']")
	private WebElement submit_button;
	
	@FindBy(xpath = "//table[contains(@class,'table-striped table-bordered')]/child::tbody/child::tr/child::td[2]")
	private WebElement req_number;
	
	@FindBy(xpath = "(//table[contains(@class,'table-striped table-bordered')]/child::tbody/child::tr/child::td)[2]")
	private WebElement req_num_first;
	
	@FindBy(xpath = "(//table[contains(@class,'table-striped table-bordered')]/child::tbody/child::tr/child::td)[4]")
	private WebElement req_num_second;
	
	@FindBy(xpath = "(//*[contains(@id,'buisnesjustifcation')])[1]")
	private WebElement business_justification_first;
	
	@FindBy(xpath = "(//*[contains(text(),'Please select the role parameters for')])[1]")
	private WebElement expand_url_first;
	
	@FindBy(xpath = "(//a[@class='select2-choice'])[1]")
	private WebElement select_org_node_first;
	
	@FindBy(xpath = "(//*[text()='Select'])[1]")
	private WebElement select_down_arrow;
	
	@FindBy(xpath = "//*[@class='select2-result-label' and contains(text(),'50000005')]")
	private WebElement select_value;
	
	@FindBy(xpath = "//*[contains(text(),'Request Home')]")
	private WebElement request_home_link;
	
	WebDriverWait wait;
	
	public SubmitPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	//method to click on submit button and return the request number of the corresponding request raised by the requester.
	public String submitAccessRequest() throws InterruptedException
	{
		String request_number="";
		if(driver.toString().contains("Firefox"))
			Thread.sleep(10000);
		((JavascriptExecutor) driver)
		    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		TestBase.javaScriptClickbyElement(driver, submit_button);
		wait.until(ExpectedConditions.visibilityOf(req_number));
		request_number=req_number.getText();
		return request_number;
		
	}
	
	//method to click on submit button and return the request number of the corresponding request raised by the requester.
	//used for test scenario 3.
	public String submitAccessAndRevokeRequest()
	{
		String request_number="";
		TestBase.scrollDownToElement(driver, submit_button);
		TestBase.javaScriptClickbyElement(driver, submit_button);
		//submit_button.click();
		wait.until(ExpectedConditions.visibilityOf(req_num_first));
		//For revoke and invoke of roles...2 request numbers are getting generated
		//So merging those to return and in the methods where we'll call it, need to split as well
		request_number=req_num_first.getText()+req_num_second.getText();
		return request_number;
	}
	
	public void nonRegressionRoleRequest() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(business_justification_first));

		Thread.sleep(20000);
		wait.until(ExpectedConditions.elementToBeClickable(expand_url_first));
		expand_url_first.click();
		try {
			select_org_node_first.click();
		} catch (Exception e2) {
			System.out.println("Normal click did not worked");
		}
		TestBase.javaScriptClickbyElement(driver, select_org_node_first);
		wait.until(ExpectedConditions.elementToBeClickable(select_down_arrow));
		select_down_arrow.click();
		wait.until(ExpectedConditions.elementToBeClickable(select_value));
		select_value.click();
	}
	
	public String returnRequestNumber() {
		String requestNumber = "";
		wait.until(ExpectedConditions.visibilityOf(req_number));
		 requestNumber= req_number.getText();
		return requestNumber;
		
	}
}
