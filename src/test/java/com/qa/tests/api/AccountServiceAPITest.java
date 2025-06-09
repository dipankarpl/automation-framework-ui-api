package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AccountService;
import com.qa.api.base.AuthService;
import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.request.SignUpRequest;
import com.qa.api.models.response.CreateAccountResponse;
import com.qa.api.models.response.LoginResponse;
import com.qa.utils.ConfigUtil;
import com.qa.utils.FakerUtil;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class AccountServiceAPITest {

	FakerUtil fakerUtil;

	@Test(description = "verify account creation api is working")
	public void signUpTest() {
		fakerUtil = new FakerUtil();
		String newUserName = fakerUtil.randomUsername();
		SignUpRequest signUpRequest = new SignUpRequest(newUserName, "password", fakerUtil.randomEmail(),
				fakerUtil.randomFirstName(), fakerUtil.randomLastName(), String.valueOf(fakerUtil.randomMobile()));
		AuthService auth = new AuthService();
		Response res1 = auth.signUp(signUpRequest);
		Assert.assertEquals(res1.asPrettyString(), "User registered successfully!");

		LoginRequest loginRequest = new LoginRequest(newUserName, "password");

		Response res2 = auth.login(loginRequest);
		LoginResponse loginResponse = res2.as(LoginResponse.class);
		Assert.assertEquals(loginResponse.getUsername(), newUserName);

		AccountService acs = new AccountService();
		Response res3 = acs.createAccount(loginResponse.getToken(), "SAVINGS", "MAIN_BRANCH");
		Assert.assertEquals(res3.getStatusCode(), 200);
		CreateAccountResponse createAccountResponse1 = res3.as(CreateAccountResponse.class);
		String transferFrom = createAccountResponse1.getAccountNumber();

		Response res4 = acs.createAccount(loginResponse.getToken(),"CURRENT", "MAIN_BRANCH");
		Assert.assertEquals(res4.getStatusCode(), 200);
		CreateAccountResponse createAccountResponse2 = res4.as(CreateAccountResponse.class);
		String transferTo = createAccountResponse2.getAccountNumber();
		
		System.out.println(transferFrom+" "+ transferTo);
	}

}
