package com.maven.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCase 
{
	@Test
	public void TC()
	{
		System.out.println("Hello Maven");
		
		System.setProperty("webdriver.chrome.driver",".\\src\\test\\resources\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		
		driver.get("https://ssm-preprod.bp.saviyntcloud.com/ECM");
		
		System.out.println(driver.getTitle());
		
		driver.quit();
	}
}