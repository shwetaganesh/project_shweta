package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class Mass_Mitigation_Test  extends TestBase {
	
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 20, 1);
	String password = "password";

	@Test
	public void massMitigationTest() throws InterruptedException, IOException
	{
		logger = extent.createTest("testing the mass mitigation scenario");
		LaunchPage launch = new LaunchPage(driver);
		//*** Login to application with Requester***
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.clickOnSOD();
		SODViolationsPage sodPage = new SODViolationsPage(driver);
		ArrayList<String> selectAccounts = sodPage.searchForRiskAndSelectFirstThreeRisks("O005");
		sodPage.moveRiskToAccepted();
		sodPage.selectMitigatingControl();
		sodPage.validateMitigatingControlAssignment(selectAccounts);
		home.logoff();
	}

}
