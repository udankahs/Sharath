package com.sfdc.pom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends BasePage {
	private WebDriver driver;

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
		this.driver = driver;
	}

	public void EmailVerification()  
	{
		Unread.click();
		Email.click();
		String resetURL = PasswordResetLink.getText();
		PasswordResetLink.click();
	}
}
