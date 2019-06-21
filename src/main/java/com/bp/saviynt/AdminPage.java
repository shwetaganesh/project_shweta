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

public class AdminPage extends TestBase
{
	
	@FindBy(xpath = "(//*[contains(text(),'Job Control Panel')])[1]")
	private WebElement jobControlPanelLink;
	
	@FindBy(xpath = "//div[@class='page-container']//tbody[@role='alert']//tr[1]//td[1]")
	private WebElement firstRowInTable;
	
	@FindBy(xpath = "//div[@id='jobstatus']//*[@class='block']")
	private WebElement jobStatusTitle;
	
	@FindBy(xpath = "//a[contains(text(),'UTILITY')]")
	private WebElement utilityLink;
	
	@FindBy(xpath = "//*[contains(text(),'Trigger Chain Job')]")
	private WebElement triggerJobLink;
	
	@FindBy(xpath = "//a[contains(text(),'PROV')]")
	private WebElement provLink;
	
	/*@FindBy(xpath = "(//div[@id='collapse_0_22']//div[@class='col-md-1' ]//*[contains(text(),'Action')])[1]")
	private WebElement actionButton;*/
	
	@FindBy(xpath = "(//div[@id='collapse_0_22']//*[contains(text(),'Action')])[1]")
	private WebElement actionButtonInProvisioningJob;
	
	@FindBy(xpath = "(//div[@id='collapse_0_21']//*[contains(text(),'Action')])[1]")
	private WebElement actionButtonInTriggerChain;
	
	@FindBy(xpath ="//div[@class='btn-group pull-right open']//*[contains(text(),'Edit Trigger')]")
	private WebElement editTrigger;
	
	@FindBy(xpath = "//button[@class='btn green' and contains(text(),'Run Now')]")
	private WebElement runButton;
	
	//14/01/19
	@FindBy(xpath ="//*[contains(text(),'Provisioning Job')]")
	private WebElement provisioningJobLink;
	
	
	/*@FindBy(xpath ="(//*[contains(text(),'Provisioning Job')]/following::a[contains(text(),'PROV')])[1]")  // job name changing everytime.
	private WebElement provisioningText;*/
	@FindBy(xpath ="(//*[contains(text(),'Provisioning Job')]/following::a[1])[1]")
	private WebElement provisioningText;
	
	@FindBy(xpath ="(//*[@id='gritter-without-image2' and @class='WSRetryJob'])[1]")
	private WebElement startButton;
	
	@FindBy(xpath ="//li[@class='select2-search-field']//input[@type='text']")
	private WebElement selectSystemTextBox;
	
	@FindBy(xpath ="(//button[@class='btn green' and contains(text(),'Submit')])[2]")
	private WebElement submitButton;
	
	//06-03-2019
	@FindBy(xpath = "//li[@id='users']//a")
	private WebElement usersLinkLeftPanel;
	
	@FindBy(xpath = "//input[@id='dtsearch_usersList']")
	private WebElement searchUserTextBox;
	
	@FindBy(xpath = "//a[@class='btn btn-primary']")
	private WebElement actionsbutton;
	
	@FindBy(xpath = "//li//a[@data-target='#upLoadUser']")
	private WebElement uploadUsers;
	
	@FindBy(xpath = "//a[@href='/ECM/users/create']")
	private WebElement createUsers;
	
	@FindBy(xpath = "//h4[contains(text(),'Select File To Upload User')]")
	private WebElement popUpheader;
	
	/*@FindBy(xpath = "//span[@id='uploadFileName']//span[@id='uneditableInputfilename']")
	private WebElement filenameTextBox;*/	
	
	@FindBy(xpath = "(//input[@type='text'])[4]") 
	private WebElement userName;
	
	@FindBy(xpath = "(//input[@type='text'])[5]") 
	private WebElement firstName;
	
	@FindBy(xpath = "(//input[@type='text'])[6]") 
	private WebElement lastName;
	
	@FindBy(xpath = "(//input[@type='text'])[8]") 
	private WebElement country;
	
	@FindBy(xpath = "(//input[@type='text'])[10]")
	private WebElement email;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Select Manager')]")
	private WebElement selectManagerButton;
	
	@FindBy(xpath = "//input[@id='dtsearch_allusers']")
	private WebElement searchManagerTextBox;
	
