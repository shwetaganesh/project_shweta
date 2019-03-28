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

}
