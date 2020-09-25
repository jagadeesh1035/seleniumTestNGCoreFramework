package utils.listeners;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utils.supportlibraries.Settings;

public class RetryAnalyzer implements IRetryAnalyzer {

	Properties properties = Settings.getPropertiesInstance();
	int counter = 1;
	int retryLimit = Integer.parseInt(properties.getProperty("RetryLimit"));

	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
