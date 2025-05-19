package com.qa.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pojo.Registration;
import com.qa.ui.base.TestBase;
import com.qa.utils.FakerUtil;

@Listeners({ com.qa.listeners.TestListener.class })
public class SignupUITest extends TestBase {
	FakerUtil fakerUtil;

	@Test(description = "Verifies user is able to sign up to the application", groups = { "regression" }, retryAnalyzer = com.qa.listeners.MyRetryAnalyzer.class)
	public void signupTest() {
		fakerUtil = new FakerUtil();
		Registration reg = new Registration(fakerUtil.randomUsername(), fakerUtil.randomEmail(), "password", "password",
				fakerUtil.randomFirstName(), fakerUtil.randomLastName(), String.valueOf(fakerUtil.randomMobile()));
		String txt = loginPage.goToSignup().registerAccountDetails(reg).getSigninText();
		Assert.assertTrue(txt.contains("Sign In"));
	}

}
