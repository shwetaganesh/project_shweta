package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Existing_Mitigated_SOD_Risk_Test_ASP extends TestBase {
	
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 22, 1);
	String  end_user;
	String password = "password";
	public String requestNumber;

	// test method to check the status of risk as Mitigated 
		@Test
		public void verifyMitigatedStatusForF001() throws IOException, InterruptedException
		{
			logger = extent.createTest("validate existing mitigated risk for application specific roles");
			requestNumber = userObject.readRequestNumber();
			LaunchPage launch = new LaunchPage(driver);
			//*** Login to application as sod approver ***
			launch.login(sodApprover, password);
			HomePage home = new HomePage(driver);
			home.openApprovalInbox();
			ApprovalInboxPage approve = new ApprovalInboxPage(driver);
			// search with the requestID
			approve.searchRequestNumber(requestNumber);
			approve.clickOnHeaderToAddSecondMC("PRKCLNT100");
			approve.getSnapshotOfMitigatedRisk("MCF001");
			TestBase.scrollUp(driver);
			approve.clickOnHeaderToAddSecondMC("PRKCLNT100");
			TestBase.scrollUp(driver);
			approve.scrollDownToFirstMCHeader("PRKCLNT100");
			Thread.sleep(2000);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			logger.pass("SOD status : Mitigated for F001", MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
			home.logoff();
		}
}
