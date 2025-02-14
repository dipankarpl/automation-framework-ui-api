package com.qa.drivers;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.utils.ConfigUtil;

public class WebDriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		if (driver.get() == null) {
			driver.set(createDriver());
		}
		return driver.get();
	}

	private static WebDriver createDriver() {
		String browser = System.getProperty("browser", "edge");
		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		boolean remoteExecution = Boolean.parseBoolean(System.getProperty("remote_execution", "false"));
		WebDriver driverInstance;

		if (remoteExecution) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(browser);
			try {
				driverInstance = new RemoteWebDriver(new URL(ConfigUtil.getGridUrl()), capabilities);
			} catch (Exception e) {
				throw new RuntimeException("Failed to connect to Selenium Grid: " + e.getMessage());
			}
		} else {
			switch (browser.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				if (headless)
					chromeOptions.addArguments("--headless");
				driverInstance = new ChromeDriver(chromeOptions);
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				if (headless)
					edgeOptions.addArguments("--headless");
				driverInstance = new EdgeDriver(edgeOptions);
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (headless)
					firefoxOptions.addArguments("--headless");
				driverInstance = new FirefoxDriver(firefoxOptions);
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		}
		return driverInstance;
	}

	public static void unloadDriver() {
		driver.remove();
	}
}
