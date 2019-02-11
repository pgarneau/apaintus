package apaintus.models;

import java.io.*;
import java.util.Properties;

public class ApplicationPreferences {
	private static ApplicationPreferences applicationPreferences;
	private Properties properties = new Properties();
	private File propertiesFile;
	private String propertiesFilePath;

	private ApplicationPreferences() {
		propertiesFilePath = "out/production/resources/config.properties";
		propertiesFile = new File(propertiesFilePath);
		if(!propertiesFile.exists())
		{
			System.out.println("creating config file...");
			try{
				propertiesFile.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	public static synchronized ApplicationPreferences getInstance() {
		if (applicationPreferences == null)
			applicationPreferences = new ApplicationPreferences();

		return applicationPreferences;
	}

	public void loadPreferences() {
		try {
			if(propertiesFile.exists()) {
				InputStream input = new FileInputStream(propertiesFile);
				properties.load(input);
			}
		} catch (IOException e) {
			System.err.println("file was never created!");
			e.printStackTrace();
		}
	}

	public void savePreferences() {
		try {
			OutputStream output = new FileOutputStream(propertiesFile);

			// save properties to project root folder
			properties.store(output, null);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPreference(Preference name) {
		return properties.getProperty(name.toString());
	}

	public void setPreference(Preference name, String value) {
		properties.setProperty(name.toString(), value);
	}
}
