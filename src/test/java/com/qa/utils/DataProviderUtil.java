package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.qa.pojo.User;

public class DataProviderUtil {
	public static Object[][] getTestDataFromJson(String fileName, String testCaseName) {
		try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/test-data/" + fileName)) {
			Gson gson = new Gson();
			// Parse JSON and extract data
			// Return as Object[][]
		} catch (Exception e) {
			throw new RuntimeException("Failed to read JSON data: " + e.getMessage());
		}
		return null;
	}

	public static Iterator<User> getTestDataFromExcel(String fileName, String sheetName) {

		File xlsxFile = new File(System.getProperty("user.dir") + "/src/test/resources/test-data/" + fileName);

		XSSFWorkbook xssfWorkbook = null;
		Row row;
		Cell usernameCell;
		Cell passwordCell;
		User user;
		List<User> userList = null;
		Iterator<Row> rowIterator;
		XSSFSheet xssfSheet;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			userList = new ArrayList<User>();
			xssfSheet = xssfWorkbook.getSheet(sheetName);
			rowIterator = xssfSheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				usernameCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(usernameCell.toString(), passwordCell.toString());
				userList.add(user);
				xssfWorkbook.close();
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return userList.iterator();

	}

}
