package com.qa.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.pojo.User;
import com.qa.utils.BrowserUtil;
import com.qa.utils.LoggerUtil;

public class AccountsPage {
	private WebDriver driver;
	private BrowserUtil browserUtil;
	Logger logger = LoggerUtil.getLogger(this.getClass());

	private static final By CREATE_ACCOUNT_LOCATOR = By.xpath("//button[normalize-space()='Create Account']");
	private static final By ACCOUNT_TYPE_LOCATOR = By.cssSelector(
			"div[class='MuiInputBase-root MuiOutlinedInput-root MuiInputBase-colorPrimary MuiInputBase-formControl css-1ssrxnz'] div[role='combobox']");
	private static final By SAVINGS_ACCOUNT_LOCATOR = By.xpath("//li[normalize-space()='Savings Account']");
	private static final By CREATE_LOCATOR = By.xpath("//button[normalize-space()='Create']");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		browserUtil = new BrowserUtil(driver);
	}

	public AccountsPage createNewAccount() {
		logger.info("Entering details of new account");
		browserUtil.clickOn(CREATE_ACCOUNT_LOCATOR);
		browserUtil.clickOn(ACCOUNT_TYPE_LOCATOR);
		browserUtil.clickOn(SAVINGS_ACCOUNT_LOCATOR);
		browserUtil.clickOn(CREATE_LOCATOR);
		return this;
	}
}
