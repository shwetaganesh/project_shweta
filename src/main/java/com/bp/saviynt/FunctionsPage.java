package com.bp.saviynt;

import java.util.List;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;

public class FunctionsPage extends TestBase {
	
	WebDriverWait wait;
	
	@FindBy(xpath = "//input[@id='dtsearch_functionListDT']")
	private WebElement functionSearchBox;
	
	@FindBy(xpath = "//h3[contains(text(),'Functions')]")
	private WebElement functionsPageHeader;
	
	@FindBy(xpath = "//button[contains(text(),'Advanced')]")
	private WebElement advancedSearchButton;
	
	@FindBy(xpath = "//*[contains(text(),'Advanced Search')]")
	private WebElement advancedSearchHeader;
	
	@FindBy(xpath ="//input[@id='name']")
	private WebElement nameTextBox;
	
	@FindBy(xpath = "//div[contains(@id,'RULESET')]/a")
	private WebElement rulesetDropDown;
	
	@FindBy(xpath = "//div[contains(text(),'PXECLNT100')]")
	private WebElement selectRuleSet;
	
	@FindBy(xpath = "//button[@class='btn green' and @id='dosearch']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//a[contains(text(),'Object')]")
	private WebElement ObjectTab;
	
	@FindBy(xpath = "//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']")
	private WebElement objectsTable;
	
	@FindBy(xpath = "(//div[contains(@id,'tcode')]/a)[2]")  //span[contains(text(),'Select Objects')]")
	private WebElement tcodeSearchBox;
	
	@FindBy(xpath = "//div[contains(@id,'accessobject')]//a")
	private WebElement objectSearchbox;
	
	@FindBy(xpath = "//div[contains(@id,'field')]//a")
	private WebElement searchField;
	
	@FindBy(xpath = "//button[@onclick='clearadvForm()']")
	private WebElement cancelButton;
	
	public FunctionsPage(WebDriver ldriver)
	{
		driver = ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}
	
	//search for the function name
	public void advancedSearch(String functionName)
	{
		wait.until(ExpectedConditions.visibilityOf(functionSearchBox));
		advancedSearchButton.click();
		wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
		nameTextBox.sendKeys(functionName);
		rulesetDropDown.click();
		wait.until(ExpectedConditions.visibilityOf(selectRuleSet));
		selectRuleSet.click();
		searchButton.click();
		wait.until(ExpectedConditions.visibilityOf(functionSearchBox));
	}
	
	public void clickOnFunction(String functionName)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//tr//td//a[contains(text(),'"+functionName+"')]")));
		driver.findElement(By.xpath("//tbody[@role='alert']//tr//td//a[contains(text(),'"+functionName+"')]")).click();
		wait.until(ExpectedConditions.visibilityOf(functionsPageHeader));
	}
	
	public void clickOnObjectTab()
	{
		wait.until(ExpectedConditions.visibilityOf(ObjectTab));
		ObjectTab.click();
		wait.until(ExpectedConditions.visibilityOf(objectsTable));
	}
	
	public boolean compareData(List<String> dataFromFunction1) throws InterruptedException
	{
		boolean status = false;
		
		for (int i = 1; i < dataFromFunction1.size(); i=i+5) 
		{
			
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.visibilityOf(advancedSearchButton));
			advancedSearchButton.click();
			wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
			
			String tcode = dataFromFunction1.get(i);
			System.out.println(tcode);
			tcodeSearchBox.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));

			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(tcode);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			i = i + 4;
			
			String object = dataFromFunction1.get(i);
			if(object.isEmpty())
			{
				
				i=i+2;
				cancelButton.click();
				continue;
			}
				
			System.out.println(object);
			wait.until(ExpectedConditions.visibilityOf(objectSearchbox));
			objectSearchbox.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(object);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			i = i + 1;
			
			String field = dataFromFunction1.get(i);
			System.out.println(field);
			wait.until(ExpectedConditions.visibilityOf(searchField));
			searchField.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(field);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);

			searchButton.click();
			Thread.sleep(3000);
			/* get the value from SOD table
			 * remove characters within (....) 
			 * obtain min and max value
			 */
			i=i+1;
			String value  = dataFromFunction1.get(i);
			String minMaxValue = value.replaceAll(" *\\(.+?\\)","");
			System.out.println(minMaxValue);
			int midpt = (minMaxValue.length())/2;
			String min = minMaxValue.substring(0, midpt);
			String max = minMaxValue.substring(midpt+1);
			System.out.println(min);
			System.out.println(max);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr")));
			List<WebElement> objectDetailsTable = driver
					.findElements(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr"));
			for (int index = 1; index <= objectDetailsTable.size(); index++) {
				int colIndex = 3;
				while (colIndex <= 8) {
					if ((driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["+index+"]//td["+colIndex+"]"))).getText().equalsIgnoreCase(tcode)) {
						colIndex = colIndex + 1;
						if ((driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["+index+"]//td["+colIndex+"]"))).getText().equalsIgnoreCase(object)) {
							colIndex = colIndex + 1;
							if ((driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["+index+"]//td["+colIndex+"]"))).getText().equalsIgnoreCase(field)) {
								colIndex = colIndex + 2;
								String minValue = driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["+index+"]//td["+colIndex+"]")).getText();
								colIndex = colIndex + 1;
								String maxValue = driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["+index+"]//td["+colIndex+"]")).getText();
								System.out.println(minValue + " and " + maxValue);
								/*
								 * compare values in function table with that of SOD table
								 */
								status = this.compareMinMaxValues(minValue,maxValue, min, max);
							} else
								break;
						} else
							break;
					} else
						break;

				}
			}

		}
		return status;
	}
	
	public  boolean compareMinMaxValues(String minValue,String maxValue,String min, String max)
	{
		char blank = ' ';
		String blank1 = Character.toString(blank);
		boolean status=false;
		if(min.contains("*") && max.contains("*"))
		{
			status = true;
			System.out.println("min and max values contain *");
		}
		else if(min.contains(blank1) && max.contains(blank1))
		{
			status = true;
			System.out.println("min max values are blank");
		}
		else if(StringUtils.isNumeric(minValue) && StringUtils.isNumeric(maxValue))
		{
			// here range is min and max values in the object table of admin.. both values inclusive.
			Range<Integer> myRange = Range.between(Integer.parseInt(minValue), Integer.parseInt(maxValue));
			if (myRange.contains(Integer.parseInt(min)) && myRange.contains(Integer.parseInt(max))){
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
		else if (min.equalsIgnoreCase(minValue) && max.equalsIgnoreCase(maxValue))
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
	}
