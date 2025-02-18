package com.qa.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.drivers.WebDriverManager;
import com.qa.utils.ExtentReportUtil;
import com.qa.utils.LoggerUtil;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtil.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReportUtil.createExtentTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {

		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		ExtentReportUtil.getTest().log(Status.PASS,
				result.getMethod().getMethodName() + " " + result.getMethod().getDescription() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());

		ExtentReportUtil.getTest().log(Status.FAIL,
				result.getMethod().getMethodName() + " " + result.getMethod().getDescription() + " " + "FAILED");
		ExtentReportUtil.getTest().log(Status.FAIL, result.getThrowable().getMessage());

		if (!result.getMethod().getMethodName().contains("API")) {
			logger.info("Capturing Screenshot for the failed tests");
			String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
			ExtentReportUtil.getTest().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(
							WebDriverManager.getScreenshot(result.getMethod().getMethodName(), browser),
							result.getMethod().getMethodName()).build());
		}

	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentReportUtil.getTest().log(Status.SKIP,
				result.getMethod().getMethodName() + " " + result.getMethod().getDescription() + " " + "SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReportUtil.setupSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed");
		ExtentReportUtil.flushReport();
	}
}
