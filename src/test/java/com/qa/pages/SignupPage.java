package com.qa.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.pojo.Registration;
import com.qa.utils.BrowserUtil;
import com.qa.utils.LoggerUtil;

public class SignupPage {
	private WebDriver driver;
	private BrowserUtil browserUtil;

	Logger logger = LoggerUtil.getLogger(this.getClass());

	private static final By USERNAME_LOCATOR = By.id("username");
	private static final By EMAIL_LOCATOR = By.id("email");
	private static final By PASSWORD_LOCATOR = By.id("password");
	private static final By CONFIRM_PASSWORD_LOCATOR = By.id("confirmPassword");
	private static final By NEXT_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Next']");
	private static final By FIRSTNAME_LOCATOR = By.id("firstName");
	private static final By LASTNAME_LOCATOR = By.id("lastName");
	private static final By MOBILE_NUMBER_LOCATOR = By.id("mobileNumber");
	private static final By REGISTER_BUTTON_LOCATOR = By.xpath("//button[normalize-space()='Register']");

	public SignupPage(WebDriver driver) {
		this.driver = driver;
		browserUtil = new BrowserUtil(driver);
	}

	public LoginPage registerAccountDetails(Registration reg) {
		logger.info("Performing user registration to application");
		browserUtil.enterText(USERNAME_LOCATOR, reg.getUsername());
		browserUtil.enterText(EMAIL_LOCATOR, reg.getEmail());
		browserUtil.enterText(PASSWORD_LOCATOR, reg.getPassword());
		browserUtil.enterText(CONFIRM_PASSWORD_LOCATOR, reg.getConfirmPassword());
		browserUtil.clickOn(NEXT_BUTTON_LOCATOR);

		browserUtil.enterText(FIRSTNAME_LOCATOR, reg.getFirstName());
		browserUtil.enterText(LASTNAME_LOCATOR, reg.getLastName());
		browserUtil.enterText(MOBILE_NUMBER_LOCATOR, reg.getMobile());
		browserUtil.clickOn(REGISTER_BUTTON_LOCATOR);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}

}
