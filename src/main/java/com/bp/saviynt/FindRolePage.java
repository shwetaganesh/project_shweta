package com.bp.saviynt;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bp.testbase.TestBase;



public class FindRolePage extends TestBase 
{
	@FindBy(xpath = "//input[@id='dtsearch_applicationlist']")
	private WebElement role_searchbox;
	
	@FindBy(xpath = "(//a[@class='btn default btn-xs  pull-right  green'])[1]")
	private WebElement first_addtocart_button;
	
	@FindBy(xpath = "(//*[contains(text(),'REMOVE FROM CART')])[1]")
	private WebElement remove_cart;
	
	@FindBy(xpath = "(//a[@class='btn red btn-xs pull-right'])[1]")
	private WebElement removeRole_button;
	//check remove_cart and remove_from_cart_button
	@FindBy(xpath = "(//a[@class='btn default btn-xs dark pull-right'])[1]")
	private WebElement remove_from_cart_button;
	
	@FindBy(xpath = "(//*[contains(@id,'buisnesjustifcation')])[1]")
	private WebElement business_justification_first;
	
	@FindBy(xpath = "//a[@id='showcheckout']")
	private WebElement checkout_button;
	
	@FindBy(xpath ="(//td[@class=' sorting_1'])[1]")
	private WebElement firstRow;
	
	//12/01/2019
	@FindBy(xpath = "(//*[@onclick='opennextpage()' and @class='btn blue button-next checkout1'])[1]")
	private WebElement newCheckoutButton;
	
	@FindBy(xpath = "//a[@class='btn default btn-xs pull-right green']")
	private WebElement addtocart_button;
	
	@FindBy(xpath ="//a[contains(@onclick,'confirmdelete')]")
	private WebElement removeAccountButton;
	
	@FindBy(xpath = "//a[contains(text(),'MODIFY EXISTING ACCOUNT')]")
	private WebElement modifyExistingAccountButton;
	
	@FindBy(xpath = "//td[@class=' sorting_1' and contains(text(),'TEST_SalesforceTest')]")
	private WebElement firstRowData;
	
	@FindBy(xpath = "(//h4[@class='modal-title'])[3]")
	private WebElement popUpHeader;
	
	@FindBy(xpath = "(//button[@class='btn green'])[2]")
	private WebElement popUpyesButton;
	
	WebDriverWait wait;
	
	public FindRolePage(WebDriver ldriver) 
	{
		driver=ldriver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver,30);
	}
	
	//method to search for the required role and add the corresponding role to the cart.
	public void searchandAddtoCart(String role_description)
	{
		wait.until(ExpectedConditions.visibilityOf(role_searchbox));
		//wait.until(ExpectedConditions.visibilityOf(first_addtocart_button));
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		role_searchbox.clear();
		role_searchbox.sendKeys(role_description);
		role_searchbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//a[contains(text(),'"+role_description+"')]")));
		wait.until(ExpectedConditions.visibilityOf(first_addtocart_button));
		first_addtocart_button.click();
		wait.until(ExpectedConditions.visibilityOf(remove_cart));
		role_searchbox.clear();
	}
	
	public void searchandAddtoCartNew(String role_description) throws InterruptedException
	{
		wait.until(ExpectedConditions.visibilityOf(role_searchbox));
		//wait.until(ExpectedConditions.visibilityOf(firstRow));
		role_searchbox.clear();
		role_searchbox.sendKeys(role_description);
		role_searchbox.sendKeys(Keys.ENTER);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Saviynt')]")));
		//wait.until(ExpectedConditions.visibilityOf(addtocart_button));
		Thread.sleep(3000);
		/*try
		{
			 if(addtocart_button.isDisplayed()) {
				 addtocart_button.click();
				 wait.until(ExpectedConditions.visibilityOf(remove_cart));
				 role_searchbox.clear();
		}
		}
		catch(Exception e){
			wait.until(ExpectedConditions.visibilityOf(modifyExistingAccountButton));
			modifyExistingAccountButton.click();
			wait.until(ExpectedConditions.visibilityOf(popUpHeader));
			popUpyesButton.click();
		}*/
		try
		{
			if(removeAccountButton.isDisplayed()) {
				modifyExistingAccountButton.click();
				wait.until(ExpectedConditions.visibilityOf(popUpHeader));
				popUpyesButton.click();
			}
		}
		catch(Exception e) {
			addtocart_button.click();
			 wait.until(ExpectedConditions.visibilityOf(remove_cart));
			 role_searchbox.clear();
			
		}
		
		
	}

	
	//method to click on the CheckOut button after adding/removing required roles 
	public void clickOnCheckout(){
		TestBase.javaScriptClickbyElement(driver, checkout_button);
		wait.until(ExpectedConditions.visibilityOf(business_justification_first));
	}
	
	public void clickOnCheckout1() {
		TestBase.javaScriptClickbyElement(driver, newCheckoutButton);
	}
	
	//17-01-19
	public void clickOnModifyExistingAccount() {
		wait.until(ExpectedConditions.visibilityOf(firstRowData));
		modifyExistingAccountButton.click();
		wait.until(ExpectedConditions.visibilityOf(popUpHeader));
		popUpyesButton.click();
	}
	
	//method to search for the required role and remove the corresponding role from the cart
	public boolean searchAndRemoveFromCart(String role_description)
	{	
		wait.until(ExpectedConditions.visibilityOf(role_searchbox));
		wait.until(ExpectedConditions.visibilityOf(firstRow));
		role_searchbox.sendKeys(role_description);
		role_searchbox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@role='alert']//a[contains(text(),'"+role_description+"')]")));
		boolean status=true;
		try
		{
			if(removeRole_button.isDisplayed())
			{
				status=true;
				System.out.println(status);
				removeRole_button.click();
				wait.until(ExpectedConditions.visibilityOf(remove_from_cart_button));
				role_searchbox.clear();		
			}
		}
		catch(Exception e)
		{
			status=false;
			role_searchbox.clear();
		}
		return status;
	}
}
	


