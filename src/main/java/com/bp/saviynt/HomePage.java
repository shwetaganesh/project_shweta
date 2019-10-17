package com.bp.saviynt;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class HomePage extends TestBase
{
	WebDriverWait wait;
	
	@FindBy(xpath = "(//*[@class='icon-angle-down'])[1]")
	private WebElement logoff_expand;
	
	@FindBy(xpath = "//a[@href='/ECM/j_spring_security_logout']")
	private WebElement logoff_link;
	
	@FindBy(xpath = "//*[contains(text(),'Request Home')]")
	private WebElement request_home_link;
	
	@FindBy(xpath = "//div[contains(text(),'Request Enterprise Roles ')]/following::a[1]")
	private WebElement req_link;
	
	@FindBy(xpath = "(//span[contains(text(),'Request Approval')])[2]")
	private WebElement approvalInboxView; // now named as request approval. before it was approval inbox.
	
	@FindBy(xpath = "//a[@href='/ECM/jbpmworkflowmanagement/showmyhistoryrequests']")
	private WebElement req_history_link;
	
	/*@FindBy(xpath = "//a[contains(text(),'HOME')]")
	private WebElement home_link;*/
	
	@FindBy(xpath = "//a[contains(text(),'ARS')]")
	private WebElement ars_link;          // instead of home
	
	@FindBy(xpath = "//a[contains(text(),'ADMIN')]")
	private WebElement adminLink;
	
	// 11-01-2019
	@FindBy(xpath = "//div[@id='arsRequestAccessForOthers'] //a[@class='more']")
	private WebElement requestAccessForOthers;           // previously it was requestApplicationSpecificRoles, and now changed to request access for others.
	
	//15-04-2019
	@FindBy(xpath ="//div[contains(text(),'Pending Tasks')]/following::a[1]")
	private WebElement pendingTasks;
	
	@FindBy(xpath="//h3[@class='page-title']") 
	private WebElement welcomeMessage;
	
	@FindBy(xpath="//div[@class='message m-portlet']")
	private WebElement userInformation;
	
	@FindBy(xpath="//div[@id='arsSetupDelegates']//a[contains(@class,'more')]")
	private WebElement setUpDelegatesLnk;
	
	@FindBy(xpath="//div[@id='arsViewExistingAccess']//a[@class='more']")
	private WebElement existingAccessViewLnk;
	
	@FindBy(xpath = "(//*[contains(text(),'SOD')])[1]")
	private WebElement SODLink;
	
	@FindBy(xpath = "(//*[contains(text(),'Tasks')])[2]")
	private WebElement taskHeaderLeftHandPanel;
	
	@FindBy(xpath = "(//*[contains(text(),'Pending Tasks')])[2]")
	private WebElement pendingTaskLeftHandPanel;
	
	@FindBy(xpath = "(//*[contains(text(),'Completed Tasks')])[2]")
	private WebElement completedTaskLeftHandPanel;
	
	public HomePage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	//method to open Request Enterprise Role tab to find the end user.
	public void openRequestEnterpriseRole()
	{
		wait.until(ExpectedConditions.elementToBeClickable(req_link));
		req_link.click();
	}
	
	//method to navigate to approval inbox
	public void openApprovalInbox()
	{
		wait.until(ExpectedConditions.elementToBeClickable(approvalInboxView));
		approvalInboxView.click();
	}
	
	//method to navigate to Request History
	public void openRequestHistory()
	{
		wait.until(ExpectedConditions.elementToBeClickable(req_history_link));
		req_history_link.click();
	}
	
	public void clickOnHome()
	{
		wait.until(ExpectedConditions.elementToBeClickable(ars_link));
		ars_link.click();
	}
	
	public void openAdminTab(){
		wait.until(ExpectedConditions.elementToBeClickable(adminLink));
		adminLink.click();
	}
	//method to logoff the User.
	public void logoff()
	{
		wait.until(ExpectedConditions.visibilityOf(logoff_expand));
		
		TestBase.javaScriptClickbyElement(driver, logoff_expand);
		//logoff_expand.click();
		wait.until(ExpectedConditions.visibilityOf(logoff_link));
		logoff_link.click();
		System.out.println("Logoff Successful");
	}
	
	//open request application specific roles link
	public void openRequestApplicationSpecificRoles()
	{
		wait.until(ExpectedConditions.visibilityOf(requestAccessForOthers));
		requestAccessForOthers.click();
	}
	
	public void openPendingTasks()
	{
		wait.until(ExpectedConditions.visibilityOf(pendingTasks));
		pendingTasks.click();
	}
	
	public boolean verifyWelcomeMessage()
	{
		wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
		return welcomeMessage.isDisplayed();
		//return welcomeMessage.getText();
	}
	
	public boolean verifyUserInformation()
	{
		return userInformation.isDisplayed();
	}
	
	// returns total number of Tiles present in Home Page
	public int totalNumberofTiles() 
	{
		return driver.findElements(By.xpath("//div[@class='col-md-4 mix tile_ord mix_all']")).size();
	}
	
	public void getNameOfTiles()
	{
		List<WebElement> tilesNameEle=driver.findElements(By.xpath("//div[@class='desc']"));
		
		for(WebElement e :tilesNameEle)
		{
			System.out.println("Tile Name: "+e.getText());
		}	
		
	}
	
	public void openSetUpDelegates()
	{
		wait.until(ExpectedConditions.visibilityOf(setUpDelegatesLnk));
		setUpDelegatesLnk.click();
	}
	
	public void openViewExistingAccess()
	{
		wait.until(ExpectedConditions.visibilityOf(existingAccessViewLnk));
		existingAccessViewLnk.click();
	}
	
	public void clickOnSOD()
	{
		wait.until(ExpectedConditions.visibilityOf(SODLink));
		SODLink.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(),'SOD Violation')]")));
	}
	
	public boolean verifyTilesName(String TileNames)
	{
		int val=0;

		List<WebElement> tilesNameEle=driver.findElements(By.xpath("//div[@class='desc']"));
		ArrayList<String> definedTile = new ArrayList<String>();
		ArrayList<String> generatedTile = new ArrayList<String>();		
		for(String tile:TileNames.split("&"))
		{			
			definedTile.add(tile);
		}		
		for(WebElement e :tilesNameEle)
		{

			  generatedTile.add(e.getText());

		}	

		for(int v=0;v<generatedTile.size();v++)
		{
			/*if(generatedTile.get(v).contains(driver.findElement(By.xpath("//div[@id='arsViewExistingAccess']")).getText()))
			{
				String viewEx=driver.findElement(By.xpath("//div[@id='arsViewExistingAccess']")).getText();
				generatedTile.remove(v);
				generatedTile.add(viewEx.replace(viewEx, "View Existing Access"));
			}*/
			if(generatedTile.get(v).contains("View Existing"+"\n"+"Access")) {
				generatedTile.remove(v);
				generatedTile.add("View Existing Access");
			}
				
		}

		if(TileNames.split("&").length==tilesNameEle.size())
		{
	        for(String ob : definedTile)
	        {       	
	        	for(String ob2 : generatedTile)
	        	{
	        		if(ob.equalsIgnoreCase(ob2))
	        		{
	        			System.out.println("Matching Tile: " +ob);
	        			val=val+1;
	        		}
	        	}
	        }

		}
		else
		{
			System.out.println("Tiles Count Not Matches");
			return false;
		}
		if(val==TileNames.split("&").length)
		{
			return true;
		}
		return false;		
	}
	
	public void clickOnTasksandGotoPendingTasks()
	{
		wait.until(ExpectedConditions.visibilityOf(taskHeaderLeftHandPanel));
		taskHeaderLeftHandPanel.click();
		pendingTaskLeftHandPanel.click();
	}
	
	public void clickOnTasksandGotoCompletedTasks()
	{
		wait.until(ExpectedConditions.visibilityOf(taskHeaderLeftHandPanel));
		taskHeaderLeftHandPanel.click();
		wait.until(ExpectedConditions.elementToBeClickable(completedTaskLeftHandPanel));
		completedTaskLeftHandPanel.click();
	}
}