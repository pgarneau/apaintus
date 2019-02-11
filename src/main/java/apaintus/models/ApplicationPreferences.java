package apaintus.models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ApplicationPreferences {
	private static ApplicationPreferences applicationPreferences;
	private Properties properties = new Properties();

	private ApplicationPreferences() {}

	public static synchronized ApplicationPreferences getInstance() {
		if (applicationPreferences == null)
			applicationPreferences = new ApplicationPreferences();

		return applicationPreferences;
	}

	public void loadPreferences() {
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");

			// load a properties file
			properties.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void savePreferences() {
		OutputStream output = null;

		try {
			output = new FileOutputStream("config.properties");

			// save properties to project root folder
			properties.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getPreference(Preference name) {
		return properties.getProperty(name.toString());
	}

	public void setPreference(Preference name, String value) {
		properties.setProperty(name.toString(), value);
	}
}
