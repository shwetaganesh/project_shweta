package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class DelegatesListPage extends TestBase{

	@FindBy(xpath="//h3[@class='page-title'][contains(text(),'Delegates')]")
	private WebElement delegatesHeader;
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	private WebElement actionLnk;
	
	@FindBy(xpath="//div[contains(@class,'actions')]/div[contains(@class,'btn-group open')]/ul[contains(@class,'dropdown-menu pull-right')]/li[1]/a[1]")
	private WebElement createDelegatesLnk;
	
	@FindBy(xpath="//table[@id='myDataTable']//tbody")
	private WebElement delegateListTable;
	
	WebDriverWait wait;
	public DelegatesListPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	public void createDelegatesRequest() {
		wait.until(ExpectedConditions.visibilityOf(delegatesHeader));
		actionLnk.click();
		createDelegatesLnk.click();
	}
	
	public boolean delegatesListMethod()
	{
		wait.until(ExpectedConditions.visibilityOf(delegatesHeader));
		return delegateListTable.isDisplayed();
	}
}
