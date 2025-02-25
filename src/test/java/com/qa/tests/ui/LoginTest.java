package com.qa.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.pojo.User;
import com.qa.ui.base.TestBase;

@Listeners({ com.qa.listeners.TestListener.class })
public class LoginTest extends TestBase {

	@Test(description = "Verifies with the valid user is able to login into the application", groups = { "sanity" }, retryAnalyzer = com.qa.listeners.MyRetryAnalyzer.class)
	public void testLoginPageTitle() {

		String url = loginPage.getLoginPageURL();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL+" "));
	}

	@Test(description = "Verifies with the valid user is able to login into the application", groups = { "regression",
			"sanity" }, dataProviderClass = com.qa.dataproviders.DataProviders.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.qa.listeners.MyRetryAnalyzer.class)
	public void testLogin(User user) {
		String username = loginPage.goToDashboard(user).getLoggedInUsername();
		Assert.assertTrue(username.contains(user.getUsername()));
	}
}
