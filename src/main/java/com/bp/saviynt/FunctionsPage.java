package com.bp.saviynt;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
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
	
	public List<String> getObjectTableData() throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'autogen')]/a")));
		driver.findElement(By.xpath("//*[contains(@id,'autogen')]/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'100')]"))).click();
		Thread.sleep(3000);
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='myDataTableFuntionsObjects']/tbody/tr"));
		System.out.println(tableRows.size());
		ArrayList tableData = new ArrayList();
		String temp;
		for(int i=1;i<=tableRows.size();i++)
		{
			for(int j=2;j<=8;j++)
			{
				temp = driver.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']/tbody//tr["+i+"]//td["+j+"]")).getText();
				tableData.add(temp);
			}
		}
		System.out.println(tableData);
		
		List newArray = ListUtils.partition(tableData, 7);
		System.out.println(newArray);
		
		return newArray;
	}
	
	public void compareData(List<String> dataFromFunction1) throws InterruptedException
	{
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//*[contains(@id,'autogen')]/a")));
		 * driver.findElement(By.xpath("//*[contains(@id,'autogen')]/a")).click();
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[contains(text(),'100')]"))).click(); Thread.sleep(3000);
		 * List<WebElement> tableRows = driver.findElements(By.xpath(
		 * "//table[@id='myDataTableFuntionsObjects']/tbody/tr"));
		 * System.out.println(tableRows.size()); String temp; ArrayList<String>
		 * singleRow = new ArrayList<String>(); for(int i=1;i<=tableRows.size();i++) {
		 * for(int j=2;j<=8;j++) { temp = driver.findElement(By.xpath(
		 * "//table[@id='myDataTableFuntionsObjects']/tbody//tr["+i+"]//td["+j+"]")).
		 * getText(); singleRow.add(temp); } for(int k =0;k<singleRow.size();k++) {
		 * String value = singleRow.get(k); System.out.println(value); } }
		 */
		for (int i = 1; i < dataFromFunction1.size(); i++) 
		{
			wait.until(ExpectedConditions.visibilityOf(advancedSearchButton));
			advancedSearchButton.click();
			wait.until(ExpectedConditions.visibilityOf(advancedSearchHeader));
			
			String tcode = dataFromFunction1.get(i);
			System.out.println(tcode);
			tcodeSearchBox.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));

			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(tcode);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			i = i + 4;
			
			String object = dataFromFunction1.get(i);
			System.out.println(object);
			wait.until(ExpectedConditions.visibilityOf(objectSearchbox));
			objectSearchbox.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(object);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);
			i = i + 1;
			
			String field = dataFromFunction1.get(i);
			System.out.println(field);
			wait.until(ExpectedConditions.visibilityOf(searchField));
			searchField.click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")));
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(field);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='select2-drop']//div[1]//input[1]")).sendKeys(Keys.ENTER);

			//TestBase.scrollDownToElement(driver, searchButton);
			searchButton.click();
			
			Thread.sleep(2000);

			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr")));
			List<WebElement> objectDetailsTable = driver
					.findElements(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr"));
			for (int index = 1; index < objectDetailsTable.size(); index++) {
				int colIndex = 3;
				while (colIndex <= 8) {
					if ((driver
							.findElement(By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr["
									+ index + "]//td[" + colIndex + "]"))).getText() == tcode) {
						colIndex = colIndex + 1;
						if ((driver.findElement(
								By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert']//tr[" + index
										+ "]//td[" + colIndex + "]"))).getText() == object) {
							colIndex = colIndex + 1;
							if ((driver.findElement(
									By.xpath("//table[@id='myDataTableFuntionsObjects']//tbody[@role='alert'])//tr["
											+ index + "]//td[" + colIndex + "]"))).getText() == field) {
								colIndex = colIndex + 2;
								String minValue = driver.findElement(
										By.xpath("//table[@id='myDataTableFuntionsObjects']/tbody[@role='alert']/tr["
												+ index + "]/td[" + colIndex + "]"))
										.getText();
								colIndex = colIndex + 1;
								String maxValue = driver.findElement(
										By.xpath("//table[@id='myDataTableFuntionsObjects']/tbody[@role='alert']/tr["
												+ index + "]/td[" + colIndex + "]"))
										.getText();
								System.out.println(minValue + " and" + maxValue);
							} else
								break;
						} else
							break;
					} else
						break;

				}
			}

		}
	}
	}
