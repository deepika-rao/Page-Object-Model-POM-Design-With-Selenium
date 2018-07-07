package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest(){
		super();//using this it will not throw null pointer exception
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabel(){
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"contacts label is missing on the page");		
	}
	
	@Test(priority=2)
	public void selectSingleContactsTest(){
		contactsPage.selectContactsByName("Adam Willey");
	}
	
	@Test(priority=3)
	public void selectMultipleContactsTest(){
		contactsPage.selectContactsByName("abc xyz");
		contactsPage.selectContactsByName("Adam Willey");
	}
	
	@DataProvider // use this for Data driven framework
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName); //this will store the complete data in data[][]
		return data; // always use return in case of using data and use object[][]
	}
	
	@Test(priority=4, dataProvider="getCRMTestData")//to use the excel sheet in test case use this line
	public void validateCreateNewContact(String title, String firstName, String lastName, String company){ //use same column names which are in excel sheet
		homePage.clickOnNewContactLink();		
		// use below line only when there are minimum records and not using excel sheet
		//contactsPage.createNewContact("Mr.", "Tom", "Peter", "Google");
		contactsPage.createNewContact(title, firstName, lastName, company);		
	}
		
	@AfterMethod(enabled=true)
	public void tearDown(){
		driver.quit();
	}

}
