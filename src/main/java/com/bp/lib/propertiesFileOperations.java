package com.bp.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class propertiesFileOperations  {
	
	
	public String generateUserName() {
		
		String name = "Auto_Test_User_";
		int aNumber = 0; 
		aNumber = (int)((Math.random() * 900)+100); 
		String userName = name+aNumber;
		System.out.println(userName);
		return userName;
		
	}
	public void writeUserName(String uname) throws IOException {
		try {
			Properties props = new Properties();
			props.setProperty("userName1", uname);
			File f = new File(".\\Test Data\\endUserData.properties");
			OutputStream out = new FileOutputStream( f );
			props.store(out, "Properties file containing end user data");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("writing into property file failed");
		}
	}
	
	public String readUserName() throws IOException {
		String uname = null;
		try {
			FileReader reader = new FileReader(".\\Test Data\\endUserData.properties");
			Properties p = new Properties();
			p.load(reader);
			uname = p.getProperty("userName1");
			System.out.println(uname);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uname;
	}
	
	public void writeRequestNumber(String requestNumber)
	{
		try {
			Properties props = new Properties();
			props.setProperty("Request_Number", requestNumber);
			File f = new File(".\\Test Data\\endUserData.properties");
			OutputStream out = new FileOutputStream( f );
			props.store(out, "Properties file containing end user data");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("writing into property file failed");
		}
	}
	
	public String readRequestNumber()
	{
		String requestNumber = null;
		try {
			FileReader reader = new FileReader(".\\Test Data\\endUserData.properties");
			Properties p = new Properties();
			p.load(reader);
			requestNumber = p.getProperty("Request_Number");
			System.out.println(requestNumber);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestNumber;
	}

	public void writeMitigationControlDetails(String riskCode,String account,String mitigatingControl)
	{
		try {
			Properties props = new Properties();
			props.setProperty("riskCode", riskCode);
			props.setProperty("account", account);
			props.setProperty("mitigatingControl", mitigatingControl);
			File f = new File(".\\Test Data\\mitigationControlDetails.properties");
			OutputStream out = new FileOutputStream( f );
			props.store(out, "Properties file containing mitigating control details");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("writing into property file failed");
		}
	}
	
	public String readAccountCode()
	{
		String account = null;
		try {
			FileReader reader = new FileReader(".\\Test Data\\mitigationControlDetails.properties");
			Properties p = new Properties();
			p.load(reader);
			account = p.getProperty("account");
			System.out.println(account);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public String readRiskCode()
	{
		String riskCode = null;
		try {
			FileReader reader = new FileReader(".\\Test Data\\mitigationControlDetails.properties");
			Properties p = new Properties();
			p.load(reader);
			riskCode = p.getProperty("riskCode");
			System.out.println(riskCode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return riskCode;
	}
	
	public String readAnalyticsName(String analyticsName)
	{
		String name = null ;
		{
			try{
				FileReader reader = new FileReader(".\\Test Data\\createReportAnalytics.properties");
				Properties p = new Properties();
				p.load(reader);
				name = p.getProperty(analyticsName);
				System.out.println(name);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return name;
		}
	}
	public String readDescription()
	{
		String desc = null ;
		{
			try{
				FileReader reader = new FileReader(".\\Test Data\\createReportAnalytics.properties");
				Properties p = new Properties();
				p.load(reader);
				desc = p.getProperty("description");
				System.out.println(desc);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return desc;
		}
	}
	
	public String readQuery(String queryName)
	{
		String query = null;
		{
			try{
				FileReader reader = new FileReader(".\\Test Data\\createReportAnalytics.properties");
				Properties p = new Properties();
				p.load(reader);
				query = p.getProperty(queryName);
				System.out.println(query);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return query;
		}
	}
}
