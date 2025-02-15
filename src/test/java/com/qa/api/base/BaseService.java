package com.qa.api.base;

import static io.restassured.RestAssured.*;

import com.qa.api.filters.LoggingFilter;
import com.qa.utils.ConfigUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
	private static final String BASE_URL = ConfigUtil.getApiBaseUrl();
	private RequestSpecification requestSpecification;
	
	static {
		RestAssured.filters(new LoggingFilter());
	}

	public BaseService() {
		requestSpecification = given().baseUri(BASE_URL);
	}

	protected void setAuthToken(String token) {
		requestSpecification.header("Authorization", "Bearer " + token);
	}

	protected Response getRequest(String endPoint) {
		return requestSpecification.get(endPoint);
	}

	protected Response postRequest(Object payload, String endPoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).post(endPoint);
	}

	protected Response putRequest(Object payload, String endPoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).put(endPoint);
	}
}
