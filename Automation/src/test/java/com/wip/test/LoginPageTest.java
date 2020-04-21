package com.wip.test;

import java.util.logging.LogManager;

//import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wip.common.HIFIConstants;
import com.wip.pages.Homepage;
import com.wip.pages.LoginPage;
import com.wip.pages.MyAccountPage;
import com.wip.pages.PaymentScreenPage;
import com.wip.pages.ProductDetailsPage;
import com.wip.pages.Searchpage;
import com.wip.util.BaseTestObject;



@Listeners(com.wip.util.ListenerTest.class)
public class LoginPageTest extends BaseTestObject
{

	//Fetching the values from the property file
	public String username = ObjProperty.getProperty("username");
	public String password = ObjProperty.getProperty("password");
	public String searchItem = ObjProperty.getProperty("searchItem");
	public  String searchItem_sel= ObjProperty.getProperty("selectItem");
	public  String invalid_username= ObjProperty.getProperty("invalid_username");
	public  String Invalid_password= ObjProperty.getProperty("invalid_password");

	//Creating the objects for the Classes
	Homepage objhomepage;
	LoginPage objloginpage;
	Searchpage objsearchpage;
	ProductDetailsPage objproductdescriptionpage;
	PaymentScreenPage objpaymentscreenpage;
	MyAccountPage objmyaccountpage;

	boolean flag=false;

	String home_page_title=null;
	String Login=null;
	String Enter_search_value=null;
	String model=null;
	String SKU=null;
	String price_val= null;
	String instock_val= null;

	//@Parameters({"browserType"})
	/**
	 * @author Manjunath
	 * Below Test case coveres End to end flow Testcase
	 * 
	 * @throws Exception
	 */
	@Test(priority=0, enabled=true)
	public void verifyEndtoEndFlowOfPurchasingtheProduct() throws Exception
	{
		try 
		{
			extest = report.startTest("verifySuccessfullLoginFunctionality Test Has Passed");
			objhomepage = new Homepage(driver);
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			flag = objhomepage.isMyAccountDisplayed();
			Assert.assertTrue(flag, "MyAccount object is not displayed");
			
			objloginpage =objhomepage.clickOnMyAccount();
			Login= objloginpage.getMyLoginText();
			Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
			
			objsearchpage=objloginpage.enterValidCredentials(username,password);
			objsearchpage.enterProductNameToSearch(searchItem);
			Enter_search_value= objsearchpage.getExtrcatedText();
			Assert.assertEquals(Enter_search_value, HIFIConstants.SEARCH_TEXT, " Search element is Missing or  not matching with the Expected value");
			
			objproductdescriptionpage=objsearchpage.ClickOnProduct();
			objproductdescriptionpage.verifyTheProductNameDescriptionPriceDetails();
			objproductdescriptionpage.clickOnAddToCart();
			objproductdescriptionpage.clickOnMyCart();
			objpaymentscreenpage=objproductdescriptionpage.clickOnCheckOut();
			objsearchpage=objpaymentscreenpage.clickOnReturntoCart();
			
			objsearchpage.isMyAccountDisplayed();
			objsearchpage.ClickOnMyAccountButtont();
			objsearchpage.ClickOnLogout();

		} 
		catch (Exception e) 
		{
			extest.log(LogStatus.FAIL, "verifyEndtoEndFlowOfPurchasingtheProduct Test has Failed "+ e.getMessage());
			throw new Exception("FAILED COMPELTE THE END TO FLOW " +e.getLocalizedMessage());
		}

	}

	/**
	 * 
	 * @author Manjunath
	 * Below Test case coveres the Login and Logout functionality
	 * 
	 * @throws Exception
	 */

	@Test(priority=1, enabled=true)
	public void verifySuccessfullLoginFunctionality() throws Exception
	{
		try 
		{
			extest = report.startTest("verifySuccessfullLoginFunctionality Test Has Passed");
			objhomepage = new Homepage(driver);
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			flag = objhomepage.isMyAccountDisplayed();
			Assert.assertTrue(flag, "MyAccount object is not displayed");
			objloginpage =objhomepage.clickOnMyAccount();
			Login= objloginpage.getMyLoginText();
			Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
			objsearchpage=objloginpage.enterValidCredentials(username,password);
			flag=objsearchpage.isSearchTextDisplayed();
			Assert.assertTrue(flag, "Search Text box object is not displayed");
			objsearchpage.isMyAccountDisplayed();
			objsearchpage.ClickOnMyAccountButtont();
			objsearchpage.ClickOnLogout();

		} 
		catch (Exception e) 
		{
			extest.log(LogStatus.FAIL, "verifySuccessfullLoginFunctionality Test has Failed "+ e.getMessage());
			throw new Exception("Valid Login Scenario " +e.getLocalizedMessage());
		}

	}
	/**
	 * @author Manjunath
	 * Below Test case covers Invalid Login functionality
	 * 
	 * @throws Exception
	 */
	@Test(priority=2, enabled=true)
	public void verifyInvalidLoginFunctionality() throws Exception
	{
		try 
		{
			extest = report.startTest("verifyInvalidLoginFunctionality Test Has Passed");
			Assert.assertTrue(true);
			objhomepage = new Homepage(driver);
			home_page_title=objhomepage.verifyHomePageTitle();
			Assert.assertEquals(home_page_title, HIFIConstants.HOME_TITLE,"Title of the Page is not Matching");
			flag = objhomepage.isMyAccountDisplayed();
			Assert.assertTrue(flag, "MyAccount object is not displayed");
			objloginpage =objhomepage.clickOnMyAccount();
			Login= objloginpage.getMyLoginText();
			Assert.assertEquals(Login, HIFIConstants.LOGIN_TEXT_HEAD," Login header details are not Matching");
			objloginpage.enterInvalidCredentials(invalid_username,Invalid_password);
			flag=objloginpage.isErrorMessageDisplayed();
			Assert.assertTrue(flag, "Error Message is not displayed");
			String msg=objloginpage.getErrorMessage();
			Assert.assertEquals(msg, HIFIConstants.ERROR_MSG," Both Error Messages are not matching");	  
			extest.log(LogStatus.PASS, "Test Case Passed is passTest");
		} 
		catch (Exception e) 
		{
			extest.log(LogStatus.FAIL, "verifyInvalidLoginFunctionality Test has Failed "+ e.getMessage());
			throw new Exception("InValid Login Scenario " +e.getLocalizedMessage());
		}

	}




}


