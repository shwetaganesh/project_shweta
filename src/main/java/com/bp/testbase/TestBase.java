package com.bp.testbase;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.bp.lib.ConfigReader;
import com.bp.lib.Screenshot;
import com.bp.lib.SendReport;
import com.bp.lib.ZipFolder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase 
{
	public WebDriver driver;
	
	
	
	public ConfigReader config=new ConfigReader();
	
	public ExtentHtmlReporter reporter=new ExtentHtmlReporter("./Reports/TestReport.html");
	
	public  static ExtentReports extent=new ExtentReports();
	
	public static  ExtentTest logger;
	
	public String browser_name=System.getProperty("BROWSER");
	
	String Sav_URL=System.getProperty("URL");
	
	
	
	@BeforeMethod
	public void openApplication()
	{
		if(browser_name==null)
			
			browser_name=config.getValue("Browser");
		
		if(Sav_URL==null)
			
			Sav_URL=config.getValue("URL");;
		
		extent.attachReporter(reporter);
		
		if(browser_name.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver",config.getValue("FirefoxDriver"));
			
			driver=new FirefoxDriver();
		}
		else if(browser_name.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",config.getValue("ChromeDriver"));
			//WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().version("74.0").setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			//browser = new ChromeDriver(options);
			driver=new ChromeDriver(options);
		}
		else if(browser_name.equalsIgnoreCase("IE"))
		{
			
			System.setProperty("webdriver.ie.driver",config.getValue("IEDriver"));
			
			driver=new InternetExplorerDriver();
			
		}
		
		System.out.println("===== Browser Started =====");
		
		driver.manage().window().maximize();
		
		driver.get(Sav_URL);
	}
	
	@AfterMethod
	public void CloseApplication(ITestResult result) throws Exception
	{		
		if(ITestResult.FAILURE==result.getStatus())
		{
			String path=Screenshot.captureScreenShot(driver);
			
			System.out.println("===== Screenshot Taken on failure=====");
			
			System.out.println(result.getThrowable().getMessage());
			
			logger.fail("Test Failed. Please find the details in the screenshot and report log", MediaEntityBuilder.createScreenCaptureFromPath(path.replace("Reports","")).build());
		}
		
		driver.quit();
		
		extent.flush();
		
		System.out.println("===== Browser Session Closed =====");
	}
	
	@AfterSuite
	public void sendReport() throws EmailException
	{	
		//extent.flush();
		
		ZipFolder.zipReport(".\\Reports", ".\\zip\\Report.zip");
		
		SendReport report1=new SendReport();
		
		report1.sendReportToMail();
	}
	
	public static void actionClickById(WebDriver local_driver,String obj_id)
	{
		Actions act=new Actions(local_driver);
		
		act.moveToElement(local_driver.findElement(By.id(obj_id))).click().build().perform();
	}
	public static void javaScriptClickbyElement(WebDriver local_driver,WebElement element)
	{
		JavascriptExecutor executor = (JavascriptExecutor)local_driver;
		
		executor.executeScript("arguments[0].click();", element);
	}
	public static void scrollDownToElement(WebDriver local_driver,WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) local_driver;
		
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public static void scrollUp(WebDriver local_driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) local_driver;
		
		js.executeScript("window.scrollTo(0, 0);");
	}
		
	public static void scrollToEndOfPage(WebDriver local_driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) local_driver;
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
}

