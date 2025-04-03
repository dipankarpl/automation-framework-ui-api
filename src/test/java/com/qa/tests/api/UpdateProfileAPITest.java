package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.base.UserManagementService;
import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.request.UpdateProfileRequest;
import com.qa.api.models.response.LoginResponse;
import com.qa.api.models.response.UserProfileResponse;
import com.qa.utils.FakerUtil;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class UpdateProfileAPITest {

	FakerUtil fakerUtil;

	@Test(description = "verify update profile api is working", groups = { "sanity" })
	public void updateProfileTest() {
		fakerUtil = new FakerUtil();
		AuthService auth = new AuthService();
		Response res = auth.login(new LoginRequest("grandmasti", "password"));
		LoginResponse loginres = res.as(LoginResponse.class);

		UserManagementService um = new UserManagementService();
		Response resp = um.updateProfile(loginres.getToken(),
				new UpdateProfileRequest("grandmasti@test.com", "grand", "masti", fakerUtil.randomMobile()));
		UserProfileResponse userProfileResponse = resp.as(UserProfileResponse.class);
		Assert.assertEquals(userProfileResponse.getUsername(), "grandmasti");
	}

}
