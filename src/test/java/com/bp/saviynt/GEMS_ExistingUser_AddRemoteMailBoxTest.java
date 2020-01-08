package com.bp.saviynt;

import java.io.IOException;

import org.testng.annotations.Test;

import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class GEMS_ExistingUser_AddRemoteMailBoxTest extends TestBase {
	
	
	String user ,admin="TSTTEN10",password="password";
	
	@Test(priority=1)
	public void addRemoteMailBoxTest() throws IOException
	{
		logger = extent.createTest("Test to add remote mail box to the user.");
		
		
		LaunchPage launch = new LaunchPage(driver);
		launch.login(admin, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.openAdminTab();
		
		AdminPage adminPage = new AdminPage(driver);
		adminPage.searchForUserAndClickOnUser(user);
		adminPage.clickOnOtherAttributesTab();
		adminPage.addCustomProperty65("RemoteUserMailBox");
		adminPage.clickOnUpdate();
		
	}

}
