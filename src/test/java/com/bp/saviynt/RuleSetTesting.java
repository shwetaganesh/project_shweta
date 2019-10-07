package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.bp.lib.ExcelOperations;
import com.bp.lib.ReadDataFromExcel;
import com.bp.testbase.TestBase;

public class RuleSetTesting extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Salesforce - Test Scenarios_V3.xlsx");
	ExcelOperations excel1 = new ExcelOperations(".\\Test Data\\ROLES.xlsx");
	ReadDataFromExcel readExcel = new ReadDataFromExcel();
	String sodUserID,password = "password";
	
	@Test
	public void SODLoginAndRulesetComparisonUsingSimulation() throws InterruptedException, IOException
	{
		String adminID = "TSTTEN10";
		logger = extent.createTest("Rule set comparison");
		SoftAssert softassert = new SoftAssert();
		sodUserID= excel.getData(0, 10, 12);
		
		LaunchPage launch = new LaunchPage(driver);
		//  Login to application with sod approver 
		launch.login(sodUserID, password);
		HomePage home = new HomePage(driver);
		//  goto SOD tab
		home.clickOnSOD();
		
		SODViolationsPage sodPage = new SODViolationsPage(driver);
		// filter recorder based on ruleset.
		sodPage.clickOnRulesetFilterDropDownAndFilter("PXECLNT100");
		//for(int i=1;i<3;i++)
		//{
			//String user = excel1.getData(0, i, 0);
			//System.out.println(user);
			sodPage.searchForUserAndClickOnRiskCode("ZTEST_F017");
		
			SODViolationDetailsPage detailsPage = new SODViolationDetailsPage(driver);
			String firstFunctionName = detailsPage.getFirstFunctionName();
			detailsPage.searchAccountNameForTable1("ZTEST_F017");
			ArrayList<String> dataFromFunction1 =  detailsPage.fetchTableDataFromFunction1();
			
			/*String secondFunctionName = detailsPage.getSecondFunctionName();
			detailsPage.searchAccountNameForTable2("ZTEST_F017");
			List dataFromFunction2 = detailsPage.fetchTableDataFromFunction2();*/
		
			String parentHandle = driver.getWindowHandle();
			driver = new ChromeDriver();
			for (String winHandle : driver.getWindowHandles()) {
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle
			}
			driver.get("https://ssm-dev.bp.saviyntcloud.com/ECM/");
			driver.manage().window().maximize();
			LaunchPage launchobj = new LaunchPage(driver);
			launchobj.login(adminID, password);
			HomePage homepage = new HomePage(driver);
			homepage.clickOnSOD();
			SODViolationsPage sodPageObject = new SODViolationsPage(driver);
			sodPageObject.gotoRulesetandClickOnFunction();
			
			FunctionsPage function = new FunctionsPage(driver);
			function.advancedSearch(firstFunctionName);
			function.clickOnFunction(firstFunctionName);
			function.clickOnObjectTab();
			//List<String> functionData = function.getObjectTableData();
			
			function.compareData(dataFromFunction1);
						
		}
			
        // Switching the control back to parent window
        //driver.switchTo().window(parentWinHandle);
	}

