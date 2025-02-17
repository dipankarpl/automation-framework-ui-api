package com.qa.api.base;

import org.testng.annotations.BeforeClass;

import com.qa.utils.ConfigUtil;

import io.restassured.RestAssured;

public class TestApiBase {
	@BeforeClass(alwaysRun = true)
	public void apiSetUp() {
		RestAssured.baseURI = ConfigUtil.getApiBaseUrl();
		RestAssured.requestSpecification = RestAssured.given().headers("Content-Type", "application/json");
		// Add authentication if needed

	}
}