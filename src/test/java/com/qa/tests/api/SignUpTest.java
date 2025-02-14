package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.models.request.SignUpRequest;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class SignUpTest {
	@Test(description = "verify signup api is working", groups = { "sanity" })
	public void signUpTest() {
		SignUpRequest signUpRequest = new SignUpRequest("disha.patani", "password", "disha.patani@test.com", "disha",
				"patani", "9876543210");
		AuthService auth = new AuthService();
		Response res = auth.signUp(signUpRequest);
		Assert.assertEquals(res.asPrettyString(), "User registered successfully!");
	}

}
