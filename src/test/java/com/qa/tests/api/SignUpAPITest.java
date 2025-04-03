package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.models.request.SignUpRequest;
import com.qa.utils.FakerUtil;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class SignUpAPITest {
	
	FakerUtil fakerUtil;
	
	@Test(description = "verify signup api is working", groups = { "sanity" })
	public void signUpTest() {
		fakerUtil = new FakerUtil();
		SignUpRequest signUpRequest = new SignUpRequest(fakerUtil.randomUsername(), "password", fakerUtil.randomEmail(),
				fakerUtil.randomFirstName(), fakerUtil.randomLastName(), String.valueOf(fakerUtil.randomMobile()));
		AuthService auth = new AuthService();
		Response res = auth.signUp(signUpRequest);
		Assert.assertEquals(res.asPrettyString(), "User registered successfully!");
	}

}
