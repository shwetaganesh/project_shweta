package com.bp.saviynt;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;


public class FindUserPage extends TestBase
{
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTable']")
	private WebElement searchbox_user;
	
	@FindBy(xpath = "//button[@id='search_myDataTable']")
	private WebElement search_button_user;
	
	@FindBy(xpath = "(//input[@name='userkey' and @type='radio'])[1]")
	private WebElement first_radio_button;
	
	@FindBy(xpath = "//button[@onclick='requestnow()' and @class='btn green']")
	private WebElement next_green_button;
	
	@FindBy(xpath = "//tbody//td[contains(text(),'No data available in table')]")
	private WebElement noDataAvaialableMessage;
	
	@FindBy(xpath = "//table[@id='myDataTable']")
	private WebElement usersListTable;
	
	WebDriverWait wait;
	
	public FindUserPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	//method to search for End user using END USER ID
	public void searchEndUser(String end_user_id ) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(searchbox_user));
		searchbox_user.sendKeys(end_user_id);
		searchbox_user.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//td[contains(text(),'"+end_user_id+"')]")));
		//wait.until(ExpectedConditions.visibilityOf(first_radio_button));
		Thread.sleep(2000); //  explicit wait  did not help
		first_radio_button.click();
		next_green_button.click();
	}
	
	public void searchForEndUser(String endUser)
	{
		wait.until(ExpectedConditions.visibilityOf(searchbox_user));
		searchbox_user.sendKeys(endUser);
		searchbox_user.sendKeys(Keys.ENTER);
		
	}
	
	public boolean getErrorMessage()
	{
		wait.until(ExpectedConditions.visibilityOf(noDataAvaialableMessage));
		if(noDataAvaialableMessage.isDisplayed())
			return true;
		else
			return false;
	}
	
	public boolean checkUsersDisplayed(ArrayList<String>ListOfUsers)
	{
		boolean result = false;
		wait.until(ExpectedConditions.visibilityOf(searchbox_user));
		wait.until(ExpectedConditions.visibilityOf(usersListTable));
		List<WebElement> table = driver.findElements(By.xpath("//table[@id='myDataTable']//tbody//tr"));
		int size = table.size();
		ArrayList<String> usersList = new ArrayList<String>();
		for(int i=1;i<=size;i++)
		{
			String temp = driver.findElement(By.xpath("//table[@id='myDataTable']//tbody//tr["+i+"]//td[2]")).getText();
			usersList.add(temp);
		}
		
		for(String s1 : ListOfUsers)
		{
			for(String s2 : usersList)
			{
				if(s2.contains(s1))
					result = true;
			}
		}
		
		return result;
	}

}
