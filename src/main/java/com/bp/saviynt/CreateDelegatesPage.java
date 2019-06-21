package com.bp.saviynt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class CreateDelegatesPage extends TestBase{


	@FindBy(xpath="//h3[contains(text(),'Create Delegate')]")
	private WebElement createDelegatesHeader;
	
	@FindBy(xpath="//input[@id='name']")
	private WebElement nameIpt;
	
	@FindBy(xpath="//input[@id='description']")
	private WebElement descriptionIpt;
	
	@FindBy(xpath="(//span[@class='input-group-btn']/button)[1]")
	private WebElement delegateFromBtn;
	
	@FindBy(xpath="(//span[@class='input-group-btn']/button)[2]")
	private WebElement delegateToBtn;
	
	
	//@FindBy(xpath="//input[@label='Sample_Test_User_1705']")
	@FindBy(xpath="//div[@id='parentusermodaldiv']//input[@name='bpowner']")
	//@FindBy(xpath="//div[@class='radio']//input")
	//@FindBy(xpath="//input[@name='bpowner']")
	private WebElement userSelectFromRadioBtn;
	
	@FindBy(xpath="//a[@id='parentUserBtn']")
	private WebElement submitUserFromBtn;
	
	@FindBy(xpath="(//input[@name='bpownerDelegate'])[1]")
	private WebElement userSelectToRadioBtn;
	
	@FindBy(xpath="//a[@id='delegateUserBtn']")
	private WebElement submitUserToBtn;
	
	@FindBy(xpath="//input[@id='enddate']")
	private WebElement endDate;
	
	@FindBy(xpath="//a[contains(text(),'Create')]")
	private WebElement createBtn;
	
	@FindBy(xpath="//div[@id='parentusermodaldiv']//h4[@class='modal-title'][contains(text(),'Select User')]")
	private WebElement selectUserFromPopUp; 
	
	@FindBy(xpath="//input[@id='dtsearch_allusers']")
	private WebElement userSearchFromIpt;
	
	@FindBy(xpath="//div[@id='usermodaldiv']//h4[@class='modal-title'][contains(text(),'Select User')]")
	private WebElement selectUserToPopUp;
	
	@FindBy(xpath="//input[@id='dtsearch_allusersofloggedmanager']")
	private WebElement userSearchToIpt;
	
	WebDriverWait wait;
	public CreateDelegatesPage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);	
	}
	
	public void delegatesCreateRequest(String name,String description,int endDateval) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(createDelegatesHeader));
		nameIpt.sendKeys(name);
		descriptionIpt.sendKeys(description);
		delegateFromBtn.click();
		wait.until(ExpectedConditions.visibilityOf(selectUserFromPopUp));
		wait.until(ExpectedConditions.visibilityOf(userSearchFromIpt));
		Thread.sleep(3000);		
		userSelectFromRadioBtn.click();
		submitUserFromBtn.click();
		Thread.sleep(3000);
		delegateToBtn.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(selectUserToPopUp));
		wait.until(ExpectedConditions.visibilityOf(userSearchToIpt));
		userSelectToRadioBtn.click();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, endDateval); // Adding 5 days
		String output = sdf.format(c.getTime());
		endDate.sendKeys(Keys.ESCAPE);
		endDate.clear();
		endDate.sendKeys(output);
		submitUserToBtn.click();
		Thread.sleep(3000);
		createBtn.click();
	}
}
