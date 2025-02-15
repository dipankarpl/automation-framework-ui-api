package com.qa.ui.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.constants.AppConstants;
import com.qa.drivers.WebDriverManager;
import com.qa.pages.LoginPage;
import com.qa.utils.ConfigUtil;

public class TestBase {

	protected WebDriver driver;
	protected LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)

	public void setUp() {
		driver = WebDriverManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(AppConstants.DEFAULT_MEDIUM_TIME_OUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConstants.DEFAULT_MEDIUM_TIME_OUT));
		driver.get(ConfigUtil.getApplicationUrl());
		loginPage = new LoginPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			WebDriverManager.unloadDriver();
		}
	}

}
