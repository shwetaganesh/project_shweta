package com.bp.lib;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader 
{
	File src;
	
	FileInputStream fis;
	
	Properties pro;
	
	public ConfigReader()
	{
		try {
			src=new File(".\\Config\\Configuration.property");
			
			fis=new FileInputStream(src);
			
			pro=new Properties();
			
			pro.load(fis);
		} 
		catch (Exception e) 
		{
			
			System.out.println("Exception occurs in ConfigReader amd the exception is "+e.getMessage());
			
		}
	}
	
	public String getValue(String key)
	{
		return pro.getProperty(key);
	}
}