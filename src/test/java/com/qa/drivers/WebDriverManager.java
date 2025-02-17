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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
		boolean remoteExecution = Boolean.parseBoolean(System.getProperty("remote_execution", "true"));
		WebDriver driverInstance=null;

		switch (browser.toLowerCase()) {
		case "chrome": {
			ChromeOptions chromeOptions = new ChromeOptions();
			if (headless)
				chromeOptions.addArguments("--headless");
			chromeOptions.setCapability("browserName", browser);

			if (remoteExecution) {
				try {
					driverInstance = new RemoteWebDriver(new URL(ConfigUtil.getGridUrl()), chromeOptions);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				driverInstance = new ChromeDriver(chromeOptions);
			}
			break;
		}
		case "edge": {
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless)
				edgeOptions.addArguments("--headless");
			edgeOptions.setCapability("browserName", browser);

			if (remoteExecution) {
				try {
					driverInstance = new RemoteWebDriver(new URL(ConfigUtil.getGridUrl()), edgeOptions);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				driverInstance = new EdgeDriver(edgeOptions);
			}
			break;
		}
		case "firefox": {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless)
				firefoxOptions.addArguments("--headless");
			firefoxOptions.setCapability("browserName", browser);

			if (remoteExecution) {
				try {
					driverInstance = new RemoteWebDriver(new URL(ConfigUtil.getGridUrl()), firefoxOptions);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} else {
				driverInstance = new FirefoxDriver(firefoxOptions);
			}
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + browser.toLowerCase());

		}
		
		if (driverInstance == null) {
            throw new RuntimeException("Driver initialization failed for browser: " + browser);
        }
		
		return driverInstance;
	}

	public static void unloadDriver() {
		driver.remove();
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
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
