package com.qa.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtil {
	private static Properties properties = new Properties();

	static {
		String environment = System.getProperty("environment", "qa");
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config/" + environment + ".properties");
			properties.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load properties file: " + e.getMessage());
		}
	}

	public static String getApplicationUrl() {
		return properties.getProperty("app.url");
	}

	public static String getApiBaseUrl() {
		return properties.getProperty("api.url");
	}

	public static String getGridUrl() {
		return properties.getProperty("grid.url");
	}

	public static String getApiUser() {
		return properties.getProperty("app.user");
	}

	public static String getApiPassword() {
		return properties.getProperty("app.password");
	}

	public static String getApiEmail() {
		return properties.getProperty("app.email");
	}

	// Add methods to retrieve other configurations as needed
}
