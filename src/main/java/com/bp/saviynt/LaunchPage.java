package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.bp.testbase.TestBase;

public class LaunchPage extends TestBase
{
	@FindBy(xpath = "//input[@id='username']")
	private WebElement u_name;
	
	@FindBy(xpath = "//input[@name='j_password']")
	private WebElement pwd;
	
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement login_button;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
	private WebElement forgot_password_link;
	
	public LaunchPage(WebDriver ldriver) 
	{
		driver=ldriver;
		
		PageFactory.initElements(driver, this);
	}
	public boolean verifyUname()
	{
		return u_name.isDisplayed();
	}
	public boolean verifyPwd()
	{
		return pwd.isDisplayed();
	}
	public boolean verifyLoginButton()
	{
		return login_button.isDisplayed();
	}
	public boolean verifyForgotPassword()
	{
		return forgot_password_link.isDisplayed();
	}
	public void login(String username, String password) 
	{
		if(this.verifyUname()&&this.verifyPwd()&&this.verifyLoginButton()&&this.verifyForgotPassword())
		{
			u_name.sendKeys(username);
			pwd.sendKeys(password);
			login_button.click();
			
			System.out.println("Login done with User id "+username);
		}
		else
			System.out.println("Elements not found in homepage");
	}
}