package com.bp.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class UsernameGeneration  {
	
	
	public String generateUserName() {
		
		String name = "Auto_Test1_";
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
			File f = new File(".\\Test Data\\endUserName.properties");
			OutputStream out = new FileOutputStream( f );
			props.store(out, "Properties file containing end user name");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("writing into property file failed");
		}
	}
	
	public String readUserName() throws IOException {
		String uname = null;
		try {
			FileReader reader = new FileReader(".\\Test Data\\endUserName.properties");
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

}