	@FindBy(xpath = "(//input[@type='radio'])[1]")
	private WebElement firstRadioButton;
	
	@FindBy(xpath = "(//a[@type='button'])[2]")
	private WebElement submitManagerButton;
	
	@FindBy(xpath = "//a[@onclick='checkAndCreateForm()']")
	private WebElement createButton;
	
	
	@FindBy(xpath = "(//a[@role='presentation'])[1]")
	private WebElement userDetailsTab;
	
	@FindBy(xpath ="(//a[@role='presentation'])[2]")
	private WebElement otherAttributesTab;
	
	@FindBy(xpath = "(//a[@role='presentation'])[6]")
	private WebElement savRoleTab;
	
	@FindBy(xpath = "//input[@id='customproperty16']")
	private WebElement upnTextBox;
	
	@FindBy(xpath = "//input[@id='customproperty24']")
	private WebElement accountDefaultValuesTextBox;
	
	@FindBy(xpath = "//a[@onclick='document.forms.updateusers.submit()']")
	private WebElement updateButton;
	
	@FindBy(xpath = "//a[@onclick='validateAndSubmit()']")
	private WebElement update;						// update button that appears once department name is entered. 
	
	@FindBy(xpath = "//input[@id='departmentname']")
	private WebElement departmentName;
	
	@FindBy(xpath = "//a[@href='#addSavRole']")
	private WebElement addSavRole;
	
	@FindBy(xpath = "//h4[contains(text(),'Add SAV Role')]")
	private WebElement addSavRoleHeader;
	
	@FindBy(xpath ="//input[@id='dtsearch_myDataTableusersavroles']")
	private WebElement savRoleSearchBox;
	
	@FindBy(xpath ="(//td//a[contains(text(),'ROLE_END_USER')])[1]")
	private WebElement firstRow;
	
	@FindBy(xpath ="(//td//input[@type='checkbox'])[1]")
	private WebElement firstCheckBox;
			
	@FindBy(xpath ="//button[@onclick='checkIfRoleSelected(this)']")
	private WebElement submitRoleButton;
	
	@FindBy(xpath = "(//span[contains(text(),'Admin Function')])[2]")
	private WebElement adminFunctionLeftHandPanel;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTableadminFunction']")
	private WebElement userNameSearchBox;
									
	@FindBy(xpath = "//a[@class='btn default btn-xs green']")
	private WebElement manageButton;
																	
	@FindBy(xpath = "//div[@id='uniform-resetpass']//span")	
	private WebElement resetPasswordCheckBox;
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement newPasswordTextBox;
	
	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(xpath = "(//a[@type='button'])[2]")
	private WebElement savePasswordButton;	
																												
	@FindBy(xpath = "(//button[contains(text(),'Close')])[3]")
	private WebElement closeButton;
	
	@FindBy(xpath = "//input[@id='employeeclass']")
	private WebElement employeeClass;
	//4/4/19
	@FindBy(xpath ="//button[@class='btn default closebutton' and contains(text(),'Close')]")
	private WebElement close; // close button that appears when "user already exists message" is displayed
	
	WebDriverWait wait;
	
	
	public AdminPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	public void openJobControlPanelLink()
	{
		wait.until(ExpectedConditions.visibilityOf(firstRowInTable));
		TestBase.javaScriptClickbyElement(driver, jobControlPanelLink);
	}
	
