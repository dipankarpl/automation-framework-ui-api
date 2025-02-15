package com.qa.dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.qa.pojo.User;
import com.qa.utils.DataProviderUtil;

public class DataProviders {

	@DataProvider(name = "LoginTestExcelDataProvider", parallel=true)
	public Iterator<User> loginExcelDataProvider() {
		return DataProviderUtil.getTestDataFromExcel("LoginData.xlsx", "LoginTestData");
	}

}
