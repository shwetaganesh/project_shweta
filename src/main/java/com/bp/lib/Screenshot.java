package com.bp.lib;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot
{	
	public static String captureScreenShot(WebDriver driver)
	{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		String fileWithPath=".\\Reports\\Screenshots\\"+System.currentTimeMillis()+".png";
		
		File DestFile=new File(fileWithPath);
		
		try 
		{
			FileUtils.copyFile(SrcFile, DestFile);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return fileWithPath;
	
	}
}
