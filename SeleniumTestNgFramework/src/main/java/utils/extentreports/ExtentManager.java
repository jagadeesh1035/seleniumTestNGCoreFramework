package utils.extentreports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.supportlibraries.Settings;

public class ExtentManager {

	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent;
	static Properties prop = Settings.getPropertiesInstance();

	public static ExtentReports getInstance() {
		if (extent == null) {
			createExtentInstance();
		}
		return extent;
	}

	public static ExtentReports createExtentInstance() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\" + getExtentReportName() + ".html");
		htmlReporter.config().setDocumentTitle(prop.getProperty("DocumentTitle"));
		// Name of the report
		htmlReporter.config().setReportName(prop.getProperty("ReportName"));
		htmlReporter.config().setEncoding("utf-8");
		// Dark Theme
		if (prop.getProperty("Theme").equalsIgnoreCase("Standard")) {
			htmlReporter.config().setTheme(Theme.STANDARD);
		} else if (prop.getProperty("Theme").equalsIgnoreCase("Dark")) {
			htmlReporter.config().setTheme(Theme.DARK);
		}
		// Create an object of Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", prop.getProperty("HostName"));
		extent.setSystemInfo("Environment", prop.getProperty("Environment"));
		extent.setSystemInfo("Tester", prop.getProperty("TesterName"));
		extent.setReportUsesManualConfiguration(true);
		return extent;
	}

	public static String getExtentReportName() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
		Date date = new Date();
		String time = formatter.format(date).replace("/", "-").replace(" ", "_").replace(":", "-");
		return "Report_" + time.substring(0, time.length() - 2)
				+ time.substring(time.length() - 2, time.length()).toUpperCase();
	}

}
