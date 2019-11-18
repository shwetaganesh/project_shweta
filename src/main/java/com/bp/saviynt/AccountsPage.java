package com.bp.saviynt;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class AccountsPage extends TestBase{

	@FindBy(xpath = "//input[@id='dtsearch_sample2']")
	private WebElement userSearchBox;
	
	@FindBy(xpath = "//button[contains(text(),'Advanced')]")
	private WebElement advancedSearchButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Advanced Search')]")
	private WebElement advancedSearchHeader;
	
	@FindBy(xpath = "//input[@name='username']")
	private WebElement userNameTextBox;
	
	@FindBy(xpath = "//span[contains(text(),'Select Security System')]")
	private WebElement selectSecuritySystem;
	
	@FindBy(xpath = "//span[contains(text(),'Select EndPoints')]")
	private WebElement selectEndPoint;
	
	@FindBy(xpath = "//button[@id='dosearch']")
	private WebElement searchButtonForAdvancedSearch;
	
	@FindBy(xpath = "//a[contains(text(),'Entitlement Hierarchy')]")
	private WebElement entitlementHierarchyTab;
	
	@FindBy(xpath = "//i[@class='jstree-icon jstree-ocl']")
	private WebElement expandMemberOf;
	
	@FindBy(xpath = "//ul[@class='jstree-children']")
	private WebElement entitlementList;
	/*@FindBy(xpath = "//div[@id='select2-drop']//div[1]//input[1]")
	private WebElement searchFor*/
	
	WebDriverWait wait;
	
	public AccountsPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,50);
	}
	
	public void  advancedSearch(String user) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(userSearchBox));
		advancedSearchButton.click();
		wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
		userNameTextBox.sendKeys(user);
		TestBase.scrollDownToElement(driver, selectSecuritySystem);
		selectSecuritySystem.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys("BP1 ActiveDirectory_TEST");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(selectEndPoint));
		selectEndPoint.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys("AAD License Mgmt Logical");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(searchButtonForAdvancedSearch));
		searchButtonForAdvancedSearch.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody[@role='alert']//tr[1]//td[3][contains(text(),'"+user+"')]")));
	}
	
	public void clickOnUser(String user)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody[@role='alert']//tr[1]//td[3][contains(text(),'"+user+"')]")));
		driver.findElement(By.xpath("//table//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+user+"')]")).click();
		
	}
	
	public void clickOnEntitlementHierarchy()
	{
		wait.until(ExpectedConditions.elementToBeClickable(entitlementHierarchyTab));
		entitlementHierarchyTab.click();
		
	}
	
	public boolean expandMemberOfTreeAndGetEntitlements(ArrayList<String> ListOfEntitlement)
	{
		boolean result=false;
		try {
		wait.until(ExpectedConditions.visibilityOf(expandMemberOf));
		expandMemberOf.click();
		}
		catch(StaleElementReferenceException e)
		{
			expandMemberOf.click();
		}
		
		ArrayList<String> entitlementArray = new ArrayList<String>();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='jstree-children']//li")));
		List<WebElement> list = driver.findElements(By.xpath("//ul[@class='jstree-children']//li"));
		int size = list.size();
		System.out.println(size);
		for(int i=1;i<=size;i++)
		{
			//wait.until(ExpectedConditions.visibilityOf(entitlementList));
			String temp = driver.findElement(By.xpath("//ul[@role='group']//li["+i+"]//span")).getText();
			entitlementArray.add(temp);
		}
		System.out.println(entitlementArray);
		
		for(String s1 : ListOfEntitlement)
		{
			for(String s2 : entitlementArray)
			{
				if(s1.contains(s2))
					result = true;
			}
		}
		
		return result;
	}
}
