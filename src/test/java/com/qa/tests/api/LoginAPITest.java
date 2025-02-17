package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.response.LoginResponse;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class LoginAPITest {
	@Test(description = "verify login api is working", groups = { "sanity", "regression" })
	public void loginTestAPI() {
		LoginRequest loginRequest = new LoginRequest("dpaul", "password");
		AuthService auth = new AuthService();
		Response res = auth.login(loginRequest);
		LoginResponse loginResponse = res.as(LoginResponse.class);
		Assert.assertTrue(res.asPrettyString().contains("token"));
		System.out.println(res.asPrettyString());
		System.out.println(loginResponse.getToken());
	}

}
