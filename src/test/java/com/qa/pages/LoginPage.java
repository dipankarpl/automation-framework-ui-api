package com.qa.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.pojo.User;
import com.qa.utils.BrowserUtil;
import com.qa.utils.LoggerUtil;

public class LoginPage {
	private WebDriver driver;
	private BrowserUtil browserUtil;
	Logger logger = LoggerUtil.getLogger(this.getClass());

	private static final By USERNAME_LOCATOR = By.id("username");
	private static final By PASSWORD_LOCATOR = By.id("password");
	private static final By SIGN_IN_LOCATOR = By.xpath("//button[normalize-space()='Sign In']");
	private static final By SIGN_UP_LOCATOR = By.xpath("//a[contains(text(),'Sign Up')]");
	private static final By SIGN_IN_TEXT_LOCATOR = By.xpath("//h1[normalize-space()='Sign In']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		browserUtil = new BrowserUtil(driver);
	}

	public String getSigninText() {
		logger.info("Getting text of sign in header");
		return browserUtil.getVisibleText(SIGN_IN_TEXT_LOCATOR);
	}

	public DashboardPage goToDashboard(User user) {
		logger.info("Performing click to sign in to application");
		browserUtil.enterText(USERNAME_LOCATOR, user.getUsername());
		browserUtil.enterText(PASSWORD_LOCATOR, user.getPassword());
		browserUtil.clickOn(SIGN_IN_LOCATOR);
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}

	public DashboardPage goToDashboard(String username, String password) {
		logger.info("Performing click to sign in to application");
		browserUtil.enterText(USERNAME_LOCATOR, username);
		browserUtil.enterText(PASSWORD_LOCATOR, password);
		browserUtil.clickOn(SIGN_IN_LOCATOR);
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;
	}

	public SignupPage goToSignup() {
		logger.info("Performing click to sign up to application");
		browserUtil.clickOn(SIGN_UP_LOCATOR);
		SignupPage signupPage = new SignupPage(driver);
		return signupPage;
	}
}
