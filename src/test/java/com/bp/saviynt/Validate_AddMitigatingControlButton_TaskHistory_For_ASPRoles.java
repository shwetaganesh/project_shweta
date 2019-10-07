package com.bp.saviynt;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.bp.lib.ExcelOperations;
import com.bp.lib.Screenshot;
import com.bp.lib.UsernameGeneration;
import com.bp.testbase.TestBase;

public class Validate_AddMitigatingControlButton_TaskHistory_For_ASPRoles extends TestBase
{

	UsernameGeneration userObject = new UsernameGeneration();
	ExcelOperations excel = new ExcelOperations(".\\Test Data\\Sav-Scenarios.xlsx"); // excel sheet containing roles and other information for new saviynt SOD scenarios
	String role10 = excel.getData(0, 7, 1);
	String role11 = excel.getData(0, 8, 1);
	String role12 = excel.getData(0, 9, 1);
	String role13 = excel.getData(0, 10, 1);
	String roleApprover = excel.getData(0, 16, 1); // one role approver for all roles - r10,11,12,13
													// requestor and role approver are same.
	String sodApprover = excel.getData(0, 22, 1); // one sod approver for all roles.
	String endUser = excel.getData(0, 27, 1);
	String password = "password",endPoint="PRKCLNT100";
	public String requestNumber;
	String requestor = excel.getData(0, 26, 1);
	
	
	@Test(priority=1)
	public void createRequestForApplicationSpecificRoleAndRoleApproval() throws InterruptedException, IOException
	{
		logger = extent.createTest("Request application specific role and check Add mitigating control button appears only in sod approval step");
		LaunchPage launch = new LaunchPage(driver);
		//Login to application with Requester 
		launch.login(roleApprover, password);
		HomePage home = new HomePage(driver);
		//open Request Application Specific Role tab 
		home.openRequestApplicationSpecificRoles();
		FindUserPage userPage = new FindUserPage(driver);
		//search for end user
		userPage.searchEndUser(endUser);
		FindRolePage rolePage = new FindRolePage(driver);
		// search for required role....add to cart.
		rolePage.selectEndPoint(endPoint);
		rolePage.clickOnModifyExistingAccount();
		rolePage.clickOnCheckout1();
		RequestAccessPage requestAcessPage = new RequestAccessPage(driver);
		requestAcessPage.searchAndAddSapRole(role10);
		requestAcessPage.searchAndAddSapRole(role11);
		requestAcessPage.searchAndAddSapRole(role12);
		requestAcessPage.searchAndAddSapRole(role13);
		requestAcessPage.clickOnNext();
		JustificationPage justifyPage = new JustificationPage(driver);
		justifyPage.clickOnSubmit();
		// retrieve the request number
		SubmitPage submit = new SubmitPage(driver);
		requestNumber = submit.returnRequestNumber();
		if (requestNumber.equals(""))
			Assert.assertTrue(false, "Request was not raised properly");
		else
			logger.pass("Request raised with number " + requestNumber, MediaEntityBuilder
					.createScreenCaptureFromPath(Screenshot.captureScreenShot(driver).replace("Reports", "")).build());
		System.out.println("Request Number is " + requestNumber);
		userObject.writeRequestNumber(requestNumber);
		// click on home
		home.clickOnHome();
		home.openApprovalInbox();
		ApprovalInboxPage approvalInbox = new ApprovalInboxPage(driver);
		approvalInbox.searchRequestNumber(requestNumber);
		approvalInbox.clickOnAcceptAll();
		boolean result = approvalInbox.clickOnConfirm();
		Assert.assertTrue(result,"End point Approver approval failed");		
		logger.pass("End Point Approver has approved the request");
		home.logoff();
		launch.login(sodApprover, password);
		home.openApprovalInbox();
		approvalInbox.searchRequestNumber(requestNumber);
		approvalInbox.verifyPresenceOfThirdAddMitigatingControlButton();
		home.logoff();
}
	
	@Test(priority=2)
	public void validateTaskHistory() throws IOException
	{
		logger = extent.createTest("VAlidate presence of separate tabs for sod and role approvers in task history");
		LaunchPage launch = new LaunchPage(driver);
		launch.login(sodApprover, password);
		HomePage home = new HomePage(driver);
		home.openRequestHistory();
		RequestHistoryPage historyPage = new RequestHistoryPage(driver);
		// search and open the request number
		historyPage.searchRequestAndOpen(requestNumber);
		historyPage.getTaskHistorySnapShot();
		home.logoff();
		
	}
}
