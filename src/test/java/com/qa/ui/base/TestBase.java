package com.qa.ui.base;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.constants.AppConstants;
import com.qa.drivers.WebDriverManager;
import com.qa.pages.LoginPage;
import com.qa.utils.ConfigUtil;
import com.qa.utils.LoggerUtil;

public class TestBase {
	Logger logger = LoggerUtil.getLogger(this.getClass());

	protected WebDriver driver;
	protected LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(@Optional("chrome") String browser) {
		driver = WebDriverManager.getDriver(browser);
		logger.info("driver initialization completed");
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
