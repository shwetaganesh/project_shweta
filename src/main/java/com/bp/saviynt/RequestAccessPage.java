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

public class RequestAccessPage  extends TestBase{
	
	@FindBy(xpath = "//div[@id='s2id_slectedEnttype544']")
	private WebElement selectProfileDropdown;
	
	@FindBy(xpath ="//div[@class='select2-result-label' and contains(text(),'BP Base')]")
	private WebElement bpBaseProfile;
	
	@FindBy(xpath ="//table[@id='myDataTable546_ALL']//a[@id='addEntitlement']")
	private WebElement firstAddPermissionButton;
	
	//@FindBy(xpath ="//div[@id='myDataTable546_ALL_wrapper']//tbody[@role='alert']//tr[3]//td[3]//a[1]")
	//private WebElement thirdAddPermissionButton;
	
	@FindBy(xpath ="//table[@id='myDataTable557_ALL']//td[@class='center']//a[@id='addEntitlement']")
	private WebElement firstAddGroupButton;
	
	@FindBy(xpath ="(//a[@onclick='gotonext()'])[2]")
	private WebElement nextButton;
	
	@FindBy(xpath ="//table[@id='myDataTable546_SEL']//a[@class='btn btn-xs default red-stripe']")
	private WebElement removePermissionButton;
	
	@FindBy(xpath ="//table[@id='myDataTable557_SEL']//a[@class='btn btn-xs default red-stripe']")
	private WebElement removeGroupButton;
	
	/*@FindBy(xpath ="(//tr[@class='odd'])[13]//td[1]//a[1]")
	private WebElement removeGroupButton2;*/
	
	@FindBy(xpath ="//div[@class='select2-result-label' and contains(text(),'Read Only')]")
	private WebElement readOnlyProfile;
	
	@FindBy(xpath ="//td//span[contains(text(),'AAA_Admin')]")
	private WebElement aaaAdminPermission;
	
	@FindBy(xpath ="//td//span[contains(text(),'AAA Members')]")
	private WebElement aaaMembersGroup;
	
	//22-2-19
	@FindBy(xpath ="//input[@id='dtsearch_myDataTable546_ALL']")
	private WebElement addPermissionTextBox;
	
	@FindBy(xpath ="//input[@id='dtsearch_myDataTable546_SEL']")
	private WebElement removePermissionTextBox;
	
	@FindBy(xpath ="//input[@id='dtsearch_myDataTable557_ALL']")
	private WebElement addGroupTextBox;
	
	@FindBy(xpath ="//input[@id='dtsearch_myDataTable557_SEL']")
	private WebElement removeGroupTextBox;
	
	@FindBy(xpath="(//table[@id='myDataTable41_ALL']//td[@class='center']//a[@id='addEntitlement'])[1]")
	private WebElement firstVisbleAddLnk;
	
	@FindBy(xpath="//table[@id='myDataTable41_SEL']//a[@class='btn btn-xs default red-stripe']")
	private WebElement removeVisibleLnk;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTable370_ALL']")
	private WebElement sapRoleSearchBox;
	
	@FindBy(xpath ="//td[@class='center']//a[@id='addEntitlement']")
	private WebElement addSapRoleButton;
	
	@FindBy(xpath ="(//td[@class='center sorting_1']//a[@class='btn btn-xs default red-stripe'])[1]")
	private WebElement removeSapRoleButton;
	
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
				wait.until(ExpectedConditions.visibilityOf(selectProfileDropdown));
				selectProfileDropdown.click();
				wait.until(ExpectedConditions.visibilityOf(readOnlyProfile));
				readOnlyProfile.click();
				Thread.sleep(2000);
		}
		}
			catch(Exception e){
				selectProfileDropdown.click(); 
				wait.until(ExpectedConditions.visibilityOf(bpBaseProfile));
				bpBaseProfile.click();
				Thread.sleep(2000);
			}
		}
	
	
	public void addPermissionSet(String permission)throws InterruptedException {
		//TestBase.scrollDownToElement(driver, firstAddPermissionButton);
		/*wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
		firstAddPermissionButton.click();
		wait.until(ExpectedConditions.visibilityOf(removePermissionButton));*/
		/*wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
		try {
			if (removePermissionButton.isDisplayed()) {
				thirdAddPermissionButton.click();
				wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
			}
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
			firstAddPermissionButton.click();
			wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
		}*/
		wait.until(ExpectedConditions.visibilityOf(addPermissionTextBox));
		addPermissionTextBox.sendKeys(permission);
		addPermissionTextBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//div[@id='myDataTable546_ALL_wrapper']//tbody[@role='alert']//tr[1]//td[1]//span[contains(text(),'"+permission+"')]")));
		wait.until(ExpectedConditions.visibilityOf(firstAddPermissionButton));
		firstAddPermissionButton.click();
		wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
		addPermissionTextBox.clear();
	}
	
	public void removePermissionSet(String permission) {
		//wait.until(ExpectedConditions.visibilityOf(aaaAdminPermission));
		//removePermissionButton.click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Read Only')]")));
		wait.until(ExpectedConditions.visibilityOf(removePermissionTextBox));
		removePermissionTextBox.sendKeys(permission);
		removePermissionTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"myDataTable546_SEL\"]//tbody//tr//td//span[contains(text(),'AAA_Admin')]")));
		wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
		removePermissionButton.click();
		removePermissionTextBox.clear();
		//wait.until(ExpectedConditions.visibilityOf(removePermissionButton));
	}
	
	public void removeGroup(String groupName) {
		//TestBase.scrollDownToElement(driver, remove);
		//removeGroupButton.click();
		TestBase.scrollDownToElement(driver, removeGroupTextBox);
		removeGroupTextBox.sendKeys(groupName);
		removeGroupTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(removeGroupButton));
		removeGroupButton.click();
		removeGroupTextBox.clear();
	}
	
	public void addGroup(String groupName) throws InterruptedException {

		/*TestBase.scrollDownToElement(driver, firstAddGroupButton);
		firstAddGroupButton.click();
		wait.until(ExpectedConditions.visibilityOf(removeGroupButton));	*/

		TestBase.scrollDownToElement(driver, addGroupTextBox);
		addGroupTextBox.sendKeys(groupName);
		addGroupTextBox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='myDataTable557_ALL']//td[contains(@class,'sorting_1')]//span[contains(text(),'"+groupName+"')]")));
		firstAddGroupButton.click();
		wait.until(ExpectedConditions.visibilityOf(removeGroupButton));
		addGroupTextBox.clear();
		
	}
	
	public void clickOnNext() {
		wait.until(ExpectedConditions.visibilityOf(nextButton));
		nextButton.click();
	}
	
	public void addUserGroup()
	{
		wait.until(ExpectedConditions.visibilityOf(firstVisbleAddLnk));
		firstVisbleAddLnk.click();
		wait.until(ExpectedConditions.visibilityOf(removeVisibleLnk));
		
	}
	
	// add sap roles for new sod scenarios
	public void searchAndAddSapRole(String role)
	{
		wait.until(ExpectedConditions.visibilityOf(sapRoleSearchBox));
		sapRoleSearchBox.sendKeys(role);
		sapRoleSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+role+"')]")));
		addSapRoleButton.click();
		wait.until(ExpectedConditions.visibilityOf(removeSapRoleButton));
		sapRoleSearchBox.clear();
	}
}
