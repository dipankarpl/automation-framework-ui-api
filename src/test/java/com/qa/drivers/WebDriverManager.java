package com.qa.drivers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.utils.ConfigUtil;

public class WebDriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver(String browser) {
		if (driver.get() == null) {
			driver.set(createDriver(browser));
		}
		return driver.get();
	}

	private static WebDriver createDriver(String browsername) {

		String browser = browsername;
		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		boolean remoteExecution = Boolean.parseBoolean(System.getProperty("remote_execution", "false"));
		WebDriver driverInstance = null;
		
		try {
			if (remoteExecution) {
				URL gridUrl = new URL(ConfigUtil.getGridUrl());
				DesiredCapabilities capabilities = new DesiredCapabilities();

				switch (browser.toLowerCase()) {
				case "chrome":
					ChromeOptions chromeOptions = new ChromeOptions();
					if (headless)
						chromeOptions.addArguments("--headless");
					capabilities.merge(chromeOptions);
					break;
				case "edge":
					EdgeOptions edgeOptions = new EdgeOptions();
					if (headless)
						edgeOptions.addArguments("--headless");
					capabilities.merge(edgeOptions);
					break;
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browser);
				}

				driverInstance = new RemoteWebDriver(gridUrl, capabilities);
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
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browser);
				}
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid Selenium Grid URL", e);
		}

		return driverInstance;
	}

	public static void unloadDriver() {
		driver.remove();
	}

	public static String getScreenshot(String methodName, String browser) {
		File srcFile = ((TakesScreenshot) getDriver(browser)).getScreenshotAs(OutputType.FILE);// temp dir
		String path = "./screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}