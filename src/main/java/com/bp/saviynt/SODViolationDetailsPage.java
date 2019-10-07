package com.bp.saviynt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class SODViolationDetailsPage extends TestBase {
	
	
	WebDriverWait wait;

	@FindBy(xpath = "(//div[@class='caption']//a)[1]")
	private WebElement functionHeader_1;
	
	@FindBy(xpath = "(//div[@class='caption']//a)[2]")
	private WebElement functionHeader_2;
	
	@FindBy(xpath = "(//div[@class='caption']//a)[3]")
	private WebElement functionHeader_3;
	
	@FindBy(xpath = "//*[@id='dtsearch_SODMappedDetail0']")
	private WebElement searchBox1;
	
	@FindBy(xpath = "//*[@id='dtsearch_SODMappedDetail1']")
	private WebElement searchBox2;
	
	public SODViolationDetailsPage(WebDriver ldriver) {
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
	}

	public String getFirstFunctionName() {
		wait.until(ExpectedConditions.visibilityOf(functionHeader_1));
		String header = functionHeader_1.getText();
		String functionName = header.replaceAll(" ", "").replaceAll("Summary", "").replaceAll("Function:", "")
				.replace("()", "");
		System.out.println(header);
		System.out.println(functionName);
		return functionName;
	}
	
	public String getSecondFunctionName() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(functionHeader_2));
		TestBase.scrollDownToElement(driver, functionHeader_2);
		String header = functionHeader_2.getText();
		String functionName = header.replaceAll(" ", "").replaceAll("Summary", "").replaceAll("Function:", "")
				.replace("()", "");
		System.out.println(header);
		System.out.println(functionName);
		return functionName;
	}
	
	
	// filter the table only for the required user in first table. 
	public void searchAccountNameForTable1(String user) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(searchBox1));
		driver.findElement(By.xpath("(//span[@class='switch-right'])[1]")).click();
		Thread.sleep(2000);
		searchBox1.sendKeys(user);
		searchBox1.sendKeys(Keys.ENTER);
		
	}
	
	// filter the table only for the required user in second table. 
		public void searchAccountNameForTable2(String user) throws InterruptedException
		{
			wait.until(ExpectedConditions.visibilityOf(searchBox2));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='switch-right'])[2]")));
			WebElement toggle = driver.findElement(By.xpath("(//span[@class='switch-right'])[2]"));
			TestBase.javaScriptClickbyElement(driver, toggle);
			Thread.sleep(2000);
			searchBox2.sendKeys(user);
			searchBox2.sendKeys(Keys.ENTER);
			
		}
	
	public ArrayList<String> fetchTableDataFromFunction1() throws InterruptedException
	{
		//increase rows of table to 100
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'autogen')]/a")));
		driver.findElement(By.xpath("//*[contains(@id,'autogen')]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]"))).click();
		Thread.sleep(5000);
		// find the number of rows in the first table.
		List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='table table-striped table-hover table-bordered dataTable'])[1]/tbody/tr"));
		System.out.println(rows.size());
		ArrayList<String> tableData = new ArrayList();
		String temp;
		for(int i=1;i<=rows.size();i++)
		{
			for(int j=1;j<=11;j++)
			{
				temp = driver.findElement(By.xpath("(//tbody[@role='alert'])[1]//tr["+i+"]//td["+j+"]")).getText();
				tableData.add(temp);
			}
		}
		System.out.println(tableData);
		
		/*List newList = ListUtils.partition(tableData, 11);
	    System.out.println(newList);*/
				
	   
		
		return tableData;
		
	}
	
	public List fetchTableDataFromFunction2() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id,'autogen')]/a)[2]")));
		driver.findElement(By.xpath("(//*[contains(@id,'autogen')]/a)[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]")));
		driver.findElement(By.xpath("//div[contains(text(),'100')]")).click();
		
		Thread.sleep(5000);
		// find the number of rows in the first table.
		List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='table table-striped table-hover table-bordered dataTable'])[2]/tbody/tr"));
		System.out.println(rows.size());
		ArrayList tableData2 = new ArrayList();
		String temp;
		for(int i=1;i<=rows.size();i++)
		{
			for(int j=1;j<=11;j++)
			{
				temp = driver.findElement(By.xpath("(//tbody[@role='alert'])[2]//tr["+i+"]//td["+j+"]")).getText();
				tableData2.add(temp);
			}
		}
		System.out.println(tableData2);
		//ArrayList newArray = (ArrayList) tableData2.subList(0, 10);
		//System.out.println(newArray);
		List newArray = ListUtils.partition(tableData2, 11);
		System.out.println(newArray);
		
		
		return newArray;
		
	}
	}

