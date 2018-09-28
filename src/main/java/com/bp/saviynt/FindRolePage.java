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

public class FindRolePage extends TestBase 
{
	@FindBy(xpath = "//input[@id='dtsearch_applicationlist']")
	private WebElement role_searchbox;
	
	@FindBy(xpath = "(//a[@class='btn default btn-xs  pull-right  green'])[1]")
	private WebElement first_addtocart_button;
	
	@FindBy(xpath = "(//*[contains(text(),'REMOVE FROM CART')])[1]")
	private WebElement remove_cart;
	
	@FindBy(xpath = "(//a[@class='btn red btn-xs pull-right'])[1]")
	private WebElement removeRole_button;
	//check remove_cart and remove_from_cart_button
	@FindBy(xpath = "(//a[@class='btn default btn-xs dark pull-right'])[1]")
	private WebElement remove_from_cart_button;
	
	@FindBy(xpath = "(//*[contains(@id,'buisnesjustifcation')])[1]")
	private WebElement business_justification_first;
	
	@FindBy(xpath = "//a[@id='showcheckout']")
	private WebElement checkout_button;
	
	WebDriverWait wait;
	
	public FindRolePage(WebDriver ldriver) 
	{
		driver=ldriver;
		
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
		
	}
	
	public void searchandAddtoCart(String role_description)
	{		
		wait.until(ExpectedConditions.visibilityOf(role_searchbox));
		role_searchbox.sendKeys(role_description);
		role_searchbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//a[contains(text(),'"+role_description+"')]")));
		wait.until(ExpectedConditions.visibilityOf(first_addtocart_button));
		first_addtocart_button.click();
		wait.until(ExpectedConditions.visibilityOf(remove_cart));
		role_searchbox.clear();
	}
		
	public void clickOnCheckout()
	{
		TestBase.javaScriptClickbyElement(driver, checkout_button);
		wait.until(ExpectedConditions.visibilityOf(business_justification_first));
	}
	
	public void searchAndRemoveFromCart(String role_description)
	{	
		wait.until(ExpectedConditions.visibilityOf(role_searchbox));
		role_searchbox.sendKeys(role_description);
		role_searchbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//a[contains(text(),'"+role_description+"')]")));
		wait.until(ExpectedConditions.visibilityOf(removeRole_button));
		removeRole_button.click();
		wait.until(ExpectedConditions.visibilityOf(remove_from_cart_button));
		role_searchbox.clear();
	}
}