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
	
	
	@FindBy(xpath = "//input[@id='username']") 
	private WebElement userName;
	
	@FindBy(xpath = "//input[@id='firstname']") 
	private WebElement firstName;
	
	@FindBy(xpath = "//input[@id='lastname']") 
	private WebElement lastName;
	
	@FindBy(xpath = "//input[@id='city']") 
	private WebElement cityName;
	
	@FindBy(xpath = "//input[@id='street']")
	private WebElement streetName;
	
	@FindBy(xpath = "//input[@id='phonenumber']")
	private WebElement phoneNumber;
	
	@FindBy(xpath = "//input[@id='city']") 
	private WebElement country;
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement email;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Select Manager')]")
	private WebElement selectManagerButton;
	
	@FindBy(xpath = "//input[@id='dtsearch_allusers']")
	private WebElement searchManagerTextBox;
	
	@FindBy(xpath = "(//input[@type='radio'])[1]")
	private WebElement firstRadioButton;
	
	@FindBy(xpath = "//*[@id=\"managerbtn\"]")
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
	private WebElement update;						// update button that appears under USER DETAILS tab. 
	
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
	
	@FindBy(xpath = "//a[@id='saveButton']")
	private WebElement savePasswordButton;	
																												
	@FindBy(xpath = "//button[@class='btn default closebutton']")
	private WebElement closeButton;
	
	@FindBy(xpath = "//input[@id='employeeclass']")
	private WebElement employeeClass;
	//4/4/19
	@FindBy(xpath ="//button[@class='btn default closebutton' and contains(text(),'Close')]")
	private WebElement close; // close button that appears when "user already exists message" is displayed
	
	@FindBy(xpath ="//li[@id='businessrule']//a[@href='/ECM/savRoles/adminfunction']")
	private WebElement adminFuntion;
	
	// xpaths used in dev only 
	@FindBy(xpath = "//h4[contains(text(),\"Congratulations\")]")
	private WebElement successMessage;
	
	@FindBy(xpath = "//tr[1]//td[1]")
	private WebElement firstRowWithReqNumber;
	
	@FindBy(xpath = "//li[@id='ADMIN']//a")
	private WebElement adminHeaderOnTop;
	
	@FindBy(xpath = "//tbody[@role='alert']//tr[1]")
	private WebElement firstRowOfUsers;
	
	@FindBy(xpath = "(//tbody[@role='alert']//td[@class=' sorting_1'])[1]")
	private WebElement name;
	
	//10-10-2019
	//objects for updateDL test
	@FindBy(xpath = "//li[@id='connect']//a")
	private WebElement connectionsLeftHandPanel;
	
	@FindBy(xpath = "//h3[contains(text(),'Connections')]")
	private WebElement connectionsHeader;
	
	@FindBy(xpath = "//input[@type='text' and @id='dtsearch_externelconnecdata']")
	private WebElement connectionSearchBox;
	
	@FindBy(xpath = "//input[@type='text' and @id='connectionname']")
	private WebElement connectionNameTextBox;
	
	@FindBy(xpath = "//textarea[@name='OBJECTFILTER']")
	private WebElement objectFilterTextBox;
	
	@FindBy(xpath = "//button[contains(text(),'Save & Test Connection')]")
	private WebElement saveConnectionButton;
	
	@FindBy(xpath = "//*[contains(text(),'Connection was Successful')]")
	private WebElement connectionTestSuccessMessage;
	
	
	@FindBy(xpath = "//input[@id='companyname']")
	private WebElement companyName;
	
	//7/11/19 -- xpaths for bp1 license mgmt test
	
	@FindBy(xpath = "(//*[contains(text(),'Identity Repository')])[2]")
	private WebElement identityRepositoryLeftHandPanel;
	
	@FindBy(xpath = "//label[contains(text(),'Employee Class')]")
	private WebElement employeeClassLabel;
	
	@FindBy(xpath = "(//label[starts-with(text(),'Status')]/following::div//a//span)[1]")
	private WebElement statusValue;
	
	@FindBy(xpath = "//div[@id='select2-drop']//div[1]//input[1]")
	private WebElement statusValueTextBox;
	
	@FindBy(xpath = "//input[@id='customproperty11']")
	private WebElement customProperty11TextBox;
	
	@FindBy(xpath = "//input[@id='customproperty50']")
	private WebElement customProperty50TextBox;
	
	@FindBy(xpath = "//input[@id='customproperty63']")
	private WebElement customProperty63TextBox;
	
	@FindBy(xpath = "//input[@id='customproperty64']")
	private WebElement customProperty64TextBox;
	
	@FindBy(xpath = "//input[@id='customproperty65']")
	private WebElement customProperty65TextBox;
	
	@FindBy(xpath ="//input[@id='customproperty1']")
	private WebElement customProperty1TextBox;
	
	@FindBy(xpath ="//input[@id='customproperty2']")
	private WebElement customProperty2TextBox;
	
	@FindBy(xpath ="//input[@id='customproperty3']")
	private WebElement customProperty3TextBox;
	
	@FindBy(xpath ="//input[@id='customproperty4']")
	private WebElement customProperty4TextBox;
	
	@FindBy(xpath ="//a[@href='/ECM/accounts/list']")
	private WebElement accountsLeftHandPanel;
	
	
	@FindBy(xpath ="//a[@role='presentation' and contains(text(),'Roles')]")
	private WebElement rolesTab;
	
	@FindBy(xpath="//a[@role='presentation' and contains(text(),'Accounts')]")
	private WebElement accountsTab;
	
	@FindBy(xpath = "//table[@id='sample2']//tbody//tr[1]//td[3]")
	private WebElement securitySystemColumn;
	
	@FindBy(xpath = "//table[@id='sample2']//tbody")
	private WebElement accountsTable;
	
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

	/*
	 * this method clicks on Users under the Identity Repository and goto
	 * Actions-->Click on Create User
	 */
	public void clickOnUserandCreateUserButton()
	{
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		usersLinkLeftPanel.click();
		wait.until(ExpectedConditions.visibilityOf(searchUserTextBox));
		actionsbutton.click();
		wait.until(ExpectedConditions.visibilityOf(createUsers));
		createUsers.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
	}
	
	public String createUser(String fname,String lname,String city,String street,String phone,String manager)
	{
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		cityName.sendKeys(city);
		streetName.sendKeys(street);
		phoneNumber.sendKeys(phone);
		TestBase.scrollDownToElement(driver, selectManagerButton);
		selectManagerButton.click();
		wait.until(ExpectedConditions.visibilityOf(searchManagerTextBox));
		searchManagerTextBox.sendKeys(manager);
		searchManagerTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+manager+"')]")));
		driver.findElement(By.xpath("//input[@type='radio' and @label='"+manager+"']")).click();
		TestBase.scrollDownToElement(driver, submitManagerButton);
		submitManagerButton.click();
		wait.until(ExpectedConditions.visibilityOf(createButton));
		createButton.click();
		wait.until(ExpectedConditions.visibilityOf(userName));
		String user = userName.getAttribute("value");
		return user;
	}
	public String clickOnUsersAndCreateUsersAndAddAttributes(String lname,String managerId,String property24) throws InterruptedException {
		
		String user = "Automation_Test_User_";
		int aNumber = 0; 
		aNumber = (int)((Math.random() * 1000)+100); 
		String fname  = user+aNumber;
		System.out.println(fname);
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
		wait.until(ExpectedConditions.visibilityOf(createButton));
		createButton.click();
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		String reqNumber = firstRowWithReqNumber.getText();
		//return reqNumber;
		adminHeaderOnTop.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		usersLinkLeftPanel.click();
		wait.until(ExpectedConditions.visibilityOf(searchUserTextBox));
		searchUserTextBox.sendKeys(fname);
		searchUserTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"usersList\"]/tbody/tr[1]/td[2][contains(text(),'"+fname+"')]")));
		//wait.until(ExpectedConditions.visibilityOf(firstRowOfUsers));
		String userName = name.getText();
		name.click();
		wait.until(ExpectedConditions.visibilityOf(userDetailsTab));
		savRoleTab.click();
		wait.until(ExpectedConditions.visibilityOf(actionsbutton));
		actionsbutton.click();
		addSavRole.click();
		wait.until(ExpectedConditions.visibilityOf(addSavRoleHeader));
		savRoleSearchBox.sendKeys("ROLE_END_USER");
		savRoleSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		Thread.sleep(3000);
		TestBase.javaScriptClickbyElement(driver, firstCheckBox);
		//firstCheckBox.click();
		submitRoleButton.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		userDetailsTab.click();
		wait.until(ExpectedConditions.visibilityOf(firstName));
		employeeClass.sendKeys("Employee");
		departmentName.sendKeys("IT&S BAS");
		TestBase.scrollUp(driver);
		otherAttributesTab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='customproperty1']")));
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		TestBase.scrollDownToElement(driver, accountDefaultValuesTextBox);
		accountDefaultValuesTextBox.sendKeys(property24);
		String property16 = userName+"@saviynt.com";
		upnTextBox.sendKeys(property16);
		TestBase.scrollToEndOfPage(driver);
		updateButton.click();
		return userName;
	
	}
	
	public void setPassword(String userName, String password) throws InterruptedException
	{
		adminHeaderOnTop.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		//TestBase.scrollDownToElement(driver, adminFunctionLeftHandPanel);
		adminFunctionLeftHandPanel.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(adminFuntion));
		adminFuntion.click();
		wait.until(ExpectedConditions.visibilityOf(userNameSearchBox));
		userNameSearchBox.sendKeys(userName);
		userNameSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class=' sorting_1']//a[contains(text(),'"+userName+"')]")));
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
	
	public void addAttributesNew(String property16, String property24,String uname,String password) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(otherAttributesTab));
		otherAttributesTab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='siteid']")));
		//TestBase.scrollDownToElement(driver, uanTextBox);
		upnTextBox.sendKeys(property16);
		//TestBase.scrollDownToElement(driver, accountDefaultValuesTextBox);
		accountDefaultValuesTextBox.sendKeys(property24);
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
		wait.until(ExpectedConditions.visibilityOf(actionsbutton));
		actionsbutton.click();
		addSavRole.click();
		wait.until(ExpectedConditions.visibilityOf(addSavRoleHeader));
		savRoleSearchBox.sendKeys("ROLE_END_USER");
		savRoleSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		Thread.sleep(3000);
		TestBase.javaScriptClickbyElement(driver, firstCheckBox);
		//firstCheckBox.click();
		submitRoleButton.click();
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		TestBase.scrollDownToElement(driver, adminFunctionLeftHandPanel);
		adminFunctionLeftHandPanel.click();
		adminFuntion.click();
		wait.until(ExpectedConditions.visibilityOf(userNameSearchBox));
		userNameSearchBox.sendKeys(uname);
		userNameSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[contains(text(),'User')]")));
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
		employeeClass.sendKeys("Employee");
		departmentName.sendKeys("IT&S BAS");
		TestBase.scrollToEndOfPage(driver);
		update.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
		savRoleTab.click();
		/*
		 * new update - adding sav role not required as, it is automatically assigned
		 * wen user is created
		 */
		wait.until(ExpectedConditions.visibilityOf(actionsbutton));
		actionsbutton.click();
		addSavRole.click();
		wait.until(ExpectedConditions.visibilityOf(addSavRoleHeader));
		savRoleSearchBox.sendKeys("ROLE_END_USER");
		savRoleSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		Thread.sleep(3000);
		TestBase.javaScriptClickbyElement(driver, firstCheckBox);
		//firstCheckBox.click();
		submitRoleButton.click();
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
	
	// click on connections, search for ISIM and click on it.
	public void clickOnConnectionsandSearchForConnection(String connectionName)
	{
		wait.until(ExpectedConditions.visibilityOf(connectionsLeftHandPanel));
		connectionsLeftHandPanel.click();
		wait.until(ExpectedConditions.visibilityOf(connectionsHeader));
		wait.until(ExpectedConditions.visibilityOf(connectionSearchBox));
		connectionSearchBox.sendKeys(connectionName);
		connectionSearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table//tbody[@role='alert']//tr[1]//td[2]//a[contains(text(),'"+connectionName+"')]")));
		driver.findElement(By.xpath("//table//tbody[@role='alert']//tr[1]//td[2]//a[contains(text(),'"+connectionName+"')]")).click();
		
	}
	
	public boolean filterUser(String connectionName)
	{
		wait.until(ExpectedConditions.visibilityOf(connectionNameTextBox));
		if(connectionNameTextBox.getAttribute("value").equalsIgnoreCase(connectionName))
		{
			TestBase.scrollDownToElement(driver, objectFilterTextBox);
			objectFilterTextBox.clear();
			objectFilterTextBox.sendKeys("(uid=ishugb)");
			TestBase.scrollToEndOfPage(driver);
			saveConnectionButton.click();
			wait.until(ExpectedConditions.visibilityOf(connectionTestSuccessMessage));
			if(connectionTestSuccessMessage.getText().equalsIgnoreCase("Connection was Successful"))
				return true;
			else
				return false;
			
		}
		else
			return false;
	}
	
	

	public String searchForUserAndGetCompanyName(String userID)
	{
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		usersLinkLeftPanel.click();
		wait.until(ExpectedConditions.visibilityOf(searchUserTextBox));
		searchUserTextBox.sendKeys(userID);
		searchUserTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+userID+"')]")));
		driver.findElement(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+userID+"')]")).click();
		wait.until(ExpectedConditions.visibilityOf(userName));
		TestBase.scrollDownToElement(driver, companyName);
		String cName = companyName.getAttribute("value");
		System.out.println(cName);
		return cName;
		
		
	}
	
	public void searchForUserAndClickOnUser(String userID)
	{
		wait.until(ExpectedConditions.visibilityOf(usersLinkLeftPanel));
		usersLinkLeftPanel.click();
		wait.until(ExpectedConditions.visibilityOf(searchUserTextBox));
		searchUserTextBox.sendKeys(userID);
		searchUserTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+userID+"')]")));
		driver.findElement(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+userID+"')]")).click();
		wait.until(ExpectedConditions.visibilityOf(userName));
	}
	
	public boolean verifyEmployeeClassAndStatus()
	{
		boolean result1 = false,result2 = false;
		TestBase.scrollDownToElement(driver, employeeClassLabel);
		String empClass = employeeClass.getAttribute("value");
		if(empClass.equalsIgnoreCase("Employee") || empClass.equalsIgnoreCase("Internal Partner"))
		{
			result1 = true;
		}
		
		String status = statusValue.getText();
		System.out.println(status);
		if(status.contains("Active"))
		{
			result2 = true;
		}
		if(result1&&result2)
			return true;
		else
			return false;
		
	}
	
	public void clearEmployeeClassValue()
	{
		employeeClass.clear();
		TestBase.scrollDownToElement(driver, update);
		update.click();
	}
	
	public void changeUserStatusAndUpdate() throws InterruptedException
	{
		//TestBase.scrollDownToElement(driver, employeeClassLabel);
		wait.until(ExpectedConditions.visibilityOf(employeeClassLabel));
		String empClass = employeeClass.getAttribute("value");
		if(empClass.equalsIgnoreCase("Employee") || empClass.equalsIgnoreCase("Internal Partner"))
		{
			String status = statusValue.getText();
			System.out.println(status);
			if(status.contains("Inactive"))
			{
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Start Date')]")));
				TestBase.scrollDownToElement(driver, driver.findElement(By.xpath("//label[contains(text(),'Start Date')]")));
				statusValue.click();
				wait.until(ExpectedConditions.visibilityOf(statusValueTextBox));
				statusValueTextBox.sendKeys("Active");
				Thread.sleep(3000);
				statusValueTextBox.sendKeys(Keys.ENTER);
				TestBase.scrollDownToElement(driver, update);
				update.click();
			}
		}
	}
	
	public void clickOnOtherAttributesTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(otherAttributesTab));
		otherAttributesTab.click();
	}
	
	public void addCustomProperties(String CP11,String CP50,String CP63,String CP64,String CP65)
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty11TextBox));
		if(CP50.contains("ID") )
		{
			if(!(customProperty1TextBox.getAttribute("value").contains("UP")))
				customProperty1TextBox.sendKeys("UP");
		}
		
		if(!(customProperty11TextBox.getAttribute("value").contains(CP11)))
		{
			customProperty11TextBox.clear();
			customProperty11TextBox.sendKeys(CP11);
		}
		
		
		TestBase.scrollDownToElement(driver, customProperty50TextBox);
		if(!(customProperty50TextBox.getAttribute("value").contains(CP50)))
		{	
			customProperty50TextBox.clear();
			customProperty50TextBox.sendKeys(CP50);
		}
		
		if(!(customProperty63TextBox.getAttribute("value").contains(CP63)))
		{	
			customProperty63TextBox.clear();
			customProperty63TextBox.sendKeys(CP63);
		}
		if(!(customProperty64TextBox.getAttribute("value").contains(CP64)))
		{	
			customProperty64TextBox.clear();
			customProperty64TextBox.sendKeys(CP64);
		}
		if(!(customProperty65TextBox.getAttribute("value").contains(CP65)))
		{	
			customProperty65TextBox.clear();
			customProperty65TextBox.sendKeys(CP65);
		}
		
	}
	
	public void clickOnUpdate()
	{
		wait.until(ExpectedConditions.elementToBeClickable(updateButton));
		updateButton.click();
	}
	
	public void clickOnIdentityRepository()
	{
		wait.until(ExpectedConditions.visibilityOf(identityRepositoryLeftHandPanel));
		identityRepositoryLeftHandPanel.click();
	}
	
	public void clickOnAccountsLeftHandPanel()
	{
		wait.until(ExpectedConditions.visibilityOf(accountsLeftHandPanel));
		accountsLeftHandPanel.click();
	}
	
	public void removeCustomProperties(String CP50,String CP63,String CP64,String CP65)
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty11TextBox));
		
		
		if(CP50.contains("ID") )
		{
			if((customProperty1TextBox.getAttribute("value").contains("UP")))
				customProperty1TextBox.clear();
		}
		
		TestBase.scrollDownToElement(driver, customProperty50TextBox);
		
		if((customProperty50TextBox.getAttribute("value").contains(CP50)))
		{	
			customProperty50TextBox.clear();
		}
		
		if((customProperty63TextBox.getAttribute("value").contains(CP63)))
		{	
			customProperty63TextBox.clear();
		}
		if((customProperty64TextBox.getAttribute("value").contains(CP64)))
		{	
			customProperty64TextBox.clear();
		}
		if((customProperty65TextBox.getAttribute("value").contains(CP65)))
		{	
			customProperty65TextBox.clear();
		}
	}
	
	public void addCustomProperty1(String value)
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty1TextBox));
		if(customProperty1TextBox.getAttribute("value").isEmpty())
		{
			customProperty1TextBox.sendKeys(value);
		}
		else
		{
			customProperty1TextBox.clear();
			customProperty1TextBox.sendKeys(value);
		}
		

	}
	
	public void removeCustomProperty1()
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty1TextBox));
		customProperty1TextBox.clear();
	}
	
	public void addCustomProperty65(String value)
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty1TextBox));
		TestBase.scrollDownToElement(driver, customProperty65TextBox);
		customProperty65TextBox.sendKeys(value);
	}
	
	public void clickOnRolesTab()
	{
		wait.until(ExpectedConditions.visibilityOf(rolesTab));
		rolesTab.click();
	}
	
	public void addEmployeeClassAndUserDetailsUpdate()
	{
		TestBase.scrollDownToElement(driver, employeeClassLabel);
		employeeClass.sendKeys("Employee");
		wait.until(ExpectedConditions.elementToBeClickable(update));
		update.click();
	}
	
	public void addCustomProperties(String cp1,String cp2,String cp3,String cp4,String cp11,String cp50)
	{
		wait.until(ExpectedConditions.visibilityOf(customProperty1TextBox));
		customProperty1TextBox.sendKeys(cp1);
		customProperty2TextBox.sendKeys(cp2);
		customProperty3TextBox.sendKeys(cp3);
		customProperty4TextBox.sendKeys(cp4);
		customProperty11TextBox.sendKeys(cp11);
		TestBase.scrollDownToElement(driver, customProperty50TextBox);
		
	}
	
	public void clickOnAccountsTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(accountsTab));
		accountsTab.click();
		
	}
	
	public boolean validateCreationOfBP1Account()
	{
		wait.until(ExpectedConditions.visibilityOf(accountsTable));
		wait.until(ExpectedConditions.visibilityOf(securitySystemColumn));
		
		String system = securitySystemColumn.getText();
		if(system.contains("BP1-Testsing"))
			return true;
		else
			return false;
	}
	
	/*
	 * upn is nothing but email id for the user this method adds email into the
	 * EMAIL textbox under user details tab
	 */
	
	public void addUPNValueAndUpdate(String upnValue)
	{
		wait.until(ExpectedConditions.visibilityOf(otherAttributesTab));
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(upnValue);
		TestBase.scrollDownToElement(driver, update);
		update.click();
		
	}
	
}
