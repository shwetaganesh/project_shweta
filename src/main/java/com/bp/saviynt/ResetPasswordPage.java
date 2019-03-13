package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class ResetPasswordPage extends TestBase{
	
	
	@FindBy(xpath ="//h1[contains(text(),'Reset Password')]")
	private WebElement resetPasswordHeader;
	
	@FindBy(xpath = "//input[@type='text']")
	private WebElement userName;
	
	@FindBy(xpath ="(//input[@type='password'])[1]")
	private WebElement oldPassword;
	
	@FindBy(xpath ="(//input[@type='password'])[2]")
	private WebElement newPassword;
	
	@FindBy(xpath ="(//input[@type='password'])[3]")
	private WebElement confirmNewPassword;
	
	@FindBy(xpath ="//button[contains(text(),'Next')]")
	private WebElement NextButton;
	
	@FindBy(xpath ="//h4[contains(text(),'Step 1 : Please set your challenge questions')]")
	private WebElement stepOneHeader;
	
	@FindBy(xpath ="(//input[@placeholder='Enter Answer'])[1]")
	private WebElement answer1;
	
	@FindBy(xpath ="(//input[@placeholder='Enter Answer'])[2]")
	private WebElement answer2;
	
	@FindBy(xpath ="//button[contains(text(),'Save')]")
	private WebElement saveButton;
	
	WebDriverWait wait;
	
	
	public ResetPasswordPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void changePassword(String uname, String oldPass, String newPass) {
		wait.until(ExpectedConditions.visibilityOf(resetPasswordHeader));
		userName.sendKeys(uname);
		System.out.println("username entered");
		oldPassword.sendKeys(oldPass);
		newPassword.sendKeys(newPass);
		confirmNewPassword.sendKeys(newPass);
		NextButton.click();
	}
	
	public HomePage setUpSecurityQuestions() {
		wait.until(ExpectedConditions.visibilityOf(stepOneHeader));
		Select question = new Select(driver.findElement(By.name("question_1")));
		question.selectByIndex(1);
		answer1.sendKeys("abcxyz");
		question = new Select(driver.findElement(By.name("question_2")));
		question.selectByIndex(1);
		answer2.sendKeys("pqrs");
		saveButton.click();
		
		return new HomePage(driver);
	}
	
	
	

	
}
;