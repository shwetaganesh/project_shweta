package com.bp.saviynt;

import static org.testng.Assert.expectThrows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class AnalyticsHistoryPage extends TestBase {
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Analytics History')]")
	private WebElement analyticsHistoryHeader;
	
	@FindBy(xpath = "(//span[contains(text(),'Analytics Configuration List')])[2]")
	private WebElement analyticsConfigurationListSidePanel;
	
	@FindBy(xpath = "(//button[@title='Select...'])[1]")
	private WebElement categoryDropDownButton;
	
	@FindBy(xpath = "(//input[@placeholder='Search'])[1]")
	private WebElement searchCategoryTextBox;
	
	@FindBy(xpath = "(//li//a//label[@class='checkbox']//input[@type='checkbox' and @value='Test'])[1]")
	private WebElement firstCheckBoxForTestOption; // check box that appears after u search for the category "test"
	
	@FindBy(xpath = "(//li//a//label[@class='checkbox']//input[@type='checkbox' and @value='Test'])[1]")
	private WebElement applyButton;
	
	@FindBy(xpath = "//table[@id='filteredDatatable']//tbody//tr[1]")
	private WebElement firstRow; // first row in the table containing control names after searching for required category
	
	@FindBy(xpath = "//a[contains(text(),'AD - ISIM Connection')]")
	private WebElement ISIMConnectionLink;
	
	public AnalyticsHistoryPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
		
	}
	
	// wait for the side panel to appear in analytics history page, click on Analytics Configuration List.
	public void clickOnConfigurationList()
	{
		wait.until(ExpectedConditions.visibilityOf(analyticsHistoryHeader));
		wait.until(ExpectedConditions.elementToBeClickable(analyticsConfigurationListSidePanel)).click();
	}
	
	public void selectCategoryFromTheDropDown()
	{
		wait.until(ExpectedConditions.visibilityOf(analyticsHistoryHeader));
		categoryDropDownButton.click();
		wait.until(ExpectedConditions.visibilityOf(searchCategoryTextBox));
		searchCategoryTextBox.sendKeys("Test");
		wait.until(ExpectedConditions.visibilityOf(firstCheckBoxForTestOption));
		firstCheckBoxForTestOption.click();
		wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();
		
	}
	
	public boolean clickOnISIMConnectionControlName()
	{
		//wait.until(ExpectedConditions.visibilityOf(analyticsHistoryHeader));
		if((driver.findElements(By.xpath("//table[@id='filteredDatatable']//tbody//tr"))).size()>0)
		{
			wait.until(ExpectedConditions.visibilityOf(firstRow));
			ISIMConnectionLink.click();
			return true;
		}
		else 
			return false;
	}
	

}
