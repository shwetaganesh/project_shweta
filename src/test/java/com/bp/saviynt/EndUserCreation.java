package com.bp.saviynt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.Test;


import com.bp.lib.ExcelOperations;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class EndUserCreation  extends TestBase {
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\EndUserData.xlsx");
	UsernameGeneration uobject = new UsernameGeneration();
	String username1;
	String firstName = excel.getData(0,1,1);
	String lastName = excel.getData(0,1,2);
	String managerId = excel.getData(0,1,3);
	

	@Test
	public void createEndUser() throws Exception {
		
		username1=uobject.generateUserName();
		System.out.println("random user generated :"+ username1);
		/*String property16 = username1+"@saviynt.com";
		LaunchPage launch = new LaunchPage(driver);
		String admin = "TSTTEN10";
		launch.login(admin, "password");
		HomePage home = new HomePage(driver);
		home.openAdminTab();
		AdminPage adminPage = new AdminPage(driver);
		adminPage.clickOnUsersAndCreateUsers(username1,firstName,lastName,managerId);
		adminPage.addAttributes(property16, "X|1|GMTUK|GB",username1);
		home.logoff();*/
		uobject.writeUserName(username1);
		String uname=uobject.readUserName();
		System.out.println(uname);
		
	}
	

}

