package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - Object Rep:
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//button[@contains(text(),'Sign Up')]")
	WebElement signupBtn;
	
	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	//Initializing the page objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);// 'this' means current class objects. Can also use LoginPage.class		
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();//always use string for getTitle		
	}
	
	public boolean validateCRMImage(){
		return crmLogo.isDisplayed();//always use boolean for isDisplayed
	}
	
	public HomePage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage();// login page lands to homepage, so use return method
	}
	
	
	
	
	
	
	
	

}
