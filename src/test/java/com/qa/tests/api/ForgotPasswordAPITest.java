package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class ForgotPasswordAPITest {
	@Test(description = "verify forget password api is working", groups = { "sanity" })
	public void forgotPasswordTest() {
		AuthService auth = new AuthService();
		Response res = auth.forgotPassword("abc.def@test.com");
		Assert.assertTrue(res.asPrettyString().contains("If your email exists in our system, you will receive reset instructions."));
	}

}
