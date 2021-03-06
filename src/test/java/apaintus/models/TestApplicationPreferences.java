package apaintus.models;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TestApplicationPreferences {
    @Test
    public void testApplicationPreferencesCreate(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        ApplicationPreferences test2 = ApplicationPreferences.getInstance();
        assertEquals("ApplicationPreferences is a singleton, both should be the same",test2,test);
    }

    @Test
    public void testApplicationPreferencesSave(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();

        for(Preference pref : Preference.values()){
            test.setPreference(pref,pref.toString());
        }
        test.savePreferences();
    }

    @Test
    public void testApplicationPreferencesLoad(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        test.loadPreferences();
    }

    @Test
    public void testApplicationPreferencesSetPreferences(){
        ApplicationPreferences applicationPreferencesTest = ApplicationPreferences.getInstance();

        for(Preference pref : Preference.values()){
            applicationPreferencesTest.setPreference(pref,pref.toString());
        }

        String wrongWidthValue = "asdkfh";
        String wrongSavePathValue = "9292";
        String wrongLoadPathValue = "9292";

        String goodWidthValue = "2.0";
        String goodSavePathValue = "";
        String goodLoadPathValue = "9292";

        applicationPreferencesTest.setPreference(Preference.LOAD_PATH,wrongLoadPathValue);
        assertEquals("Acceptd a value type other than alphabetic-only",
                goodLoadPathValue,
                applicationPreferencesTest.getPreference(Preference.LOAD_PATH));

        //Testing good values.
        applicationPreferencesTest.setPreference(Preference.WIDTH,goodWidthValue);
        assertEquals("did not set the value correctly after a first set",
                "2.0",
                applicationPreferencesTest.getPreference(Preference.WIDTH));
    }

    @Test
    public void testApplicationPreferencesGetPreference(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();

        for (Preference pref : Preference.values()) {
            test.setPreference(pref, pref.toString());
        }

        for (Preference pref : Preference.values()) {
            assertNotNull(test.getPreference(pref));
        }
    }
}