package com.qa.tests.api;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.api.base.AuthService;
import com.qa.api.base.UserManagementService;
import com.qa.api.models.request.ChangePasswordRequest;
import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.request.UpdateProfileRequest;
import com.qa.api.models.response.LoginResponse;
import com.qa.api.models.response.UserProfileResponse;
import com.qa.utils.ConfigUtil;
import com.qa.utils.FakerUtil;

import io.restassured.response.Response;

@Listeners({ com.qa.listeners.TestListener.class })
public class UserManagementServiceAPITest {

	FakerUtil fakerUtil;

	@Test(description = "verify update profile api is working", groups = { "sanity" })
	public void updateProfileTest() {
		fakerUtil = new FakerUtil();
		AuthService auth = new AuthService();
		Response res = auth.login(new LoginRequest(ConfigUtil.getApiUser(), ConfigUtil.getApiPassword()));
		LoginResponse loginres = res.as(LoginResponse.class);

		UserManagementService um = new UserManagementService();
		Response rs = um.getProfile(loginres.getToken());

		UserProfileResponse upr = rs.as(UserProfileResponse.class);

		Response resp = um.updateProfile(loginres.getToken(), new UpdateProfileRequest(upr.getEmail(),
				upr.getFirstName(), upr.getLastName(), fakerUtil.randomMobile()));
		UserProfileResponse userProfileResponse = resp.as(UserProfileResponse.class);
		Assert.assertEquals(userProfileResponse.getId(), upr.getId());
	}

	@Test(description = "verify change password api is working", groups = { "regression" })
	public void changePasswordTest() {
		AuthService auth = new AuthService();
		Response res = auth.login(new LoginRequest(ConfigUtil.getApiUser(), ConfigUtil.getApiPassword()));

		UserManagementService um = new UserManagementService();
		Response rs = um.changePassword(res.as(LoginResponse.class).getToken(), new ChangePasswordRequest(
				ConfigUtil.getApiPassword(), ConfigUtil.getApiPassword(), ConfigUtil.getApiPassword()));
		Assert.assertEquals(rs.statusCode(), 200);
	}

}
