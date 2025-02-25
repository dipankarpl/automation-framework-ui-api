package com.qa.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.qa.constants.AppConstants;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	private static int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		
		if (currentAttempt <= AppConstants.MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}

		return false;
	}

}
