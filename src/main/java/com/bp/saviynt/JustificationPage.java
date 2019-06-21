package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class JustificationPage extends TestBase {
	
	@FindBy(xpath ="//textarea[starts-with(@id,'comments')]")
	private WebElement commentsTextBox;
	
	@FindBy(xpath ="(//textarea[starts-with(@id,'buisnesjustifcation')])[1]")
	private WebElement businessJustificationBox1;
	
	@FindBy(xpath ="(//textarea[starts-with(@id,'buisnesjustifcation')])[2]")
	private WebElement businessJustificationBox2;
	
	@FindBy(xpath ="(//textarea[starts-with(@id,'buisnesjustifcation')])[3]")
	private WebElement businessJustificationBox3;
	
	@FindBy(xpath ="(//textarea[starts-with(@id,'buisnesjustifcation')])[4]")
	private WebElement businessJustificationBox4;
	
	@FindBy(xpath ="(//a[@onclick='opennextpage()' and @class='btn green button-submit'])[2]")
	private WebElement submitButton;
	
	WebDriverWait wait;
	
	public JustificationPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	public void enterBusinessJustificationAndComment() {
		wait.until(ExpectedConditions.visibilityOf(businessJustificationBox1));
		businessJustificationBox1.sendKeys("abc");
		businessJustificationBox2.sendKeys("abc");
		try{
			if(businessJustificationBox3.isDisplayed()){
				businessJustificationBox3.sendKeys("abc");
				TestBase.scrollDownToElement(driver, commentsTextBox);
				commentsTextBox.sendKeys("abc");
		}
		}
			catch(Exception e){
				TestBase.scrollDownToElement(driver, commentsTextBox);
				commentsTextBox.sendKeys("abc");
				
			}
			
		}
	
	public void clickOnSubmit() {
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		TestBase.javaScriptClickbyElement(driver, submitButton);
		//submitButton.click();
	}
}
