package utils.listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utils.extentreports.ExtentManager;
import utils.supportlibraries.BaseTest;
import utils.supportlibraries.DataFactory;
import utils.supportlibraries.ScriptHelper;
import utils.supportlibraries.Util;
import utils.supportlibraries.WebDriverUtils;

public class ExtentListener extends BaseTest implements ITestListener {
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	String methodName;

	public synchronized void onStart(ITestContext context) {
		extent = ExtentManager.createExtentInstance();
	}

	public synchronized void onFinish(ITestContext context) {
		Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();

		while (skippedTestCases.hasNext()) {
			ITestResult skippedTestCase = skippedTestCases.next();
			ITestNGMethod method = skippedTestCase.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 0) {
				skippedTestCases.remove();
			}
		}
		extent.flush();
	}

	public synchronized void onTestStart(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		String className = result.getTestClass().getName().substring(result.getTestClass().getName().indexOf(".") + 1);
		logger = extent.createTest(className + "_" + methodName, result.getMethod().getDescription());
		test.set(logger);
		dataTable = new DataFactory(System.getProperty("user.dir") + "\\DataTables", className);
		dataTable.setCurrentRow(methodName);
		scriptHelper = new ScriptHelper(driver, dataTable, logger, properties);
		utils = new WebDriverUtils(scriptHelper);
	}

	public synchronized void onTestSuccess(ITestResult result) {
		String logText = "<b>TEST CASE: " + methodName + " PASSED</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.get().pass(m);
	}

	public synchronized void onTestFailure(ITestResult result) {
		try {
			logger.log(Status.FAIL, "Script is failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Util.getScreenShot(driver)).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail(
				"<details><summary><b><font color = red>Exception is thrown! Click here to see</font></b></summary>"
						+ exceptionMessage.replaceAll(",", "<br>") + "</details>\n");

		String failureLog = "<b>TEST CASE: " + methodName + " FAILED</b>";
		Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
		test.get().fail(m);
	}

	public synchronized void onTestSkipped(ITestResult result) {
		String currentSkipTest = result.getMethod().getMethodName();
		extent.removeTest(logger);
		if (currentSkipTest.equals(previousSkipTest)) {
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			test.get().skip(
					"<details><summary><b><font color = red>Exception is thrown! Click here to see</font></b></summary>"
							+ exceptionMessage.replaceAll(",", "<br>") + "</details>\n");
			String failureLog = "<b>TEST CASE: " + methodName + " SKIPPED</b>";
			Markup m = MarkupHelper.createLabel(failureLog, ExtentColor.BLUE);
			test.get().skip(m);
		}
		previousSkipTest = currentSkipTest;
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
}
