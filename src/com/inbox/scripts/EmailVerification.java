package com.inbox.scripts;

import org.testng.annotations.Test;

import com.lib.ExcelLib;
import com.userInbox.pom.InboxPage;
import com.userInbox.pom.LoginPage;

public class EmailVerification extends SuperTestNG 
{
	@Test
	public void testEmailVerification() 
	{
		LoginPage loginPage = new LoginPage(driver);
		String xlPath = "D:/Selenium/test data/test data_login.xlsx";
		String sheetName="Email";
		
		String un =ExcelLib.getCellValue(xlPath, sheetName, 1, 0);
		
		String pw =ExcelLib.getCellValue(xlPath, sheetName, 1, 1);
		System.out.println(un);
		System.out.println(pw);
		loginPage.login(un, pw);
		
		InboxPage inbox = new InboxPage(driver);
		inbox.EmailVerification();
	}
}
