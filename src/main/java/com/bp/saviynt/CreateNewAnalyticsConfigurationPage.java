package com.bp.saviynt;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class CreateNewAnalyticsConfigurationPage extends TestBase{
	
	String analyticsName = "Test S1";
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'Create New Analytics Configuration')]")
	private WebElement createNewAnalyticsConfigPageHeader;
	
	@FindBy(xpath = "//label[contains(text(),'Analytics Name')]")
	private WebElement analyticsNameTextBoxLabel;
	
	@FindBy(xpath = "//input[@id='analyticsName']")
	private WebElement analyticsNameTextBox;
	
	@FindBy(xpath = "//textarea[@id='analyticsQry']")
	private WebElement analyticsQueryTextBox;
	
	@FindBy(xpath = "//textarea[@id='description']")
	private WebElement descriptionTextBox;
	
	@FindBy(xpath = "(//label[contains(text(),'Category')])[1]/following::a[1]")
	private WebElement categoryDropDownButton;
	
	@FindBy(xpath = "(//label[contains(text(),'Risk')])[1]/following::a[1]")
	private WebElement riskDropDownButton;
	
	@FindBy(xpath = "//a[contains(text(),'Create')]")
	private WebElement createButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Saviynt Security Manager')]")
	private WebElement errorMessagePopUpHeader;
	
	@FindBy(xpath = "//div[contains(text(),'Analytics Name Already Exists')]")
	private WebElement errorMessage;
	
	@FindBy(xpath = "//button[@class='btn default closebutton']")
	private WebElement errorMessagePopUpCloseButton;
	
	/*@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	
	@FindBy(xpath = "")
	private WebElement 
	*/
	
	public CreateNewAnalyticsConfigurationPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,40);
	}
	
	public void addDetails() throws InterruptedException
	{
		Random rand = new Random();
		int number = rand.nextInt(9 - 0 + 1) + 0;
		String analyticsName = "Test S"+number;
		wait.until(ExpectedConditions.visibilityOf(analyticsNameTextBoxLabel));
		analyticsNameTextBox.sendKeys(analyticsName);
		analyticsQueryTextBox.sendKeys("select username,displayname from users where statuskey=’1’ limit 20000");
		descriptionTextBox.sendKeys("Test description");
		categoryDropDownButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys("Test");
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys(Keys.ENTER);
		
		riskDropDownButton.click();
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys("Low");
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[@class='select2-search']//input[1]")).sendKeys(Keys.ENTER);
		
		TestBase.scrollDownToElement(driver, createButton);
		createButton.click();
	
		for(int i=0;i<5;i++)
		{
			if(driver.findElements(By.xpath("//div[contains(text(),'Analytics Name Already Exists')]")).size()>0) {
				errorMessagePopUpCloseButton.click();
				TestBase.scrollUp(driver);
				number=number++;
				analyticsName = analyticsName+number;
				analyticsNameTextBox.sendKeys(analyticsName);
				TestBase.scrollDownToElement(driver, createButton);
				createButton.click();
			}
			else {
				break;
			}
			
		}
			
			
	}

}
