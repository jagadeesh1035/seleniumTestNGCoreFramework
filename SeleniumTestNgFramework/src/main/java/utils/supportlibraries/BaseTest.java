package utils.supportlibraries;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {

	protected static WebDriver driver;
	protected static ExtentReports extent;
	protected static ExtentTest logger;
	protected static Properties properties;
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	protected static WebDriverUtils utils;
	protected static String previousSkipTest = "";
	protected static DataFactory dataTable;
	protected static ScriptHelper scriptHelper;

	
	@BeforeSuite
	@Parameters()
	public void setUp() {
		properties = Settings.getPropertiesInstance();
	}

	@BeforeMethod
	public void setup() {
		driver = WebDriverFactory.getDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
		driver.quit();
	}
}
