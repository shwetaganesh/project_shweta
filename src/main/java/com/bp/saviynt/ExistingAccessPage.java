package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class ExistingAccessPage  extends TestBase{

	@FindBy(xpath="//h3[contains(text(),'View Existing Access')]")
	private WebElement existingHeader;
	
	@FindBy(xpath="//input[@placeholder='User']")
	private WebElement userValueIpt;
	
	@FindBy(xpath="//span[@class='input-group-btn']/a[1]")
	private WebElement changeUserBtn;
	
	@FindBy(xpath="//div[@class='radio']//input[@name='userkey']")
	private WebElement userRadioBtn;
	
	@FindBy(xpath="//div[@id='usermodaldiv']//a[@class='btn green'][contains(text(),'Submit')]")
	private WebElement submitUserLnk;
	
	WebDriverWait wait;
	public ExistingAccessPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	public String existingAccessRequest()
	{
		wait.until(ExpectedConditions.visibilityOf(existingHeader));	
		return userValueIpt.getAttribute("value");
		
	}
}

