/*"a) SOD details in the access request show the SOD status as 'Mitigated'
b) SOD details show the mitigating control assigned, but with blank start and end dates"
*/
package com.bp.saviynt;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Existing_Mitigated_SOD_Risk_Test_ER  extends TestBase{

	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApproverForR4_R5 = excel.getData(0, 19, 1);
	String sodApproverForR8_R9 = excel.getData(0, 20, 1);
	String newApprover = excel.getData(0, 21, 1);
	String  end_user;
	String password = "password";
	public String requestNumber;
	
	
	// test method to check the status of risk as Mitigated 
	@Test
	public void verifyMitigatedStatusForP206() throws IOException, InterruptedException
	{
		logger = extent.createTest("validate existing mitigated sod risk for enterprise roles");
		requestNumber = userObject.readRequestNumber();
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application as sod approver for r4,r5***
		launch.login(sodApproverForR4_R5, password);
		HomePage home = new HomePage(driver);
		home.openApprovalInbox();
		ApprovalInboxPage approve = new ApprovalInboxPage(driver);
		// search with the requestID
		approve.searchRequestNumber(requestNumber);
		approve.clickOnHeaderToAddSecondMC("PROCLNT100");
		approve.getSnapshotOfMitigatedRisk("MCP206");
		TestBase.scrollUp(driver);
		approve.clickOnHeaderToAddSecondMC("PROCLNT100");
		TestBase.scrollUp(driver);
		approve.scrollDownToFirstMCHeader("PROCLNT100");
		Thread.sleep(2000);
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.pass("SOD status : Mitigated for P206", MediaEntityBuilder
				.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		home.logoff();
	}
}
