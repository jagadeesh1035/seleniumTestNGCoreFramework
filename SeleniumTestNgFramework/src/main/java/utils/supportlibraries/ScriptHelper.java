package utils.supportlibraries;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class ScriptHelper {

	public static WebDriver driver;
	public static DataFactory dataTable;
	public static ExtentTest logger;
	public static Properties properties;

	public ScriptHelper(WebDriver driver, DataFactory dataTable, ExtentTest logger, Properties properties) {
		ScriptHelper.driver = driver;
		ScriptHelper.dataTable = dataTable;
		ScriptHelper.logger = logger;
		ScriptHelper.properties = properties;
	}

	public static DataFactory getDataTable() {
		return dataTable;
	}

	public static ExtentTest getLogger() {
		return logger;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
