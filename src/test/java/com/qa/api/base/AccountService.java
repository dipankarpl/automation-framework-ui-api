package com.qa.api.base;

import java.util.HashMap;

import io.restassured.response.Response;

public class AccountService extends BaseService {
	private static final String BASE_PATH = "/api/accounts";

	public Response createAccount(String token, String type, String branch) {
		setAuthToken(token);
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("accountType", type);
		payload.put("branch", branch);
		return postRequest(payload, BASE_PATH);
	}
}
