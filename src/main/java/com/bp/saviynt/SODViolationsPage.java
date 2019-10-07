package com.bp.saviynt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class SODViolationsPage extends TestBase {
	
	UsernameGeneration object = new UsernameGeneration();

	@FindBy(xpath = "//a[contains(text(),'SOD')]")
	private WebElement sod;
	
	@FindBy(xpath = "//div[@class='btn-group']//label[1]")
	private WebElement open;

	@FindBy(xpath = "//div[@class='btn-group']//label[3]")
	private WebElement riskAccepted;

	@FindBy(xpath = "//h3[contains(text(),'SOD Violation')]")
	private WebElement SODViolationPageHeader;

	@FindBy(xpath = "//input[@id='dtsearch_SODMapped']")
	private WebElement textBox;

	@FindBy(xpath = "(//span[@class='select2-chosen'][contains(text(),'All')])[2]")
	private WebElement endPointFilterDropDown;

	@FindBy(xpath = "//div[contains(text(),'PR5CLNT100')]")
	private WebElement dropDownElement;

	@FindBy(xpath = "//div[@class='page-container']//tbody//tr[1]")
	private WebElement firstRow;

	@FindBy(xpath = "//tbody[@role='alert']//tr[1]//td[1]//input[@type='checkbox']")
	private WebElement firstCheckbox;

	@FindBy(xpath = " //div[@id='s2id_stlid']//span[@class='select2-chosen'][contains(text(),'Select')]")
	private WebElement moveToDropDown;
	
	@FindBy(xpath = "//div[contains(text(),'Risk Accepted')]")
	private WebElement riskAcceptedDropDownElement;
	
	@FindBy(xpath = "//h4[@class='modal-title' and contains(text(),'Select Mitigating Control')]")
	private WebElement selectmitigatingControlHeader;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTablemitigatingControls']")
	private WebElement mitigatingControlSearchBox;
	
	@FindBy(xpath = "//input[@name='mitigatingcontrol']")
	private WebElement radioButtonCutOver; // radio button to select MCCUTOVER mitigating control
	
	@FindBy(xpath = "//textarea[@id='comments']")
	private WebElement commentBox;
	
	@FindBy(xpath = "//button[@id='submitdate']")
	private WebElement nextButton;
	
	@FindBy(xpath = " (//input[@name ='mitigatingcontrol'])[1]")
	private WebElement firstRadioButton;
	
	@FindBy(xpath = "//div[@class='input-group date date-picker']//button[@class='btn default']")
	private WebElement calendarButton;
	
	@FindBy(xpath = "//input[@id='startdate']")
	private WebElement startDate;
	
	@FindBy(xpath = "//input[@id='mcenddate']")
	private WebElement mcEndDate;
	
	@FindBy(xpath = "//button[@title='Change']")
	private WebElement changeButton;
	
	@FindBy(xpath = "//input[@type='checkbox' and @onclick='refreshtable(this)']")
	private WebElement mitigatingControlCheckBox;
	
	@FindBy(xpath = "//*[@id='mcname']")
	private WebElement newMitigatingControlName;
	
	@FindBy(xpath = "(//*[@id=\"SODMapped\"]//td//a[contains(text(),'O005')])[1]")
	private WebElement firstRowWithRiskCode;
	
	@FindBy(xpath = "//*[@id=\"myDataTablemitigatingControls\"]/tbody/tr/td[2]")
	private WebElement firstRowInMitigatingControlPopUp;
	
	//12/09/19
	@FindBy(xpath = "//div[@title='TOGGLE SIDEBAR']")
	private WebElement toggleSideBar;
	
	@FindBy(xpath = "//a[@href='/ECM/simulationDetail/list']")
	private WebElement simulationHeaderLeftHandSidePanel;
	
	//23-09-2019
	@FindBy(xpath = "//div[@id='s2id_rulesetFilter']//a[@class='select2-choice']")
	private WebElement rulesetFilterDropDown;
	
	@FindBy(xpath = "//button[contains(text(),'Advanced')]")
	private WebElement advancedButton;
	
	@FindBy(xpath = "//h4[contains(text(),'Advanced Search')]")
	private WebElement advancedSearchHeader;
	
	@FindBy(xpath = "//a[contains(text(),'Accounts')]")
	private WebElement accountsTab;
	
	@FindBy(xpath = "//input[@id='A_NAME']")
	private WebElement accountNameTextBox;
	
	@FindBy(xpath = "//button[@class='btn green' and @id='dosearch']")
	private WebElement searchButtonForAccount;
	
	@FindBy(xpath = "(//span[contains(text(),'Ruleset')])[2]")
	private WebElement rulesetHeaderSidePanel;
	
	@FindBy(xpath = "//a[@href='/ECM/functions/list']")
	private WebElement functionsHeaderSidePanel;
	
	WebDriverWait wait;

	String riskCode, account, mitigatingControl;

	public SODViolationsPage(WebDriver ldriver) {
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}

	public void clickOnToggleSideBarandgotoSimulation()
	{
		toggleSideBar.click();
		simulationHeaderLeftHandSidePanel.click();
	}
	public void clickOnOpenRisks() {
		wait.until(ExpectedConditions.visibilityOf(SODViolationPageHeader));
		open.click();
		wait.until(ExpectedConditions.visibilityOf(textBox));
	}
	
	public void clickOnRiskAccepted() {
		wait.until(ExpectedConditions.visibilityOf(SODViolationPageHeader));
		riskAccepted.click();
		wait.until(ExpectedConditions.visibilityOf(textBox));
	}

	public void filterByEndPoints() {
		endPointFilterDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(dropDownElement));
		dropDownElement.click();
		wait.until(ExpectedConditions.visibilityOf(firstRow));
	}

	public ArrayList<String> selectFirstCheckBoxAndRetrieveFirstRowData() throws InterruptedException {
		 wait.until(ExpectedConditions.visibilityOf(firstRow));
		// firstCheckbox.click();
		//Thread.sleep(2000);
		TestBase.javaScriptClickbyElement(driver, firstCheckbox);
		Thread.sleep(2000);
		//firstCheckbox.click();
		System.out.println("first check box clicked successfully");
		List<WebElement> firstRow = driver.findElements(By.xpath("//div[@class='page-container']//tbody//tr[1]//td"));
		ArrayList<String> firstRowData = new ArrayList<String>();
		String temp;
		for (int i = 2; i <= firstRow.size(); i++) {
			temp = driver.findElement(By.xpath("//div[@class='page-container']//tbody//tr[1]//td[" + i + "]"))
					.getText();
			firstRowData.add(temp);

		}
		System.out.println(firstRowData);
		riskCode = firstRowData.get(0);
		account = firstRowData.get(6);
		mitigatingControl = firstRowData.get(8);
		System.out.println(riskCode);
		System.out.println(account);
		System.out.println(mitigatingControl);
		object.writeMitigationControlDetails(riskCode, account, mitigatingControl);
		return firstRowData;

	}
	
	public void moveRiskToAccepted()
	{
		 moveToDropDown.click();
		riskAcceptedDropDownElement.click();
	}
	
	public void selectMitigatingControl() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(selectmitigatingControlHeader));
		wait.until(ExpectedConditions.visibilityOf(mitigatingControlSearchBox));
		wait.until(ExpectedConditions.visibilityOf(firstRowInMitigatingControlPopUp));
		/*if(account.startsWith("CU"))
		{
			mitigatingControlSearchBox.sendKeys("MCCUTOVER");
			mitigatingControlSearchBox.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			radioButtonCutOver.click();
			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		}*/
		firstRadioButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(nextButton));
		
		commentBox.sendKeys("mitigated");
		String date1 = startDate.getText();
		System.out.println(date1);
		int newDate = Integer.parseInt(date1.substring(4, 6)); // get date value from MMM/DD/YYYY formate of date
		newDate = newDate+1; // add 1 to the date value
		String  endDate = Integer.toString(newDate); 
		Calendar cal = Calendar.getInstance();
		String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};
        String month = monthName[cal.get(Calendar.MONTH)]; //obtain current month the
        System.out.println("Month name: " + month);
        if(month.equalsIgnoreCase("December"))
        {
        	month = "January";
        }
        if(newDate==30 || newDate==31)
        {
        	endDate=Integer.toString(1);
        	month = monthName[cal.get(Calendar.MONTH)]+1;
        }
        String shortMonth = month.substring(0, 3);
        driver.findElement(By.xpath("//input[@id='enddate']")).click();
        //driver.findElement(By.xpath("//th[contains(text(),'December 2019')]")).click();
        //driver.findElement(By.xpath("//span[contains(text(),'"+shortMonth+"')]")).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'23')]")));
        
        driver.findElement(By.xpath("//td[@class='day' and contains(text(),'20')]")).click(); // variable used before was endDate
        driver.findElement(By.xpath("//input[@id='enddate']")).sendKeys(Keys.ESCAPE);
        nextButton.click(); // click on next button and application will automatically redirect to risk accepted tab.
		
	}

	/*
	 * after adding mitigating control to the risks in open tab, we are
	 * automatically redirected to risk accepted tab. to validate our operation is
	 * successful search for risk already added wit mitigating control and take
	 * screenshot.
	 */
	public void searchInRiskAccepted() throws InterruptedException
	{
		account = object.readAccountCode();
		riskCode =object.readRiskCode();
		wait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys(account); 
		textBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		Thread.sleep(3000);
		//WebElement rowDataWithRisk = driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']"));
		TestBase.scrollDownToElement(driver, driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']")));
		driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']")).getText();
		
	}
	
	public void clickOnRiskToModifyDate() throws InterruptedException
	{
		account = object.readAccountCode();
		riskCode =object.readRiskCode();
		
		WebElement rowDataWithRisk = driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']"));
		rowDataWithRisk.click();
		wait.until(ExpectedConditions.visibilityOf(mcEndDate));
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 3); // Adding 3 days
		String output = sdf.format(c.getTime());
		System.out.println(output);
		
		mcEndDate.click();
		mcEndDate.sendKeys(Keys.ESCAPE);
		mcEndDate.clear();
		mcEndDate.sendKeys(output);
		sod.click();
		wait.until(ExpectedConditions.visibilityOf(textBox));
		riskAccepted.click();
		wait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys(account);
		textBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		TestBase.scrollDownToElement(driver, rowDataWithRisk);
		String modifiedDateDetails = driver.findElement(By.xpath("td[contains(text(),'"+riskCode+"')]")).getText(); 
		System.out.println(modifiedDateDetails);
	
	}
	
	
	public void  changeMitigatingControl() throws InterruptedException
	{
		account = object.readAccountCode();
		riskCode =object.readRiskCode();
		
		//WebElement rowDataWithRisk = driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']"));
		//rowDataWithRisk.click();
		wait.until(ExpectedConditions.visibilityOf(selectmitigatingControlHeader));
			wait.until(ExpectedConditions.visibilityOf(mitigatingControlSearchBox));
			/*if(account.startsWith("CU"))
			{
				mitigatingControlSearchBox.sendKeys("MCCUTOVER");
				mitigatingControlSearchBox.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				radioButtonCutOver.click();
				wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			}*/
			firstRadioButton.click();
			wait.until(ExpectedConditions.elementToBeClickable(nextButton));
			
			commentBox.sendKeys("mitigated");
	        driver.findElement(By.xpath("//input[@id='enddate']")).click();
	        //driver.findElement(By.xpath("//th[contains(text(),'December 2019')]")).click();
	        //driver.findElement(By.xpath("//span[contains(text(),'"+shortMonth+"')]")).click();
	        Thread.sleep(2000);
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'23')]")));
	        
	        driver.findElement(By.xpath("//td[@class='day' and contains(text(),'23')]")).click(); // variable used before was endDate
	        driver.findElement(By.xpath("//input[@id='enddate']")).sendKeys(Keys.ESCAPE);
	        nextButton.click(); // click on next button and application will automatically redirect to risk accepted tab.
	        //String newMC = newMitigatingControlName.getText();
	        sod.click();
	        wait.until(ExpectedConditions.visibilityOf(textBox));
	        riskAccepted.click();
	        wait.until(ExpectedConditions.visibilityOf(textBox));
			//return newMC;
		}
		
	
	
	
	public void clickOnRisk()
	{
		account = object.readAccountCode();
		riskCode =object.readRiskCode();
		
		WebElement rowDataWithRisk = driver.findElement(By.xpath("//table[@id='SODMapped']//td//a[@title='"+riskCode+"']"));
		rowDataWithRisk.click();
		wait.until(ExpectedConditions.visibilityOf(mcEndDate));
		
	}
	public void clickOnChangeButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(mcEndDate));
		changeButton.click();
		wait.until(ExpectedConditions.visibilityOf(selectmitigatingControlHeader));
		wait.until(ExpectedConditions.visibilityOf(mitigatingControlSearchBox));
		mitigatingControlCheckBox.click();
		Thread.sleep(2000);
		
	}
	
	public ArrayList<String> searchForRiskAndSelectFirstThreeRisks(String riskCode)
	{
		wait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys(riskCode);
		textBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		wait.until(ExpectedConditions.visibilityOf(firstRowWithRiskCode));
		ArrayList<String> accountNames = new ArrayList<String>();
		for(int i=1;i<=3;i++)
		{
			WebElement checkBox = driver.findElement(By.xpath("//tbody[@role='alert']//tr["+i+"]//td[1]//input[@type='checkbox']"));
			TestBase.javaScriptClickbyElement(driver, checkBox);
			String temp;
			temp = driver.findElement(By.xpath("//tbody[@role='alert']//tr["+i+"]//td[8]")).getText();
			accountNames.add(temp);
		}
		System.out.println(accountNames);
		return accountNames;
	}
	
	public void validateMitigatingControlAssignment(ArrayList<String> selectAccounts) throws IOException
	{
		wait.until(ExpectedConditions.visibilityOf(textBox));
		for(int i=0;i<=selectAccounts.size();i++)
		{
			try
			{
				String accountName = selectAccounts.get(i);
			textBox.sendKeys(accountName);
			textBox.sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.visibilityOf(firstRowWithRiskCode));
			if(driver.findElements(By.xpath("(//*[@id='SODMapped']//td//a[contains(text(),'O005')])[1]")).size()>0)
			{
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='SODMapped']/tbody/tr[1]/td[10][contains(text(),'"+accountName+")]")));
				logger.pass("Mitigating control assigned for risk O005", MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			}
			else
				logger.fail("Mitigation control assignment fail", MediaEntityBuilder
						.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			textBox.clear();
			}
			catch(IndexOutOfBoundsException e)
			{
				break;
			}
		}
		
	}
	
	public String getNewMCName()
	{
		wait.until(ExpectedConditions.visibilityOf(newMitigatingControlName));
		String newMC = newMitigatingControlName.getAttribute("value");
		return newMC;
	}
	
	public void clickOnRulesetFilterDropDownAndFilter(String ruleSet)
	{
		wait.until(ExpectedConditions.visibilityOf(rulesetFilterDropDown));
		rulesetFilterDropDown.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+ruleSet+"')]")));
		driver.findElement(By.xpath("//div[contains(text(),'"+ruleSet+"')]")).click();;
	}
	
	// used for Ruleset testing.
	// search for user and click on the risk code.
	public void searchForUserAndClickOnRiskCode(String user) throws InterruptedException
	{
		
		wait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys(user);
		textBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		//obtain risk code from username
		String riskCode = user.substring(6);
		System.out.println(riskCode);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr//td//a[contains(text(),'"+riskCode+"')]")));
		driver.findElement(By.xpath("//tbody[@role='alert']//tr//td//a[contains(text(),'"+riskCode+"')]")).click();
		
	}
	
	public void clickOnAdvancedButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(advancedButton));
		advancedButton.click();
		wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
	}
	
	public void gotoRulesetandClickOnFunction()
	{
		wait.until(ExpectedConditions.visibilityOf(toggleSideBar));
		toggleSideBar.click();
		rulesetHeaderSidePanel.click();
		wait.until(ExpectedConditions.visibilityOf(functionsHeaderSidePanel));
		functionsHeaderSidePanel.click();
	}
	
	
}
	
	

