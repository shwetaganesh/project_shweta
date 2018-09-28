package com.bp.saviynt;

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
	
	WebDriverWait wait;
	
	public RequestHistoryPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	public void searchRequestAndOpen(String request_number)
	{
		wait.until(ExpectedConditions.visibilityOf(first_result_row));
		role_search_input.clear();
		role_search_input.sendKeys(request_number);
		role_search_button.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+request_number+"')]")));
		driver.findElement(By.xpath("//a[contains(text(),'"+request_number+"')]")).click();
	}
	
	
	public ArrayList<String> clickOnTaskAndFetchEndPoints()
	{
		TestBase.scrollDownToElement(driver, task_history);
		task_link.click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='#scroller1']/child::tbody")));
		List<WebElement> end_points=driver.findElements(By.xpath("//table[@id='#scroller1']/child::tbody/child::tr"));
		ArrayList<String> arlist = new ArrayList<String>();
		for(int i=1;i<=end_points.size();i++)
			arlist.add(driver.findElement(By.xpath("//table[@id='#scroller1']/child::tbody/child::tr["+i+"]/child::td[8]")).getText());
		System.out.println("Current Endpoints are");
		System.out.println(arlist);
		return arlist;
	}
	
	public boolean validateEndPoints(ArrayList<String>arlist,ArrayList<String>validate)
	{
		boolean verify_endpoints=true;
		for(String validate_check:validate)
		{
			if(arlist.contains(validate_check))
				System.out.println(validate_check+" is found");
			else
			{
				System.out.println(validate_check+" is missing");
				verify_endpoints=false;
			}
		}
		return verify_endpoints;
	}
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
}
