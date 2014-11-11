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

public class MyTerritorySection extends BasePage {
	
	private WebDriver driver;

	static Row rownum1;
	static Row row_account;
	static String Field_Name;
	static String Account_Name;
	static int record_found = 0;

	int exit_loop = 0;
	int exec_end = 0;

	@FindBy(xpath = ".//td[text()='Account Record Type']/../td[4]")
	private WebElement AccountRecordType;

	@FindBy(xpath = ".//h3[text()='My Territory']")
	private WebElement MyTerritory;

	public MyTerritorySection(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void MyTerritorySectionVerification(String AccountName,String xlPath, int j) throws InterruptedException, IOException,InvalidFormatException {

		FileInputStream fis = new FileInputStream(xlPath);

		Workbook wb = WorkbookFactory.create(fis);
		Sheet s1 = wb.getSheet("My Territory");

		exit_loop = 0;
		exec_end = 0;
		int i = 0;

		row_account = s1.getRow(i);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		j++;

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.backgroundColor='yellow';", MyTerritory);

		AccountRecordType.click();

		String Account_record_type = AccountRecordType.getText();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String filepath = "D://Selenium//Screenshots" + AccountName + ".png";

		FileUtils.copyFile(scrFile, new File(filepath));

		Iterator<Row> iterator1 = s1.iterator();

		do {

			Row row_record = iterator1.next();

			Cell record_type1 = row_record.getCell(0);

			if (record_type1 == null) {

			} else {

				int rowcount = record_type1.getRowIndex();

				String record_type = record_type1.toString();// row_record.getCell(0,Row.CREATE_NULL_AS_BLANK).getStringCellValue();

				if (Account_record_type.equals(record_type)) {
					i = rowcount;
					record_found = 3;

				}
			}
		} while (iterator1.hasNext());


		do {

			rownum1 = (Row) s1.getRow(i);
			try {
				Field_Name = rownum1.getCell(1).getStringCellValue();
			} catch (NullPointerException ds) {

				exit_loop = 5;
			}

			if (exit_loop < 1) {

				driver.switchTo().defaultContent();

				driver.switchTo().frame("01NA0000000aPXw");

				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				try {
					String ab = driver.findElement(By.xpath("//label[(text()='" + Field_Name + "')]")).getText();
					System.out.println(ab);
					rownum1.createCell(j).setCellValue("PASS");

				} catch (NoSuchElementException fd)

				{
					rownum1.createCell(j).setCellValue("FAIL");
				}
			}

			i++;
		} while (exit_loop != 5);

		// }
		// }while (exec_end != 5);


		FileOutputStream file1 = new FileOutputStream(xlPath);

		wb.write(file1);
		file1.close();

	}
}
