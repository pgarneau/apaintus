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
        switch (name) {
            case HEIGHT:
            case WIDTH:
            case STROKE_SIZE:
                try {
                    double d = Double.parseDouble(value);

                } catch (NumberFormatException e) {
                    return;
                }
                break;
            case SAVE_PATH:
            case LOAD_PATH:
                File file = new File(value);
                if(!(file.canRead() || file.canWrite()))
                    return;
                break;
            case STROKE_COLOR:
            case FILL_COLOR:
                if(!value.matches("0x([0-9a-fA-F]{6})"))
                    return;
                break;
        }
        properties.setProperty(name.toString(), value);
    }

    public void setPropertiesFilePath(String filePath) {
        propertiesFilePath = filePath;
        propertiesFile = new File(filePath);
    }
}
