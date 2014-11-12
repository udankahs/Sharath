package com.userInbox.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	@FindBy(id="input_1")
	private WebElement unTextBox;
	
	@FindBy(id="input_2")
	private WebElement pwTextBox;
	
	@FindBy(xpath="//input[@type='submit' and @value='Logon']")
	private WebElement loginButton;
	
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	public void login(String un,String pw)
	{
		unTextBox.sendKeys(un);
		pwTextBox.sendKeys(pw);
		loginButton.click();
	}
	
}
