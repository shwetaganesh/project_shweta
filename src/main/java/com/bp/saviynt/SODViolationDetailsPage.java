package com.bp.saviynt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
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
		Thread.sleep(3000);
		searchBox1.sendKeys(user);
		searchBox1.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		//increase rows of table to 100
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'autogen')]/a")));
		driver.findElement(By.xpath("//*[contains(@id,'autogen')]/a")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys("100");
		driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
				
		
	}
	
	// filter the table only for the required user in second table. 
		public void searchAccountNameForTable2(String user) throws InterruptedException
		{
			wait.until(ExpectedConditions.visibilityOf(searchBox2));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='switch-right'])[2]")));
			
			WebElement toggle = driver.findElement(By.xpath("(//span[@class='switch-right'])[2]"));
			TestBase.javaScriptClickbyElement(driver, toggle);
			Thread.sleep(3000);
			
			searchBox2.sendKeys(user);
			searchBox2.sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@id,'autogen')]/a)[2]")));
			driver.findElement(By.xpath("(//*[contains(@id,'autogen')]/a)[2]")).click();
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys("100");
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			
			//TestBase.javaScriptClickbyElement(driver, driver.findElement(By.xpath("//div[contains(text(),'100')]")));
			//driver.findElement(By.xpath("//div[contains(text(),'100')]")).click();
			
			Thread.sleep(5000);
			
		}
	
	//public ArrayList<String> fetchTableDataFromFunction1(String riskCode) throws InterruptedException
	public boolean DataComparisonInTable1() throws InterruptedException
		{
		
		Thread.sleep(3000);
		// find the number of rows in the first table.
		List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='table table-striped table-hover table-bordered dataTable'])[1]/tbody/tr"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='SODMappedDetail0']//tbody[@role='alert']//tr[1]//td[1][contains(text(),'ZTEST')]")));
		ArrayList<String> tableData = new ArrayList<String>();
		String temp;
		boolean status = false;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='SODMappedDetail0']")));
		System.out.println(rows.size());
		for(int i=1;i<=rows.size();i++)
		{
			/*for(int j=1;j<=11;j++)
			{
				temp = driver.findElement(By.xpath("//table[@id='SODMappedDetail0']//tbody[@role='alert']//tr["+i+"]//td["+j+"]")).getText();
				tableData.add(temp);
			}*/
			
			String values = driver.findElement(By.xpath("//table[@id='SODMappedDetail0']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText();
			if (values.isEmpty()) {
				continue;
				}
			else {
				System.out.println(values);
				temp = values;
				String rangeMinMaxValues = temp.split("[\\(\\)]")[1];
				System.out.println(rangeMinMaxValues);
				int midpt = (rangeMinMaxValues.length()) / 2;
				String rangeMin = rangeMinMaxValues.substring(0, midpt);
				String rangeMax = rangeMinMaxValues.substring(midpt + 1);
				System.out.println("Range min : " + rangeMin);
				System.out.println("Range max : " + rangeMax);

				String assingedMinMaxValues = values.replaceAll(" *\\(.+?\\)", "");
				midpt = (assingedMinMaxValues.length()) / 2;
				String assignedMin = assingedMinMaxValues.substring(0, midpt);
				String assignedMax = assingedMinMaxValues.substring(midpt + 1);
				System.out.println(assignedMin);
				System.out.println(assignedMax);

				status = this.compareMinMaxValues(rangeMin, rangeMax, assignedMin, assignedMax);
			} 
			}

		
		//System.out.println(tableData);
		
		//return tableData;
		return status;
	}
	
	//public ArrayList<String> fetchTableDataFromFunction2() throws InterruptedException
	public boolean DataComparisonInTable2() throws InterruptedException
	{
		
		Thread.sleep(3000);
		// find the number of rows in the second table.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='SODMappedDetail1']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='SODMappedDetail1']//tbody//tr[1]//td[1][contains(text(),'ZTEST')]")));
		List<WebElement> rows = driver.findElements(By.xpath("(//table[@class='table table-striped table-hover table-bordered dataTable'])[2]/tbody/tr"));
		System.out.println(rows.size());
		ArrayList<String>tableData2 = new ArrayList<String>();
		String temp;
		boolean status = false;
		for(int i=1;i<=rows.size();i++)
		{
			/*for(int j=1;j<=11;j++)
			{
				temp = driver.findElement(By.xpath("//table[@id='SODMappedDetail1']//tbody[@role='alert']//tr["+i+"]//td["+j+"]")).getText();
				tableData2.add(temp);
			}*/
			String values = driver.findElement(By.xpath("//table[@id='SODMappedDetail1']//tbody[@role='alert']//tr["+i+"]//td[8]")).getText();
			if (values.isEmpty()) {
				continue;
			}
			else {
				System.out.println(values);
				temp = values;
				String rangeMinMaxValues = temp.split("[\\(\\)]")[1];
				System.out.println(rangeMinMaxValues);
				int midpt = (rangeMinMaxValues.length()) / 2;
				String rangeMin = rangeMinMaxValues.substring(0, midpt);
				String rangeMax = rangeMinMaxValues.substring(midpt + 1);
				System.out.println("Range min : " + rangeMin);
				System.out.println("Range max : " + rangeMax);

				String assingedMinMaxValues = values.replaceAll(" *\\(.+?\\)", "");
				midpt = (assingedMinMaxValues.length()) / 2;
				String assignedMin = assingedMinMaxValues.substring(0, midpt);
				String assignedMax = assingedMinMaxValues.substring(midpt + 1);
				System.out.println(assignedMin);
				System.out.println(assignedMax);

				status = this.compareMinMaxValues(rangeMin, rangeMax, assignedMin, assignedMax);

			} 
		}
		//System.out.println(tableData2);
		
		//return tableData2;
		return status;
	}
	
	
	public  boolean compareMinMaxValues(String rangeMin,String rangeMax,String assignedMin, String assignedMax)
	{
		char blank = ' ';
		String blank1 = Character.toString(blank);
		boolean status=false;
		if(assignedMin.contains("*") && assignedMax.contains("*"))
		{
			status = true;
			System.out.println("min and max values contain *");
		}
		else if(assignedMin.contains(blank1) && assignedMax.contains(blank1))
		{
			status = true;
			System.out.println("min max values are blank");
		}
		else if(StringUtils.isNumeric(rangeMin) && StringUtils.isNumeric(rangeMax))
		{
			// here range is min and max values in the object table of admin.. both values inclusive.
			Range<Integer> myRange = Range.between(Integer.parseInt(rangeMin), Integer.parseInt(rangeMax));
			if (myRange.contains(Integer.parseInt(assignedMin)) && myRange.contains(Integer.parseInt(assignedMax))){
				{
					status = true;
					System.out.println("min and max are numbers and  values match with the table");
					}
			}
			else
			{
				status = false;
				System.out.println("min and max are numbers but donot match with the table");
			}

		}
		else if (rangeMin.equalsIgnoreCase(assignedMin) && rangeMax.equalsIgnoreCase(assignedMax))
		{
			status = true;
			System.out.println("min and max values match with the table");
		}
		else {
			status = false;
			System.out.println("min and max values donot match with the table");
		}
		return status;
	}
	
	public void clickOnSOD()
	{
		driver.findElement(By.xpath("(//a[@href='/ECM/soddetail/sodmapped'])[1]")).click();
	}
	
}
