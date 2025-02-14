package com.qa.api.base;

import java.util.HashMap;

import com.qa.api.models.request.LoginRequest;
import com.qa.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class AuthService extends BaseService {
	private static final String BASE_PATH = "/api/auth/";

	public Response login(LoginRequest loginRequest) {
		return postRequest(loginRequest, BASE_PATH + "login");
	}

	public Response signUp(SignUpRequest signUpRequest) {
		return postRequest(signUpRequest, BASE_PATH + "signup");
	}

	public Response forgotPassword(String email) {
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("email", email);
		return postRequest(payload, BASE_PATH + "forgot-password");
	}
}
