package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@FindBy(xpath = "//*[contains(text(),'Login Again')]")
	private WebElement loginAgainButton;
	
	WebDriverWait wait;
	
	public LaunchPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	//this method verifies presence of text field for user name
	public boolean verifyUname()
	{
		return u_name.isDisplayed();
	}
	//this method verifies presence of text field for password
	public boolean verifyPwd()
	{
		return pwd.isDisplayed();
	}
	//this method verifies presence of Login Button
	public boolean verifyLoginButton()
	{
		return login_button.isDisplayed();
	}
	//this method verifies presence of Forgot Password Link.
	public boolean verifyForgotPassword()
	{
		return forgot_password_link.isDisplayed();
	}
	
	//login with user name and password if all the specified fields i.e.,User Name, Password, Login Button,Forgot Password link, are displayed in the launch page.
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
	
	public void clickOnLoginAgain()
	{
		wait.until(ExpectedConditions.visibilityOf(loginAgainButton));
		loginAgainButton.click();
		wait.until(ExpectedConditions.visibilityOf(u_name));
	}
}