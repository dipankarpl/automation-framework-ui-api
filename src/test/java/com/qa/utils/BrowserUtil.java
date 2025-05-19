package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.constants.AppConstants;

public class BrowserUtil {
	private WebDriver driver;
	private WebDriverWait wait;
	Logger logger = LoggerUtil.getLogger(this.getClass());

	public BrowserUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.DEFAULT_MEDIUM_TIME_OUT));
	}

	public String getVisibleText(By locator) {
		logger.info("Finding Element with the locator" + locator);

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element Found and now returning the visibile " + element.getText());

		return element.getText();
	}

	public void clearText(By textBoxLocator) {
		logger.info("Finding Element with the locator" + textBoxLocator);

		// WebElement element = driver.get().findElement(textBoxLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));

		logger.info("Element Found and clearing the text box field");

		element.clear();
	}

	public void selectFromDropDown(By dropDownLocator, String optionToSelect) {
		logger.info("Finding Element with the locator" + dropDownLocator);
		WebElement element = driver.findElement(dropDownLocator);
		Select select = new Select(element);
		logger.info("Selecting the Option " + optionToSelect);

		select.selectByVisibleText(optionToSelect);
	}

	public void clickOnCheckBox(By locator) {
		logger.info("Finding Element with the locator" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("Element Found and now performing Click");
		element.click();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding Element with the locator" + locator);

		// WebElement element = driver.get().findElement(locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		logger.info("Element Found and now enter text " + textToEnter);

		element.sendKeys(textToEnter);
	}

	public void clickOn(By locator) {
		logger.info("Finding Element with the locator" + locator);

		// WebElement element = driver.get().findElement(locator);// Find the element!!!
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		logger.info("Element Found and now performing Click");

		element.click();
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);
		String path = "./screenshots/" + name + " - " + timeStamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
