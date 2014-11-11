package com.sfdc.scripts;

import org.testng.annotations.Test;

import com.lib.ExcelLib;
import com.sfdc.pom.LoginPage;

public class EmailVerification extends SuperTestNG {
	@Test
	public void testEmailVerification() 
	{
		LoginPage loginPage = new LoginPage(driver);
		String xlPath = "D:/Selenium/test data/test data_Final.xlsx";
		String sheetName="Email";
		
		
		String un =ExcelLib.getCellValue(xlPath, sheetName, 1, 0);
		String pw =ExcelLib.getCellValue(xlPath, sheetName, 1, 1);
		
		loginPage.login(un, pw);
		

	}
}
