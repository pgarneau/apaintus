package apaintus.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ApplicationPreferences {
    private static ApplicationPreferences applicationPreferences;
    private Properties properties = new Properties();
    private File propertiesFile;
    private String propertiesFilePath;
    private static final Logger LOGGER = LogManager.getLogger(ApplicationPreferences.class);


    private ApplicationPreferences() {
        propertiesFilePath = "config.properties";
        propertiesFile = new File(propertiesFilePath);
    }

    public static synchronized ApplicationPreferences getInstance() {
        if (applicationPreferences == null)
            applicationPreferences = new ApplicationPreferences();

        return applicationPreferences;
    }

    public void loadPreferences() {
        try {
            if (!propertiesFile.createNewFile()) {
                InputStream input = new FileInputStream(propertiesFile);
                properties.load(input);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void savePreferences() {
        try {
            OutputStream output = new FileOutputStream(propertiesFile);

            // save properties to project root folder
            properties.store(output, null);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getPreference(Preference name) {
        return properties.getProperty(name.toString());
    }

    public void setPreference(Preference name, String value) {
        properties.setProperty(name.toString(), value);
    }
}
