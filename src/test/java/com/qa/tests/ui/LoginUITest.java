package com.qa.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pojo.User;
import com.qa.ui.base.TestBase;

@Listeners({ com.qa.listeners.TestListener.class })
public class LoginUITest extends TestBase {

	@Test(description = "Verify user is able to sign up for the application", groups = { "regression",
			"sanity" }, dataProviderClass = com.qa.dataproviders.DataProviders.class, dataProvider = "LoginTestExcelDataProvider", retryAnalyzer = com.qa.listeners.MyRetryAnalyzer.class)
	public void loginTest(User user) {
		String username = loginPage.goToDashboard(user).getLoggedInUsername();
		Assert.assertTrue(username.contains(user.getUsername()));
	}
}
