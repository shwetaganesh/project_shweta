package com.bp.saviynt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class RolesPage  extends TestBase{
	
	WebDriverWait wait;
	
	
	@FindBy(xpath = "//table[@id='mydatatableusers']")
	private WebElement roleTable;
	
	@FindBy(xpath = "//table[@id='mydatatableusers']//tbody//tr[1]//td[2]")
	private WebElement roleName;
	
	@FindBy(xpath = "//*[contains(text(),'No data available')]")
	private WebElement noDataAvailable;

	public RolesPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	public boolean verifyAssignedRole(String roleDescription)
	{
		wait.until(ExpectedConditions.visibilityOf(roleTable));
		String role = roleName.getText();
		if(role.contains(roleDescription))
			return true;
		else
			return false;
	}
	
	public boolean verifyNoData()
	
	{
		wait.until(ExpectedConditions.visibilityOf(noDataAvailable));
		if(noDataAvailable.isDisplayed())
			return true;
		else
			return false;
	}
	
}
