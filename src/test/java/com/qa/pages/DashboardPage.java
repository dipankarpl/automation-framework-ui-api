package com.qa.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.utils.BrowserUtil;
import com.qa.utils.LoggerUtil;

public class DashboardPage {
	private WebDriver driver;
	private BrowserUtil browserUtil;
	Logger logger = LoggerUtil.getLogger(this.getClass());
	
	private static final By USERNAME_LOCATOR = By.cssSelector(".MuiTypography-root.MuiTypography-h4.MuiTypography-gutterBottom.css-pdmxks");

	public DashboardPage(WebDriver driver) {
		this.driver=driver;
		browserUtil=new BrowserUtil(driver);
	}
	
	public String getLoggedInUsername() {
		return browserUtil.getVisibleText(USERNAME_LOCATOR);
	}
}
