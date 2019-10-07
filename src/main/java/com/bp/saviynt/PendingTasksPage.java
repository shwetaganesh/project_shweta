package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
		
	}
