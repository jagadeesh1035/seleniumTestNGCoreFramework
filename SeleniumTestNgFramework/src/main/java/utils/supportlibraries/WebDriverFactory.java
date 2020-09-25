package utils.supportlibraries;

import java.util.Collections;
import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import utils.core.Browser;


public class WebDriverFactory {
	private static Properties prop;
	public static ChromeOptions ops;

	public static WebDriver getDriver() {
		WebDriver driver = null;
		prop = Settings.getPropertiesInstance();
		Browser browser = null;
		if (prop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			browser = Browser.Chrome;
		} else if (prop.getProperty("Browser").equalsIgnoreCase("Firefox")) {
			browser = Browser.Firefox;
		} else if (prop.getProperty("Browser").equalsIgnoreCase("IE")) {
			browser = Browser.InternetExplorer;
		} else if (prop.getProperty("Browser").equalsIgnoreCase("Edge")) {
			browser = Browser.Edge;
		}
		switch (browser) {
		case Chrome:
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverPath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(options);
			break;
		case Firefox:
			System.setProperty("webdriver.gecko.driver", prop.getProperty("GeckoDriverPath"));
			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("network.negotiate-auth.delegation-uris", "http://,https://");
			ffProfile.setPreference("network.negotiate-auth.trusted-uris", "http://,https://");
			ffProfile.setPreference("network.auth.force-generic-ntlm", true);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("firefox_profile", ffProfile);
			capabilities.setCapability("marionette", true);
			FirefoxOptions ffOptions = new FirefoxOptions(capabilities);
			ffOptions.setBinary(prop.getProperty("GeckoDriverPath"));
			driver = new FirefoxDriver(ffOptions);
			break;
		case InternetExplorer:
			System.setProperty("webdriver.ie.driver", prop.getProperty("InternetExplorerDriverPath"));
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,
					UnexpectedAlertBehaviour.IGNORE);
			ieCapabilities.setJavascriptEnabled(true);
			InternetExplorerOptions ieOptions = new InternetExplorerOptions(ieCapabilities);
			driver = new InternetExplorerDriver(ieOptions);
			break;
		case Edge:
			System.setProperty("webdriver.edge.driver", prop.getProperty("EdgeDriverPath"));
			driver = new EdgeDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
}
