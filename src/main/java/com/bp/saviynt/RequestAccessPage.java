package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class RequestAccessPage  extends TestBase{
	
	@FindBy(xpath = "//*[@id=\"s2id_slectedEnttype111\"]")
	private WebElement selectProfileDropdown;
	
	@FindBy(xpath ="//div[@class='select2-result-label' and contains(text(),'BP Base')]")
	private WebElement bpBaseProfile;
	
	@FindBy(xpath ="//div[@id='myDataTable546_ALL_wrapper']//tbody[@role='alert']//tr[1]//td[3]//a[1]")
	private WebElement firstAddPermissionButton;
	
	@FindBy(xpath ="//div[@id='myDataTable546_ALL_wrapper']//tbody[@role='alert']//tr[3]//td[3]//a[1]")
	private WebElement thirdAddPermissionButton;
	
	@FindBy(xpath ="(//td[@class='center']//a[@id='addEntitlement'])[7]")
	private WebElement firstAddGroupButton;
	
	@FindBy(xpath ="(//a[@onclick='gotonext()'])[2]")
	private WebElement nextButton;
	
	@FindBy(xpath ="(//tr[@class='odd'])[9]//td[1]//a[1]")
	private WebElement removePermissionButton;
	
	@FindBy(xpath ="(//tr[@class='odd'])[13]//td[1]//a[1]")
	private WebElement removeGroupButton;
	
	/*@FindBy(xpath ="(//tr[@class='odd'])[13]//td[1]//a[1]")
	private WebElement removeGroupButton2;*/
	
	@FindBy(xpath ="//div[@class='select2-result-label' and contains(text(),'Read Only')]")
	private WebElement readOnlyProfile;
	
	@FindBy(xpath ="//td//span[contains(text(),'AAA_Admin')]")
	private WebElement aaaAdminPermission;
	
	@FindBy(xpath ="//td//span[contains(text(),'AAA Members')]")
	private WebElement aaaMembersGroup;
	
	WebDriverWait wait;
	
	public RequestAccessPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	public void selectProfile() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(selectProfileDropdown));
		/*if(driver.findElement(By.xpath("//span[contains(text(),'BP Base')]")).isDisplayed()) {
			selectProfileDropdown.click();
			readOnlyProfile.click();
			Thread.sleep(2000);
		}
		else {
			selectProfileDropdown.click(); 
			bpBaseProfile.click();
			Thread.sleep(2000);	
		}*/
		try {
			if(driver.findElement(By.xpath("//span[contains(text(),'BP Base')]")).isDisplayed()) {
				selectProfileDropdown.click();
				readOnlyProfile.click();
				Thread.sleep(2000);
		}
		}
			catch(Exception e){
				selectProfileDropdown.click(); 
				bpBaseProfile.click();
				Thread.sleep(2000);
			}
		}
	
	
	public void addPermissionSet() {
		//TestBase.scrollDownToElement(driver, firstAddPermissionButton);
		/*wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
		firstAddPermissionButton.click();
		wait.until(ExpectedConditions.visibilityOf(removePermissionButton));*/
		wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
		try {
			if (removePermissionButton.isDisplayed()) {
				thirdAddPermissionButton.click();
				wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
			}
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
			firstAddPermissionButton.click();
			wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
		}
			
	}
	
	public void removePermissionSet() {
		wait.until(ExpectedConditions.visibilityOf(aaaAdminPermission));
		removePermissionButton.click();	
	}
	
	public void removeGroup() {
		TestBase.scrollDownToElement(driver, aaaMembersGroup);
		removeGroupButton.click();
		
	}
	public void addGroup() {
		TestBase.scrollDownToElement(driver, firstAddGroupButton);
		firstAddGroupButton.click();
		wait.until(ExpectedConditions.visibilityOf(removeGroupButton));	
	}
	
	public void clickOnNext() {
		nextButton.click();
	}
}
