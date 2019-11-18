package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;
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
	public void SODLoginAndRulesetComparisonTest() throws InterruptedException, IOException
	{
		
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
		for(int i=61;i<=72;i++)
		{
			String user = excel1.getData(0, i, 0);
			System.out.println(user);
			String riskCode = sodPage.searchForUserAndClickOnRiskCode(user);
		
			SODViolationDetailsPage detailsPage = new SODViolationDetailsPage(driver);
			
			String firstFunctionName = detailsPage.getFirstFunctionName();
			 detailsPage.searchAccountNameForTable1(user);
			
			//ArrayList<String> dataFromFunction1 =  detailsPage.fetchTableDataFromFunction1(riskCode);
			 System.out.println("*** Validating object data for function : "+firstFunctionName+ " ***");
			 boolean result = detailsPage.DataComparisonInTable1();
			 if(result) {
				 System.out.println("all data in range");
			 	logger.pass("All values in range for the function: "+firstFunctionName+" in risk "+user);
			 }
			 else {
				 logger.fail("All values not in range for the function : "+firstFunctionName+" in risk "+user);
			 }
			
			 
			String secondFunctionName = detailsPage.getSecondFunctionName();
			detailsPage.searchAccountNameForTable2(user);
			System.out.println("*** Validating object data for function : "+secondFunctionName+ " ***");
			 result =detailsPage.DataComparisonInTable2();
			 if(result) {
				 System.out.println("all data in range");
			 	logger.pass("All values in range for the function: "+ secondFunctionName+" in risk "+user);
			 }
			 else {
				 logger.fail("All values not in range for the function : "+secondFunctionName+" in risk "+user);
			 }
			 TestBase.scrollUp(driver);
			detailsPage.clickOnSOD();
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 //ArrayList<String>dataFromFunction2 = detailsPage.fetchTableDataFromFunction2();
		
			/*String parentHandle = driver.getWindowHandle();
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
			
			System.out.println("*** Validating object data for function : "+firstFunctionName+ " ***");
			
			boolean status = function.compareData(dataFromFunction1);
			softassert.assertTrue(status, "All data validated");
			
			System.out.println("*** Validating object data for function : "+secondFunctionName+ " ***");
			
			sodPageObject.clickOnFunction();
			function.advancedSearch(secondFunctionName);
			function.clickOnFunction(secondFunctionName);
			function.clickOnObjectTab();
			
			status = function.compareData(dataFromFunction2);
			softassert.assertTrue(status, "All data validated");
			softassert.assertAll();
						
		}*/
			
        // Switching the control back to parent window
        //driver.switchTo().window(parentWinHandle);
	}
}
}

