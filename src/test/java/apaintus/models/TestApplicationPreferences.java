package apaintus.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestApplicationPreferences {
    private String fakeConfigFilePath = "src/test/resources/test.properties";

    @Test
    public void testApplicationPreferencesCreate() {
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        ApplicationPreferences test2 = ApplicationPreferences.getInstance();
        assertEquals(test2, test);
    }

    @Test
    public void testApplicationPreferencesSave() {
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        test.setPropertiesFilePath(fakeConfigFilePath);

        String redColor = "0xff0000";
        String width = "20.0";
        String height = "30.0";
        String strokeColor = "0x000000";

        test.setPreference(Preference.FILL_COLOR, redColor);
        test.setPreference(Preference.WIDTH, width);
        test.setPreference(Preference.HEIGHT, height);
        test.setPreference(Preference.STROKE_COLOR, strokeColor);

        test.savePreferences();
        test.loadPreferences();

        assertAll(() -> assertEquals(redColor, test.getPreference(Preference.FILL_COLOR)),
                () -> assertEquals(width, test.getPreference(Preference.WIDTH)),
                () -> assertEquals(height, test.getPreference(Preference.HEIGHT)),
                () -> assertEquals(strokeColor, test.getPreference(Preference.STROKE_COLOR))
        );
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
        applicationPreferencesTest.setPropertiesFilePath(fakeConfigFilePath);

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