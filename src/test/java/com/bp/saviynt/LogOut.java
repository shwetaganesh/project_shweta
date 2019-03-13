package com.bp.saviynt;

import org.testng.annotations.Test;

import com.bp.testbase.TestBase;

public class LogOut  extends TestBase{
	@Test
	public void test() {
		
	
	LaunchPage launch = new LaunchPage(driver);
	//*** Login to application with Requester***
	launch.login("RGTSM01","password1");
	HomePage home = new HomePage(driver);
	home.logoff();

}
}
