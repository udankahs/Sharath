package com.userInbox.pom;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends BasePage {
	static Row rownum1;
	static Row row_account;
	static String Field_Name;
	static String Account_Name;
	static int record_found = 0;

	int exit_loop = 0;
	int exec_end = 0;

	@FindBy(id = "_ariaId_65")
	private WebElement Unread;

	@FindBy(xpath = "//span[contains(text(), 'Your Salesforce password has been reset by your administrator')]")
	private WebElement Email;

	@FindBy(xpath = "//a[1]")
	private WebElement PasswordResetLink;

	public InboxPage (WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void EmailVerification()  
	{
		Unread.click();
		Email.click();
		PasswordResetLink.getText();
		PasswordResetLink.click();
	}
}
