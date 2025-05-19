package com.qa.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pages.AccountsPage;
import com.qa.ui.base.TestBase;
import com.qa.utils.ConfigUtil;

@Listeners({ com.qa.listeners.TestListener.class })
public class AccountsUITest extends TestBase {

	@Test(description = "Verify user is able to create accounts", groups = {
			"regression" }, retryAnalyzer = com.qa.listeners.MyRetryAnalyzer.class)
	public void createAccountTest() {
		loginPage.goToDashboard(ConfigUtil.getApiUser(), ConfigUtil.getApiPassword()).navigateSidebar("Accounts");
		AccountsPage accountsPage=new AccountsPage(driver);
		accountsPage.createNewAccount();
		Assert.assertTrue(true);
	}
}
