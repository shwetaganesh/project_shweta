package com.bp.saviynt;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class ApprovalInboxPage extends TestBase
{
	WebDriverWait wait;
	
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[1]")
	private WebElement acceptButton1;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[2]")
	private WebElement rejectButton1;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[3]")
	private WebElement acceptButton2;

	@FindBy(xpath = "(//button[contains(@id,'asynx')])[4]")
	private WebElement rejectButton2;

	@FindBy(xpath = "//a[contains(@class,'buttonreject') and contains(text(),'Reject All')]")
	private WebElement rejectAllButton;
	
	@FindBy(xpath = "//a[@onclick='confirmandsubmit()']")
	private WebElement confirmButton;
	
	@FindBy(xpath = "//a[contains(text(),'HOME')]")
	private WebElement req_home_link;

	@FindBy(xpath = "//*[@class='page-title']")
	private WebElement approvalInboxText;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTable123']")
	private WebElement searchBox;
	
	 @FindBy(xpath = "//*[contains(text(),'Approval Inbox')]")
	 private WebElement approval_inbox_side;
	 
	 @FindBy(xpath = "(//*[contains(text(),'Request Approval')])[2]")
	 private WebElement requestApprovalHeaderSidePanel;
	 
	 @FindBy(xpath = "(//*[contains(text(),'Add Mitigating Control')])[1]")
	 private WebElement addMitigatingControlButton;
	 
	 @FindBy(xpath = "(//*[contains(@id,'addmitigating')])[2]")
	 private WebElement addMitigatingControlButton2;
	 
	 @FindBy(xpath = "//td[contains(text(),'EPOMC001')]")
	 private WebElement mitigatingControl_Row1;
	 
	 @FindBy(xpath ="//td[contains(text(),'EPOMC002')]")
	 private WebElement mitigatingControl_Row;
	 
	 @FindBy(xpath = "(//*[@type='radio' and contains(@title,'MCP106')])[1]")
	 private WebElement firstRadioButton;
	 
	 @FindBy(xpath = "(//*[@type='radio' and contains(@title,'EPOMC002')])[1]")
	 private WebElement radioButton; // 1st radio button displayed fr 2nd MC
	 
	 /*@FindBy(xpath = "(//*[@type='radio' and @class='uniform' and @name='mitigatingcontrol' and @title='EPOMC002'])[1]")
	 private WebElement secondRadioButton;*/
	 
	 @FindBy(xpath = "(//*[@type='radio'])[6]")
	 private WebElement secondRadioButton;
	 @FindBy(xpath = "//a[@id='nextButtonMC']")
	 private WebElement nextButton;
	 
	 @FindBy(xpath = "//button[@id='submitdate']")
	 private WebElement submitDateButton;
	 
	@FindBy(xpath="//span[contains(text(),'(Purchase Order Entry and Goods')]")
	private WebElement purchaseOrderHeader;
	
	@FindBy(xpath = "//*[@id = 'dtsearch_myDataTablemitigatingControls']")
	private WebElement textBox;
	
	//14/1/19
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[5]")
	private WebElement acceptButton3;
	
	@FindBy(xpath = "(//button[contains(@id,'asynx')])[6]")
	private WebElement rejectButton3;
	
	@FindBy(xpath = "//a[contains(text(),'ARS')]")
	private WebElement ars_link;           // instead of home
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTablemitigatingControls']")
	private WebElement searchBoxForMC; // search box for mitigating control
	
	@FindBy(xpath ="//button[contains(text(),'Submit')]")
	private WebElement submitMC; // submit mitigating control
	
	//10/4/19
	@FindBy(xpath = "//button[@class='btn default closebutton']")
	private WebElement closeButton; //close button in popUp
	
	@FindBy(xpath ="//div[contains(text(),'Please add mitigating controls to violations')]")
	private WebElement popUpMessage;
	
	@FindBy(xpath = "(//h4)[7]//span[contains(text(),'PROCLNT100')]")
	private WebElement headerPROCLNT100_2;
	
	@FindBy(xpath ="(//h4)[6]//span[contains(text(),'PROCLNT100')]")
	private WebElement headerPROCLNT100_1;

	@FindBy(xpath ="//div[contains(text(),'Total 3 Segregation of Duty Violation Found')]")
	private WebElement sodHeader;
	
	@FindBy(xpath ="//div[@id='collapse1_1']//label[@class='clearfix'][contains(text(),'Business Justification')]")
	private WebElement businessJustificationHeader;
	
	@FindBy(xpath ="//div[@id='collapse1_1']//label[@class='clearfix'][contains(text(),'Additional Justification')]")
	private WebElement additionalJustificationHeader;
	
	@FindBy(xpath = "//*[contains(text(),'SOD-Approval Task')]")
	private WebElement sodApprovalTaskTab;
	
	@FindBy(xpath = "//div[contains(text(),'Task History')]")
	private WebElement taskHistoryHeader;
	
	@FindBy(xpath = "//h4[contains(text(),'Enter Comment')]")
	private WebElement commentsHeader;
	
	@FindBy(xpath = "//textarea[@id='approverejectcomments']")
	private WebElement commentsTextBox;
	
	@FindBy(xpath ="//button[contains(text(),'Submit')]")
	private WebElement submitComments;
	
	@FindBy(xpath = "//h4[contains(text(),'Do You Wish To Continue?')]")
	private WebElement continueAlertBoxHeader;
	
	@FindBy(xpath = "//div[@class='modal-footer']//button[@class='btn green yesandstayincurrentrequestbtn']")
	private WebElement yesAndStayInCurrentRequestButton;
	
	@FindBy(xpath = "(//div[@id='accordion3']//span[1]//i[1][contains(text(),'Mitigating Control')])[2]")
	private WebElement mitigatingControlStatusforP206;
	
	@FindBy(xpath ="(//div[@id='accordion3']//span[1]//i[1][contains(text(),'Mitigating Control')])[1]")
	private WebElement mitigatingControlStatusforP106;
	
	//18-6-19
	@FindBy(xpath = "//a[@onclick=\"reassignapprover('all')\"]")
	private WebElement modifyApproverButton;
	
	@FindBy(xpath = "//input[@id='dtsearch_allusersofloggedmanager']")
	private WebElement sodApproverSearchBox;
	
	@FindBy(xpath = "(//input[@type='radio'])[6]")
	private WebElement radioButtonToselectApprover; // first radio button that appears after searching for new sod approver.
	
	@FindBy(xpath ="//button[@onclick=\"settextvalueinuser()\"]")
	private WebElement submitNewApprover;  // submit button to be clicked after selecting new SOD approver
	
	public ApprovalInboxPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//method to search for the request number which is raised by the requester for the end user
	public void searchRequestNumber(String requestID)
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox));
		searchBox.clear();
		searchBox.sendKeys(requestID);
		searchBox.sendKeys(Keys.ENTER);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+requestID+"')]")));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(approval_inbox_side));
	}
	
	//method to accept the first role for the end user
	public void acceptFirstRole() 
	{
		acceptButton1.click();
		System.out.println("Role1 approved for the user");
	}
	
	//method to reject the first role for the end user
	public void rejectFirstRole() 
	{
		rejectButton1.click();
		System.out.println("Role1 rejected for the user");
	}

	//method to accept the second role for the end user
	public void acceptSecondRole() 
	{
		acceptButton2.click();
		System.out.println("Role2 approved for the user");
	}

	//method to reject the first role for the end user
	public void rejectSecondRole()
	{
		rejectButton2.click();
		System.out.println("Role2 rejected for the user");
	}
	
	public void acceptThirdRole()
	{
		acceptButton3.click();
		System.out.println("role3 accepted");
	}
	
	public void rejectAllRole()
	{
		TestBase.scrollUp(driver);
		rejectAllButton.click();
		System.out.println("All roles rejected for the user");
	}
	
	public void clickOnConfirmWithoutAddingMC() {
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
		TestBase.javaScriptClickbyElement(driver, confirmButton);
		//confirmButton.click();
		System.out.println("confirm button clicked");
		
	}
	//method to click on the confirm button after accepting/rejecting the roles requested
	public boolean clickOnConfirm()
	{
		wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
		TestBase.javaScriptClickbyElement(driver, confirmButton);
		//confirmButton.click();
		System.out.println("confirm button clicked");
		wait.until(ExpectedConditions.elementToBeClickable(req_home_link));
		//wait.until(ExpectedConditions.visibilityOf(requestApprovalHeaderSidePanel));
		//boolean result = requestApprovalHeaderSidePanel.getText().equalsIgnoreCase("Request Approval");
		boolean result = approvalInboxText.getText().equalsIgnoreCase("Approval Inbox");
		//ars_link.click();
		req_home_link.click();
		return result;
	}
	
	public void addMitigatingControl(String controlName) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMitigatingControlButton));
		addMitigatingControlButton.click();
		wait.until(ExpectedConditions.visibilityOf(searchBoxForMC));
		searchBoxForMC.sendKeys(controlName);
		searchBoxForMC.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+controlName+"')]")));
		//wait.until(ExpectedConditions.visibilityOf(firstRadioButton));
		driver.findElement(By.xpath("//input[contains(@title,'"+controlName+"')]")).click();
		/*TestBase.javaScriptClickbyElement(driver, nextButton);
		wait.until(ExpectedConditions.elementToBeClickable(submitDateButton));
		submitDateButton.click();*/
		wait.until(ExpectedConditions.elementToBeClickable(submitMC));
		TestBase.javaScriptClickbyElement(driver, submitMC);
		
	}
	
	
	/*public void clickPurchaseOrderEntryHeader()
	{
		TestBase.javaScriptClickbyElement(driver, purchaseOrderHeader);
	}*/
	public void clickOnHeaderToAddFirstMC()
	{
		TestBase.javaScriptClickbyElement(driver, headerPROCLNT100_1);
	}
	public void clickOnHeaderToAddSecondMC() 
	{
		TestBase.javaScriptClickbyElement(driver, headerPROCLNT100_2);
	}
	
	public void addMitigatingControl2(String controlName) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(addMitigatingControlButton2));
		TestBase.javaScriptClickbyElement(driver, addMitigatingControlButton2);
		wait.until(ExpectedConditions.visibilityOf(searchBoxForMC));
		searchBoxForMC.sendKeys(controlName);
		searchBoxForMC.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+controlName+"')]")));
		radioButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitMC));
		TestBase.javaScriptClickbyElement(driver, submitMC);
	}
	
	public String checkForPopUpMessage() {
		String message = null;
		try{
			if(driver.findElements(By.xpath("//div[contains(text(),'Please add mitigating controls to violations')]")).size()>0) {
				 message = popUpMessage.getText();
				 System.out.println(message);
				 closeButton.click();
			}	
		}
		catch(Exception e) {
			System.out.println("pop up message to add mitigating control is not displayed");
		}
		return message;
		
	}
	
	//3-6-19
	public String verifyPresenceOfSODHeader()
	{
		wait.until(ExpectedConditions.visibilityOf(sodHeader));
		return sodHeader.getText();
	}

	public boolean verifyPresenceOfBusinessJustificationHeader()
	{
		TestBase.scrollDownToElement(driver, businessJustificationHeader);
		if(businessJustificationHeader.getSize()!=null)
			return true;
		else
			return false;
	}
	
	public boolean verifyPresenceOfAdditionalJustificationHeader()
	{
		TestBase.scrollDownToElement(driver, additionalJustificationHeader);
		if(additionalJustificationHeader.getSize()!=null)
			return true;
		else
			return false;
	}
	
	public void clickOnSODApprovalTask()
	{
		TestBase.scrollDownToElement(driver, taskHistoryHeader);
		sodApprovalTaskTab.click();
	}
	
	public void addCommentsAndSubmit()
	{
		wait.until(ExpectedConditions.visibilityOf(commentsHeader));
		commentsTextBox.sendKeys("reject role");
		submitComments.click();
	}
	public void clickOnStayInCurrentRequest() throws InterruptedException
	{
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(yesAndStayInCurrentRequestButton));
		//yesAndStayInCurrentRequestButton.click();
		TestBase.javaScriptClickbyElement(driver, yesAndStayInCurrentRequestButton);
		Thread.sleep(5000);
	}
	
	public String verifyMitigatingControlStatus()
	{
		int r1,r2;
		wait.until(ExpectedConditions.visibilityOf(headerPROCLNT100_1));
		headerPROCLNT100_1.click();
		String status = mitigatingControlStatusforP106.getText();
		if (status.contains("Mitigating Control Available"))
			r1 = 0;
		else if(status.contains("Mitigating Control Added"))
			r1 = 1;
		else
			r1 = 2;
		wait.until(ExpectedConditions.visibilityOf(headerPROCLNT100_2));
		status = mitigatingControlStatusforP206.getText();
		if (status.contains("Mitigating Control Available"))
			r2 = 0;
		else if(status.contains("Mitigating Control Added"))
			r2=1;
		else 
			r2 = 2;
		if(r1==0 && r2 == 0)
			return "Mitigating Control Available";
		else if(r1==1 && r2 ==1)
			return "Mitigating Control Added";
		else
			return "Error with mitigating control status";
			
	}
	
	public void clickOnModifyApproversButton()
	{
		wait.until(ExpectedConditions.visibilityOf(modifyApproverButton));
		modifyApproverButton.click();
	}
	public boolean searchAndAssignNewSODApprover(String newApprover) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(sodApproverSearchBox));
		sodApproverSearchBox.sendKeys(newApprover);
		sodApproverSearchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[contains(text(),'"+newApprover+"')])[1]")));
		radioButtonToselectApprover.click();
		submitNewApprover.click();
		wait.until(ExpectedConditions.visibilityOf(approval_inbox_side));
		if(approval_inbox_side.getText().contains("Approval Inbox"))
			return true;
		else
			return false;
		}
	}
