package com.bp.saviynt;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class PendingTasksPage  extends TestBase{
	
	WebDriverWait  wait;
	
	@FindBy(xpath ="//a[@id='arsPendingTaskAction']")
	private WebElement actionButton;
	
	@FindBy(xpath ="//input[@type='text' and @placeholder='Search by User']")
	private WebElement searchBox;
	
	@FindBy(xpath ="//a[@id='ptActionDiscontinueAllTask']")
	private WebElement discontinueTasks;
	
	@FindBy(xpath = "(//*[contains(text(),'Yes')])[2]")
	private WebElement yesButton;
	
	@FindBy(xpath ="//textarea[@id='discontinuecomments']")
	private WebElement commentBox;
			
	@FindBy(xpath="//a[@onclick='discontinueAllTasks()']")
	private WebElement submitButton;
	
	@FindBy(xpath ="//h4[contains(text(),'Enter Comments')]")
	private WebElement commentsHeader;
	
	@FindBy(xpath ="(//a[contains(text(),'Action')])[1]")
	private WebElement actionDropdown; // first column in table under the header Actions
	
	@FindBy(xpath ="//tbody[@role='alert']//tr[1]//td[1]//div[1]//ul[1]//li[3]//a[@class='removeAccount']")
	private WebElement complete;
	
	@FindBy(xpath ="//h4[contains(text(),'comments')]")
	private WebElement commentHeadline;
	
	@FindBy(xpath ="//input[@class='uniform' and @type='checkbox']")
	private WebElement checkBox;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	private WebElement submit;
	
	@FindBy(xpath ="(//span[contains(text(),'Tasks')])[2]")
	private WebElement tasksTabInLeftPanel;
	
	//8-11-19
	@FindBy(xpath = "//button[@id='advSearchButton_usersList']")
	private WebElement advancedSearchButton;
	
	@FindBy(xpath ="//h4[contains(text(),'Advanced Search')]")
	private WebElement advancedSearchHeader;
	
	@FindBy(xpath = "//a[contains(text(),'Endpoints')]")
	private WebElement endPointTab;
	
	@FindBy(xpath = "(//label[contains(text(),'Endpoint')])[2]/following::div[2]")
	private WebElement endPointSelectBox;
	
	@FindBy(xpath = "//a[contains(text(),'Users')]")
	private WebElement usersTab;
	
	@FindBy(xpath = "(//label[contains(text(),'User Name')])[1]/following::div//input[@name='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath = "//button[@onclick='showadvancesearched();']")
	private WebElement searchButtonForAdvancedSearch;
	
	@FindBy(xpath = "//tbody//td[contains(text(),'No data available in table')]")
	private WebElement noDataAvailable;
	
	public PendingTasksPage(WebDriver ldriver) {
		
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}

	public void discontinueAllTasks(String userName)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.sendKeys(userName);
		searchBox.sendKeys(Keys.ENTER);
		//wait.until(ExpectedConditions.visibilityOf(actionButton));
		actionButton.click();
		wait.until(ExpectedConditions.visibilityOf(discontinueTasks));
		discontinueTasks.click();
		wait.until(ExpectedConditions.visibilityOf(yesButton));
		yesButton.click();
		wait.until(ExpectedConditions.visibilityOf(commentsHeader));
		commentBox.sendKeys("discontinue");
		submitButton.click();
	}
	
	public void completeTheTask(String userName)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(userName);
		searchBox.sendKeys(Keys.ENTER);
		  try {
			wait.until(ExpectedConditions.visibilityOf(tasksTabInLeftPanel));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[5][contains(text(),'"+userName+"')]")));
			actionDropdown.click();
			wait.until(ExpectedConditions.visibilityOf(complete));
			complete.click();
			wait.until(ExpectedConditions.visibilityOf(commentHeadline));
			WebElement ele = driver.findElement(By.xpath("//textarea[@placeholder='Comments']"));
			TestBase.scrollDownToElement(driver, ele);	
			wait.until(ExpectedConditions.elementToBeClickable(submit));
			submit.click();
			
				
		  }
		  catch(ElementNotInteractableException e)
		  {
			  System.out.println("element not interactable");  
			  e.printStackTrace();
		  }
		  catch(NoSuchElementException e1)
		  {
			  System.out.println("no such element"); 
			  e1.printStackTrace();
		  }
		  catch(Exception e2)
		  {
			  System.out.println("All tasks completed");
		  }
		}
	
	
		public void searchForTaskCreatedForUser(String user)
		{
			wait.until(ExpectedConditions.visibilityOf(searchBox));
			searchBox.sendKeys(user);
			searchBox.sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[5][contains(text(),'"+user+"')]")));
		}
		
		
		public void advancedSearch(String endPoint,String user) throws InterruptedException
		{
			wait.until(ExpectedConditions.visibilityOf(searchBox));
			advancedSearchButton.click();
			wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
			endPointTab.click();
			/*Actions action = new Actions(driver);
			action.doubleClick(endPointSelectBox).perform();
			wait.until(ExpectedConditions.elementToBeClickable(endPointSelectBox)).click();
			//wait.until(ExpectedConditions.elementToBeClickable(endPointSelectBox)).click();
			Thread.sleep(2000);*/
			wait.until(ExpectedConditions.elementToBeClickable(endPointSelectBox)).click();
			driver.findElement(By.xpath("(//label[contains(text(),'Endpoint')])[2]/following::div[2]//ul//li//input")).sendKeys(endPoint);
			//endPointSelectBox.sendKeys(endPoint);
			Thread.sleep(2000);
			//endPointSelectBox.sendKeys(Keys.ENTER);
			driver.findElement(By.xpath("(//label[contains(text(),'Endpoint')])[2]/following::div[2]//ul//li//input")).sendKeys(Keys.ENTER);
			usersTab.click();
			wait.until(ExpectedConditions.elementToBeClickable(userNameTextBox));
			userNameTextBox.click();
			userNameTextBox.sendKeys(user);
			
			wait.until(ExpectedConditions.visibilityOf(searchButtonForAdvancedSearch));
			searchButtonForAdvancedSearch.click();
			
		}
		
		public boolean checkErrorMessage() throws InterruptedException
		{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(noDataAvailable));
			if(noDataAvailable.isDisplayed())
				return true;
			else
				return false;
			
		}
		
		public void getPendingTaskDetails(String user)
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[5][contains(text(),'"+user+"')]")));
			List<WebElement> numberOfRows = driver.findElements(By.xpath("//table[@id='usersList']//tbody//tr"));
			System.out.println(numberOfRows.size());
			
			for(int i=1;i<=numberOfRows.size();i++)
			{
				for(int j=2;j<=9;j++) {
				
				String taskNumber = driver.findElement(By.xpath("//table[@id='usersList']//tbody//tr["+i+"]//td[2]")).getText();
				j=j+1;
				String taskType = driver.findElement(By.xpath("//table[@id='usersList']//tbody//tr["+i+"]//td["+j+"]//div//p")).getText();
				j=j+6;
				String entitlementName = driver.findElement(By.xpath("//table[@id='usersList']//tbody//tr["+i+"]//td["+j+"]")).getText();
				System.out.println(entitlementName);
				
				//entitlementName = entitlementName.replaceAll("\r","").replaceAll("\n", " ");
				if(taskType.contains("New Account"))
				{
					System.out.println("new account created");
				}
				else if(taskType.contains("Add Access") )
				{
					System.out.println("Add access task created");
				}
				
				else if(taskType.contains("Remove Access"))
				{
					System.out.println("Remove access task created");
				}
				}
		}
	}
		
}
