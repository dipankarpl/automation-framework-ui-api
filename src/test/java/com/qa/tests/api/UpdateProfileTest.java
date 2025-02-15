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

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class UpdateProfileTest {
	@Test(description = "verify update profile api is working", groups = { "sanity" })
	public void updateProfileTest() {
		AuthService auth = new AuthService();
		Response res = auth.login(new LoginRequest("dpaul", "password"));
		LoginResponse loginres = res.as(LoginResponse.class);

		UserManagementService um = new UserManagementService();
		Response resp = um.updateProfile(loginres.getToken(),
				new UpdateProfileRequest("dpaul@test.com", "dip", "ron", "9876540123"));
		UserProfileResponse userProfileResponse = resp.as(UserProfileResponse.class);
		System.out.println(resp.asPrettyString());
		Assert.assertEquals(userProfileResponse.getUsername(), "dpaul");
	}

}
