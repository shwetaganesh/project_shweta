package com.bp.saviynt;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.lib.ExcelOperations;
import com.bp.testbase.TestBase;

public class SODSimulationPage  extends TestBase{

	ExcelOperations excel = new ExcelOperations(".\\Test Data\\ROLES.xlsx");
	
	@FindBy(xpath = "//h3[contains(text(),'Simulation')]")
	private WebElement simulationHeader;
	
	@FindBy(xpath = "//input[@id='dtsearch_simulationList']")
	private WebElement searchSimulationNameTextBox;
	
	@FindBy(xpath = "//a[contains(text(),'Action')]")
	private WebElement actionDropDown;
	
	@FindBy(xpath = "//a[@href='/ECM/simulationDetail/detail/39']")
	private WebElement view;
	
	@FindBy(xpath = "//input[@id='dtsearch_myDataTable']")
	private WebElement searchRoleTextBox;
	
	@FindBy(xpath = "//a[contains(text(),'Object') and @href=\"/ECM/simulationDetail/objectdetail/83\"]")
	private WebElement objectTab;
	
	@FindBy(xpath = "//table[@id='objectdetail']")
	private WebElement objectTable;
	
	@FindBy(xpath = "(//a[contains(text(),'Tcode')])[1]")
	private WebElement tcodeTab;
	
	@FindBy(xpath = "//table[@id='myDataTable2']//tbody[@role='alert']//tr[1]")
	private WebElement firstRowInTcodeTable;
	
	WebDriverWait wait;

	

	public SODSimulationPage(WebDriver ldriver) {
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}
	
	public void searchSimulationName(String name)
	{
		wait.until(ExpectedConditions.visibilityOf(searchSimulationNameTextBox));
		searchSimulationNameTextBox.sendKeys(name);
		searchSimulationNameTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[2][contains(text(),'"+name+"')]")));
	}
	
	public void clickOnActionandGotoView()
	{
		actionDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(view));
		view.click();
		
	}
	
	public void searchRoleandClickOnRole(String role) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(searchRoleTextBox));
		searchRoleTextBox.sendKeys(role);
		searchRoleTextBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+role+"')]")));
		driver.findElement(By.xpath("//tbody[@role='alert']//tr[1]//td[1]//a[contains(text(),'"+role+"')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Role  Detail')]")));
	}
	
	public void clickOnObject()
	{
		wait.until(ExpectedConditions.visibilityOf(objectTab));
		objectTab.click();
	}
	
	public void clickOnTcode() throws InterruptedException
	{
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(tcodeTab));
		tcodeTab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='dtsearch_myDataTable2']")));
		//wait.until(ExpectedConditions.visibilityOf(firstRowInTcodeTable));
		
	}
	
	public void getDataFromTable()
	{
		wait.until(ExpectedConditions.visibilityOf(objectTable));
		
	}
	
	public ArrayList getTcodeValuesFromTable() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[contains(@id,'autogen')]/a)[10]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]")));
		driver.findElement(By.xpath("//div[contains(text(),'100')]")).click();
		Thread.sleep(2000);
		List<WebElement> tcodecolumn = driver.findElements(By.xpath("//table[@id='myDataTable2']/tbody[@role='alert']/tr"));
		System.out.println(tcodecolumn.size());
		ArrayList arlist = new ArrayList();
		String temp;
		for(int i=1;i<=tcodecolumn.size();i++)
		{
			temp=driver.findElement(By.xpath("//table[@id='myDataTable2']//tbody[@role='alert']//tr["+i+"]//td[1]")).getText();
			arlist.add(temp);		
		}
		System.out.println(arlist);
		return arlist;
	}
	
	public void clearRoleSearch()
	{
		searchRoleTextBox.clear();
	}
	
	public boolean compareTcodes(ArrayList<String> tcode, ArrayList<String> tcodeFromExcel)
	{
		boolean verify_tcodes=true;
		boolean all_found=true;
		for(String validate_tcode:tcodeFromExcel)
		{
			for(String temp:tcode)
			{
				if(temp.contains(validate_tcode))
				{
					verify_tcodes=true;
					System.out.println(validate_tcode+" is found");
					break;
				}
				else
					verify_tcodes=false;
			}
			if(!verify_tcodes)
			{
				System.out.println(validate_tcode+" is missing");

				all_found=false;
			}
		}
		return all_found;
		
		
	}
	
}
