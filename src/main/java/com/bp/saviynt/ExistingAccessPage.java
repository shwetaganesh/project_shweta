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
	
	@FindBy(xpath="//input[@class='form-control change_user']")
	private WebElement userValueIpt;
	
	@FindBy(xpath = "//*[@id=\"selecteduser\"]")
	private WebElement existingAccessEndUserHeader; // header along with end user name and username
	
	@FindBy(xpath="//span[@class='input-group-btn']/a[1]")
	private WebElement changeUserBtn;
	
	@FindBy(xpath="//div[@class='radio']//input[@name='userkey']")
	private WebElement userRadioBtn;
	
	@FindBy(xpath="//div[@id='usermodaldiv']//a[@class='btn green'][contains(text(),'Submit')]")
	private WebElement submitUserLnk;
	
	@FindBy(xpath = "//div[@class='caption' and contains(text(),'ACCESS DETAILS')]")
	private WebElement accessDetailsHeader;
	
	WebDriverWait wait;
	public ExistingAccessPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	public boolean existingAccessRequest(String endUser)
	{
		wait.until(ExpectedConditions.visibilityOf(existingHeader));	
		
		System.out.println(existingAccessEndUserHeader.getText());
		String userName = existingAccessEndUserHeader.getText();
		String userNameSplit[] = userName.split("\\s");
		for (int i=0; i < userNameSplit.length; i++)
		      System.out.println(userNameSplit[i]);
		if(userNameSplit[2].contains(endUser.toUpperCase()))
			return true;
		else
			return false;
		 
	}
	public boolean accessDetailsSnapShot()
	{
		TestBase.scrollToEndOfPage(driver);
		return accessDetailsHeader.isDisplayed();
	}
}

