package com.bp.saviynt;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.testbase.TestBase;

public class UMT_L2Manager_ViewRequestHistory extends TestBase{
	
	String user1,user2, role, L2Manager = "L2TEST13", admin="TSTTEN10";
	String password = "password";
	
	
	@Test
	public void checkRequestHistory() throws InterruptedException, IOException
	{
		logger = extent.createTest("Test to check request history data, so that it contains requests only for L2TEST11 and L2TEST12");
		
		//L2 manager login
		LaunchPage launch = new LaunchPage(driver);
		launch.login(L2Manager, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openRequestHistory();
		
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		boolean result = historyPage.checkRequestedForUser();
		if(result)
			logger.pass("Request history contains requests for L2TEST11 and L2TEST12 only");
		else
			logger.fail("request for users other than L2TEST11 and L2TEST12 also exist in request history");
		
	}

}
