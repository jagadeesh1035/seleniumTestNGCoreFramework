package utils.supportlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import utils.core.FrameworkException;

public class Settings {

	public static Properties getPropertiesInstance() {
		return loadFromPropertiesFile();
	}

	public static Properties loadFromPropertiesFile() {
		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(
					System.getProperty("user.dir") + Util.getFileSeparator() + "Global Settings.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("FileNotFountException while loading the Global Settings.properties file");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FrameworkException("IOException while loading the Global Settings.properties file");
		}
		return properties;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
