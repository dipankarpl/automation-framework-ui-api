package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.request.SignUpRequest;
import com.qa.api.models.response.LoginResponse;
import com.qa.utils.ConfigUtil;
import com.qa.utils.FakerUtil;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class AuthenticationServiceAPITest {

	FakerUtil fakerUtil;

	@Test(description = "verify signup api is working")
	public void signUpTest() {
		fakerUtil = new FakerUtil();
		SignUpRequest signUpRequest = new SignUpRequest(fakerUtil.randomUsername(), "password", fakerUtil.randomEmail(),
				fakerUtil.randomFirstName(), fakerUtil.randomLastName(), String.valueOf(fakerUtil.randomMobile()));
		AuthService auth = new AuthService();
		Response res = auth.signUp(signUpRequest);
		Assert.assertEquals(res.asPrettyString(), "User registered successfully!");
	}

	@Test(description = "verify login api is working")
	public void loginTestAPI() {
		LoginRequest loginRequest = new LoginRequest(ConfigUtil.getUser(), ConfigUtil.getPassword());
		AuthService auth = new AuthService();
		Response res = auth.login(loginRequest);
		LoginResponse loginResponse = res.as(LoginResponse.class);
		Assert.assertEquals(loginResponse.getUsername(), ConfigUtil.getUser());
	}

	@Test(description = "verify forget password api is working")
	public void forgotPasswordTest() {
		AuthService auth = new AuthService();
		Response res = auth.forgotPassword(ConfigUtil.getApiEmail());
		Assert.assertTrue(res.asPrettyString()
				.contains("If your email exists in our system, you will receive reset instructions."));
	}

}