	public void openUtilityandTriggerChain()
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, triggerJobLink);
		triggerJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provLink));
		wait.until(ExpectedConditions.visibilityOf(actionButtonInTriggerChain));
		actionButtonInTriggerChain.click();
		editTrigger.click();
		wait.until(ExpectedConditions.visibilityOf(runButton));
		runButton.click();
	}
	
	public void openUtilityandProvisioningJob()
	{
		wait.until(ExpectedConditions.visibilityOf(utilityLink));
		utilityLink.click();
		TestBase.scrollDownToElement(driver, provisioningJobLink);
		provisioningJobLink.click();
		wait.until(ExpectedConditions.visibilityOf(provisioningText));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Provisioning Job')]/following::a[1]")));
		actionButtonInProvisioningJob.click();
		wait.until(ExpectedConditions.visibilityOf(startButton));
		startButton.click();
		wait.until(ExpectedConditions.visibilityOf(selectSystemTextBox));
		selectSystemTextBox.sendKeys("TEST_SalesforceTest");
		selectSystemTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(submitButton));
		submitButton.click();		
		
	}
	public String clickOnUsersAndCreateUsers(String fname,String lname,String managerId) throws InterruptedException {
		String user = null;
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		usersLinkLeftPanel.click();
		wait.until(ExpectedConditions.visibilityOf(searchUserTextBox));
		actionsbutton.click();
		wait.until(ExpectedConditions.visibilityOf(createUsers));
		createUsers.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		//userName.sendKeys(uname);
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		country.sendKeys("germany");
		email.sendKeys("meegeaten.sellamuthu@bp.com");
		TestBase.scrollDownToElement(driver, selectManagerButton);
		selectManagerButton.click();
		wait.until(ExpectedConditions.visibilityOf(searchManagerTextBox));
		searchManagerTextBox.sendKeys(managerId);
		searchManagerTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+managerId+"')]")));
		driver.findElement(By.xpath("//input[@type='radio' and @label='"+managerId+"']")).click();
		TestBase.scrollDownToElement(driver, submitManagerButton);
		submitManagerButton.click();
		for(int i=0;i<5;i++)
		{
			try {
				String name = "Sample_Test_User_";
				int aNumber = 0; 
				aNumber = (int)((Math.random() * 1000)+1000); 
				user = name+aNumber;
				System.out.println(user);
				TestBase.scrollUp(driver);
				wait.until(ExpectedConditions.visibilityOf(userName));
				userName.clear();
				userName.sendKeys(user);
				TestBase.scrollDownToElement(driver, createButton);
				createButton.click();
				Thread.sleep(2000);
				if(driver.findElements(By.xpath("//div[contains(text(),'Username Already Exists')]")).size() > 0)
				{
					
					close.click();
				}
				else
				{
					
					break;
					
					
				}
				
			}
			catch(Exception e) {
				System.out.println("user not created successfully");
			}
		}
		return user;
		
	
	}
	
	public void addAttributes(String property16, String property24,String uname,String password) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(otherAttributesTab));
		otherAttributesTab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='siteid']")));
		//TestBase.scrollDownToElement(driver, uanTextBox);
		upnTextBox.sendKeys(property16);
		//TestBase.scrollDownToElement(driver, accountDefaultValuesTextBox);
		accountDefaultValuesTextBox.sendKeys("X|1|GMTUK|GB");
		TestBase.scrollToEndOfPage(driver);
		updateButton.click();
		wait.until(ExpectedConditions.visibilityOf(userDetailsTab));
		userDetailsTab.click();
		// newly added function
		employeeClass.sendKeys("Employee");
		departmentName.sendKeys("IT&S BAS");
		TestBase.scrollToEndOfPage(driver);
		update.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
		savRoleTab.click();
		/*
		 * new update - adding sav role not required as, it is automatically assigned
		 * when user is created
		 */
		/*wait.until(ExpectedConditions.visibilityOf(actionsbutton));
		actionsbutton.click();
		addSavRole.click();
		wait.until(ExpectedConditions.visibilityOf(addSavRoleHeader));
		savRoleSearchBox.sendKeys("ROLE_END_USER");
		savRoleSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		Thread.sleep(3000);
		TestBase.javaScriptClickbyElement(driver, firstCheckBox);
		//firstCheckBox.click();
		submitRoleButton.click();*/
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		TestBase.scrollDownToElement(driver, adminFunctionLeftHandPanel);
		adminFunctionLeftHandPanel.click();
		wait.until(ExpectedConditions.visibilityOf(userNameSearchBox));
		userNameSearchBox.sendKeys(uname);
		userNameSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'User NTID')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class=' sorting_1']//a[contains(text(),'"+uname+"')]")));
		manageButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Manage User')]")));
		TestBase.scrollDownToElement(driver, resetPasswordCheckBox);
		resetPasswordCheckBox.click();
		newPasswordTextBox.sendKeys(password);
		confirmPasswordTextBox.sendKeys(password);
		savePasswordButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Saviynt Security Manager')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Password reset successfully!')]")));
		closeButton.click();
		Thread.sleep(3000);

	}
	

}
