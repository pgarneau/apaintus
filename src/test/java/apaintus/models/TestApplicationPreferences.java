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

        for(Preference pref : Preference.values()){
            System.out.println(pref.toString());
            assertNotNull(test.getPreference(pref));
        }
    }

    @Test
    public void testApplicationPreferencesSetPreferences(){
        ApplicationPreferences applicationPreferencesTest = ApplicationPreferences.getInstance();

        for(Preference pref : Preference.values()){
            applicationPreferencesTest.setPreference(pref,pref.toString());
        }

        String goodWidthValue = "2.0";

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