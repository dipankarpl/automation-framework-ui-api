package com.qa.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.constants.AppConstants;
import com.qa.pojo.Registration;
import com.qa.ui.base.TestBase;
import com.qa.utils.FakerUtil;

@Listeners({ com.qa.listeners.TestListener.class })
public class SignupTest extends TestBase {

	@Test(description = "Verifies user is able to signup to the application", groups = { "sanity" })
	public void testSignup() {
		FakerUtil fakerUtil = new FakerUtil();
		Registration reg = new Registration(fakerUtil.randomUsername(), fakerUtil.randomEmail(), "password", "password",
				fakerUtil.randomFirstName(), fakerUtil.randomLastName(), String.valueOf(fakerUtil.randomMobile()));
		String url = loginPage.goToSignup().registerAccountDetails(reg).getSigninText();
		Assert.assertTrue(url.contains("Sign In"));
	}

}
