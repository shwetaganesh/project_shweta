package com.bp.saviynt;

import com.bp.lib.ExcelOperations;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Existing_Mitigated_SOD_Risk_Test_ASP extends TestBase {
	
	
	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String sodApprover = excel.getData(0, 22, 1);
	String  end_user;
	String password = "password";
	public String requestNumber;

}
