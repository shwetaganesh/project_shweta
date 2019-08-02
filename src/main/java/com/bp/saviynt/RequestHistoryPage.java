package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;



public class RequestHistoryPage extends TestBase
{
	@FindBy(xpath = "//input[@id='dtsearch_sample2']")
	private WebElement role_search_input;
	
	@FindBy(xpath = "//button[@id='search_sample2']")
	private WebElement role_search_button;
	
	@FindBy(xpath = "//tr[@class='odd']/child::td[10]/child::span")
	private WebElement verify_req_status;
	
	@FindBy(xpath = "//div[contains(text(),'Task History')]")
	private WebElement task_history;

	@FindBy(xpath = "//a[contains(@href,'/ECM/jbpmworkflowmanagement/tasklistforrequest?reqid=')]")
	private WebElement task_link;
	
	@FindBy(xpath = "(//td[@class=' sorting_1'])[1]")
	private WebElement first_result_row;
	
	@FindBy(xpath ="//a[contains(text(),'HOME')]")
	private WebElement home_button;
	
	@FindBy(xpath ="(//div[contains(@id,'dataBlock_RequestKey')])[1]/child::*")
	private WebElement request_type;
	
	//17/01/19
	@FindBy(xpath ="//table[@id='#scroller1']")
	private WebElement tableScroller;
	
	@FindBy(xpath = "//th[contains(text(),'Creation Date')]")
	private WebElement lastColumnInTable;
	
	@FindBy(xpath = "//td[10]")
	private WebElement statusColumn;
	
	
	WebDriverWait wait;
	
	public RequestHistoryPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
		
	}
	
	// method to search for the request and open it accordingly
	public void searchRequestAndOpen(String request_number){
		
	//boolean verify_endpoints=true;
	wait.until(ExpectedConditions.visibilityOf(first_result_row));
	role_search_input.clear();
	role_search_input.sendKeys(request_number);
	role_search_button.click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+request_number+"')]")));
	driver.findElement(By.xpath("//a[contains(text(),'"+request_number+"')]")).click();
	}
	
	// method to fetch the END POINTS after the roles requested are accepted.
	public ArrayList<String> clickOnTaskAndFetchEndPoints()
	{
		TestBase.scrollDownToElement(driver, task_history);
		task_link.click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='#scroller1']/child::tbody")));
		////table[@id='#scroller1']/child::tbody/child::tr
		List<WebElement> end_points=driver.findElements(By.xpath("//div[@id='ui-tabs-1']//div//tbody//tr"));
		ArrayList<String> arlist = new ArrayList<String>();
		String temp;
		for(int i=1;i<=end_points.size();i++)
		{
			temp=driver.findElement(By.xpath("//div[@id='ui-tabs-1']//div//tbody//tr["+i+"]//td[8]")).getText();
			////table[@id='#scroller1']/child::tbody/child::tr["+i+"]/child::td[8]
			arlist.add(temp);
		}
		TestBase.scrollToEndOfPage(driver);
		System.out.println("Current Endpoints are");
		System.out.println(arlist);
		return arlist;
	}
	
	// method to validate the END POINTS with reference to those provided in the spread sheet.
	public boolean validateEndPoints(ArrayList<String>arlist,ArrayList<String>validate)
	{
		boolean verify_endpoints=true;
		boolean all_found=true;
		for(String validate_check:validate)
		{
			for(String temp:arlist)
			{
				if(temp.contains(validate_check))
				{
					verify_endpoints=true;
					System.out.println(validate_check+" is found");
					break;
				}
				else
					verify_endpoints=false;
			}
			if(!verify_endpoints)
			{
				System.out.println(validate_check+" is missing");

				all_found=false;
			}
		}
		return all_found;
	}
	
	// method to verify whether the request number is for removing the role.
	public boolean isRoleRevokeRequest(String req_num)
	{
		wait.until(ExpectedConditions.visibilityOf(first_result_row));
		role_search_input.clear();
		role_search_input.sendKeys(req_num);
		role_search_button.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+req_num+"')]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+req_num+"')]")).click();
		if(request_type.getText().equalsIgnoreCase("Revoke Access"))
			return true;
		else
			return false;
	}

	public ArrayList<String> clickOnTaskAndFetchEntitlements(){
		TestBase.scrollDownToElement(driver, task_history);
		task_link.click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='#scroller1']/child::tbody")));
		List<WebElement> end_points=driver.findElements(By.xpath("//div[@id='ui-tabs-1']//div//tbody//tr"));
		ArrayList<String> arlist = new ArrayList<String>();
		String temp;
		for(int i=1;i<=end_points.size();i++)
		{
			temp=driver.findElement(By.xpath("//div[@id='ui-tabs-1']//div//tbody//tr["+i+"]//td[9]")).getText();
			////table[@id='#scroller1']/child::tbody/child::tr["+i+"]/child::td[8]
			arlist.add(temp);		
		}
		
		TestBase.scrollDownToElement(driver, lastColumnInTable);
		TestBase.scrollToEndOfPage(driver);
		System.out.println("Current Entitlements are :");
		
		System.out.println(arlist);
		return arlist;

		
		
	}
	public void getTaskHistorySnapShot() throws IOException
	{
		TestBase.scrollDownToElement(driver, task_history);
		//task_link.click();
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		logger.pass("Task history snapshot", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
	}
	
	public String searchRequestNumberAndCheckStatus(String request_number)
	{
		wait.until(ExpectedConditions.visibilityOf(first_result_row));
		role_search_input.clear();
		role_search_input.sendKeys(request_number);
		role_search_button.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+request_number+"')]")));
		TestBase.scrollDownToElement(driver, statusColumn);
		return statusColumn.getText();
	}
}
