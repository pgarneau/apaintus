package apaintus.models;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TestApplicationPreferences {
    private String fakeConfigFilePath = "test.properties";

    @Test
    public void testApplicationPreferencesCreate() {
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        ApplicationPreferences test2 = ApplicationPreferences.getInstance();
        assertEquals("ApplicationPreferences is a singleton, both should be the same", test2, test);
    }

    @Test
    public void testApplicationPreferencesSave() {
        ApplicationPreferences test = ApplicationPreferences.getInstance();

        test.savePreferences();
    }

    @Test
    public void testApplicationPreferencesLoad() {
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        test.setPropertiesFilePath(fakeConfigFilePath);

        test.loadPreferences();
        assertNotNull(test.getPreference(Preference.FILL_COLOR));

    }

    @Test
    public void testApplicationPreferencesSetPreferences() {
        ApplicationPreferences applicationPreferencesTest = ApplicationPreferences.getInstance();
        applicationPreferencesTest.setPropertiesFilePath("test.properties");

        String wrongWidthValue = "asdkfh";
        String wrongSavePathValue = "9292";
        String wrongLoadPathValue = "9292";

        String goodWidthValue = "2.0";
        String goodSavePathValue = "test.properties";
        String goodLoadPathValue = "test.properties";

        applicationPreferencesTest.setPreference(Preference.WIDTH, wrongWidthValue);
        assertNull(applicationPreferencesTest.getPreference(Preference.WIDTH));

        applicationPreferencesTest.setPreference(Preference.SAVE_PATH, wrongSavePathValue);
        assertNull(applicationPreferencesTest.getPreference(Preference.SAVE_PATH));

        applicationPreferencesTest.setPreference(Preference.LOAD_PATH, wrongLoadPathValue);
        assertNull(applicationPreferencesTest.getPreference(Preference.LOAD_PATH));

        //Testing good values.
        applicationPreferencesTest.setPreference(Preference.WIDTH, goodWidthValue);
        assertEquals("2.0", applicationPreferencesTest.getPreference(Preference.WIDTH));
    }
}