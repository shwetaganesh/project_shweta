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

	public class CompletedTasksPage extends TestBase{
	
	WebDriverWait  wait; 
	
	
	@FindBy(xpath ="//input[@type='text' and @placeholder='Search by User']")
	private WebElement searchBox;
	
	@FindBy(xpath ="(//span[contains(text(),'Tasks')])[2]")
	private WebElement tasksTabInLeftPanel;
	
	@FindBy(xpath = "//table[@id='usersList']//tbody//tr[1]//td[7]")
	private WebElement securitySystemColumnData;
	
	@FindBy(xpath ="//table[@id='usersList']//tbody//tr[1]//td[13]")
	private WebElement statusColumnData;
	
	@FindBy(xpath = "//table[@id='usersList']//tbody//tr[1]//td[9]")
	private WebElement entitlementColumnData;
	
	public CompletedTasksPage(WebDriver ldriver) {
		
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}

	public boolean verifyStatusOfTask(String userName)
	{
		boolean status;
	wait.until(ExpectedConditions.visibilityOf(searchBox));
	searchBox.clear();
	searchBox.sendKeys(userName);
	searchBox.sendKeys(Keys.ENTER);
	wait.until(ExpectedConditions.visibilityOf(tasksTabInLeftPanel));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[6][contains(text(),'"+userName+"')]")));
	String securitySystem = securitySystemColumnData.getText();
	System.out.println(securitySystem);
	
	securitySystem = securitySystem.replace("\n", "").replace("\r", "");
	
	String jobStatus = statusColumnData.getText();
	
	String entitlement = entitlementColumnData.getText();
	entitlement = entitlement.replace("\n", "").replace("\r", "");
	System.out.println(entitlement);
	String code = entitlement.substring(33, 35);
	System.out.println(code);
	boolean companyCodeStatus = this.verifyCompanyGroupCode(code);
	
	if (securitySystem.contains("BP1ActiveDirectory_TEST") && jobStatus.contains("Completed") && companyCodeStatus)
		status = true;
	else
		status = false;
	
	return status;
	}
	
	
	public boolean verifyCompanyGroupCode(String code)
	{
		switch(Integer.parseInt(code))
		{
		case 01: System.out.println("Company : Wipro");
				 return true;
				 
		case 03: System.out.println("Company : T-Systems");
		 		 return true;
			
		case 05:System.out.println("Company : Siemens");
		 		return true;
			
		case 15:System.out.println("Company : Infosys");
		 		return true;
			
		case 18:System.out.println("Company : IBM");
		 		return true;
			
		case 20:System.out.println("Company : HP/HP ghs/ EDS");
		 		return true;
			
		case 22:System.out.println("Company : Accenture");
		 		return true;
		 		
		default : System.out.println("Company codes not matching to given conditions");
			return false;
		
		 }
	}
	}
